package jegmezo;


import java.util.*;

/** Mező, amire a játékosok léphetnek. Hóréteg kerülhet rá, lehet rajta tárgy, iglu illetve játékosok. Hóvihar hatással lehet a mezőkre, ezen kívül minden viselkedésta
 *  specializált osztályok kezelnek le. Tárolja a szomszédait és a rajta álló Player-eket.*/
public abstract class Tile {
	protected int snow;
	protected GameController gameController;
	protected List<Tile> neighbours = new ArrayList<>();
	protected List<Player> players = new ArrayList<>();
	protected PolarBear polarBear;
	protected int id;

	public Tile(GameController gameController, int id, int snow) {
		this.gameController = gameController;
		this.id = id;
		this.snow = snow;
	}

	public int getId() {
		return id;
	}

	/**
	 * A Player hívja, amikor rálép a Tile-re, hozzáadja a Player-t a players listához
	 *
	 * @param player   Player, amelyik rálépett a mezőre
	 * @param prevTile Az előző mező, amin a Player állt
	 */
	public abstract void stepOnto(Player player, Tile prevTile);

	/**
	 * Egy Entity hívja, amikor lelép a Tile-ről, kiszedi az entity-t az entities listából
	 *
	 * @param entity Entity, amelyik lelépett a mezőről
	 */
	public void stepOff(Entity entity) {
		System.out.println("Tile stepOff");
	}

	/**
	 * @return A Tile-on álló maximum Player szám, amit után átfordul/Player-ek beleesnek
	 */
	public abstract int getPlayerLimit();

	/**
	 * Hozzáad egy Player-t a players listához
	 *
	 * @param player Player amit hozzáad
	 */
	public void addPlayer(Player player) {
		this.players.add(player);
	}

	/**
	 * Beállítja a tile jegesmedvéjét (csak egy lehet)
	 *
	 * @param bear PolarBear amit hozzáad
	 */
	public void addPolarBear(PolarBear bear) {
		this.polarBear = bear;
	}

	/**
	 * @return Visszaadja a Tile szomszédjait
	 */
	public List<Tile> getNeighbours() {
		return neighbours;
	}

	/**
	 * Összeköti ezt a Tile-t egy másikkal (neighbour listákat updateli)
	 *
	 * @param tile Másik Tile
	 */
	public void connectTile(Tile tile) {
		if (!neighbours.contains(tile)) neighbours.add(tile);
		if (!tile.neighbours.contains(this)) tile.neighbours.add(this);
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
	 *
	 * @param amount Hóréteg szám, amennyit le kell szedni
	 * @return true-t ad vissza, ha volt akár egy hóréteg is, amit leszedett
	 */
	public boolean removeSnow(int amount) {
		System.out.println("Tile removeSnow");
		return false;
	}

	/**
	 * A kapott épületet megépíti a mezőre,
	 *
	 * @return true has sikerült false ha nem
	 */
	public abstract boolean build(Building building);

	/**
	 * Leszedi a Tile-ről a rajta lévő Item-et
	 */
	public abstract void removeItem();

	/**
	 * Ezen a Tile-on van e olyan Player, aki megtud menteni valakit (van kötele)
	 *
	 * @return Van true, nincs false
	 */
	public abstract boolean canSave();

	/**
	 * Növeli a Tile-on lévő hómennyiséget 1-gyel.
	 */
	public void increaseSnow() {
		System.out.println("Tile increaseSnow");
		snow++;
	}

	/**
	 * Egy Player-t lehet vele kiválasztani (előzőhöz egy kiválasztó menüt)
	 *
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
		System.out.println("has all/has none?");
		switch (new Scanner(System.in).nextLine()) {
			case "has all":
				return true;
		}
		return false;
	}

	public void stepOnPolarBear(PolarBear pb, Tile prevTile) {

	}

	/**
	 * Kiírja a Tile egy reprezentációját a standard outputra
	 */
	public abstract void serialize();
}
