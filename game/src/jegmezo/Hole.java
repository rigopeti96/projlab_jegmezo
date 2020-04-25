package jegmezo;




/** A lyukat megvalósítítja meg*/
public class Hole extends Tile {
	public Hole(GameController gameController, int id, int snow) {
		super(gameController, id, snow);
	}

	/** A játékos rálép a mezőre*/
	public void stepOnto(Player player, Tile prevTile) {
		if(player.canSurvive())
			return;
		for(int i=0;i<neighbours.size();i++){
			if(neighbours.get(i).canSave()) {
				neighbours.get(i).stepOnto(player, prevTile);
				return;
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

	@Override
	public boolean build(Building building) {
		return false;
	}

	/** A mezőn lévő tárgy levétele*/
	public void removeItem() {
		System.out.println("Hole removeItem");
	}
	
	/** Van-e olyan játékos a mezőn, aki meg tud menteni másik játékost (van kötele)
	 * @return mindig false, mert a lyukon nem lehet több játékos*/
	public boolean canSave() {
		return false;
	}

	/**
	 * Kiírja a Tile egy reprezentációját a standard outputra
	 */
	@Override
	public void serialize() {
		System.out.println("Hole(ID=" + id + ",snow=" + snow + ")");
	}

	@Override
	public String toShortString() { return "Hole(ID=" + id + ")"; }

	@Override
	public String toLongString() {	return "Hole(ID=" + id + ", snow=" + snow + ")"; }

	public void stepOnPolarBear(PolarBear pb,Tile prevTile){return;}

	@Override
	public void turnEnd() {
		//a lyuk nem csinál semmit a kör végén
	}

}
