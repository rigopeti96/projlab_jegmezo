package jegmezo.model;


import jegmezo.controller.GameController;
import jegmezo.view.AssetManager;
import jegmezo.view.GameWindow;
import jegmezo.view.PlayerView;
import jegmezo.view.ScientistView;

import java.util.ArrayList;
import java.util.List;

/**A Player egy típusa, speciális képessége, hogy meg tudja határozni egy szomszédos mező bíróképességét (hány játékos állhat rajta beszakadás nélkül */
public class Scientist extends Player {
	/**
	 * Automatikus létrehozáshoz konstruktor
	 * @param gameController GameController paraméter
	 * @param number azonosító
	 */
	public Scientist(GameController gameController, int number) {
		super(gameController, number);
	}

	/**
	 * Kézi létrehozáshoz konstruktor
	 * @param gameController GameController paraméter
	 * @param number azonosító
	 * @param bodyHeat testhőmérséklet
	 */
	public Scientist(GameController gameController, int number, int bodyHeat) {
		super(gameController, number, bodyHeat);
	}


	/**
	 * Létrehozza a kutatóhoz tartozó View-t
	 * @param gameWindow - a viewnak szüksége lesz a gameWindowra
	 * @param assetManager - a viewnak szüksége lesz az assetManagerre
	 * @return Az elkészült PlayerView
	 */
	@Override
	public PlayerView createView(GameWindow gameWindow, AssetManager assetManager) {
		return new ScientistView(gameWindow, assetManager, this);
	}

	/**A kutató kiválasztja, hogy melyik Tile-t vizsgálja, majd megkapja az eredményt
	 */
	public boolean examine(Tile selectedTile) {
		int limit = selectedTile.examinePlayerLimit();

		gameController.getConsoleView().writeLine("Player "+ number + " checked the stability of "+ selectedTile.toShortString() + ". It can take " + limit + " players.");
		return true;
	}

	/**
	 * Visszaadja a tudós akcióit.
	 * @param selectedTile - megkapja a kiválasztott tile-t
	 * @return A játékos akciója
	 */
	public List<NamedAction> getTileActions(Tile selectedTile) {
		List<NamedAction> list = super.getTileActions(selectedTile);
		list.add(new NamedAction("Examine", () -> gameController.examine(selectedTile)));
		return list;
	}

	/**
	 * Kiírja az entitás egy reprezentációját a standard outputra
	 */
	@Override
	public void serialize() {
		System.out.println("Scientist(number=" + number + ",heat=" + bodyHeat + ",tile=" + tile.getId() + ")");
	}


	/**
	 * Visszaadja a játékos szerepét
	 * @return A játék szerepe
	 */
	@Override
	public String getName(){
		return "Scientist";
	}
}
