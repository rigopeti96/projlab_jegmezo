package jegmezo;


/** Ha a Player ezt használva ás, akkor 2 havat takarít el a választott mezõrõl.
 * Implementálja az Item interfészt. */
public class Shovel implements Item {
	
	/** Beteszi egy Player Inventory-jába magát
	 *  @param inventory - A Player inventoryja, amibe be kell tenni a Shovelt.
	 *  @return bool - Sikerült-e betenni.  */
	public boolean equip(Inventory inventory) {
		System.out.println("Shovel equip");
		return inventory.equipShovel();
	}
	
	/** kiveszi egy Player Inventory-jából magát
	 *  @param inventory - A Player inventoryja, amibõl ki kell tenni a Shovelt.
	 *  @return boolean - Sikerült-e kivenni */
	public boolean unequip(Inventory inventory) {
		System.out.println("Shovel unequip");
		return inventory.unequipShovel();
	}
	
	/** Eltakarit 2 réteg havat a mezõrõl, amin a Player tartózkodik.
	 * @param player - A játékos, aki használja a tárgyat
	 * @return boolean - Sikerült-e az akció*/
	public boolean use(Player player) {
		System.out.println("Shovel use");
		return player.digWithShovel();
	}
	
	/** Mindig hamisat ad (érvényes specifikáció szerint) 
	 * @return false - A Shovel nem tudja megmenteni játékosát*/
	public boolean canSurvive() {
		System.out.println("Shovel canSurvive");
		return false;
	}
	
	/** Mindig hamisat ad (érvényes specifikáció szerint) 
	 * @return false - A Shovel nem tud megmenteni más játékosokat*/
	public boolean canSave() {
		System.out.println("Shovel canSave");
		return false;
	}
}