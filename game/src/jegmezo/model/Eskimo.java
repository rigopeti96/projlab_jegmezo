package jegmezo.model;

import jegmezo.controller.GameController;

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
