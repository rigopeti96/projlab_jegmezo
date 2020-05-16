package jegmezo.model;

/**
 * A játékban szereplő entitások (playerek, jegesmedve) ősosztálya
 */
public abstract class Entity {
    /**
     * Az entitás aktuális tile-ja
     */
    protected Tile tile;
    /**
     * a játékos gamecontrollere
     */
    protected GameController gameController;

    /**
     * Ráteszi az entitást egy mezőre (kezdetben)
     * @param tile Mező, amire tesszük
     */
    public void spawnOnto(Tile tile) {
        this.tile = tile;
    }

    /**
     * Az entitás átlép egy szomszédos mezőre
     * @return Sikerült-e a lépés
     */
    public abstract boolean move();

    /**
     * Kiválaszt egy szomszédos tile-t
     * @return a kiválasztott tile vagy cancel esetén NULL
     */
    public abstract Tile selectTile();

    /**
     * Beállitja a tile-t, amin az entity áll.
     * @param tile
     */
    public void movedToTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * Kiírja az entitás egy reprezentációját a standard outputra
     */
    public abstract void serialize();
}
