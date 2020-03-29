package jegmezo;




/** */
public class WinItem implements Item {
	/** */
	private String name;

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		if (inventory.equipWinItem() ) {
			System.out.println("Win Item equiped");
			return true;
		}
		System.out.println("Win Item not equiped");
		return false;
	}
	
	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		if (inventory.unequipWinItem() ) {
			System.out.println("Win Item unequiped");
			return true;
		}
		System.out.println("Win Item not unequiped");
		return false;
	}
	
	/** a Player használja a tárgyat */
	public boolean use(Player player) {
		if (player.useWinItems() ) {
			System.out.println("Win Item used");
			return true;
		}
		System.out.println("ShovelNotUsed");
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
