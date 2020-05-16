package jegmezo.model;


/** Jégtábla, tárol egy játékoslétszámot ami felet a jégtábla átfordul, van-e rajta igloo, és ha van rajta tárgy akkor azt is tárolja*/
public class IceSheet extends Tile {
	/**
	 * a limit, ami megadja, hogy mennyi játékost bír el a mező
	 */
	private Discoverable<Integer> playerLimit;
	/**
	 * a tárgy, ami a mezőn van
	 */
	private Discoverable<Item> item;
	/**
	 * egy enum, ami azt jelzi, van-e épület építve a mezőre és milyen
	 */
	protected Building building = Building.none;

	/**
	 * Az IceSheet konstruktora, meg lehet adni
	 * @param gameController - a gameControllert
	 * @param id - a mező indexét
	 * @param playerLimit - a limitet, amennyi játékost elbír az IceSheet
	 * @param snow - a mezőn lévő hómennyiség
	 */
	public IceSheet(GameController gameController, int id, int playerLimit, int snow)
	{
		super(gameController, id, snow);
		this.playerLimit = new Discoverable<>(playerLimit);
		this.item = new Discoverable<>(null, "none");
	}

	/** A játékos rálép a jégtáblára, ha több játékos lenne a táblán akkor átfordul*/
	@Override
	public void stepOnto(Player player, Tile prevTile) {
		discovered=true;
		if (prevTile != null) System.out.println("Player "+player.getNumber()+" moved to "+toShortString() + ".");
		if(players.size()==playerLimit.getElement()){
			System.out.println("Sheet "+id+" turned over.\nAll players on it drowned.");
			gameController.gameOver();
			return;
		}
		if(polarBear!=null && (building==Building.none ||building==Building.tent)) {
			player.eaten();
			return;
		}
		players.add(player);
		if (prevTile != null) prevTile.stepOff(player);


		player.movedToTile(this);
		if (snow == 0) item.discover(() -> System.out.println("Found item " + item.toDiscoveredString() + "."));
	}

	/**
	 * Ez a függvény csökkenti a mezőn lévő hómennyiséget
	 * @param amount Hóréteg szám, amennyit le kell szedni
	 * @return ret (boolean) - ez a bool érték jelzi, hogy a hó eltávolítása sikeres volt-e vagy sem
	 */
	@Override
	public boolean removeSnow(int amount) {
		if (snow > 0) System.out.println(" removes " + amount + " snow from " + toShortString() + ".");
		else System.out.println(" can't remove snow from " + toShortString() + ".");
		boolean ret = super.removeSnow(amount);
		if (snow == 0) item.discover(() -> System.out.println("Found item " + item.toDiscoveredString() + "."));
		return ret;
	}

	/** A játékoslétszám lekérdezése
	 * @return játékoslétszám*/
	@Override
	public int examinePlayerLimit() {
		discovered = true;
		playerLimit.discover();
		return playerLimit.getElement();
	}


	/** A mezőn található tárgy lekérdezése
	 * @return a mezőn lévő tárgy vagy null, ha nincs*/
	@Override
	public Item getItem() {
		return item.getElement();
	}

	/**
	 * A mezőn található item beállítása (spawn-nál)
	 * @param item a mezőn lévő tárgy vagy null, ha nincs
	 */
	public void setItem(Item item) {
		this.item.setElement(item);
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
		item.setElement(null);
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
		if(hasBuilding())
			return false;
		this.building = building;
		return true;
	}

	/** A hóvihar lefolytatása*/
	@Override
	public void blizzard(){
		if(this.building == Building.none) {
			for (Player player : super.players) {
				player.decreaseBodyHeat();
			}
		}
		increaseSnow();
	}

	/**
	 * Ellenőrzi, hogy van-e valami építmény a tile-on
	 * @return ha van építmény TRUE egyébként false
	 */
	public boolean hasBuilding() {
		if(this.building != Building.none)
			return true;
		return false;
	}

	/**
	 * Megvalósítja a Tile destroyTent függvényét, ha a mezőn egy TENT egység áll, akkor azt eltörli
	 */
	@Override
	public void destroyTent() {
		if(this.building == Building.tent) {
			this.building = Building.none;
			System.out.println("All tents destroyed.");
		}
	}

	/**
	 * Ez a függvény kezeli le, ha egy medve rálép a mezőre
	 * @param pb A medve
	 * @param prevTile A medve előző Tile-ja
	 */
	public void stepOnPolarBear(PolarBear pb, Tile prevTile) {
		System.out.println("Polar bear moved to "+toShortString() + ".");
		if((building==Building.none ||building==Building.tent)&&!players.isEmpty()){
			for (Player player: players) {
				System.out.println("Player "+player.getNumber()+" was not in an igloo.");
				player.eaten();
			}
			return;
		}
		polarBear=pb;
		polarBear.movedToTile(this);
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
				+ ",item=" + item.toDiscoveredString() + ",itemDiscovered="+ (item.isDiscovered() ? "true" : "false")
				+ ",building=" + building.toString()
				+ ",discovered=" + (discovered ? "true" : "false") + ")");
	}

	/**
	 * Ez a függvény visszaad egy string-et a mező id-jával
	 * @return string - ezt tároló string
	 */
	@Override
	public String toShortString() {	return "Sheet(ID=" + id + ")";	}

	/**
	 * Ez a függvény visszaad egy string-et a mező id-jával, a rajta levő hó mennyiségével, a limitjével, a rajta levő tárggyal és a rajta levő épülettel
	 * @return string - ezeket az adatokat tároló string
	 */
	@Override
	public String toLongString() {
		if(!discovered)
			return "Tile(ID="+id+")";
		return "Sheet(ID=" + id + ",snow=" + snow + ",limit=" + playerLimit +
				",item=" + item.toString() + ",building=" + building.toString() + ")";
	}

	@Override
	public String getDescription() {
		if(!discovered)
			return "Tile(ID="+id+")";
		return "Sheet(ID=" + id + "\nsnow=" + snow + "\nlimit=" + playerLimit +
				"\nitem=" + item.toString() + "\nbuilding=" + building.toString() + ")";
	}
}
