package jegmezo;

public class PolarBear extends Entity{


    @Override
    public boolean move() {
        return false;
    }

    public Tile selectTile() {

        return tile.getNeighbours().get(0);
    }

    public void serialize() {

    }

    @Override
    public boolean canSave() {
        return false;
    }

    @Override
    public void decreaseBodyHeat() {

    }
}
