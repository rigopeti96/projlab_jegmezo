package jegmezo;

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
    @Override
    public boolean move() {
        if( !gameController.isControlledRandomness() ) {
        //ha nincs controlled randomness
            ArrayList<Tile> neighbourTiles= new ArrayList<Tile>();
            neighbourTiles.addAll(this.tile.getNeighbours());
            Random rand = new Random();

            while( true ){
                int random_mezo = rand.nextInt(neighbourTiles.size() );
                if (neighbourTiles.get(random_mezo).examinePlayerLimit() ==0){
                    neighbourTiles.remove(random_mezo);
                    if ( neighbourTiles.isEmpty() ){
                         System.out.println("Polar bear can't move.");
                         return false;
                    }
                }else{
                    neighbourTiles.get(random_mezo).stepOnPolarBear(this, tile);
                    return true;
                }
            }
        }else{
            //ha van controlled randomness
            System.out.println("[controlled randomness] Polar bear moves to tile (<ID>):");
            Tile hova = gameController.getTileById(gameController.getScanner().nextInt() );
            gameController.getScanner().nextLine();
            if(hova == null)
                return false;
            hova.stepOnPolarBear(this, tile);
            return true;
        }
    }

    /**
     * Kiválaszt egy szomszédos tile-t
     * @return a kiválasztott tile vagy cancel esetén NULL
     */
    public Tile selectTile() {

        System.out.println("Neighbouring tiles:");
        ArrayList<Tile> neighbourTiles= new ArrayList<Tile>();
        neighbourTiles.addAll(this.tile.getNeighbours());
        for (Tile tile: neighbourTiles){
            System.out.println(tile.toLongString() );
        }

        while (true){
            System.out.println("Select tile (<ID>/cancel):");
            String line = gameController.getScanner().nextLine().trim();

            if(line.equals("cancel")) return null;
            for (Tile tile : neighbourTiles) {
                if (String.valueOf(tile.getId()).equals(line)) {
                    return tile;
                }
            }
            System.out.println("No tile with " + line + " ID.");
        }
    }

    /**
     * A jegesmedve állapotának elmentése
     */
    public void serialize() {
        System.out.println("PolarBear(tile=" + tile.getId() + ")");
    }
}
