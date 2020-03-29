package jegmezo;

/** */
public class Eskimo extends Player {
	public Eskimo(GameController gameController, Tile tile) {
		super(gameController, tile);
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
