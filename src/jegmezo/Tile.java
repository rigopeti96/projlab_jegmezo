package jegmezo;


import java.util.*;

/** Mező, amire a játékosok léphetnek. Hóréteg kerülhet rá, lehet rajta tárgy, iglu illetve játékosok. Hóvihar hatással lehet a mezőkre, ezen kívül minden viselkedésta
 *  specializált osztályok kezelnek le. Tárolja a szomszédait és a rajta álló Player-eket.*/
public abstract class Tile {
	private int snow;
	protected List<Tile> neighbours = new ArrayList<>();
	protected List<Player> players = new ArrayList<>();
	
	/** A Player hívja, amikor rálép a Tile-re, hozzáadja a Player-t a players listához
	 * @param player Player, amelyik rálépett a mezőre
	 * @param prevTile Az előző mező, amin a Player állt
	 * */
	public abstract void stepOnto(Player player, Tile prevTile);

	/** A Player hívja, amikor lelép a Tile-ről, kiszedi a Player-t a players listából
	 * @param player Player, amelyik lelépett a mezőről
	 * */
	public void stepOff(Player player) {
		System.out.println("Tile stepOff");
	}

	/**
	 * @return A Tile-on álló maximum Player szám, amit után átfordul/Player-ek beleesnek
	 */
	public abstract int getPlayerLimit();

	public void addPlayer(Player player) {
		System.out.println("Tile addPlayer");
		this.players.add(player);
	}

	/**
	 * @return Visszaadja a Tile szomszédjait
	 */
	public Tile getNeighbours() {
		System.out.println("Tile getNeighbours");
		System.out.println("Milyen Tile-ra lépjen?\n1:IceSheet\n2:Hole");
		String choice =new Scanner(System.in).nextLine();
		if (choice.equals("2")) {
			return new Hole();
		}
		else {
			return new IceSheet(4);
		}
	}

	public void connectTile(Tile tile) {
		neighbours.add(tile);
		tile.neighbours.add(tile);
	}

	/**
	 * @return Visszadja a Tile-on lévő Item-et
	 */
	public abstract Item getItem();

	/**
	 * Hóvihar hatása az adott Tile-re, plusz hóréteg sorsolása és a Player-ek megfagyasztása (testhő csökkentése eggyel)
	 */
	public void blizzard() {
		System.out.println("Tile blizzard");
	}

	/**
	 * Leszed egy bizonyos mennyiségű hóréteget a Tile-ról
	 * @param amount Hóréteg szám, amennyit le kell szedni
	 * @return true-t ad vissza, ha volt akár egy hóréteg is, amit leszedett
	 */
	public boolean removeSnow(int amount) {
		System.out.println("Tile removeSnow");
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
		System.out.println("Tile removeSnow");
		snow++;
	}

	/**
	 * Egy Player-t lehet vele kiválasztani (előzőhöz egy kiválasztó menüt)
	 * @return Player amit választottak
	 */
	public Player selectPlayer() {
		System.out.println("Tile selectPlayer");
		return null;
	}

	/**
	 * @return true-t ad vissza, ha az összes játékos a mezőn tartózkodik, különben false-ot.
	 */
	public boolean hasAllPlayers() {
		System.out.println("Tile hasAllPlayers");
		return false;
	}
}
