package jegmezo;

/** */
public class Food implements Item {

	/** beteszi egy Player Inventory-j�ba mag�t */
	public boolean equip(Inventory inventory){
		if (inventory.equipFood() ) {
			System.out.println("\nFood equiped\n");
			return true;
		}
		System.out.println("\nFood not equiped\n");
		return false;
	}

	/** kiveszi egy Player Inventory-j�b�l mag�t */
	public boolean unequip(Inventory inventory){
		if (inventory.unequipFood() ) {
			System.out.println("\nFood unequiped\n");
			return true;
		}
		System.out.println("\nFood not unequiped\n");
		return false;
	}

	/** */
	public boolean use(Player p) {
		p.increaseBodyHeat();
		if (unequip(p.getInventory())) {
			System.out.println("\nFood used\n");
			return true;
		}
		System.out.println("\nFood not used\n");
		return false;
	}

	/** megn�zi, hogy meg lehet-e menteni csapatt�rs�t ezzel a t�rggyal */
	public boolean canSave(){
		return false;
	}
	
	/** megn�zi, hogy t�l lehet-e �lni a lyukba es�st ezzel a t�rggyal*/
	public boolean canSurvive(){
		return false;
	}


}
