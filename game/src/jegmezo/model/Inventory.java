package jegmezo.model;

import jegmezo.controller.GameController;

import java.util.ArrayList;
import java.util.List;

/**Minden játékoshoz tartozik egy Inventory, ebben van eltárolva, hogy melyik tárgyból mennyi van. A WinItemeken kívül mindenből csak egy lehet egy játékosnál */
public class Inventory {
	/**
	 * a gameController
	 */
	private GameController gameController;
	/**
	 * a játékos tárgyait tároló list
	 */
	private List<Item> items = new ArrayList<>();
	/**
	 * a játékos shovel-einek a száma
	 */
	private int countShovel;
	/**
	 * a játékos ételeinek a száma
	 */
	private int countFood;
	/**
	 * a játékos köteleinek a száma
	 */
	private int countRope;
	/**
	 * a játékos búvárruháinak száma
	 */
	private int countScubaGear;
	/**
	 * a játékos győzelmi tárgyainka száma
	 */
	private int countWinItem;
	/**
	 * a játékos törékeny ásóinak száma
	 */
	private int countBreakableShovel;
	/**
	 * a játékos sátrainak száma
	 */
	private int countTent;

	/**
	 * @param gameController GameController, ami scanner DI-ja miatt kell
	 */
	public Inventory(GameController gameController){
		this.gameController = gameController;
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
		countShovel++;
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

	/** Növeli eggyel a ScubaGear-ek számát
	 *  @param item - ScubaGear item
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipScubaGear(Item item) {
		if (countScubaGear > 0) return false;
		items.add(item);
		countScubaGear++;
		return true;
	}

	/**
	 * Ez a függvény veszi fel a játékos tárgyai közé a törékeny ásót, ha a játékos felvesz egy törékeny ásót
	 * @param item - a törékeny ásó
	 * @return bool - azt jelzi, hogy a tárgy felvétele sikeres volt-e
	 */
	public boolean equipBreakableShovel(Item item) {
		if (countShovel > 0 || countBreakableShovel > 0) return false;
		items.add(item);
		countBreakableShovel++;
		return true;
	}

	/** Növeli egyel a Tent-ek számát és felveszi a Tent-et az item-ek közé
	 * @param item - maga a Tent
	 * @return bool - Sikerült-e felvenni az itemet. */
	public boolean equipTent(Item item) {
		if (countTent > 0) return false;
		items.add(item);
		countTent++;
		return true;
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
	 *  @param item - a ScubaGear
	 *  @return bool - Sikerült-e kivenni az itemet az Inventory-ból. */
	public boolean unequipScubaGear(Item item) {
		if (countScubaGear < 1) return false;
		items.remove(item);
		countScubaGear--;
		return true;
	}

	/** Csökkenti a BreakableShovel-ek számát
	 * @param item - BreakableShovel
	 * @return bool - Sikerült-e kivenni az itemet az Inventory-ból. */
	public boolean unequipBreakableShovel(Item item) {
		if (countBreakableShovel < 1) return false;
		items.remove(item);
		countBreakableShovel--;
		return true;
	}

	/** Csökkenti a Tent-ek számát
	 * @param item - a Tent
	 * @return bool - Sikerült-e kivenni az itemet az Inventory-ból. */
	public boolean unequipTent(Item item) {
		if (countTent < 1) return false;
		items.remove(item);
		countTent--;
		return true;
	}

	/**
	 * Ez a függvény kiírja a játékos tárgyait
	 * @param number - a játékos száma
	 */
	public void serialize(int number) {
		if (countFood > 0) {
			System.out.println("Player-Item(number=" + number + ",item=food,count=" + countFood +")");
		}
		for (Item item: items) {
			System.out.println("Player-Item(number=" + number + ",item=" + item.getName() + ",count=1)");
		}
	}

	/** Visszaadja a kötél (Rope) számát
	 *  @return int - az inventoryban levő kötelek száma */
	public int getRopeCount(){
		return countRope;
	}

	/** Visszaadja a búvárruha (ScubaGear) számát
	 *  @return int - az inventoryban levő búvárruhák száma */
	public int getScubaCount(){
		return countScubaGear;
	}
	/** Visszaadja a győzelmitárgyak (WinItem) számát
	 *  @return int - az inventoryban levő győzelmitárgyak száma */
	public int getWinItemCount(){
		return countWinItem;
	}

	public int getTentCount(){return countTent;}
	public int getShovelCount(){return countShovel;}
	public int getBreakableShovelCount(){return countBreakableShovel;}
	public int getFoodCount(){return countFood;}
	public Item getItem(String name){
		for (Item item: items) {
			if (item.getName().equals(name)) return item;
		}
		return null;
	}
}
