package jegmezo;


import java.util.Scanner;

/** Jégtábla, tárol egy játékoslétszámot ami felet a jégtábla átfordul, van-e rajta igloo, és ha van rajta tárgy akkor azt is tárolja*/
public class IceSheet extends Tile {

	private int playerLimit;
	private Item item;
	protected Building building = Building.NONE;

	public IceSheet(GameController gameController, int id, int playerLimit, int snow)
	{
		super(gameController, id, snow);
		this.playerLimit = playerLimit;
	}

	/** A játékos rálép a jégtáblára, ha több játékos lenne a táblán akkor átfordul*/
	@Override
	public void stepOnto(Player player, Tile prevTile) {
		if(players.size()==playerLimit){
			player.drown();
			return;
		}
		if(polarBear!=null && (building==Building.NONE||building==Building.TENT)){
			player.eaten();
			return;
		}
		players.add(player);
		prevTile.stepOff(player);

		// TODO: Item felfedezés, ha még nincs felfedezve

	}

	/** A játékoslétszám lekérdezése
	 * @return játékoslétszám*/
	@Override
	public int getPlayerLimit() {
		System.out.println("IceSheet getPlayerLimit");
		return playerLimit;
	}


	/** A mezőn található tárgy lekérdezése
	 * @return a mezőn lévő tárgy vagy null, ha nincs*/
	@Override
	public Item getItem() {
		System.out.println("IceSheet getItem");
		return item;
	}

	/**
	 * A mezőn található item beállítása (spawn-nál)
	 * @param item a mezőn lévő tárgy vagy null, ha nincs
	 */
	public void setItem(Item item) {
		this.item = item;
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
		System.out.println("Sheet(ID=" + id + ",playerLimit=" + playerLimit + ",snow=" + snow + ",item=" + (item != null ? item.getName() : "none") + ")");
	}

	@Override
	public void toShortString() {
		System.out.println("Sheet(ID=" + id + ")");
	}

	@Override
	public void toLongString() {

		String itemname= (snow == 0 ? (item != null ? item.getName() : "none")  : "?");
		String buildingname= building.toString();

		System.out.println("Sheet(ID=" + id + ", snow=" + snow + ", limit=" + playerLimit +
				", item=" + itemname + ", building=" + buildingname + ")" );
	}
}
