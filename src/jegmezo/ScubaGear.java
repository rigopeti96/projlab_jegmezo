package jegmezo;


/**Passzív tárgy, megmenti a vízbe esett gazdáját. Implementálja az Item interfészt.*/
public class ScubaGear implements Item {
	
	/** Beteszi egy Player Inventory-jába magát 
	 *  @param inventory - A Player inventoryja, amibe be kell tenni a ScubaGeart.
	 *  @return bool - Sikerült-e betenni
	 * */
	public boolean equip(Inventory inventory) {
		if (inventory.equipScubaGear() ) {
			System.out.println("\nScubaGearEquiped\n");
			return true;
		}
		System.out.println("\nScubaGearNotEquiped\n");
		return false;
	}
	
	/** kiveszi egy Player Inventory-jából magát 
	 *  @param inventory - A Player inventoryja, amibõl ki kell tenni a ScubaGeart.
	 *  @return boolean - Sikerült-e kivenni
	 * */
	public boolean unequip(Inventory inventory) {
		if (inventory.unequipScubaGear() ) {
			System.out.println("\nScubaGearUnequiped\n");
			return true;
		}
		System.out.println("\nScubaGearNotUnequiped\n");
		return false;
	}
	
	/** Mindig hamisat ad, nem lehet használni (passzív tárgy)
	 *  @param player - A Player, aki használni akarja a ScubaGeart.
	 *  @return  false - Nem lehet használni*/
	public boolean use(Player player) {
		System.out.println("\nScubaGearUseItem\n");
		return false;
	}
	
	/** Mindig hamisat ad (érvényes specifikáció szerint)
	 * @return false - Nem tud megmenteni más játékost. */
	public boolean canSave() {
		System.out.println("\nScubaGearcanSave()\n");
		return false;
	}
	
	/** Mindig igazat ad (érvényes specifikáció szerint)
	 *  @return true - A viselõje túléli a lyukbaesést. */
	public boolean canSurvive() {
		System.out.println("\nScubaGearcanSurvive()\n");
		return true;
	}
}
