package jegmezo;

/** */
public class Eskimo extends Player {
	public Eskimo(){
		super();
	}

	/** */
	public boolean buildIgloo() {
	    Tile tile = new Tile();
	    tile.buildIgloo();
		return true;
	}
	
	/** */
	public boolean selectAction() {
		return false;
	}
}
