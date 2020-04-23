package jegmezo;


import java.util.Scanner;

/** Játékos, lehet eszkimó és kutató. Birtokolhat tárgyat, használhatja azt a tárgyat és átadhatja másik játékosnak. át tud lépni szomszédos mezőre. */
public abstract class Player extends Entity{
	private int bodyHeat;
	protected int actions;
	protected int number;
	protected Tile tile;
	private Inventory inventory = new Inventory();
	private GameController gameController;

	public Player(GameController gameController, int number) {
		this.gameController = gameController;
		this.number = number;
	}

	/**
	 * Ráteszi a Player-t egy mezőre (kezdetben)
	 * @param tile Mező, amire tesszük
	 */
	@Override
	public void spawnOnto(Tile tile) {
		this.tile = tile;
		this.tile.addPlayer(this);
	}

	/**
	 * @return A Player Inventory-ja */
	public Inventory getInventory(){
		System.out.println("Player getInventory");
		return inventory;
	}

	/** A játékos körét kezeli le */
	public void takeTurn() {
		actions = 4;
		while (actions > 0) {
			if (selectAction()) actions--;
			if (gameController.getGameState() != GameState.Running) return;
		}
	}
	
	/** A játékos egyik nála lévő tárgyat átadhatja egyik játékos társának
	 * @return true ha sikeres, false ha nem */
	public boolean trade() {
		System.out.println("Player trade");
		return true;
	}
	
	/** A játékos felvesz egy tárgyat
	 * @return true, ha a játékos eltárolta az adott tárgyat, false ha nem vette fel */
	public boolean takeItem(Item item) {
		System.out.println("Player takeItem");
		return new Shovel().equip(inventory);
	}
	
	/** A játékos használja a győzelmi tárgyat
	 * @return ha minden alkatrész megvan és az összes játékos ugyanazon a mezőn van, akkor megnyerik a játékot és true-t ad vissza, amúgy false*/
	public boolean useWinItems() {
		System.out.println("Player useWinItems");
		if(tile.hasAllPlayers() && inventory.getWinItemCount()==3) {
			gameController.win();
			return true;
		}
		return false;
	}
	
	/** A játékos mozog (tile-t választ és odamozog)
	 * @return true has sikeres, false ha nem */
	public boolean move() {
		System.out.println("Player move");
		Tile hova=selectTile();
		hova.stepOnto(this, tile);
		return true;
	}
	
	/** Megnöveli a játékos testhőjét 1-gyel */
	public void increaseBodyHeat() {
		System.out.println("Player increaseBodyHeat");
		bodyHeat++;
	}
	
	/** Lecsökkenti a játékos testhőjét 1-gyel */
	public void decreaseBodyHeat() {
		System.out.println("Player decreaseBodyHeat");
		bodyHeat--;
		System.out.println("dead/deadn't?");
		switch (new Scanner(System.in).nextLine()) {
			case "dead":
				gameController.gameOver();
				break;
			case "deadn't":
				break;
		}
	}
	
	/** A játékos megfullad */
	public void drown() {
		System.out.println("Player drown");
		gameController.gameOver();
	}
	
	/** A játékos elhasznál egy akciópontot */
	public void loseAP() {
		System.out.println("Player loseAP");
		actions--;
	}
	
	/** A Player kézzel és és 1 egység havat takarít el a mezőjéről,
	 * @return true ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithHands() {
		System.out.println("Player digWithHands");
		return false;
	}

	/** A Player ásóval és és 2 egység havat takarít el a mezőjéről,
	 * @return True ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithShovel() {
		System.out.println("Player digWithShovel");
		tile.removeSnow(2);
		return false;
	}
	
	/** A játékos felveszi a tárgyat a mezőről, amin áll
	 * @return true ha feltudta venni, false ha nem*/
	public boolean pickup() {
		System.out.println("Player pickup");
		if (this.takeItem(this.tile.getItem())) {
			this.tile.removeItem();
			return true;
		}

		return false;
	}

	/** A játékos kiválaszthat egy mezőt, amire lépni fog, vagy megnézi sarkkutatóval (menüt dob fel)
	 * @return kiválasztott tile (tile.getNeighbours eleme) */
	public Tile selectTile() {
		System.out.println("Player selectTile");
		System.out.println("ice sheet/hole?");
		switch (new Scanner(System.in).nextLine()) {
			case "ice sheet":
				return tile.getNeighbours().get(0);
			case "hole":
				return tile.getNeighbours().get(1);
		}

		return null;
	}
	
	/** A játékos kiválaszt egy tárgyat, amelyet használ (menüt dob fel)
	 * @return true ha sikeres, false nem */
	public boolean useItem() {
		System.out.println("Player useItem");
		return true;
	}
	
	/** A játékos választ egy akciót, true-val tér vissza
	 * @return true ha az akció sikeres, false ha nem vagy a játékos nem választott akciót */
	public abstract boolean selectAction();

	/**
	 * A többi játékos implementáció hívja, a közös akciókat implementálja
	 * @param command Parancs
	 * @return true ha az akció sikeres, false ha nem vagy a játékos nem választott akciót
	 */
	protected boolean selectActionCommon(String command) {
		switch (command) {
			case "trade":
				return this.trade();
			default:
				gameController.handleControlCommand(command);
				return false;
		}
	}

	public boolean canSave(){
		System.out.println("Player canSave");
		int r=inventory.getRopeCount();
		if (r>0)
			return true;
		return false;
	}

	public boolean canSurvive(){
		System.out.println("Player canSurvive");
		int r=inventory.getScubaCount();
		if (r>0)
			return true;
		return false;
	}

	public boolean buildTent() {

		return true;
	}

	public void serialize() {

	}
}

