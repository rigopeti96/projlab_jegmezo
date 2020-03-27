package jegmezo;




/** */
public abstract class Tile {
	/** */
	private int snow;
	
	/** */
	private GameController tiles;
	
	/** */
	public abstract void stepOnto(Player player, Tile prevTile);
	
	/** */
	public abstract int getPlayerLimit();
	
	/** */
	public void blizzard() {
	}
	
	/** */
	public abstract Item getItem();
	
	/** */
	public boolean removeSnow(amount int) {
	}
	
	/** */
	public abstract boolean buildIgloo();
	
	/** */
	public void stepOff(Player player) {
	}
	
	/** */
	public abstract void removeItem();
	
	/** */
	public abstract boolean canSave();
	
	/** */
	public void increaseSnow() {
	}
	
	/** */
	public void getNeighbours() {
	}
	
	/** */
	public Player selectPlayer() {
	}
	
	/** */
	public boolean hasAllPlayers() {
	}
}
