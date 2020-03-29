package jegmezo;

import java.util.Scanner;

/**A Player egy típusa, speciális képessége, hogy tud iglut építeni az aktuális mezőre (ha még nincs rajta iglu) */
public class Eskimo extends Player {
	public Eskimo(GameController gameController, Tile tile) {
		super(gameController, tile);
	}

	/**Meghívja az Tile buildIgloo függvényét
	 *  @param
	 *  @return bool - Sikerült-e építeni.  */
	public boolean buildIgloo() {
		System.out.println("Build Igloo");
		switch (new Scanner(System.in).nextLine()) {
			case "can":
				return true;
			case "can't":
				return false;
		}
		return false;
	}

	/**Kiválaszja az akciót
	 *  @param
	 *  @return bool - Sikerült-e akciót választani.  */
	public boolean selectAction() {
		System.out.println("Action selected");
		return false;
	}
}
