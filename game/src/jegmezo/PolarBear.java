package jegmezo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PolarBear extends Entity{

    public PolarBear(GameController gc) {
        this.gameController = gc;
    }

    /**
     * Ráteszi a Jegemedvét egy mezőre (kezdetben)
     * @param tile Mező, amire tesszük
     */
    @Override
    public void spawnOnto(Tile tile) {
        this.tile = tile;
        tile.addPolarBear(this);
    }

    /**
     * A medve átlép egy szpmszédos jégtáblára.
     * @return Sikerült e átlépni
     */
    @Override
    public boolean move() {
        if( !gameController.isControlledRandomness() ) {
        //ha nincs conttolled randomness
            ArrayList<Tile> neighbourTiles= new ArrayList<Tile>();
            neighbourTiles.addAll(this.tile.getNeighbours());
            Random rand = new Random();

            while( true ){
                int random_mezo = rand.nextInt(neighbourTiles.size() );
                if (neighbourTiles.get(random_mezo).examinePlayerLimit() ==0){
                    neighbourTiles.remove(random_mezo);
                    if ( neighbourTiles.isEmpty() ){
                         System.out.println("Polarbear can't move");
                         return false;
                    }
                }else{
                    neighbourTiles.get(random_mezo).stepOnPolarBear(this, tile);
                    return true;
                }
            }
        }else{
            //ha van controlled randomness
            System.out.println("Move Polarbear to new tile");
            Tile hova=selectTile();
            if(hova == null)
                return false;

            hova.stepOnPolarBear(this, tile);
            return true;
        }
    }

    public Tile selectTile() {

        System.out.println("Neighbouring tiles: ");
        ArrayList<Tile> neighbourTiles= new ArrayList<Tile>();
        neighbourTiles.addAll(this.tile.getNeighbours());
        for (Tile tile: neighbourTiles){
            System.out.println(tile.toLongString() );
        }

        while (true){
            System.out.println("Select tile (<ID>/cancel)");
            String line = new Scanner(System.in).nextLine().trim();

            if(line.equals("cancel")) return null;
            for (Tile tile : neighbourTiles) {
                if (String.valueOf(tile.getId()).equals(line)) {
                    return tile;
                }
            }
            System.out.println("No tile with " + line + " ID.");
        }
    }

    public void serialize() {
        System.out.println("PolarBear(tile=" + tile.getId() + ")");
    }
}
