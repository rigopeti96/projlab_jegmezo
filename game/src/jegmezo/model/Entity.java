package jegmezo.model;

import jegmezo.controller.GameController;

/**
 * A játékban szereplő entitások (playerek, jegesmedve) ősosztálya
 */
public abstract class Entity {
    /**
     * Az entitás aktuális tile-ja
     */
    protected Tile tile;
    /**
     * A game controller
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
     * Beállitja a tile-t, amin az entity áll.
     * @param tile
     */
    public void movedToTile(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    /**
     * Kiírja az entitás egy reprezentációját a standard outputra
     */
    public abstract void serialize();
}
