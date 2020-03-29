package jegmezo;

/**A Player egy típusa, speciális képessége, hogy meg tudja határozni egy szomszédos mező bíróképességét (hány játékos állhat rajta beszakadás nélkül */
public class Scientist extends Player {
	public Scientist(GameController gameController, Tile tile) {
		super(gameController, tile);
	}

	/**A kutató kiválasztja, hogy melyik Tile-t vizsgálja, majd megkapja az eredményt
	 */
	public void examine() {
		System.out.println("Scientist examine");
		selectTile().getPlayerLimit();
	}

	/**Kiválaszja az akciót
	 *  @return bool - Sikerült-e akciót választani.  */
	public boolean selectAction() {
		System.out.println("Scientist selectAction");
		return true;
	}
}
