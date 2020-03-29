package jegmezo;




/** A lyukat megvalósítítja meg*/
public class Hole extends Tile {
	public Hole(GameController gameController) {
		super(gameController);
	}

	/** A játékos rálép a mezőre*/
	public void stepOnto(Player player, Tile prevTile) {
		System.out.println("Hole stepOnto");
		if (player.canSurvive()) return;
		this.neighbours.add(new IceSheet(gameController, 4));
		for (int i=0; i<neighbours.size(); i++){
			if (neighbours.get(i).canSave()) {
				neighbours.get(i).stepOnto(player,prevTile);
			}
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
		return false;
	}
	
}
