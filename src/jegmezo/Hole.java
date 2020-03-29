package jegmezo;




/** */
public class Hole extends Tile {
	/** */
	public void stepOnto(Player player, Tile prevTile) {
		System.out.println("\nHole stepOnto\n");
		boolean survive=player.canSurvive();
		if(survive)
			return;
		this.neighbours.add(new IceSheet());
		for (int i=0; i<neighbours.size(); i++){
			boolean save=neighbours.get(i).canSave();
			if(save)
				return;
		}
		player.drown();
	}
	
	/** */
	public int getPlayerLimit() {
		System.out.println("\nHole getPlayerLimit\n");
		return 0;
	}

	/** */
	public Item getItem() {
		System.out.println("\nHole getItem\n");
		return null;
	}
	
	/** */
	public boolean buildIgloo() {
		System.out.println("\nHole buildIgloo\n");
		return false;
	}

	/** */
	public void removeItem() {
		System.out.println("\nHole removeItem\n");
	}
	
	/** */
	public boolean canSave() {
		System.out.println("\nHole canSave\n");
		for (int i=0; i<players.size(); i++) {
			boolean save=players.get(i).canSave();
			if(save)
				return true;
		}
		return false;
	}
	
}
