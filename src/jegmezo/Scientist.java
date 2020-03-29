package jegmezo;

/** */
public class Scientist extends Player {
	public Scientist(GameController gameController, Tile tile) {
		super(gameController, tile);
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
