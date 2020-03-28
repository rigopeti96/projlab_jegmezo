package jegmezo;

/** */
public class Scientist extends Player {
	public Scientist(GameController gameController) {
		super(gameController);
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
