package jegmezo;

import java.util.Scanner;

/**A Player egy típusa, speciális képessége, hogy tud iglut építeni az aktuális mezőre (ha még nincs rajta iglu) */
public class Eskimo extends Player {
	public Eskimo(GameController gameController, Tile tile) {
		super(gameController, tile);
	}

	/** Meghívja az Tile buildIgloo függvényét
	 *  @return bool - Sikerült-e építeni.  */
	public boolean buildIgloo() {
		System.out.println("Eskimo buildIgloo");
		return tile.buildIgloo();
	}

	/** Kiválaszja az akciót
	 *  @return bool - Sikerült-e akciót választani.  */
	public boolean selectAction() {
		System.out.println("Eskimo selectAction");
		return false;
	}

}
