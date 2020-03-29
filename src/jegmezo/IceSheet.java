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


	@Override
	public Item getItem() {
		System.out.println("\nIceSheet getItem\n");
		return item;
	}

	@Override
	public void removeItem() {
		System.out.println("\nIceSheet removeItem\n");
		item=null;
	}

	@Override
	public boolean canSave() {
		System.out.println("\nIceSheet canSave\n");
		return false;
	}

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

	@Override
	public void blizzard(){
		System.out.println("\nIceSheet blizzard\n");
	}

}
