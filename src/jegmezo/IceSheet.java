package jegmezo;


import java.util.Scanner;

/** Jégtábla, tárol egy játékoslétszámot ami felet a jégtábla átfordul, van-e rajta igloo, és ha van rajta tárgy akkor azt is tárolja*/
public class IceSheet extends Tile {

	private int playerLimit;
	private boolean hasIgloo;
	private Item item;

	public IceSheet(int playerLimit) {
		this(playerLimit, null);
	}

	public IceSheet(int playerLimit, Item item) {
		this.playerLimit = playerLimit;
		this.item = item;
	}

	/** A játékos rálép a jégtáblára, ha több játékos lenne a táblán akkor átfordul*/
	@Override
	public void stepOnto(Player player, Tile prevTile) {
		System.out.println("IceSheet stepOnto\n");
		System.out.println("Átfordul-e\n1:Igen\n2.Nem");
		String choice=new Scanner(System.in).nextLine();
		if(choice.equals("1")){
			player.drown();
		}
		else{
			prevTile.stepOff(player);
			//this.players.add(player);
		}
	}

	/** A játékoslétszám lekérdezése
	 * @return játékoslétszám*/
	@Override
	public int getPlayerLimit() {
		System.out.println("IceSheet getPlayerLimit\n");
		return playerLimit;
	}


	/** A mezőn található tárgy lekérdezése
	 * @return a mezőn lévő tárgy vagy null, ha nincs*/
	@Override
	public Item getItem() {
		System.out.println("IceSheet getItem\n");
		return item;
	}

	/** A mezőn lévő tárgy levétele*/
	@Override
	public void removeItem() {
		System.out.println("IceSheet removeItem\n");
		item=null;
	}

	/** Van-e olyan játékos a mezőn, aki meg tud menteni másik játékost (van kötele)
	 * @return true-t ad, ha van, false-t ha nincs*/
	@Override
	public boolean canSave() {
		System.out.println("IceSheet canSave\n");
		return false;
	}

	/** A mezőre épül igloo
	 * @return true, ha sikeres volt, false, ha nem*/
	@Override
	public boolean buildIgloo(){
		System.out.println("IceSheet buildIgloo\n");
		if(!hasIgloo) {
			hasIgloo=true;
			System.out.println("Épült igloo\n");
			return true;
		}
		System.out.println("Nem épült igloo\n");
		return false;
	}

	/** A hóvihar lefolytatása*/
	@Override
	public void blizzard(){
		System.out.println("IceSheet blizzard\n");
	}

}
