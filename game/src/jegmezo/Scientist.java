package jegmezo;

import java.util.Scanner;

/**A Player egy típusa, speciális képessége, hogy meg tudja határozni egy szomszédos mező bíróképességét (hány játékos állhat rajta beszakadás nélkül */
public class Scientist extends Player {
	public Scientist(GameController gameController, int number) {
		super(gameController, number);
	}

	/**A kutató kiválasztja, hogy melyik Tile-t vizsgálja, majd megkapja az eredményt
	 */
	public boolean examine() {
		System.out.println("Scientist examine");
		selectTile().getPlayerLimit();
		return true;
	}

	/**Kiválaszja az akciót
	 *  @return bool - Sikerült-e akciót választani.  */
	public boolean selectAction() {
		System.out.print("Player " + number + " please select an action (scientist actions remaining (" + actions + ")): ");
		String command = new Scanner(System.in).nextLine();
		if (command.equals("examine")) {
			return this.examine();
		} else return this.selectActionCommon(command);
	}
}
