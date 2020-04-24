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
		System.out.println("IceSheet canSave");
		return false;
	}

	/** A mezőre épül egy épület
	 * @return true, ha sikeres volt, false, ha nem*/
	@Override
	public boolean build(Building building){
		System.out.println("IceSheet buildSthing");
		return true;
	}

	/** A hóvihar lefolytatása*/
	@Override
	public void blizzard(){
		super.blizzard();
		System.out.println("IceSheet blizzard");
		System.out.println("Has igloo? (igloo/igloon't)");
		switch (new Scanner(System.in).nextLine()) {
			case "igloo":
				break;
			case "igloon't":
				for (Player player: players) {
					player.decreaseBodyHeat();
				}
				break;
		}
	}

	public boolean hasBuilding() {

		return false;
	}

	public void destroyTent() {

	}

	public void stepOnPolarBear(PolarBear pb, Tile prevTile) {
		if((building==Building.NONE||building==Building.TENT)&&!players.isEmpty()){
			for (Player player: players) {
				player.eaten();
			}
			return;
		}
		polarBear=pb;
		prevTile.stepOff(pb);

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
