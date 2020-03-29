package jegmezo;

/**A Player egy típusa, speciális képessége, hogy meg tudja határozni egy szomszédos mező bíróképességét (hány játékos állhat rajta beszakadás nélkül */
public class Scientist extends Player {
	public Scientist(GameController gameController, Tile tile) {
		super(gameController, tile);
	}

	/**A kutató kiválasztja, hogy melyik Tile-t vizsgálja, majd megkapja az eredményt
	 * @param
	 */
	public void examine() {
        Tile tile = selectTile();
        tile.getPlayerLimit();
	}

	/**Kiválaszja az akciót
	 *  @param
	 *  @return bool - Sikerült-e akciót választani.  */
	public boolean selectAction() {
		System.out.println("Action selected");
		return false;
	}
}
