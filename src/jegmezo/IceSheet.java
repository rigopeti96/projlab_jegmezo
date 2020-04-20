package jegmezo;


import java.util.Scanner;

/** Jégtábla, tárol egy játékoslétszámot ami felet a jégtábla átfordul, van-e rajta igloo, és ha van rajta tárgy akkor azt is tárolja*/
public class IceSheet extends Tile {

	private int playerLimit;
	private Item item;
	protected Building building = Building.NONE;

	public IceSheet(GameController gameController, int playerLimit) {
		this(gameController, playerLimit, null);
	}

	public IceSheet(GameController gameController, int playerLimit, Item item) {
		super(gameController);
		this.playerLimit = playerLimit;
		this.item = item;
	}

	/** A játékos rálép a jégtáblára, ha több játékos lenne a táblán akkor átfordul*/
	@Override
	public void stepOnto(Player player, Tile prevTile) {
		System.out.println("IceSheet stepOnto");
		System.out.println("stable/unstable (turns over)?");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("unstable")){
			player.drown();
		}
		else{
			prevTile.stepOff(player);
			this.entities.add(player);
		}
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
		addPlayer(new Eskimo(gameController, 1, this));
		for (int i=0; i<entities.size(); i++) {
			boolean save=entities.get(i).canSave();
			if(save)
				return true;
		}
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
				for (Entity entity: entities) {
					entity.decreaseBodyHeat();
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

	}

	public void serialize() {

	}

}
