package jegmezo;

import java.util.Scanner;

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
		System.out.println("Inventory equipFood");
		System.out.println("Can/can't?");
		switch (new Scanner(System.in).nextLine()) {
			case "can":
				return true;
			case "can't":
				return false;
		}
		return false;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipWinItem() {
		System.out.println("Inventory equipWinItem");
		System.out.println("Can/can't?");
		switch (new Scanner(System.in).nextLine()) {
			case "can":
				return true;
			case "can't":
				return false;
		}
		return false;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipShovel() {
		System.out.println("Inventory equipShovel");
		System.out.println("Can/can't?");
		switch (new Scanner(System.in).nextLine()) {
			case "can":
				return true;
			case "can't":
				return false;
		}
		return false;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipRope() {
		System.out.println("Inventory equipRope");
		System.out.println("Can/can't?");
		switch (new Scanner(System.in).nextLine()) {
			case "can":
				return true;
			case "can't":
				return false;
		}
		return false;
	}

	/** Növeli eggyel a Win Itemek számát
	 *  @param
	 *  @return bool - Sikerült-e felvenni az itemet  */
	public boolean equipScubaGear() {
		System.out.println("Inventory equipScubaGear");
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
		System.out.println("Inventory unequipShovel");
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

	/** Visszaadja az étel (Food) számát
	 *  @return int - az inventoryban levő ételek száma */
	public int getFoodCount(){
		System.out.println("Has Food? (has/hasn't)");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("has"))
			return 1;
		else{
			return 0;
		}
	}
	/** Visszaadja az ásó (Shovel) számát
	 *  @return int - az inventoryban levő ásók száma */
	public int getShovelCount(){
		System.out.println("Has Shovel? (has/hasn't)");
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
