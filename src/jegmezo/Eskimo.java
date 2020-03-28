package jegmezo;

/** */
public class Eskimo extends Player {
	public Eskimo(){
		super();
	}

	/** */
	public boolean buildIgloo() {
	    Tile tile = selectTile();
	    tile.buildIgloo();
		return true;
	}
	
	/** */
	public boolean selectAction() {
		return false;
	}
}
