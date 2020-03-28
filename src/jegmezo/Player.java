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

	//A j�t�kos inventory-ja
	private Inventory inventory;
	
	//inventory get f�ggv�nye
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
		System.out.println("\nPlayer move\n");
		Tile hova=selectTile();
		hova.stepOnto(this,tile);
		return true;
	}
	
	/** */
	public void increaseBodyHeat() {
	}
	
	/** */
	public void decreaseBodyHeat() {
	}
	
	/** */
	public void drown() {
		System.out.println("\nPlayer drown\n");
		gamecontroller.gameOver();
	}
	
	/** */
	public void loseAP() {
	}
	
	/** */
	public Tile selectTile() {
		System.out.println("\nPlayer selectTile\n");
		Tile ret=tile.getNeighbours();
		return ret;
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

	//Hozzáírtam mert nem került bele, illetve még ki lehet bővíteni az inventory munkájával is egyenlőre így hagytam hátha valami változik  -T
	public boolean canSave(){
		System.out.println("\nPlayer canSave\n");
		System.out.println("\nTud menekíteni a játékos?\n1:Igen\n2:Nem");
		String choice=System.console().readLine();
		if(choice=="1")
			return true;
		return false;
	}
}

