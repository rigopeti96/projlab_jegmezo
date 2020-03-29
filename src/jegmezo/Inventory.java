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

	/** Ellenőrzni, hogy az adott játékosnál megvan-e az összes győzelmi eszköz (WinItem)
	 *  @param
	 *  @return bool - Megvan-e minden item. */
	public boolean hasAllWinItem() {
		if(getWinItemCount()==3){
			System.out.println("Has All Win Item");
			return true;
		}
		System.out.println("Doesn't have all Win Item");
		return false;
	}

	public int getRopeCount(){
		System.out.println("Van-e nála kötél?");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("has"))
			return 1;
		else{
			return 0;
		}
	}
	public int getFoodCount(){
		System.out.println("Van-e nála étel?");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("has"))
			return 1;
		else{
			return 0;
		}
	}
	public int getShovelCount(){
		System.out.println("Van-e nála ásó?");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("has"))
			return 1;
		else{
			return 0;
		}
	}
	public int getScubaCount(){
		System.out.println("Van-e nála búvárruha?");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("has"))
			return 1;
		else{
			return 0;
		}
	}
	public int getWinItemCount(){
		System.out.println("Van-e nála győzelmi tárgy?");
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
