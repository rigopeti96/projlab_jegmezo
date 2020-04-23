package jegmezo;

import java.util.Scanner;

/**A Player egy típusa, speciális képessége, hogy tud iglut építeni az aktuális mezőre (ha még nincs rajta iglu) */
public class Eskimo extends Player {
	public Eskimo(GameController gameController, int number) {
		super(gameController, number);
	}

	/** Meghívja az Tile buildIgloo függvényét
	 *  @return bool - Sikerült-e építeni.  */
	public boolean buildIgloo() {
		System.out.println("Eskimo buildIgloo");
		return tile.build(Building.IGLOO);
	}

	/** Kiválaszja az akciót
	 *  @return bool - Sikerült-e akciót választani.  */
	public boolean selectAction() {
		System.out.print("Player " + number + " please select an action (eskimo, actions remaining (" + actions + ")): ");
		String command = new Scanner(System.in).nextLine();
		if (command.equals("build igloo")) {
			return this.buildIgloo();
		} else return this.selectActionCommon(command);
	}

}
