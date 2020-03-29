package jegmezo;




/** Játékos, lehet eszkimó és kutató. Birtokolhat tárgyat, használhatja azt a tárgyat és átadhatja másik játékosnak. át tud lépni szomszédos mezőre. */
public abstract class Player {
	private int bodyHeat;

	private int actions;

	private Tile tile;

	private Inventory inventory;

	private GameController gameController;

	public Player(GameController gameController) {
		this.gameController = gameController;
	}

	public Inventory getInventory(){
		System.out.println("\nPlayer getInventory\n");
		return inventory;
	}

	/** A játékos körét kezeli le */
	public void takeTurn() {
		System.out.println("\nPlayer takeTurn\n");
	}
	
	/** A játékos egyik nála lévő tárgyat átadhatja egyik játékos társának
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
	
	/** A játékos használja a győzelmi tárgyat
	 * @return ha minden alkatrész megvan és az összes játékos ugyanazon a mezőn van, akkor megnyerik a játékot és true-t ad vissza, amúgy false*/
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
		Tile hova=selectTile();
		hova.stepOnto(this,tile);
		return true;
	}
	
	/** Megnöveli a játékos testhőjét 1-gyel */
	public void increaseBodyHeat() {
		System.out.println("\nPlayer increaseBodyHeat\n");
		bodyHeat++;
	}
	
	/** Lecsökkenti a játékos testhőjét 1-gyel */
	public void decreaseBodyHeat() {
		System.out.println("\nPlayer decreaseBodyHeat\n");
		bodyHeat--;
	}
	
	/** A játékos megfullad */
	public void drown() {
		System.out.println("\nPlayer drown\n");
		gameController.gameOver();
	}
	
	/** A játékos elhasznál egy akciópontot */
	public void loseAP() {
		System.out.println("\nPlayer loseAP\n");
		actions--;
	}
	
	/** A Player kézzel és és 1 egység havat takarít el a mezőjéről,
	 * @return true ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithHands() {
		System.out.println("\nPlayer digWithHands\n");
		return false;
	}

	/** A Player ásóval és és 2 egység havat takarít el a mezőjéről,
	 * @return True ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithShovel() {
		System.out.println("\nPlayer digWithShovel\n");
		return false;
	}
	
	/** A játékos felveszi a tárgyat a mezőről, amin áll
	 * @return true ha feltudta venni, false ha nem*/
	public boolean pickup() {
		System.out.println("\nPlayer pickup\n");
		return false;
	}

	/** A játékos kiválaszthat egy mezőt, amire lépni fog, vagy megnézi sarkkutatóval (menüt dob fel)
	 * @return kiválasztott tile (tile.getNeighbours eleme) */
	public Tile selectTile() {
		System.out.println("\nPlayer selectTile\n");
		Tile ret=tile.getNeighbours();
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

	public boolean canSave(){
		System.out.println("\nPlayer canSave\n");
		System.out.println("\nTud menekíteni a játékos?\n1:Igen\n2:Nem");
		String choice=System.console().readLine();
		if (choice.equals("1"))
			return true;
		return false;
	}

	public boolean canSurvive(){
		System.out.println("\nPlayer canSurvive\n");
		System.out.println("\nTud menekíteni a játékos?\n1:Igen\n2:Nem");
		String choice=System.console().readLine();
		if (choice.equals("1"))
			return true;
		return false;
	}
}

