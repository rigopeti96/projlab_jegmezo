package jegmezo;




/** */
public class Rope implements Item {

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		System.out.println("Rope equip");
		return inventory.equipRope();
	}
	
	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		System.out.println("Rope unequip");
		return inventory.unequipRope();
	}

	@Override
	public boolean use(Player p) {
		System.out.println("Rope use");
		return false;
	}

	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal */
	public boolean canSave(){
		System.out.println("Rope canSave");
		return true;
	}
	
	/** megnézi, hogy túl lehet-e élni a lyukba esést ezzel a tárggyal*/
	public boolean canSurvive(){
		System.out.println("Rope canSurvive");
		return false;
	}
}
