package jegmezo.model;


import jegmezo.controller.GameController;

/** A Player ennek használatával ki tud menteni egy játékost egy szomszédos lyukból.
 * Implementálja az Item interfészt. */
public class Rope extends Shovel {

	public Rope(GameController gameController) {
		super(gameController);
	}

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		return inventory.equipRope(this);
	}
	
	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		return inventory.unequipRope(this);
	}

	/** a Player használja a tárgyat
	 * @param player Player, aki használja a tárgyat
	 * @return true ha sikeres, false ha nem */
	@Override
	public boolean use(Player player) {
		return false;
	}

	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal */
	public boolean canSave(){
		return true;
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
		return "Rope";
	}

	/**
	 * Meghívja a getName függvényt, hogy rendes nevet adjon vissza
	 * @return - a tárgy neve
	 */
	@Override
	public String toString(){return getName();}

	@Override
	public String getDescription(){
		return "Kötél:\nHa melletted lyukba esik valaki, ezzel kihúzod.";
	}

	@Override
	public boolean isUseable(){
		return false;
	}
}
