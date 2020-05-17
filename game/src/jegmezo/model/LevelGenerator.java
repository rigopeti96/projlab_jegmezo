package jegmezo.model;

import jegmezo.controller.GameController;

import java.util.*;

/**
 * Pálya generálásért felelős (hexagonal)
 */
public class LevelGenerator {
    /**
     * Játék kontroller, DI miatt kell
     */
    private GameController gameController;

    /**
     * Hexagonal grid mezői (nem játék Tile, hanem LevelTile, mivel ebben van geometriai információ)
     */
    protected LevelTile[][] tiles;
    /**
     * A játék által használt mezők
     */
    private Map<LevelTile, Tile> gameTiles = new HashMap<>();

    /**
     * Játék mező - LevelTile mapping
     */
    private Map<Tile, LevelTile> levelTiles = new HashMap<>();
    /**
     * Legenerált IceSheet-ek, amiken még nincs item (ezekre lehet rakni)
     */
    private List<IceSheet> iceSheets = new ArrayList<>();
    /**
     * A hexagonal grid x tengelyű sugara
     */
    protected int rx;
    /**
     * A hexagonal grid y tengelyű sugara
     */
    protected int ry;
    /**
     * Játékosok száma
     */
    private int playerCount;
    /**
     * Az algoritmus által használt random threshold
     */
    protected static final double CHANCE = 0.3;
    /**
     * Minimum része a pályának, ami IceSheet kell, hogy legyen (0-1-ig)
     */
    protected static final double MIN_FRACTION = 0.3;
    /**
     * A level generáló által használt random
     */
    protected Random random;
    /**
     * A generált jegesmedge
     */
    private PolarBear polarBear;

    /**
     * @param gameController Játék kontroller, DI miatt kell
     * @param playerCount Játékosok száma
     */
    public LevelGenerator(GameController gameController, int playerCount) {
        this.gameController = gameController;
        this.playerCount = playerCount;
        random = new Random();
        rx = playerCount * 3;
        ry = playerCount * 2;
        tiles = new LevelTile[rx * 2 + 1][ry * 2 + 1];
    }

    /**
     * A játék Tile-ok legenerálása
     * @return A játék tile-ok listája
     */
    public List<Tile> generate() {
        generateLevelTiles();
        generateGameTiles();
        generateGameTileConnections();
        placePolarBear();
        generateItems();

        return new ArrayList<>(gameTiles.values());
    }

    /**
     * @return A generált jegesmedge
     */
    public PolarBear getPolarBear() {
        return polarBear;
    }

    public Map<Tile, LevelTile> getLevelTiles() {
        return levelTiles;
    }

    /**
     * A hexagonal tile-ok legenerálása (nem game tile, hanem a geometriával rendelkező reprezentáció)
     */
    private void generateLevelTiles() {
        for (int x = -rx; x <= rx; x++) {
            for (int y = -ry; y <= ry; y++) {
                tiles[rx + x][ry + y] = new LevelTile(this, x, y);
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
                        tiles[rx + x][ry + y].reset();
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

    /**
     * A játék tileok legenerálása a level tileokból
     */
    private void generateGameTiles() {
        int counter = 1;
        for (int x = -rx; x <= rx; x++) {
            for (int y = -ry; y <= ry; y++) {
                if (tiles[rx + x][ry + y].isSelected()) {
                    IceSheet sheet = new IceSheet(gameController, (x == 0 && y == 0) ? 0 : counter++, x == 0 && y == 0 ? playerCount :  random.nextInt(playerCount) + 1, getSnowAmmount());
                    gameTiles.put(tiles[rx + x][ry + y], sheet);
                    levelTiles.put(sheet, tiles[rx + x][ry + y]);
                    iceSheets.add(sheet);
                }
                else if (tiles[rx + x][ry + y].isHole()) {
                    Hole hole = new Hole(gameController, counter++, getSnowAmmount());
                    gameTiles.put(tiles[rx + x][ry + y], hole);
                    levelTiles.put(hole, tiles[rx + x][ry + y]);
                }
            }
        }
    }

    /**
     * A jegesmedve elhelyezése
     */
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
            polarBear = new PolarBear(gameController);
            polarBear.spawnOnto(polarBearSpawns.get(random.nextInt(polarBearSpawns.size())));
        }
    }

    /**
     * Random hószámhoz használt függvény
     * @return 1-3 közötti random szám
     */
    private int getSnowAmmount() {
        return random.nextInt(2) + 1;
    }

    /**
     * A játék tile kapcsolatok legenerálása a level tile geometriából
     */
    private void generateGameTileConnections() {
        for (LevelTile tile: gameTiles.keySet()) {
            Tile gameTile = gameTiles.get(tile);
            for (LevelTile neighbour: tile.getNeighbours()) {
                Tile otherTile = gameTiles.get(neighbour);
                if (otherTile != null) gameTile.connectTile(otherTile);
            }
        }
    }

    /**
     * Tárgyak legenerálása
     */
    private void generateItems() {
        if (iceSheets.size() == 0) return;
        IceSheet picked = iceSheets.get(random.nextInt(iceSheets.size()));
        picked.setItem(new WinItem(gameController, "Cartridge"));
        iceSheets.remove(picked);

        if (iceSheets.size() == 0) return;
        picked = iceSheets.get(random.nextInt(iceSheets.size()));
        picked.setItem(new WinItem(gameController, "Flare"));
        iceSheets.remove(picked);

        if (iceSheets.size() == 0) return;
        picked = iceSheets.get(random.nextInt(iceSheets.size()));
        picked.setItem(new WinItem(gameController, "Flare gun"));
        iceSheets.remove(picked);

        if ((int)(iceSheets.size() / 4.0) == 0) return;
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
            if (iceSheets.size() == 0) return;
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Shovel(gameController));
            iceSheets.remove(picked);
        }

        for (int i = 0; i < breakableShovel; i++) {
            if (iceSheets.size() == 0) return;
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new BreakableShovel(gameController));
            iceSheets.remove(picked);
        }

        for (int i = 0; i < rope; i++) {
            if (iceSheets.size() == 0) return;
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Rope(gameController));
            iceSheets.remove(picked);
        }

        for (int i = 0; i < scubaGear; i++) {
            if (iceSheets.size() == 0) return;
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new ScubaGear(gameController));
            iceSheets.remove(picked);
        }

        for (int i = 0; i < food; i++) {
            if (iceSheets.size() == 0) return;
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Food(gameController));
            iceSheets.remove(picked);
        }

        for (int i = 0; i < tent; i++) {
            if (iceSheets.size() == 0) return;
            picked = iceSheets.get(random.nextInt(iceSheets.size()));
            picked.setItem(new Tent(gameController));
            iceSheets.remove(picked);
        }
    }
}
