package jegmezo;

public abstract class Entity {

    protected Tile tile;
    protected GameController gameController;

    /**
     * Ráteszi az entitást egy mezőre (kezdetben)
     * @param tile Mező, amire tesszük
     */
    public void spawnOnto(Tile tile) {
        this.tile = tile;
    }

    public abstract boolean move();

    public abstract Tile selectTile();

    public void movedToTile(Tile tile) {
        this.tile = tile;
    }

    /**
     * Kiírja az entitás egy reprezentációját a standard outputra
     */
    public abstract void serialize();
}
