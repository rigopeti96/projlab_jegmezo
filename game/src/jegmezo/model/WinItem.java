package jegmezo.model;


import jegmezo.controller.GameController;

/** Ha a Player ezt használja, nála van az összes győzelmi tárgy és az összes játékos egy helyen van,
 *  akkor megnyerik a játékot
 * Implementálja az Item interfészt. */
public class WinItem extends Shovel {
	/**
	 * Az item pontos neve
	 */
	private String name;

	/**
	 * Win Item konstruktora, meg lehet adni a nevét
	 * @param name - a neve a győzelmi tárgynak
	 */
	public WinItem(GameController gameController, String name) {
		super(gameController);
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
		return player.useWinItems();
	}
	
	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal */
	public boolean canSave() {
		return false;
	}
	
	/** megnézi, hogy túl lehet-e élni a lyukba esést ezzel a tárggyal*/
	public boolean canSurvive(){
		return false;
	}

	/**
	 * Visszaadja a tárgy nevét
	 * @return A tárgy neve
	 */
	public String getName() {
		return name;
	}

	/**
	 * Meghívja a getName függvényt, hogy rendes nevet adjon vissza
	 * @return - a tárgy neve
	 */
	@Override
	public String toString(){return getName();}

	/**
	 * @return A tárgyhoz tatrozó leírás
	 */
	@Override
	public String getDescription(){
		return "Győzelmi tárgy:\nÖssze kell gyűjteni mind a hármat.\nHa egy mezőn álltok és elsütöd, nyertek.";
	}

	/**
	 * @return Használható-e a tárgy
	 */
	@Override
	public boolean isUseable(){
		return true;
	}
}
