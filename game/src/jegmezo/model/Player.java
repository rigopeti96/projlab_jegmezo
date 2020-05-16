package jegmezo.model;


import java.util.List;

/** Játékos, lehet eszkimó és kutató. Birtokolhat tárgyat, használhatja azt a tárgyat és átadhatja másik játékosnak. át tud lépni szomszédos mezőre. */
public abstract class Player extends Entity{
	/**
	 * testhőmérséklet
	 */
	protected int bodyHeat;
	/**
	 * akciók száma
	 */
	protected int actions;
	/**
	 * játékos azonosítója
	 */
	protected int number;
	/**
	 * A játékos inventory-ja
	 */
	private Inventory inventory;

	/**
	 * Konstruktor automatikus létrehozáshoz
	 * @param gameController GameController attribútum
	 * @param number azonosító
	 */
	public Player(GameController gameController, int number) {
		this(gameController, number, 4);
	}

	/**
	 * Konstruktor kézi létrehozáshoz
	 * @param gameController GameController attribútum
	 * @param number azonosító
	 * @param bodyHeat testhőmérséklet
	 */
	public Player(GameController gameController, int number, int bodyHeat) {
		this.gameController = gameController;
		this.inventory = new Inventory(gameController);
		this.number = number;
		this.bodyHeat = bodyHeat;
	}

	/**
	 * Visszaadja a játékos számát.
	 * @return a player száma
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Ráteszi a Player-t egy mezőre (kezdetben)
	 * @param tile Mező, amire tesszük
	 */
	@Override
	public void spawnOnto(Tile tile) {
		super.spawnOnto(tile);
		this.tile.stepOnto(this, null);
	}

	/**
	 * Visszaadja a player inventoryját.
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
		System.out.println("Player " + number + "'s items:");
		Item item = inventory.selectItem();
		if (item == null) return false;
		if (player.takeItem(item)) {
			System.out.println("Player " + number + " traded their " + item.getName() + " to Player " + player.getNumber() + ".");
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
		if(tile.hasAllPlayers() && inventory.getWinItemCount()==3) {
			System.out.println("Player " + number+ " assembles and uses the flare gun.");
			gameController.win();
			return true;
		}
		if(!tile.hasAllPlayers()) System.out.println("Player " + number+ " can't assemble the flare gun (not all players present).");
		if(!(inventory.getWinItemCount()==3)) System.out.println("Player " + number+ " can't assemble the flare gun (parts missing).");
		return false;
	}
	
	/** A játékos mozog (tile-t választ és odamozog)
	 * @return true has sikeres, false ha nem */
	public boolean move() {
		Tile hova=selectTile();
		if(hova == null)
			return false;

		hova.stepOnto(this, tile);
		return true;
	}
	
	/** Megnöveli a játékos testhőjét 1-gyel */
	public void increaseBodyHeat() {
		bodyHeat++;
		System.out.println("Player " + number + "'s body heat increases by 1 to " + bodyHeat + ".");
	}
	
	/** Lecsökkenti a játékos testhőjét 1-gyel */
	public void decreaseBodyHeat() {
		bodyHeat--;
		System.out.println("Player " + number + "'s body heat decrease by 1 to " + bodyHeat + ".");
		if(bodyHeat == 0) {
			gameController.gameOver();
		}
	}
	
	/** A játékos megfullad */
	public void drown() {
		System.out.println("Player "+number+" drowned");
		gameController.gameOver();
	}

	/** A játékost megette a medve*/
	public void eaten(){
		System.out.println("Player "+number+" was eaten by the polar bear.");
		gameController.gameOver();
	}
	
	/** A Player kézzel és és 1 egység havat takarít el a mezőjéről,
	 * @return true ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithHands() {
		System.out.print("Player " + number);
		return tile.removeSnow(1);
	}

	/** A Player ásóval és és 2 egység havat takarít el a mezőjéről,
	 * @return True ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithShovel() {
		System.out.print("Player " + number);
		return tile.removeSnow(2);
	}
	
	/** A játékos felveszi a tárgyat a mezőről, amin áll
	 * @return true ha feltudta venni, false ha nem*/
	public boolean pickup() {
		Item item;
		if (tile.getSnow() != 0){
			System.out.println("Can't attempt to pick up item (Sheet is covered by snow.)");
			return false;
		}
		item= this.tile.getItem();
		if(item == null) {
			System.out.println("There is nothing to pick up");
			return false;
		}
		if (this.takeItem(item) ) {
			this.tile.removeItem();
			System.out.println("Player " + number + " picks up "+ item.getName() + " from "+ tile.toShortString() + ".");
			return true;
		}else{
			System.out.println("Can't pick up "+ item.getName() + " (already has too much).");
			return false;
		}
	}

	/** A játékos kiválaszthat egy mezőt, amire lépni fog, vagy megnézi sarkkutatóval (menüt dob fel)
	 * @return kiválasztott tile (tile.getNeighbours eleme)
	 * visszalépés esetén null-lal tér vissza*/
	public Tile selectTile() {
		System.out.println("Neighbouring tiles:");
		List<Tile> neighbourTiles = this.tile.getNeighbours();
		for (Tile tile: neighbourTiles) {
			System.out.println(tile.toLongString() );
		}

		while (true){
			System.out.println("Select tile (<ID>/cancel):");
			String line = gameController.getScanner().nextLine().trim();

			if(line.equals("cancel")) return null;
			for (Tile tile : neighbourTiles) {
				if (String.valueOf(tile.getId()).equals(line)) {
					return tile;
				}
			}
			System.out.println("No tile with " + line + " ID.");
		}
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
			case "pickup":
				return pickup();
			case "move":
				return move();
			default:
				gameController.handleControlCommand(command);
				return false;
		}
	}

	/**
	 * Meg tudja-e menteni a játékos egy másik játékost a lyukba lépés után
	 * @return TRUE ha megmenthető, egyébként FALSE
	 */
	public boolean canSave(){
		if (inventory.getRopeCount()>0)
			return true;
		return false;
	}

	/**
	 * Túl tud-e élni a jákékos lyukba lépés után (van-e nála búvárruha)
	 * @return TRUE ha megmenthető, egyébként FALSE
	 */
	public boolean canSurvive(){
		if (inventory.getScubaCount()>0)
			return true;
		return false;
	}

	/**
	 * Épít egy Tent-et a mezőre amin áll.
	 * @return
	 */
	public boolean buildTent() {
		if(tile.build(Building.tent)){
			System.out.println("Player "+number+" places a tent on " + tile.toShortString() + ".");
			return true;
		}
		System.out.println("Player "+number+" can't place a tent " + tile.toShortString() + " already has one.");
		return false;
	}

	public int getActions() {
		return this.actions;
	}

	abstract public String getName();
}

