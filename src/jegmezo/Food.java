package jegmezo;

/** */
public class Food implements Item {

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		if (inventory.equipFood() ) {
			System.out.println("Food equiped");
			return true;
		}
		System.out.println("Food not equiped");
		return false;
	}

	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		if (inventory.unequipFood() ) {
			System.out.println("Food unequiped");
			return true;
		}
		System.out.println("Food not unequiped");
		return false;
	}

	/** */
	public boolean use(Player p) {
		p.increaseBodyHeat();
		if (unequip(p.getInventory())) {
			System.out.println("Food used");
			return true;
		}
		System.out.println("Food not used");
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
