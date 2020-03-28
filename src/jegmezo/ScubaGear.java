package jegmezo;


/**Passz�v t�rgy, megmenti a v�zbe esett gazd�j�t. Implement�lja az Item interf�szt.*/
public class ScubaGear implements Item {
	
	/** Beteszi egy Player Inventory-j�ba mag�t 
	 *  @param inventory - A Player inventoryja, amibe be kell tenni a ScubaGeart.
	 *  @return bool - Siker�lt-e betenni
	 * */
	public boolean equip(Inventory inventory) {
		if (inventory.equipScubaGear() ) {
			System.out.println("\nScubaGearEquiped\n");
			return true;
		}
		System.out.println("\nScubaGearNotEquiped\n");
		return false;
	}
	
	/** kiveszi egy Player Inventory-j�b�l mag�t 
	 *  @param inventory - A Player inventoryja, amib�l ki kell tenni a ScubaGeart.
	 *  @return boolean - Siker�lt-e kivenni
	 * */
	public boolean unequip(Inventory inventory) {
		if (inventory.unequipScubaGear() ) {
			System.out.println("\nScubaGearUnequiped\n");
			return true;
		}
		System.out.println("\nScubaGearNotUnequiped\n");
		return false;
	}
	
	/** Mindig hamisat ad, nem lehet haszn�lni (passz�v t�rgy)
	 *  @param player - A Player, aki haszn�lni akarja a ScubaGeart.
	 *  @return  false - Nem lehet haszn�lni*/
	public boolean use(Player player) {
		System.out.println("\nScubaGearUseItem\n");
		return false;
	}
	
	/** Mindig hamisat ad (�rv�nyes specifik�ci� szerint)
	 * @return false - Nem tud megmenteni m�s j�t�kost. */
	public boolean canSave() {
		System.out.println("\nScubaGearcanSave()\n");
		return false;
	}
	
	/** Mindig igazat ad (�rv�nyes specifik�ci� szerint)
	 *  @return true - A visel�je t�l�li a lyukbaes�st. */
	public boolean canSurvive() {
		System.out.println("\nScubaGearcanSurvive()\n");
		return true;
	}
}
