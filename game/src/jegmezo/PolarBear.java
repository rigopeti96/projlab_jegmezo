package jegmezo;

public class PolarBear extends Entity{

    /**
     * Ráteszi a Jegemedvét egy mezőre (kezdetben)
     * @param tile Mező, amire tesszük
     */
    @Override
    public void spawnOnto(Tile tile) {
        this.tile = tile;
        tile.addPolarBear(this);
    }

    @Override
    public boolean move() {
        return false;
    }

    public Tile selectTile() {

        return tile.getNeighbours().get(0);
    }

    public void serialize() {

    }
}
