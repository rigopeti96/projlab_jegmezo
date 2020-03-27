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
	private GameController players;
	
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
