package jegmezo;

/** Fel lehet venni, amivel beállítja a Player Inventory-ját, 
 * el lehet dobni/elhasználni, amit szintén beállítja az Inventory-t lehet használni,
 *  illetve megmondja magáról, hogy meg lehet-e vele menteni másokat/magadat. */
public interface Item {
	
	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory);
	
	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory);
	
	/** a Player használja a tárgyat */
	public boolean use(Player p);
	
	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal */
	public boolean canSave();
	
	/** megnézi, hogy túl lehet-e élni a lyukba esést ezzel a tárggyal*/
	public boolean canSurvive();
}
