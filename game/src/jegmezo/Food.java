package jegmezo;

/** Ha a Player ezt használja, akkor egyel több testhője lesz.
 * Implementálja az Item interfészt. */
public class Food implements Item {

	/** beteszi egy Player Inventory-jába magát */
	public boolean equip(Inventory inventory){
		System.out.println("Food equip");
		return inventory.equipFood();
	}

	/** kiveszi egy Player Inventory-jából magát */
	public boolean unequip(Inventory inventory){
		System.out.println("Food unequip");
		return inventory.unequipFood();
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


}
