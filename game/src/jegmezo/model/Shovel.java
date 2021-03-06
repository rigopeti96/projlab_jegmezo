package jegmezo.model;


import jegmezo.controller.GameController;

/** Ha a Player ezt használva ás, akkor 2 havat takarít el a választott mezõrõl.
 * Implementálja az Item interfészt. */
public class Shovel extends Item {

	public Shovel(GameController gameController) {
		super(gameController);
	}

	/** Beteszi egy Player Inventory-jába magát
	 *  @param inventory - A Player inventoryja, amibe be kell tenni a Shovelt.
	 *  @return bool - Sikerült-e betenni.  */
	public boolean equip(Inventory inventory) {
		return inventory.equipShovel(this);
	}
	
	/** kiveszi egy Player Inventory-jából magát
	 *  @param inventory - A Player inventoryja, amibõl ki kell tenni a Shovelt.
	 *  @return boolean - Sikerült-e kivenni */
	public boolean unequip(Inventory inventory) {
		return inventory.unequipShovel(this);
	}
	
	/** Eltakarit 2 réteg havat a mezõrõl, amin a Player tartózkodik.
	 * @param player - A játékos, aki használja a tárgyat
	 * @return boolean - Sikerült-e az akció*/
	public boolean use(Player player) {
		return player.digWithShovel();
	}
	
	/** Mindig hamisat ad (érvényes specifikáció szerint) 
	 * @return false - A Shovel nem tudja megmenteni játékosát*/
	public boolean canSurvive() {
		return false;
	}
	
	/** Mindig hamisat ad (érvényes specifikáció szerint) 
	 * @return false - A Shovel nem tud megmenteni más játékosokat*/
	public boolean canSave() {
		return false;
	}

	/**
	 * Visszaadja a tárgy nevét
	 * @return A tárgy neve
	 */
	public String getName() {
		return "Shovel";
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
		return "Ásó:\n2 egység havat lapátolhatsz el vele.";
	}

	/**
	 * Visszaadja, hogy a tárgy használható-e
	 * @return Ennek értéke
	 */
	@Override
	public boolean isUseable(){
		return true;
	}
}