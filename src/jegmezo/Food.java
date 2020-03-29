package jegmezo;

/** */
public class Food implements Item {

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		if (inventory.equipFood() ) {
			System.out.println("\nFood equiped\n");
			return true;
		}
		System.out.println("\nFood not equiped\n");
		return false;
	}

	/** kiveszi egy Player Inventory-jából magát */
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

	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal */
	public boolean canSave(){
		return false;
	}
	
	/** megnézi, hogy túl lehet-e élni a lyukba esést ezzel a tárggyal*/
	public boolean canSurvive(){
		return false;
	}


}
