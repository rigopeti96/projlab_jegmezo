package jegmezo;



/** */
public class Hole extends Tile {
	/** */
	public void stepOnto(Player player, Tile prevTile) {
		System.out.println("\nHole stepOnto\n");
	}
	
	/** */
	public int getPlayerLimit() {
		System.out.println("\nHole getPlayerLimit\n");
		return 0;
	}

	/** */
	public Item getItem() {
		System.out.println("\nHole getItem\n");
		return null;
	}
	
	/** */
	public boolean buildIgloo() {
		System.out.println("\nHole buildIgloo\n");
		return false;
	}

	/** */
	public void removeItem() {
		System.out.println("\nHole removeItem\n");
	}
	
	/** */
	public boolean canSave() {
		System.out.println("\nHole canSave\n");
		return false;
	}
	
}
