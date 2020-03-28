package jegmezo;




/** */
public class WinItem implements Item {
	/** */
	private String name;

	/** beteszi egy Player Inventory-j�ba mag�t */
	public boolean equip(Inventory inventory){
		if (inventory.equipWinItem() ) {
			System.out.println("\nWin Item equiped\n");
			return true;
		}
		System.out.println("\nWin Item not equiped\n");
		return false;
	}
	
	/** kiveszi egy Player Inventory-j�b�l mag�t */
	public boolean unequip(Inventory inventory){
		if (inventory.unequipWinItem() ) {
			System.out.println("\nWin Item unequiped\n");
			return true;
		}
		System.out.println("\nWin Item not unequiped\n");
		return false;
	}
	
	/** a Player haszn�lja a t�rgyat */
	public boolean use(Player p) {
		if (player.useWinItems() ) {
			System.out.println("\nWin Item used\n");
			return true;
		}
		System.out.println("\nShovelNotUsed\n");
		return false;
	}
	
	/** megn�zi, hogy meg lehet-e menteni csapatt�rs�t ezzel a t�rggyal */
	public boolean canSave(){
		return false;
	}
	
	/** megn�zi, hogy t�l lehet-e �lni a lyukba es�st ezzel a t�rggyal*/
	public boolean canSurvive(){
		return false;
	}
}
