package jegmezo;




/** Ha a Player ezt használja, nála van az összes győzelmi tárgy és az összes játékos egy helyen van,
 *  akkor megnyerik a játékot
 * Implementálja az Item interfészt. */
public class WinItem implements Item {
	private String name;

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		System.out.println("WinItem canSave");
		return inventory.equipWinItem();
	}
	
	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		System.out.println("WinItem unequip");
		return inventory.unequipWinItem();
	}
	
	/** a Player használja a tárgyat */
	public boolean use(Player player) {
		System.out.println("WinItem use");
		return player.useWinItems();
	}
	
	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal */
	public boolean canSave() {
		System.out.println("WinItem canSave");
		return false;
	}
	
	/** megnézi, hogy túl lehet-e élni a lyukba esést ezzel a tárggyal*/
	public boolean canSurvive(){
		System.out.println("WinItem canSurvive");
		return false;
	}
}
