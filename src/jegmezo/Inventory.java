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

	/** Növeli egyel a Win Itemek számát*/
	public boolean equipWinItem() {
		return true;
	}
	
	/** */
	public boolean equipShovel() {
		switch (System.console().readLine()) {
			case "can":
				return true;
			case "can't":
				return false;
		}

		return false;
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
	
	/** Csökkenti a Win Item-ek számát */
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

	/** Egyelőre null a visszatérési értéke, ez majd változni fog*/
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
