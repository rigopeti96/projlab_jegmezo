package jegmezo;


/**Passzív tárgy, megmenti a vízbe esett gazdáját. Implementálja az Item interfészt.*/
public class ScubaGear implements Item {
	
	/** Beteszi egy Player Inventory-jába magát 
	 *  @param inventory - A Player inventoryja, amibe be kell tenni a ScubaGeart.
	 *  @return bool - Sikerült-e betenni
	 * */
	public boolean equip(Inventory inventory) {
		if (inventory.equipScubaGear() ) {
			System.out.println("ScubaGearEquiped");
			return true;
		}
		System.out.println("ScubaGearNotEquiped");
		return false;
	}
	
	/** kiveszi egy Player Inventory-jából magát 
	 *  @param inventory - A Player inventoryja, amibõl ki kell tenni a ScubaGeart.
	 *  @return boolean - Sikerült-e kivenni
	 * */
	public boolean unequip(Inventory inventory) {
		if (inventory.unequipScubaGear() ) {
			System.out.println("ScubaGearUnequiped");
			return true;
		}
		System.out.println("ScubaGearNotUnequiped");
		return false;
	}
	
	/** Mindig hamisat ad, nem lehet használni (passzív tárgy)
	 *  @param player - A Player, aki használni akarja a ScubaGeart.
	 *  @return  false - Nem lehet használni*/
	public boolean use(Player player) {
		System.out.println("ScubaGearUseItem");
		return false;
	}
	
	/** Mindig hamisat ad (érvényes specifikáció szerint)
	 * @return false - Nem tud megmenteni más játékost. */
	public boolean canSave() {
		System.out.println("ScubaGearcanSave()");
		return false;
	}
	
	/** Mindig igazat ad (érvényes specifikáció szerint)
	 *  @return true - A viselõje túléli a lyukbaesést. */
	public boolean canSurvive() {
		System.out.println("ScubaGearcanSurvive()");
		return true;
	}
}
