package jegmezo;

import java.util.*;

public class LevelGenerator {
    private GameController gameController;
    private LevelTile[][] tiles;
    private Map<LevelTile, Tile> gameTiles = new HashMap<>();
    private List<IceSheet> iceSheets = new ArrayList<>();
    private int rx;
    private int ry;
    private int playerCount;
    private static final double CHANCE = 0.3;
    private static final double MIN_FRAGMENT = 0.3;
    private Random random;
    private PolarBear polarBear;

    private class LevelTile {
        private int x;
        private int y;
        private boolean selected = false;
        private boolean hole = false;
        private boolean skipped = false;

        public LevelTile(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isSelected() {
            return selected;
        }

        public boolean isHole() {
            return hole;
        }

        public void select() {
            selected = true;
        }

        public void reset() {
            skipped = false;
        }

        public void skip() {
            skipped = true;
        }

        public void hole() {
            if (selected) return;
            hole = true;
        }

        public List<LevelTile> getNeighbours() {
            List<LevelTile> ret = new ArrayList<>();
            if (x > -rx) ret.add(tiles[x + rx - 1][y + ry]);
            if (x < rx) ret.add(tiles[x + rx + 1][y + ry]);
            if (y > -ry) ret.add(tiles[x + rx][y + ry - 1]);
            if (y < ry) ret.add(tiles[x + rx][y + ry + 1]);
            if (x % 2 == 0 && x > -rx && y < ry) ret.add(tiles[x + rx - 1][y + ry + 1]);
            if (x % 2 == 0 && x < rx && y < ry) ret.add(tiles[x + rx + 1][y + ry + 1]);
            if (Math.abs(x % 2) == 1 && x > -rx && y > -ry) ret.add(tiles[x + rx - 1][y + ry - 1]);
            if (Math.abs(x % 2) == 1 && x < rx && y > -ry) ret.add(tiles[x + rx + 1][y + ry - 1]);
            return ret;
        }

        public boolean isOpenable() {
            if (getNeighbours().size() < 6) return false;
            return !skipped && !selected;
        }

        public boolean hasOpenable() {
            for (LevelTile tile: getNeighbours()) {
                if (tile.isOpenable()) return true;
            }

            return false;
        }

        public boolean hasSelected() {
            for (LevelTile tile: getNeighbours()) {
                if (tile.selected) return true;
            }

            return false;
        }

        public void open(List<LevelTile> openableList) {
            if (this.isOpenable() && random.nextDouble() < CHANCE) {
                openableList.add(this);
            } else {
                this.skipped = true;
            }
        }

        public void openNeighbours(List<LevelTile> openableList) {
            for (LevelTile tile: this.getNeighbours()) {
                tile.open(openableList);
            }
        }
    }

    public LevelGenerator(GameController gameController, int playerCount) {
        this.gameController = gameController;
        this.playerCount = playerCount;
        random = new Random(0);
        rx = /*playerCount **/ 3;
        ry = /*playerCount */ 2;
        tiles = new LevelTile[rx * 2 + 1][ry * 2 + 1];
    }

    public List<Tile> generate() {
        generateLevelTiles();
        generateGameTiles();
        generateGameTileConnections();
        placePolarBear();
        generateItems();

        return new ArrayList<>(gameTiles.values());
    }

    public PolarBear getPolarBear() {
        return polarBear;
    }

    private void generateLevelTiles() {
        for (int x = -rx; x <= rx; x++) {
            for (int y = -ry; y <= ry; y++) {
                tiles[rx + x][ry + y] = new LevelTile(x, y);
            }
        }

        int generated = 0;
        int min = (int)((rx * 2 + 1) * (ry * 2 + 1) * CHANCE);
        List<LevelTile> openableList = new ArrayList<>();
        openableList.add(tiles[rx][ry]);

        do {
            while (openableList.size() > 0) {
                List<LevelTile> iterated = new ArrayList<>();
                iterated.addAll(openableList);
                openableList.clear();
                for (LevelTile tile: iterated) {
                    if (!tile.isSelected()) generated++;
                    tile.select();
                    tile.openNeighbours(openableList);
                }
            }

            if (generated < min) {
                for (int x = -rx; x <= rx; x++) {
                    for (int y = -ry; y <= ry; y++) {
                        tiles[rx + x][ry + y].skipped = false;
                    }
                }

                List<LevelTile> newPivots = new ArrayList<>();
                for (int x = -rx; x <= rx; x++) {
                    for (int y = -ry; y <= ry; y++) {
                        if (tiles[rx + x][ry + y].getNeighbours().size() == 6 && tiles[rx + x][ry + y].hasOpenable() && tiles[rx + x][ry + y].hasSelected()) {
                            newPivots.add(tiles[rx + x][ry + y]);
                        }
                    }
                }

                if (newPivots.size() > 0) openableList.add(newPivots.get(random.nextInt(newPivots.size())));
                else break; // Once in a lifetime
            }
        } while (generated < min);

        for (int x = -rx; x <= rx; x++) {
            for (int y = -ry; y <= ry; y++) {
                if (!tiles[rx + x][ry + y].isSelected()) continue;
                for (LevelTile tile: tiles[rx + x][ry + y].getNeighbours()) {
                    tile.hole();
                }
            }
        }
    }

