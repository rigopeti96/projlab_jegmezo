package jegmezo;

/** */
public class Scientist extends Player {
	public Scientist(){
		super();
	}

	/** */
	public void examine() {
        Tile tile = selectTile();
        tile.getPlayerLimit();
	}
	
	/** */
	public boolean selectAction() {
		return false;
	}
}
