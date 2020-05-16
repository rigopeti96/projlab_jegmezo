package jegmezo.model;


import jegmezo.controller.GameController;

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
	public boolean examine(Tile selectedTile) {
		int limit = selectedTile.examinePlayerLimit();

		gameController.getConsoleView().writeLine("Player "+ number + " checked the stability of "+ selectedTile.toShortString() + ". It can take " + limit + " players.");
		return true;
	}

	/**
	 * Kiírja az entitás egy reprezentációját a standard outputra
	 */
	@Override
	public void serialize() {
		System.out.println("Scientist(number=" + number + ",heat=" + bodyHeat + ",tile=" + tile.getId() + ")");
	}

	@Override
	public String getName(){
		return "Scientist";
	}
}