    private void generateGameTiles() {
        int counter = 0;
        for (int x = -rx; x <= rx; x++) {
            for (int y = -ry; y <= ry; y++) {
                if (tiles[rx + x][ry + y].isSelected()) {
                    IceSheet sheet = new IceSheet(gameController, counter++, random.nextInt(playerCount) + 1, getSnowAmmount());
                    gameTiles.put(tiles[rx + x][ry + y], sheet);
                    iceSheets.add(sheet);
                }
                else if (tiles[rx + x][ry + y].isHole()) {
                    gameTiles.put(tiles[rx + x][ry + y], new Hole(gameController, counter++, getSnowAmmount()));
                }
            }
        }
    }

    private void placePolarBear() {
        List<Tile> polarBearSpawns = new ArrayList<>();

        for (int distance = rx; distance > 0 && polarBearSpawns.size() == 0; distance--) {
            for (int x = -rx; x <= rx; x++) {
                for (int y = -ry; y <= ry; y++) {
                    if (x + y >= distance && tiles[x + rx][y + ry].isSelected()) {
                        polarBearSpawns.add(gameTiles.get(tiles[rx + x][ry + y]));
                    }
                }
            }
        }

        if (polarBearSpawns.size() > 0) {
            polarBear = new PolarBear();
            polarBear.spawnOnto(polarBearSpawns.get(random.nextInt(polarBearSpawns.size())));
        }
    }

    private int getSnowAmmount() {
        return random.nextInt(2) + 1;
    }

    private void generateGameTileConnections() {
        for (LevelTile tile: gameTiles.keySet()) {
            Tile gameTile = gameTiles.get(tile);
            for (LevelTile neighbour: tile.getNeighbours()) {
                Tile otherTile = gameTiles.get(neighbour);
                if (otherTile != null) gameTile.connectTile(otherTile);
            }
        }
    }

    private void generateItems() {
        IceSheet picked = iceSheets.get(random.nextInt(iceSheets.size()));
        picked.setItem(new WinItem("cartridge"));
        iceSheets.remove(picked);

        picked = iceSheets.get(random.nextInt(iceSheets.size()));
        picked.setItem(new WinItem("flare"));
        iceSheets.remove(picked);

        picked = iceSheets.get(random.nextInt(iceSheets.size()));
        picked.setItem(new WinItem("flare gun"));
        iceSheets.remove(picked);

        int count = random.nextInt((int)(iceSheets.size() / 4.0)) + (int)(iceSheets.size() / 2.0);

        int shovel = 0; // 2/16
        int breakableShovel = 0; // 3/16
        int rope = 0; // 1/16
        int scubaGear = 0; // 1/16
        int food = 0; // 6/16
        int tent = 0; // 3/16

        for (int i = 0; i < count; i++) {
            if (random.nextDouble() < 6.0 / 16.0) food++;
            else if (random.nextDouble() < 9.0 / 16.0) breakableShovel++;
            else if (random.nextDouble() < 12.0 / 16.0) tent++;
            else if (random.nextDouble() < 14.0 / 16.0) shovel++;
            else if (random.nextDouble() < 15.0 / 16.0) rope++;
            else scubaGear++;
        }

        for (int i = 0; i < shovel; i++) {
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Shovel());
            iceSheets.remove(picked);
        }

        for (int i = 0; i < breakableShovel; i++) {
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Shovel());
            iceSheets.remove(picked);
        }

        for (int i = 0; i < rope; i++) {
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Shovel());
            iceSheets.remove(picked);
        }

        for (int i = 0; i < scubaGear; i++) {
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Shovel());
            iceSheets.remove(picked);
        }

        for (int i = 0; i < food; i++) {
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Food());
            iceSheets.remove(picked);
        }

        for (int i = 0; i < tent; i++) {
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Tent());
            iceSheets.remove(picked);
        }
    }
}
