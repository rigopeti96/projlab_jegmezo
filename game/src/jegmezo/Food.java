package jegmezo;

/** Ha a Player ezt használja, akkor egyel több testhője lesz.
 * Implementálja az Item interfészt. */
public class Food implements Item {

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		return inventory.equipFood(this);
	}

	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		return inventory.unequipFood(this);
	}

	/** a Player használja a tárgyat
	 * @param player Player, aki használja a tárgyat
	 * @return true ha sikeres, false ha nem */
	public boolean use(Player player) {
		System.out.println("Food use");
		player.increaseBodyHeat();
		return unequip(player.getInventory());
	}

	/** megnézi, hogy meg lehet-e menteni csapattársát ezzel a tárggyal */
	public boolean canSave(){
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
	@Override
	public String getName() {
		return "food";
	}
}
