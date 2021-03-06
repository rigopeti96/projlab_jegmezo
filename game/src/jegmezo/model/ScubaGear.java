package jegmezo.model;


import jegmezo.controller.GameController;

/**Passzív tárgy, megmenti a vízbe esett gazdáját. Implementálja az Item interfészt.*/
public class ScubaGear extends Item {

	public ScubaGear(GameController gameController) {
		super(gameController);
	}

	/** Beteszi egy Player Inventory-jába magát
	 *  @param inventory - A Player inventoryja, amibe be kell tenni a ScubaGeart.
	 *  @return bool - Sikerült-e betenni
	 * */
	public boolean equip(Inventory inventory) {
		return inventory.equipScubaGear(this);
	}
	
	/** kiveszi egy Player Inventory-jából magát 
	 *  @param inventory - A Player inventoryja, amibõl ki kell tenni a ScubaGeart.
	 *  @return boolean - Sikerült-e kivenni
	 * */
	public boolean unequip(Inventory inventory) {
		return inventory.unequipScubaGear(this);
	}
	
	/** Mindig hamisat ad, nem lehet használni (passzív tárgy)
	 *  @param player - A Player, aki használni akarja a ScubaGeart.
	 *  @return  false - Nem lehet használni*/
	public boolean use(Player player) {
		return false;
	}
	
	/** Mindig hamisat ad (érvényes specifikáció szerint)
	 * @return false - Nem tud megmenteni más játékost. */
	public boolean canSave() {
		return false;
	}
	
	/** Mindig igazat ad (érvényes specifikáció szerint)
	 *  @return true - A viselõje túléli a lyukbaesést. */
	public boolean canSurvive() {
		return true;
	}

	/**
	 * Visszaadja a tárgy nevét
	 * @return A tárgy neve
	 */
	public String getName() {
		return "Scuba gear";
	}

	/**
	 * Meghívja a getName függvényt, hogy rendes nevet adjon vissza
	 * @return - a tárgy neve
	 */
	@Override
	public String toString(){return getName();}


	/**
	 * Visszaadja a tárgy leírását
	 * @return A tárgyhoz tatrozó leírás
	 */
	@Override
	public String getDescription(){
		return "Búvárruha:\nHa lyukba esel, ezzel túléled.";
	}

	/**
	 * Visszaadja, hogy a tárgy használható-e
	 * @return Ennek értéke
	 */
	@Override
	public boolean isUseable(){
		return false;
	}
}
