package jegmezo;




/** */
private abstract class Player {
	/** */
	private int actions;
	
	/** */
	private Tile tile;
	
	/** */
	private Tile players;
	
	/** */
	private GameController gamecontroller;

	//A játékos inventory-ja
	private Inventory inventory;
	
	//inventory get függvénye
	public Inventory getInventory(){
		return inventory;
	}

	/** */
	public void takeTurn() {
	}
	
	/** */
	public boolean trade() {
	}
	
	/** */
	public boolean takeItem(Item item) {
	}
	
	/** */
	public void useWinItems() {
		if(tile.hasAllPlayers() && inventory.hasAllWinItem()){
			gamecontroller.win();
			System.out.println("\nPlayers Win\n");
			return true;
		}
		System.out.println("\Players don't win\n");
		return false;
	}
	
	/** */
	public boolean move() {
	}
	
	/** */
	public void increaseBodyHeat() {
	}
	
	/** */
	public void decreaseBodyHeat() {
	}
	
	/** */
	public void drown() {
	}
	
	/** */
	public void loseAP() {
	}
	
	/** */
	public Tile selectTile() {
	}
	
	/** */
	public boolean digWithHands() {
	}
	
	/** */
	public boolean digWithShovel() {
	}
	
	/** */
	public boolean pickup() {
	}
	
	/** */
	public boolean useItem() {
	}
	
	/** */
	public abstract boolean selectAction();
}
