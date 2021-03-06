package jegmezo.model;

import jegmezo.controller.GameController;

/** Ha a Player ezt használja, akkor egyel több testhője lesz.
 * Implementálja az Item interfészt. */
public class Food extends Item {
	/**
	 * a food konstruktora
	 * @param gameController - a gamecontrollerre szüksége van az item-nek
	 */
	public Food(GameController gameController) {
		super(gameController);
	}

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		return inventory.equipFood(this);
	}

	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		return inventory.unequipFood(this);
	}

	/** a Player használja a tárgyat
	 * @param player Player, aki használja a tárgyat
	 * @return true ha sikeres, false ha nem */
	public boolean use(Player player) {
		gameController.getConsoleView().writeLine("Player " + player.getNumber() + " eats a food.");
		player.increaseBodyHeat();
		return unequip(player.getInventory());
	}

	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal */
	public boolean canSave(){
		return false;
	}
	
	/** megnézi, hogy túl lehet-e élni a lyukba esést ezzel a tárggyal*/
	public boolean canSurvive(){
		return false;
	}

	/**
	 * Visszaadja a tárgy nevét
	 * @return A tárgy neve
	 */
	public String getName() {
		return "Food";
	}

	/**
	 * Meghívja a getName függvényt, hogy rendes nevet adjon vissza
	 * @return - a tárgy neve
	 */
	@Override
	public String toString(){return getName();}

	/**
	 * visszaadja a food leírását
	 * @return - a leírás stringje
	 */
	@Override
	public String getDescription(){
		return "Étel:\nHa megeszed, megnő a testhőd.";
	}

	/**
	 * visszaadja, hogy használható-e a food
	 * @return - bool, ennek a jelzésére
	 */
	@Override
	public boolean isUseable(){
		return true;
	}
}
