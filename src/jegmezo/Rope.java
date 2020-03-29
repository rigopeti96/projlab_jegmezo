package jegmezo;




/** */
public class Rope implements Item {

	/** beteszi egy Player Inventory-j�ba mag�t */
	public boolean equip(Inventory inventory){
		if (inventory.equipRope() ) {
			System.out.println("\nRope equiped\n");
			return true;
		}
		System.out.println("\nRope not equiped\n");
		return false;
	}
	
	/** kiveszi egy Player Inventory-j�b�l mag�t */
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

	/** megn�zi, hogy meg lehet-e menteni csapatt�rs�t ezzel a t�rggyal */
	public boolean canSave(){
		return true;
	}
	
	/** megn�zi, hogy t�l lehet-e �lni a lyukba es�st ezzel a t�rggyal*/
	public boolean canSurvive(){
		return false;
	}
}
