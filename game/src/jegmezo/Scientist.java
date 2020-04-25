package jegmezo;

import java.util.Scanner;

/**A Player egy típusa, speciális képessége, hogy meg tudja határozni egy szomszédos mező bíróképességét (hány játékos állhat rajta beszakadás nélkül */
public class Scientist extends Player {
	public Scientist(GameController gameController, int number) {
		super(gameController, number);
	}

	public Scientist(GameController gameController, int number, int bodyHeat) {
		super(gameController, number, bodyHeat);
	}

	/**A kutató kiválasztja, hogy melyik Tile-t vizsgálja, majd megkapja az eredményt
	 */
	public boolean examine() {
		Tile examined = selectTile();
		int limit = examined.getPlayerLimit();

		System.out.println("Player "+ number + " checked the stability of "+ examined.toShortString() + "It can take " + limit + " players.");
		return true; //TODO: Sztem tök fölösleges a boolean
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

	/**
	 * Kiírja az entitás egy reprezentációját a standard outputra
	 */
	@Override
	public void serialize() {
		System.out.println("Scientist(number=" + number + ",heat=" + bodyHeat + ",tile=" + tile.getId() + ")");
	}
}
