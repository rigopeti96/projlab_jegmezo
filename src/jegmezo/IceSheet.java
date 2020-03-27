package jegmezo;




/** */
public class IceSheet extends Tile {
	/** */
	private int playerLimit;
	
	/** */
	private boolean hasIgloo;

	@Override
	public void stepOnto(Player player, Tile prevTile) {

	}

	@Override
	public int getPlayerLimit() {
		return 0;
	}

	@Override
	public Item getItem() {
		return null;
	}

	@Override
	public boolean buildIgloo() {
		return false;
	}

	@Override
	public void removeItem() {

	}

	@Override
	public boolean canSave() {
		return false;
	}
}
