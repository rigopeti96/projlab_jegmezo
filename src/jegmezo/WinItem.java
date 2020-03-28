package jegmezo;




/** */
public class WinItem implements Item {
	/** */
	private String name;

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		if (inventory.equipWinItem() ) {
			System.out.println("\nWin Item equiped\n");
			return true;
		}
		System.out.println("\nWin Item not equiped\n");
		return false;
	}
	
	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		if (inventory.unequipWinItem() ) {
			System.out.println("\nWin Item unequiped\n");
			return true;
		}
		System.out.println("\nWin Item not unequiped\n");
		return false;
	}
	
	/** a Player használja a tárgyat */
	public boolean use(Player p) {
		if (player.useWinItems() ) {
			System.out.println("\nWin Item used\n");
			return true;
		}
		System.out.println("\nShovelNotUsed\n");
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
