package jegmezo;


/**A Player egy típusa, speciális képessége, hogy meg tudja határozni egy szomszédos mező bíróképességét (hány játékos állhat rajta beszakadás nélkül */
public class Scientist extends Player {
	/**
	 * Automatikus létrehozáshoz konstruktor
	 * @param gameController GameController paraméter
	 * @param number azonosító
	 */
	public Scientist(GameController gameController, int number) {
		super(gameController, number);
	}

	/**
	 * Kézi létrehozáshoz konstruktor
	 * @param gameController GameController paraméter
	 * @param number azonosító
	 * @param bodyHeat testhőmérséklet
	 */
	public Scientist(GameController gameController, int number, int bodyHeat) {
		super(gameController, number, bodyHeat);
	}

	/**A kutató kiválasztja, hogy melyik Tile-t vizsgálja, majd megkapja az eredményt
	 */
	public boolean examine() {
		Tile examined = selectTile();
		if(examined == null)
			return false;
		int limit = examined.examinePlayerLimit();

		System.out.println("Player "+ number + " checked the stability of "+ examined.toShortString() + ". It can take " + limit + " players.");
		return true;
	}

	/**Kiválaszja az akciót, amit végre akar hajtani
	 *  @return bool - Sikerült-e akciót választani.  */
	public boolean selectAction() {
		System.out.println("Player " + number + " select an action (move/dig/pickup/use item/trade/examine) [" + actions + " remaining]:");
		String command = gameController.getScanner().nextLine().trim();
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
