package jegmezo;




/** Jégtábla, tárol egy játékoslétszámot ami felet a jégtábla átfordul, van-e rajta igloo, és ha van rajta tárgy akkor azt is tárolja*/
public class IceSheet extends Tile {

	private int playerLimit;

	private boolean hasIgloo;

	private Item item;

	/** A játékos rálép a jégtáblára, ha több játékos lenne a táblán akkor átfordul*/
	@Override
	public void stepOnto(Player player, Tile prevTile) {
		System.out.println("\nIceSheet stepOnto\n");
		System.out.println("\nÁtfordul-e\n1:Igen\n2.Nem");
		String choice=System.console().readLine();
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
		System.out.println("\nIceSheet getPlayerLimit\n");
		return playerLimit;
	}


	/** A mezőn található tárgy lekérdezése
	 * @return a mezőn lévő tárgy vagy null, ha nincs*/
	@Override
	public Item getItem() {
		System.out.println("\nIceSheet getItem\n");
		return item;
	}

	/** A mezőn lévő tárgy levétele*/
	@Override
	public void removeItem() {
		System.out.println("\nIceSheet removeItem\n");
		item=null;
	}

	/** Van-e olyan játékos a mezőn, aki meg tud menteni másik játékost (van kötele)
	 * @return true-t ad, ha van, false-t ha nincs*/
	@Override
	public boolean canSave() {
		System.out.println("\nIceSheet canSave\n");
		return false;
	}

	/** A mezőre épül igloo
	 * @return true, ha sikeres volt, false, ha nem*/
	@Override
	public boolean buildIgloo(){
		System.out.println("\nIceSheet buildIgloo\n");
		if(!hasIgloo) {
			hasIgloo=true;
			System.out.println("\nÉpült igloo\n");
			return true;
		}
		System.out.println("\nNem épült igloo\n");
		return false;
	}

	/** A hóvihar lefolytatása*/
	@Override
	public void blizzard(){
		System.out.println("\nIceSheet blizzard\n");
	}

}
