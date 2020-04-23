package jegmezo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**Minden játékoshoz tartozik egy Inventory, ebben van eltárolva, hogy melyik tárgyból mennyi van. A WinItemeken kívül mindenből csak egy lehet egy játékosnál */
public class Inventory {
	private List<Item> items = new ArrayList<>();
	private int countShovel;
	private int countFood;
	private int countRope;
	private int countScubaGear;
	private int countWinItem;
	private int countBreakableShovel;
	private int countTent;

	public Inventory(){
		countShovel = 0;
		countFood = 0;
		countRope = 0;
		countScubaGear = 0;
		countWinItem = 0;
		countBreakableShovel= 0;
		countTent = 0;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param item Item, amit felveszünk
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipFood(Food item) {
		countFood++;
		return true;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param item Item, amit felveszünk
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipWinItem(WinItem item) {
		items.add(item);
		countWinItem++;
		return true;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param item Item, amit felveszünk
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipShovel(Shovel item) {
		if (countShovel > 0 || countBreakableShovel > 0) return false;
		items.add(item);
		countWinItem++;
		return true;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param item Item, amit felveszünk
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipRope(Item item) {
		if (countRope > 0) return false;
		items.add(item);
		countRope++;
		return true;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipScubaGear(Item item) {
		if (countScubaGear > 0) return false;
		items.add(item);
		countScubaGear++;
		return true;
	}

	public boolean equipBreakableShovel(Item item) {
		if (countShovel > 0 || countBreakableShovel > 0) return false;
		items.add(item);
		countBreakableShovel++;
		return true;
	}

	public boolean equipTent(Item item) {
		System.out.println("Inventory equipTent");
		System.out.println("Can/can't?");
		switch (new Scanner(System.in).nextLine()) {
			case "can":
				return true;
			case "can't":
				return false;
		}
		return false;
	}

	/** Csökkenti a Food-ok számát
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat)  */
	public boolean unequipFood(Item item) {
		if (countFood < 1) return false;
		countFood--;
		return true;
	}

	/** Csökkenti a Win Item-ek számát
	 *  @param
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat) */
	public boolean unequipWinItem(Item item) {
		if (countWinItem < 1) return false;
		items.remove(item);
		countWinItem--;
		return true;
	}

	/** Csökkenti a lapátok(Shovel) számát
	 *  @param
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat) */
	public boolean unequipShovel(Item item) {
		if (countShovel < 1) return false;
		items.remove(item);
		countShovel--;
		return true;
	}

	/** Csökkenti a kötelek (Rope) számát
	 *  @param
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat) */
	public boolean unequipRope(Item item) {
		if (countRope < 1) return false;
		items.remove(item);
		countRope--;
		return true;
	}

	/** Csökkenti a búvárruhát (ScubaGear) számát
	 *  @param
	 *  @return bool - Sikerült-e átadni/használni az itemet (sikeres használat) */
	public boolean unequipScubaGear(Item item) {
		if (countScubaGear < 1) return false;
		items.remove(item);
		countScubaGear--;
		return true;
	}

	public boolean unequipBreakableShovel(Item item) {
		if (countBreakableShovel < 1) return false;
		items.remove(item);
		countBreakableShovel--;
		return true;
	}

	public boolean unequipTent(Item item) {
		if (countTent < 1) return false;
		items.remove(item);
		countTent--;
		return true;
	}

	public void serialize(int number) {
		if (countFood > 0) {
			System.out.println("Player-Item(number=" + number + ",item=food,count=" + countFood +")");
		}
		for (Item item: items) {
			System.out.println("Player-Item(number=" + number + ",item=" + item.getName() + ",count=1)");
		}
	}

	public Item selectItem() {
		return null;
	}

	/** Visszaadja a kötél (Rope) számát
	 *  @return int - az inventoryban levő kötelek száma */
	public int getRopeCount(){
		System.out.println("Has rope? (has/hasn't)");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("has"))
			return 1;
		else{
			return 0;
		}
	}

	/** Visszaadja a búvárruha (ScubaGear) számát
	 *  @return int - az inventoryban levő búvárruhák száma */
	public int getScubaCount(){
		System.out.println("Has ScubaGear? (has/hasn't)");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("has"))
			return 1;
		else{
			return 0;
		}
	}
	/** Visszaadja a győzelmitárgyak (WinItem) számát
	 *  @return int - az inventoryban levő győzelmitárgyak száma */
	public int getWinItemCount(){
		System.out.println("Has Win Item? (has three/has two/has one/has none)");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("has three"))
			return 3;
		if(choice.equals("has two"))
			return 2;
		if(choice.equals("has one"))
			return 1;
		else{
			return 0;
		}
	}
}
