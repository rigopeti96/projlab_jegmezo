package jegmezo;



/** */
public interface Item {
	/** */
	public boolean equip(Inventory inventory);
	/** */
	public boolean unequip(Inventory inventory);
	
	/** */
	public boolean use(Player p);
	
	/** */
	public boolean canSave();
	
	/** */
	public boolean canSurvive();
}
