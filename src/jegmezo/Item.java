package jegmezo;

/** Fel lehet venni, amivel be�ll�tja a Player Inventory-j�t, 
 * el lehet dobni/elhaszn�lni, amit szint�n be�ll�tja az Inventory-t lehet haszn�lni,
 *  illetve megmondja mag�r�l, hogy meg lehet-e vele menteni m�sokat/magadat. */
public interface Item {
	
	/** beteszi egy Player Inventory-j�ba mag�t */
	public boolean equip(Inventory inventory);
	
	/** kiveszi egy Player Inventory-j�b�l mag�t */
	public boolean unequip(Inventory inventory);
	
	/** a Player haszn�lja a t�rgyat */
	public boolean use(Player p);
	
	/** megn�zi, hogy meg lehet-e menteni csapatt�rs�t ezzel a t�rggyal */
	public boolean canSave();
	
	/** megn�zi, hogy t�l lehet-e �lni a lyukba es�st ezzel a t�rggyal*/
	public boolean canSurvive();
}
