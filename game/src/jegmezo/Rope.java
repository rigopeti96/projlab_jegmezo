package jegmezo;




/** A Player ennek használatával ki tud menteni egy játékost egy szomszédos lyukból.
 * Implementálja az Item interfészt. */
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

	/** a Player használja a tárgyat
	 * @param player Player, aki használja a tárgyat
	 * @return true ha sikeres, false ha nem */
	@Override
	public boolean use(Player player) {
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
