package jegmezo;


import java.util.Scanner;

/** Játékos, lehet eszkimó és kutató. Birtokolhat tárgyat, használhatja azt a tárgyat és átadhatja másik játékosnak. át tud lépni szomszédos mezőre. */
public abstract class Player extends Entity{
	protected int bodyHeat;
	protected int actions;
	protected int number;
	protected Tile tile;
	private Inventory inventory = new Inventory();
	private GameController gameController;

	public Player(GameController gameController, int number) {
		this(gameController, number, 4);
	}

	public Player(GameController gameController, int number, int bodyHeat) {
		this.gameController = gameController;
		this.number = number;
		this.bodyHeat = bodyHeat;
	}

	public int getNumber() {
		return number;
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
		Player player = tile.selectPlayer(this);
		if (player == null) return false;
		Item item = inventory.selectItem();
		if (item == null) return false;
		if (player.takeItem(item)) {
			System.out.println("Player " + number + " traded their " + item.getName() + " to Player " + player.getNumber());
			item.unequip(inventory);
			return true;
		} else {
			System.out.println("Player " + number + " can't trade their " + item.getName() + " to Player " + player.getNumber() + " (Player " + player.getNumber() + " can't take it).");
			return false;
		}
	}
	
	/** A játékos felvesz egy tárgyat
	 * @return true, ha a játékos eltárolta az adott tárgyat, false ha nem vette fel */
	public boolean takeItem(Item item) {
		return item.equip(inventory);
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
		System.out.println("Player drowned");
		gameController.gameOver();
	}

	/** A játékost megette a medve*/
	public void eaten(){
		System.out.println("Player has been eaten");
		gameController.gameOver();
	}
	
	/** A Player kézzel és és 1 egység havat takarít el a mezőjéről,
	 * @return true ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithHands() {
		System.out.println("Player digWithHands");
		return tile.removeSnow(1);
	}

	/** A Player ásóval és és 2 egység havat takarít el a mezőjéről,
	 * @return True ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithShovel() {
		System.out.println("Player digWithShovel");
		return tile.removeSnow(2);
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
		System.out.println("Player " + number + "'s items:");
		Item item = inventory.selectItem();
		if (item == null) return false;
		return item.use(this);
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
			case "pass":
				return true;
			case "trade":
				return this.trade();
			case "use item":
				return this.useItem();
			case "dig":
				return this.digWithHands();
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

	/**
	 * Épít egy Tent-et a mezőre amin áll.
	 * @return
	 */
	public boolean buildTent() {
		return tile.build(Building.TENT);
	}
}

