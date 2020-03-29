package jegmezo;

/**Minden játékoshoz tartozik egy Inventory, ebben van eltárolva, hogy melyik tárgyból mennyi van. A WinItemeken kívül mindenből csak egy lehet egy játékosnál */
public class Inventory {
	private int countShovel = 0;
	private int countFood = 0;
	private int countRope = 0;
	private int countScubaGear = 0;
	private int countWinItem = 0;

	public Inventory(){
		countShovel = 0;
		countFood = 0;
		countRope = 0;
		countScubaGear = 0;
		countWinItem = 0;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipFood() {
		return true;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipWinItem() {
		return true;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipShovel() {
		return true;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipRope() {
		return true;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipScubaGear() {
		return true;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean unequipFood() {
		return true;
	}

	/** Csökkenti a Win Item-ek számát
	 *  @param
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat) */
	public boolean unequipWinItem() {
		return true;
	}

	/** Csökkenti a lapátok(Shovel) számát
	 *  @param
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat) */
	public boolean unequipShovel() {
		return true;
	}

	/** Csökkenti a kötelek (Rope) számát
	 *  @param
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat) */
	public boolean unequipRope() {
		return true;
	}

	/** Csökkenti a búvárruhát (ScubaGear) számát
	 *  @param
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat) */
	public boolean unequipScubaGear() {
		return true;
	}

	/** Csökkenti a búvárruhát (ScubaGear) számát
	 *  @param
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat) */
	public Item selectItem() {
		return null;
	}

	/** Ellenőrzni, hogy az adott játékosnál megvan-e az összes győzelmi eszköz (WinItem)
	 *  @param
	 *  @return bool - Megvan-e minden item. */
	public boolean hasAllWinItem() {
		if(countWinItem==3){
			System.out.println("\nHas All Win Item\n");
			return true;
		}
		System.out.println("\nDoesn't have all Win Item\n");
		return false;
	}
}
