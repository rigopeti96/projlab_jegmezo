package jegmezo;

/** */
public class Inventory {
	/** */
	private int countShovel = 0;
	
	/** */
	private int countFood = 0;
	
	/** */
	private int countRope = 0;
	
	/** */
	private int countScubaGear = 0;
	
	/** */
	private int countWinItem = 0;

	
	
	/** */
	public boolean equipFood() {
		return true;
	}

	public Inventory(){
		countShovel = 0;
		countFood = 0;
		countRope = 0;
		countScubaGear = 0;
		countWinItem = 0;
	}

	/** N�veli egyel a Win Itemek sz�m�t*/
	public boolean equipWinItem() {
		return true;
	}
	
	/** */
	public boolean equipShovel() {
		return true;
	}
	
	/** */
	public boolean equipRope() {
		return true;
	}
	
	/** */
	public boolean equipScubaGear() {
		return true;
	}
	
	/** */
	public boolean unequipFood() {
		return true;
	}
	
	/** Cs�kkenti a Win Item-ek sz�m�t */
	public boolean unequipWinItem() {
		return true;
	}
	
	/** */
	public boolean unequipShovel() {
		return true;
	}
	
	/** */
	public boolean unequipRope() {
		return true;
	}
	
	/** */
	public boolean unequipScubaGear() {
		return true;
	}

	/** Egyel?re null a visszat�r�si �rt�ke, ez majd v�ltozni fog*/
	public Item selectItem() {
		return null;
	}

	/** */
	public boolean hasAllWinItem() {
		if(countWinItem==3){
			System.out.println("\nHas All Win Item\n");
			return true;
		}
		System.out.println("\nDoesn't have all Win Item\n");
		return false;
	}
}
