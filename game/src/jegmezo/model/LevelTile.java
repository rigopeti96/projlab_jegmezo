package jegmezo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Egy hexagonal tile-nak felel meg
 */
public class LevelTile {
    /**
     * a levelgenerátor
     */
    private final LevelGenerator levelGenerator;

    /**
     * visszaadja a leveltile x koordinátáját
     * @return - int, az x koordináta
     */
    public int getX() {
        return x;
    }

    /**
     * visszaadja a leveltile y koordinátáját
     * @return - int, az y koordináta
     */
    public int getY() {
        return y;
    }

    /**
     * X koordináta a hexagonal gridben
     */
    protected int x;
    /**
     * Y koordináta a hexagonal gridben
     */
    protected int y;
    /**
     * Ki van e választva sheet-nek
     */
    protected boolean selected = false;
    /**
     *  Ki van e választva hole-nak
     */
    protected boolean hole = false;
    /**
     * Nem újra kiválaszható (ha true)
     */
    protected boolean skipped = false;

    /**
     * @param x X koordináta hexagonal gridben
     * @param y Y koordináta hexagonal gridben
     */
    public LevelTile(LevelGenerator levelGenerator, int x, int y) {
        this.levelGenerator = levelGenerator;
        this.x = x;
        this.y = y;
    }

    /**
     * @return Ki van e választva sheet-nek
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @return Ki van e választva hole-nak
     */
    public boolean isHole() {
        return hole;
    }

    /**
     * Kiválasztja sheet-nek
     */
    public void select() {
        selected = true;
    }

    /**
     * Visszareseteli a tile-t, hogy újra kiválasztható legyen
     */
    public void reset() {
        skipped = false;
    }

    /**
     * Skippeli a tile-t, hogy ne legyen újra kiválaszható
     */
    public void skip() {
        skipped = true;
    }

    /**
     * Lyuknak jelöli a tile-t
     */
    public void hole() {
        if (selected) return;
        hole = true;
    }

    /**
     * @return A szomszéd tile-ok listája
     */
    public List<LevelTile> getNeighbours() {
        List<LevelTile> ret = new ArrayList<>();
        if (x > -levelGenerator.rx) ret.add(levelGenerator.tiles[x + levelGenerator.rx - 1][y + levelGenerator.ry]);
        if (x < levelGenerator.rx) ret.add(levelGenerator.tiles[x + levelGenerator.rx + 1][y + levelGenerator.ry]);
        if (y > -levelGenerator.ry) ret.add(levelGenerator.tiles[x + levelGenerator.rx][y + levelGenerator.ry - 1]);
        if (y < levelGenerator.ry) ret.add(levelGenerator.tiles[x + levelGenerator.rx][y + levelGenerator.ry + 1]);
        if (x % 2 == 0 && x > -levelGenerator.rx && y < levelGenerator.ry) ret.add(levelGenerator.tiles[x + levelGenerator.rx - 1][y + levelGenerator.ry + 1]);
        if (x % 2 == 0 && x < levelGenerator.rx && y < levelGenerator.ry) ret.add(levelGenerator.tiles[x + levelGenerator.rx + 1][y + levelGenerator.ry + 1]);
        if (Math.abs(x % 2) == 1 && x > -levelGenerator.rx && y > -levelGenerator.ry) ret.add(levelGenerator.tiles[x + levelGenerator.rx - 1][y + levelGenerator.ry - 1]);
        if (Math.abs(x % 2) == 1 && x < levelGenerator.rx && y > -levelGenerator.ry) ret.add(levelGenerator.tiles[x + levelGenerator.rx + 1][y + levelGenerator.ry - 1]);
        return ret;
    }

    /**
     * @return  Ki lehet e választani a tile-t
     */
    public boolean isOpenable() {
        if (getNeighbours().size() < 6) return false;
        return !skipped && !selected;
    }

    /**
     * @return Van e kiválasztható szomszédja
     */
    public boolean hasOpenable() {
        for (LevelTile tile: getNeighbours()) {
            if (tile.isOpenable()) return true;
        }

        return false;
    }

    /**
     * @return Van e kiválasztott szomszédja
     */
    public boolean hasSelected() {
        for (LevelTile tile: getNeighbours()) {
            if (tile.selected) return true;
        }

        return false;
    }

    /**
     * Kiválasztja a tile-t és hozzáadja egy listához
     * @param openableList Kiválasztottak, de még nem feldolgozottak listája
     */
    public void open(List<LevelTile> openableList) {
        if (this.isOpenable() && levelGenerator.random.nextDouble() < LevelGenerator.CHANCE) {
            openableList.add(this);
        } else {
            skip();
        }
    }

    /**
     * Kiválasztja a szomszédokat, akiket ki lehet és hozzáadja egy listához
     * @param openableList Kiválasztottak, de még nem feldolgozottak listája
     */
    public void openNeighbours(List<LevelTile> openableList) {
        for (LevelTile tile: this.getNeighbours()) {
            tile.open(openableList);
        }
    }
}
