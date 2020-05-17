package jegmezo.model;

import jegmezo.controller.GameController;

/** Fel lehet venni, amivel beállítja a Player Inventory-ját,
 * el lehet dobni/elhasználni, amit szintén beállítja az Inventory-t lehet használni,
 *  illetve megmondja magáról, hogy meg lehet-e vele menteni másokat/magadat. */
public abstract class Item {
	/** a gameController */
	protected GameController gameController;

	/**
	 * az Item konstruktora
	 * @param gameController - a gameController
	 */
	public Item(GameController gameController) {
		this.gameController = gameController;
	}

	/** beteszi egy Player Inventory-jába magát */
	public abstract boolean equip(Inventory inventory);

	/** kiveszi egy Player Inventory-jából magát */
	public abstract boolean unequip(Inventory inventory);
	
	/** a Player használja a tárgyat
	 * @param player Player, aki használja a tárgyat
	 * @return true ha sikeres, false ha nem */
	public abstract boolean use(Player player);
	
	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal*/
	public abstract boolean canSave();
	
	/** megnézi, hogy túl lehet-e élni a lyukba esést ezzel a tárggyal*/
	public abstract boolean canSurvive();

	/**
	 * Visszaadja a tárgy nevét
	 * @return A tárgy neve
	 */
	public abstract String getName();

	/**
	 * az item leírása, absztrakt függvény, minden item felülírja
	 * @return - a leírást tartalmazó string
	 */
	public abstract String getDescription();

	/**
	 * visszaadja, hogy az item használható-e vagy sem, absztrakt, minden item felülírja
	 * @return - ezt jelző bool
	 */
	public abstract boolean isUseable();
}
