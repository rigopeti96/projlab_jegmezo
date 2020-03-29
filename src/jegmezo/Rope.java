package jegmezo;




/** */
public class Rope implements Item {

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		if (inventory.equipRope() ) {
			System.out.println("\nRope equiped\n");
			return true;
		}
		System.out.println("\nRope not equiped\n");
		return false;
	}
	
	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		if (inventory.unequipRope() ) {
			System.out.println("\nRope unequiped\n");
			return true;
		}
		System.out.println("\nRope not unequiped\n");
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
