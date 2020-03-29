package jegmezo;

/**A Player egy típusa, speciális képessége, hogy tud iglut építeni az aktuális mezőre (ha még nincs rajta iglu) */
public class Eskimo extends Player {
	public Eskimo(GameController gameController, Tile tile) {
		super(gameController, tile);
	}

	/**Meghívja az Tile buildIgloo függvényét
	 *  @param
	 *  @return bool - Sikerült-e építeni.  */
	public boolean buildIgloo() {
	    Tile tile = selectTile();
	    tile.buildIgloo();
		System.out.println("Igloo is built");
		return true;
	}

	/**Kiválaszja az akciót
	 *  @param
	 *  @return bool - Sikerült-e akciót választani.  */
	public boolean selectAction() {
		System.out.println("Action selected");
		return false;
	}
}
