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
	public Tile getNeighbours() {
		System.out.println("\nTile getNeighbours\n");
		System.out.println("\nMilyen Tile-ra lépjen?\n1:IceSheet\n2:Hole");
		String choice =System.console().readLine();
		if(choice=="2"){
			return new Hole();
		}
		else{
			return new IceSheet();
		}
	}
	
	/** */
	public Player selectPlayer() {
	}
	
	/** */
	public boolean hasAllPlayers() {
	}
}
