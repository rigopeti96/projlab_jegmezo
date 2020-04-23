package jegmezo;



/** Ha a Player ezt használja, nála van az összes győzelmi tárgy és az összes játékos egy helyen van,
 *  akkor megnyerik a játékot
 * Implementálja az Item interfészt. */
public class WinItem implements Item {
	private String name;

	public WinItem(String name) {
		this.name = name;
	}

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		return inventory.equipWinItem(this);
	}
	
	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		return inventory.unequipWinItem(this);
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

	/**
	 * Visszaadja a tárgy nevét
	 * @return A tárgy neve
	 */
	@Override
	public String getName() {
		return name;
	}
}
