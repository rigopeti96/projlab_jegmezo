package jegmezo.model;

import jegmezo.controller.GameController;
import jegmezo.view.AssetManager;
import jegmezo.view.EskimoView;
import jegmezo.view.GameWindow;
import jegmezo.view.PlayerView;

import java.util.ArrayList;
import java.util.List;

/**A Player egy típusa, speciális képessége, hogy tud iglut építeni az aktuális mezőre (ha még nincs rajta iglu) */
public class Eskimo extends Player {
	/**
	 * Konstruktor az automatikus létrehozáshoz
	 * @param gameController GameController paraméter
	 * @param number azonosító
	 */
	public Eskimo(GameController gameController, int number) {
		super(gameController, number);
	}

	/**
	 * Konstruktor a kézi létrehozáshoz
	 * @param gameController GameController paraméter
	 * @param number azonosító
	 * @param bodyHeat testhőmérséklet
	 */
	public Eskimo(GameController gameController, int number, int bodyHeat) {
		super(gameController, number, bodyHeat);
	}

	@Override
	public PlayerView createView(GameWindow gameWindow, AssetManager assetManager) {
		return new EskimoView(gameWindow, assetManager, this);
	}

	/** Meghívja az Tile buildIgloo függvényét
	 *  @return bool - Sikerült-e építeni.  */
	public boolean buildIgloo() {
		if(tile.build(Building.igloo)) {
			gameController.getConsoleView().writeLine("Player "+ number +" builds igloo on " + tile.toShortString() + ".");
			return true;
		}
		gameController.getConsoleView().writeLine("Player "+ number +" can't build igloo on " + tile.toShortString() + ".");
		return false;
	}

	public List<NamedAction> getActions() {
		List<NamedAction> list = super.getActions();
		list.add(new NamedAction("Build igloo", () -> gameController.buildIgloo()));
		return list;
	}

	/**
	 * Kiírja az entitás egy reprezentációját a standard outputra
	 */
	@Override
	public void serialize() {
		System.out.println("Eskimo(number=" + number + ",heat=" + bodyHeat + ",tile=" + tile.getId() + ")");
	}

	@Override
	public String getName(){
		return "Eskimo";
	}
}
