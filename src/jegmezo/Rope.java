package jegmezo;




/** */
public class Rope implements Item {

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		if (inventory.equipRope() ) {
			System.out.println("Rope equiped");
			return true;
		}
		System.out.println("Rope not equiped");
		return false;
	}
	
	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		if (inventory.unequipRope() ) {
			System.out.println("Rope unequiped");
			return true;
		}
		System.out.println("Rope not unequiped");
		return false;
	}

	@Override
	public boolean use(Player p) {
		return false;
	}

	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal */
	public boolean canSave(){
		return true;
	}
	
	/** megnézi, hogy túl lehet-e élni a lyukba esést ezzel a tárggyal*/
	public boolean canSurvive(){
		return false;
	}
}
