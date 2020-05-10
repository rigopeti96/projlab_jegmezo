package jegmezo;

/** Fel lehet venni, amivel beállítja a Player Inventory-ját, 
 * el lehet dobni/elhasználni, amit szintén beállítja az Inventory-t lehet használni,
 *  illetve megmondja magáról, hogy meg lehet-e vele menteni másokat/magadat. */
public interface Item {
	
	/** beteszi egy Player Inventory-jába magát */
	boolean equip(Inventory inventory);
	
	/** kiveszi egy Player Inventory-jából magát */
	boolean unequip(Inventory inventory);
	
	/** a Player használja a tárgyat
	 * @param player Player, aki használja a tárgyat
	 * @return true ha sikeres, false ha nem */
	boolean use(Player player);
	
	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal*/
	boolean canSave();
	
	/** megnézi, hogy túl lehet-e élni a lyukba esést ezzel a tárggyal*/
	boolean canSurvive();

	/**
	 * Visszaadja a tárgy nevét
	 * @return A tárgy neve
	 */
	String getName();

	String getDescription();

	String getFileName();

	boolean isUseable();
}
