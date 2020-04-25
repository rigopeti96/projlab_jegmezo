package jegmezo;




/** A lyukat megvalósítítja meg*/
public class Hole extends Tile {
	public Hole(GameController gameController, int id, int snow) {
		super(gameController, id, snow);
	}

	/** A játékos rálép a mezőre*/
	public void stepOnto(Player player, Tile prevTile) {
		System.out.println("Player "+player.getNumber()+" fell into "+this.toLongString());
		if(player.canSurvive()) {
			System.out.println("Player "+player.getNumber()+" had Scuba Gear, survived, went back to the original Sheet");
			return;
		}
		for(int i=0;i<neighbours.size();i++){
			if(neighbours.get(i).canSave()) {
				System.out.println("Someone on "+neighbours.get(i).toShortString()+" has a rope.");
				neighbours.get(i).stepOnto(player, prevTile);
				return;
			}
		}
		System.out.println("No neighbouring players with rope.");
		player.drown();
	}
	
	/** A játékoslétszám lekérdezése
	 * @return játékoslétszám*/
	public int examinePlayerLimit() {
		return 0;
	}

	/** A mezőn található tárgy lekérdezése
	 * @return a mezőn lévő tárgy vagy null, ha nincs*/
	public Item getItem() {
		return null;
	}

	@Override
	public boolean build(Building building) {
		return false;
	}

	/** A mezőn lévő tárgy levétele*/
	public void removeItem() {

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
	public String toLongString() {
		if(!discovered)
			return "Tile(ID="+id+")";
		return "Hole(ID=" + id + ", snow=" + snow + ")";
	}


	public void stepOnPolarBear(PolarBear pb, Tile prevTile){return;}

	@Override
	public void destroyTent(){return;}

	@Override
	public void blizzard(){return;}

}
