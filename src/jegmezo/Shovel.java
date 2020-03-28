package jegmezo;


/** Ha a Player ezt használva ás, akkor 2 havat takarít el a választott mezõrõl.
 * Implementálja az Item interfészt. */
public class Shovel implements Item {
	
	/** Beteszi egy Player Inventory-jába magát
	 *  @param inventory - A Player inventoryja, amibe be kell tenni a Shovelt.
	 *  @return bool - Sikerült-e betenni.  */
	public boolean equip(Inventory inventory) {
		if (inventory.equipShovel() ) {
			System.out.println("\nShovelEquiped\n");
			return true;
		}
		System.out.println("\nShovelNotEquiped\n");
		return false;
	}
	
	/** kiveszi egy Player Inventory-jából magát
	 *  @param inventory - A Player inventoryja, amibõl ki kell tenni a Shovelt.
	 *  @return boolean - Sikerült-e kivenni */
	public boolean unequip(Inventory inventory) {
		if (inventory.unequipScubaGear() ) {
			System.out.println("\nShovelUnequiped\n");
			return true;
		}
		System.out.println("\nShovelNotUnequiped\n");
		return false;
	}
	
	/** Eltakarit 2 réteg havat a mezõrõl, amin a Player tartózkodik.
	 * @param player - A játékos, aki használja a tárgyat
	 * @return boolean - Sikerült-e az akció*/
	public boolean use(Player player) {
		if (player.digWithShovel() ) {
			System.out.println("\nShovelUsed\n");
			return true;
		}
		System.out.println("\nShovelNotUsed\n");
		return false;
	}
	
	/** Mindig hamisat ad (érvényes specifikáció szerint) 
	 * @return false - A Shovel nem tudja megmenteni játékosát*/
	public boolean canSurvive() {
		System.out.println("\ncanSurvive()\n");
		return false;
	}
	
	/** Mindig hamisat ad (érvényes specifikáció szerint) 
	 * @return false - A Shovel nem tud megmenteni más játékosokat*/
	public boolean canSave() {
		System.out.println("\nShovelcanSave()\n");
		return false;
	}
}
