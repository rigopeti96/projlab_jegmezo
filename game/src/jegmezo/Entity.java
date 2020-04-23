package jegmezo;

import java.util.Scanner;

public abstract class Entity {

    protected Tile tile;

    /**
     * Ráteszi az entitást egy mezőre (kezdetben)
     * @param tile Mező, amire tesszük
     */
    public abstract void spawnOnto(Tile tile);

    public abstract boolean move();

    public abstract Tile selectTile();

    /**
     * Kiírja az entitás egy reprezentációját a standard outputra
     */
    public abstract void serialize();
}
