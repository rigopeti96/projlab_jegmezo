package jegmezo;

import java.util.Scanner;

public abstract class Entity {

    protected Tile tile;

    public abstract boolean move();

    public abstract Tile selectTile();

    public abstract boolean canSave();

    public abstract void decreaseBodyHeat();

    public void serialize() {

    }
}
