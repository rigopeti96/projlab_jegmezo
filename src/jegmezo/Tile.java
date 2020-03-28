package jegmezo;


import java.util.*;

/** Mező, amire a játékosok léphetnek. Hóréteg kerülhet rá, lehet rajta tárgy, iglu illetve játékosok. Hóvihar hatással lehet a mezőkre, ezen kívül minden viselkedésta
 *  specializált osztályok kezelnek le. Tárolja a szomszédait és a rajta álló Player-eket.*/
public abstract class Tile {
	/** */
	private int snow;
	private List<Tile> neighbours;
	private List<Player> players;
	
	/** A Player hívja ,amikor rálép a Tile-re, hozzáadja a Player-t a players listához
	 * @param player Player, amelyik rálépett a mezőre
	 * @param prevTile Az előző mező, amin a Player állt
	 * */
	public abstract void stepOnto(Player player, Tile prevTile);

	/** A Player hívja, amikor lelép a Tile-ről, kiszedi a Player-t a players listából
	 * @param player Player, amelyik lelépett a mezőről
	 * */
	public void stepOff(Player player) {
		System.out.println("\nTile stepOff\n");
	}

	/**
	 * @return A Tile-on álló maximum Player szám, amit után átfordul/Player-ek beleesnek
	 */
	public abstract int getPlayerLimit();

	/**
	 * @returna Visszaadja a Tile szomszédjait
	 */
	public List<Tile> getNeighbours() {
		System.out.println("\nTile getNeighbours\n");
		return null;
	}

	/**
	 * @return Visszadja a Tile-on lévő Item-et
	 */
	public abstract Item getItem();

	/**
	 * Hóvihar hatása az adott Tile-re, plusz hóréteg sorsolása és a Player-ek megfagyasztása (testhő csökkentése eggyel)
	 */
	public void blizzard() {
		System.out.println("\nTile blizzard\n");
	}

	/**
	 * Leszed egy bizonyos mennyiségű hóréteget a Tile-ról
	 * @param amount Hóréteg szám, amennyit le kell szedni
	 * @return true-t ad vissza, ha volt akár egy hóréteg is, amit leszedett
	 */
	public boolean removeSnow(int amount) {
		System.out.println("\nTile removeSnow " + amount + "\n");
		return false;
	}

	/**
	 * Eskimo hívja iglut épít a mezőre,
	 * @return true has sikerült false ha nem
	 */
	public abstract boolean buildIgloo();

	/**
	 * Leszedi a Tile-ről a rajta lévő Item-et
	 */
	public abstract void removeItem();

	/**
	 * Ezen a Tile-on van e olyan Player, aki megtud menteni valakit (van kötele)
	 * @return Van true, nincs false
	 */
	public abstract boolean canSave();

	/**
	 * Növeli a Tile-on lévő hómennyiséget 1-gyel.
	 */
	public void increaseSnow() {
		System.out.println("\nTile removeSnow\n");
		snow++;
	}

	/**
	 * Egy Player-t lehet vele kiválasztani (előzohoz egy kiválasztó menüt)
	 * @return Player amit választottak
	 */
	public Player selectPlayer() {
		System.out.println("\nTile selectPlayer\n");
		return null;
	}

	/**
	 * @return true-t ad vissza, ha az összes játékos a mezőn tartózkodik, különben false-ot.
	 */
	public boolean hasAllPlayers() {
		System.out.println("\nTile hasAllPlayers\n");
		return false;
	}
}
