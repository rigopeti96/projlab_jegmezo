package jegmezo;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class LevelGenerator {
    private LevelTile[][] tiles;
    private int rx;
    private int ry;
    private static final double CHANCE = 0.3;
    private static final double MIN_FRAGMENT = 0.3;
    private Random random;

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
            if (x > -rx) ret.add(tiles[x - 1][y]);
            if (x < rx) ret.add(tiles[x + 1][y]);
            if (y > -ry) ret.add(tiles[x][y - 1]);
            if (y < ry) ret.add(tiles[x][y + 1]);
            if (x % 2 == 0 && x > -rx && y < ry) ret.add(tiles[x - 1][y + 1]);
            if (x % 2 == 0 && x < rx && y < ry) ret.add(tiles[x + 1][y + 1]);
            if (Math.abs(x % 2) == 1 && x > -rx && y > -ry) ret.add(tiles[x - 1][y - 1]);
            if (Math.abs(x % 2) == 1 && x < rx && y > -ry) ret.add(tiles[x + 1][y - 1]);
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

    public LevelGenerator(int playerCount) {
        random = new Random();
        rx = playerCount * 5;
        ry = playerCount * 3;
        tiles = new LevelTile[rx * 2 + 1][ry * 2 + 1];
    }

    public void generate() {
        generateLevelTiles();
        
        // TODO: Convert to game tiles
        // TODO: Win item spawn
        // TODO: Item spawns
        // TODO: Spawn polar bear
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
                    else tile.select();
                    tile.openNeighbours(openableList);
                }
            }

            if (generated < min) {
                LevelTile newPivot = null;
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
                if (tiles[rx + x][ry + y].isSelected()) continue;
                for (LevelTile tile: tiles[rx + x][ry + y].getNeighbours()) {
                    tile.hole();
                }
            }
        }
    }
}
