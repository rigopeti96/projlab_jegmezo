package jegmezo;


/** Ha a Player ezt haszn�lva �s, akkor 2 havat takar�t el a v�lasztott mez�r�l.
 * Implement�lja az Item interf�szt. */
public class Shovel implements Item {
	
	/** Beteszi egy Player Inventory-j�ba mag�t
	 *  @param inventory - A Player inventoryja, amibe be kell tenni a Shovelt.
	 *  @return bool - Siker�lt-e betenni.  */
	public boolean equip(Inventory inventory) {
		if (inventory.equipShovel() ) {
			System.out.println("\nShovelEquiped\n");
			return true;
		}
		System.out.println("\nShovelNotEquiped\n");
		return false;
	}
	
	/** kiveszi egy Player Inventory-j�b�l mag�t
	 *  @param inventory - A Player inventoryja, amib�l ki kell tenni a Shovelt.
	 *  @return boolean - Siker�lt-e kivenni */
	public boolean unequip(Inventory inventory) {
		if (inventory.unequipScubaGear() ) {
			System.out.println("\nShovelUnequiped\n");
			return true;
		}
		System.out.println("\nShovelNotUnequiped\n");
		return false;
	}
	
	/** Eltakarit 2 r�teg havat a mez�r�l, amin a Player tart�zkodik.
	 * @param player - A j�t�kos, aki haszn�lja a t�rgyat
	 * @return boolean - Siker�lt-e az akci�*/
	public boolean use(Player player) {
		if (player.digWithShovel() ) {
			System.out.println("\nShovelUsed\n");
			return true;
		}
		System.out.println("\nShovelNotUsed\n");
		return false;
	}
	
	/** Mindig hamisat ad (�rv�nyes specifik�ci� szerint) 
	 * @return false - A Shovel nem tudja megmenteni j�t�kos�t*/
	public boolean canSurvive() {
		System.out.println("\ncanSurvive()\n");
		return false;
	}
	
	/** Mindig hamisat ad (�rv�nyes specifik�ci� szerint) 
	 * @return false - A Shovel nem tud megmenteni m�s j�t�kosokat*/
	public boolean canSave() {
		System.out.println("\nShovelcanSave()\n");
		return false;
	}
}
