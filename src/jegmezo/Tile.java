package jegmezo;


import java.util.*;

/** Mez?, amire a j�t�kosok l�phetnek. H�r�teg ker�lhet r�, lehet rajta t�rgy, iglu illetve j�t�kosok. H�vihar hat�ssal lehet a mez?kre, ezen k�v�l minden viselked�sta
 *  specializ�lt oszt�lyok kezelnek le. T�rolja a szomsz�dait �s a rajta �ll� Player-eket.*/
public abstract class Tile {
	private int snow;
	protected List<Tile> neighbours;
	protected List<Player> players;
	
	/** A Player h�vja ,amikor r�l�p a Tile-re, hozz�adja a Player-t a players list�hoz
	 * @param player Player, amelyik r�l�pett a mez?re
	 * @param prevTile Az el?z? mez?, amin a Player �llt
	 * */
	public abstract void stepOnto(Player player, Tile prevTile);

	/** A Player h�vja, amikor lel�p a Tile-r?l, kiszedi a Player-t a players list�b�l
	 * @param player Player, amelyik lel�pett a mez?r?l
	 * */
	public void stepOff(Player player) {
		System.out.println("\nTile stepOff\n");
	}

	/**
	 * @return A Tile-on �ll� maximum Player sz�m, amit ut�n �tfordul/Player-ek beleesnek
	 */
	public abstract int getPlayerLimit();

	/**
	 * @return Visszaadja a Tile szomsz�djait
	 */
	public Tile getNeighbours() {
		System.out.println("\nTile getNeighbours\n");
		System.out.println("\nMilyen Tile-ra l�pjen?\n1:IceSheet\n2:Hole");
		String choice =System.console().readLine();
		if (choice.equals("2")) {
			return new Hole();
		}
		else {
			return new IceSheet();
		}
	}

	/**
	 * @return Visszadja a Tile-on l�v? Item-et
	 */
	public abstract Item getItem();

	/**
	 * H�vihar hat�sa az adott Tile-re, plusz h�r�teg sorsol�sa �s a Player-ek megfagyaszt�sa (testh? cs�kkent�se eggyel)
	 */
	public void blizzard() {
		System.out.println("\nTile blizzard\n");
	}

	/**
	 * Leszed egy bizonyos mennyis�g? h�r�teget a Tile-r�l
	 * @param amount H�r�teg sz�m, amennyit le kell szedni
	 * @return true-t ad vissza, ha volt ak�r egy h�r�teg is, amit leszedett
	 */
	public boolean removeSnow(int amount) {
		System.out.println("\nTile removeSnow " + amount + "\n");
		return false;
	}

	/**
	 * Eskimo h�vja iglut �p�t a mez?re,
	 * @return true has siker�lt false ha nem
	 */
	public abstract boolean buildIgloo();

	/**
	 * Leszedi a Tile-r?l a rajta l�v? Item-et
	 */
	public abstract void removeItem();

	/**
	 * Ezen a Tile-on van e olyan Player, aki megtud menteni valakit (van k�tele)
	 * @return Van true, nincs false
	 */
	public abstract boolean canSave();

	/**
	 * N�veli a Tile-on l�v? h�mennyis�get 1-gyel.
	 */
	public void increaseSnow() {
		System.out.println("\nTile removeSnow\n");
		snow++;
	}

	/**
	 * Egy Player-t lehet vele kiv�lasztani (el?z?h�z egy kiv�laszt� men�t)
	 * @return Player amit v�lasztottak
	 */
	public Player selectPlayer() {
		System.out.println("\nTile selectPlayer\n");
		return null;
	}

	/**
	 * @return true-t ad vissza, ha az �sszes j�t�kos a mez?n tart�zkodik, k�l�nben false-ot.
	 */
	public boolean hasAllPlayers() {
		System.out.println("\nTile hasAllPlayers\n");
		return false;
	}
}
