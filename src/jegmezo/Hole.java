package jegmezo;




/** A lyukat megvalósítítja meg*/
public class Hole extends Tile {
	/** A játékos rálép a mezőre*/
	public void stepOnto(Player player, Tile prevTile) {
		System.out.println("Hole stepOnto");
		boolean survive=player.canSurvive();
		if(survive)
			return;
		this.neighbours.add(new IceSheet(4));
		for (int i=0; i<neighbours.size(); i++){
			boolean save=neighbours.get(i).canSave();
			if(save)
				return;
		}
		player.drown();
	}
	
	/** A játékoslétszám lekérdezése
	 * @return játékoslétszám*/
	public int getPlayerLimit() {
		System.out.println("Hole getPlayerLimit");
		return 0;
	}

	/** A mezőn található tárgy lekérdezése
	 * @return a mezőn lévő tárgy vagy null, ha nincs*/
	public Item getItem() {
		System.out.println("Hole getItem");
		return null;
	}
	
	/** Lehet-e igloot építeni a mezőre
	 * @return true, ha lehet, false, ha nem*/
	public boolean buildIgloo() {
		System.out.println("Hole buildIgloo");
		return false;
	}

	/** A mezőn lévő tárgy levétele*/
	public void removeItem() {
		System.out.println("Hole removeItem");
	}
	
	/** Van-e olyan játékos a mezőn, aki meg tud menteni másik játékost (van kötele)
	 * @return mindig false, mert a lyukon nem lehet több játékos*/
	public boolean canSave() {
		System.out.println("Hole canSave");
		/*for (int i=0; i<players.size(); i++) {
			boolean save=players.get(i).canSave();
			if(save)
				return true;
		}*/
		return false;
	}
	
}
