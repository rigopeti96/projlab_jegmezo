package jegmezo;

/** */
public class Food implements Item {

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		System.out.println("Food equip");
		return inventory.equipFood();
	}

	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		System.out.println("Food unequip");
		return inventory.unequipFood();
	}

	/** */
	public boolean use(Player p) {
		System.out.println("Food use");
		p.increaseBodyHeat();
		return unequip(p.getInventory());
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
