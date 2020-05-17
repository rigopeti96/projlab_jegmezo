package jegmezo.model;

import jegmezo.controller.GameController;

import java.util.ArrayList;
import java.util.Random;

/**
 * A jegesmedve class, az Entity leszármazottja, minden kör végén átlép egy szomszédos mezőre.
 */
public class PolarBear extends Entity{
    /**
     * Jegesmedve konstruktora
     * @param gc GameController paraméter
     */
    public PolarBear(GameController gc) {
        this.gameController = gc;
    }

    /**
     * Ráteszi a Jegemedvét egy mezőre (kezdetben)
     * @param tile Mező, amire tesszük
     */
    @Override
    public void spawnOnto(Tile tile) {
        super.spawnOnto(tile);
        tile.addPolarBear(this);
    }

    /**
     * A medve átlép egy véletlenszerű szomszédos jégtáblára minden kör végén.
     * Ha a controlled randomness engedélyezve van, akkor a felhasználó lépteti és bármely mezőre
     * @return Sikerült e átlépni
     */
    public boolean move() {
        ArrayList<Tile> neighbourTiles= new ArrayList<Tile>();
        neighbourTiles.addAll(this.tile.getNeighbours());
        Random rand = new Random();

        while( true ){
            int random_mezo = rand.nextInt(neighbourTiles.size() );
            if (neighbourTiles.get(random_mezo).getPlayerLimit() ==0){
                neighbourTiles.remove(random_mezo);
                if ( neighbourTiles.isEmpty() ){
                     return false;
                }
            }else{
                neighbourTiles.get(random_mezo).stepOnPolarBear(this, tile);
                return true;
            }
        }
    }

    /**
     * A jegesmedve állapotának elmentése
     */
    public void serialize() {
        System.out.println("PolarBear(tile=" + tile.getId() + ")");
    }
}
