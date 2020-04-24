package jegmezo;


import java.util.ArrayList;
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
		Tile hova=selectTile();
		if(hova == null)
			return false;

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
			System.out.print("Player " + number + " picks up "+ item.getName() + " from "); tile.toShortString();
			return true;
		}else{
			System.out.println("Cant pick up "+ item.getName() + " (Already has too much.)");
			return false;
		}
	}

	/** A játékos kiválaszthat egy mezőt, amire lépni fog, vagy megnézi sarkkutatóval (menüt dob fel)
	 * @return kiválasztott tile (tile.getNeighbours eleme)
	 * visszalépés esetén null-lal tér vissza*/
	public Tile selectTile() {
		System.out.println("Neighbouring tiles: ");
		ArrayList<Tile> neighbourTiles= new ArrayList<Tile>();
		neighbourTiles.addAll(this.tile.getNeighbours());
		for (Tile tile: neighbourTiles){
			tile.toLongString();
		}

		while (true){
			System.out.println("Select tile (<ID>/cancel)");
			String line = new Scanner(System.in).nextLine().trim();

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
}

