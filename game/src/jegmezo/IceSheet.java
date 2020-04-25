package jegmezo;


import java.util.Scanner;

/** Jégtábla, tárol egy játékoslétszámot ami felet a jégtábla átfordul, van-e rajta igloo, és ha van rajta tárgy akkor azt is tárolja*/
public class IceSheet extends Tile {

	private Discoverable<Integer> playerLimit;
	private Discoverable<Item> item;
	protected Building building = Building.NONE;

	public IceSheet(GameController gameController, int id, int playerLimit, int snow)
	{
		super(gameController, id, snow);
		this.playerLimit = new Discoverable<>(playerLimit);
	}

	/** A játékos rálép a jégtáblára, ha több játékos lenne a táblán akkor átfordul*/
	@Override
	public void stepOnto(Player player, Tile prevTile) {
		System.out.println("Player "+player.getNumber()+" moved to "+toLongString());
		if(players.size()==playerLimit.getElement()){
			System.out.println("Sheet "+id+" turned over.\nAll players on it drowned.");
			gameController.gameOver();
			return;
		}
		if(polarBear!=null && (building==Building.NONE||building==Building.TENT)){
			player.eaten();
			return;
		}

		players.add(player);
		prevTile.stepOff(player);


		if (snow == 0) item.discover(() -> System.out.println("Found item " + item.getElement().toString()));
	}

	@Override
	public boolean removeSnow(int amount) {
		boolean ret = super.removeSnow(amount);
		if (snow == 0) item.discover(() -> System.out.println("Found item " + item.getElement().toString()));
		return ret;
	}

	/** A játékoslétszám lekérdezése
	 * @return játékoslétszám*/
	@Override
	public int examinePlayerLimit() {
		playerLimit.discover();
		return playerLimit.getElement();
	}


	/** A mezőn található tárgy lekérdezése
	 * @return a mezőn lévő tárgy vagy null, ha nincs*/
	@Override
	public Item getItem() {
		System.out.println("IceSheet getItem");
		return item.getElement();
	}

	/**
	 * A mezőn található item beállítása (spawn-nál)
	 * @param item a mezőn lévő tárgy vagy null, ha nincs
	 */
	public void setItem(Item item) {
		this.item = new Discoverable<>(item, "none");
	}

	/**
	 * Felfedezi a mezőn található item-et
	 */
	public void discoverItem() {
		item.discover();
	}

	/**
	 * Felfedezi a mező playerLimit-jét
	 */
	public void discoverPlayerLimit() {
		playerLimit.discover();
	}

	/** A mezőn lévő tárgy levétele*/
	@Override
	public void removeItem() {
		System.out.println("IceSheet removeItem");
		item=null;
	}

	/** Van-e olyan játékos a mezőn, aki meg tud menteni másik játékost (van kötele)
	 * @return true-t ad, ha van, false-t ha nincs*/
	@Override
	public boolean canSave() {
		for(int i=0;i<players.size();i++){
			if(players.get(i).canSave())
				return true;
		}
		return false;
	}

	/** A mezőre épül egy épület
	 * @return true, ha sikeres volt, false, ha nem*/
	@Override
	public boolean build(Building building){
		if(!hasBuilding())
			return false;
		this.building = building;
		System.out.println();
		return true;
	}

	/** A hóvihar lefolytatása*/
	@Override
	public void blizzard(){
		super.blizzard();
		if(this.building == Building.NONE) {
			for (Player player : super.players) {
				player.decreaseBodyHeat();
			}
		}
	}

	public boolean hasBuilding() {
		if(this.building != Building.NONE)
			return true;
		return false;
	}

	@Override
	public void destroyTent() {
		if(this.building == Building.TENT) {
			this.building = Building.NONE;
			System.out.println("All tents destroyed");
		}
	}

	public void stepOnPolarBear(PolarBear pb, Tile prevTile) {
		if((building==Building.NONE||building==Building.TENT)&&!players.isEmpty()){
			for (Player player: players) {
				player.eaten();
			}
			return;
		}
		polarBear=pb;
		prevTile.steOffPolarBear();

	}

	/**
	 * Kiírja a Tile egy reprezentációját a standard outputra
	 */
	@Override
	public void serialize() {
		System.out.println("Sheet(ID=" + id
				+ ",playerLimit=" + playerLimit.toDiscoveredString()
				+ ",playerLimitDiscovered=" + (playerLimit.isDiscovered() ? "true" : "false")
				+ ",snow=" + snow
				+ ",item=" + item.toDiscoveredString() + ",itemDiscovered="+ (item.isDiscovered() ? "true" : "false") + ")");
	}

	@Override
	public String toShortString() {	return "Sheet(ID=" + id + ")";	}

	@Override
	public String toLongString() {
		return "Sheet(ID=" + id + ", snow=" + snow + ", limit=" + playerLimit +
				", item=" + item.toString() + ", building=" + building.toString() + ")";
	}
}
