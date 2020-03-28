package jegmezo;




/** Játékos, lehet eszkimó és kutató. Birtokolhat tárgyat, használhatja azt a tárgyat és átadhatja másik játékosnak. Át tud lépni szomszédos mez?re. */
public abstract class Player {
	private int bodyHeat;

	private int actions;

	private Tile tile;

	private Inventory inventory;

	private GameController gameController;

	public Player(GameController gameController) {
		this.gameController = gameController;
	}

	/** A játékos Inventory-ja */
	public Inventory getInventory(){
		System.out.println("\nPlayer getInventory\n");
		return inventory;
	}

	/** A játékos körét kezeli le */
	public void takeTurn() {
		System.out.println("\nPlayer takeTurn\n");
	}
	
	/** A játékos egyik nála lév? tárgyat átadhatja egyik játékos társának
	 * @return true ha sikeres, false ha nem */
	public boolean trade() {
		System.out.println("\nPlayer trade\n");
		return false;
	}
	
	/** A játékos felvesz egy tárgyat
	 * @return true, ha a játékos eltárolta az adott tárgyat, false ha nem vette fel */
	public boolean takeItem(Item item) {
		System.out.println("\nPlayer takeItem\n");
		return false;
	}
	
	/** A játékos használja a gy?zelmi tárgyat
	 * @return ha minden alkatrész megvan és az összes játékos ugyanazon a mez?n van, akkor megnyerik a játékot és true-t ad vissza, amúgy false*/
	public boolean useWinItems() {
		System.out.println("\nPlayer useWinItems\n");
		if(tile.hasAllPlayers() && inventory.hasAllWinItem()){
			gameController.win();
			System.out.println("\nPlayers Win\n");
			return true;
		}
		System.out.println("\nPlayers don't win\n");
		return false;
	}
	
	/** A játékos mozog (tile-t választ és odamozog)
	 * @return true has sikeres, false ha nem */
	public boolean move() {
		System.out.println("\nPlayer move\n");
		return false;
	}
	
	/** Megnöveli a játékos testh?jét 1-gyel */
	public void increaseBodyHeat() {
		System.out.println("\nPlayer increaseBodyHeat\n");
		bodyHeat++;
	}
	
	/** Lecsökkenti a játékos testh?jét 1-gyel */
	public void decreaseBodyHeat() {
		System.out.println("\nPlayer decreaseBodyHeat\n");
		bodyHeat--;
	}
	
	/** A játékos megfullad */
	public void drown() {
		System.out.println("\nPlayer drown\n");
		gameController.lose();
	}
	
	/** A játékos elhasznál egy akciópontot */
	public void loseAP() {
		System.out.println("\nPlayer loseAP\n");
		actions--;
	}
	
	/** A Player kézzel ás és 1 egység havat takarít el a mez?jér?l,
	 * @return true ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithHands() {
		System.out.println("\nPlayer digWithHands\n");
		return false;
	}

	/** A Player ásóval ás és 2 egység havat takarít el a mez?jér?l,
	 * @return True ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithShovel() {
		System.out.println("\nPlayer digWithShovel\n");
		return false;
	}
	
	/** A játékos felveszi a tárgyat a mez?r?l, amin áll
	 * @return true ha feltudta venni, false ha nem*/
	public boolean pickup() {
		System.out.println("\nPlayer pickup\n");
		return false;
	}

	/** A játékos kiválaszthat egy mez?t, amire lépni fog, vagy megnézi sarkkutatóval (menüt dob fel)
	 * @return kiválasztott tile (tile.getNeighbours eleme) */
	public Tile selectTile() {
		System.out.println("\nPlayer selectTile\n");
		return null;
	}
	
	/** A játékos kiválaszt egy tárgyat, amelyet használ (menüt dob fel)
	 * @return true ha sikeres, false nem */
	public boolean useItem() {
		System.out.println("\nPlayer useItem\n");
		return false;
	}
	
	/** A játékos választ egy akciót, true-val tér vissza
	 * @return true ha az akció sikeres, false ha nem vagy a játékos nem választott akciót */
	public abstract boolean selectAction();
}
