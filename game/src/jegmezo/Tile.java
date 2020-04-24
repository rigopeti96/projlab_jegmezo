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
	 * Egy Tile hívja, amikor egy Player lelép egy Tile-ről, kiszedi az player-t a megfelelő listából
	 *
	 * @param player Player, amelyik lelépett a mezőről
	 */
	public void stepOff(Player player) {
			players.remove(player);
	}

    /**
     * A medve lelép a mezőről.
     */
	public void steOffPolarBear(){
	    polarBear=null;
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
		if(snow == 0) return false; //a legkevesebb hó mennyiség a 0, az alá nem lehet csökkenteni
		if(snow < amount) snow = 0;
		else snow = snow - amount;
		return true;
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
	public Player selectPlayer(Player excluding) {
		System.out.println("Close players:");
		ArrayList<Player> sortedPlayers = new ArrayList<>(players);
		sortedPlayers.sort(Comparator.comparingInt(Player::getNumber));
		for (Player player: sortedPlayers) {
			if (player != excluding) System.out.println("Player " + player.getNumber());
		}
		while (true) {
			System.out.println("Select player (<number/cancel>):" );
			String line = new Scanner(System.in).nextLine().trim();

			if (line.equals("cancel")) return null;
			for (Player player: sortedPlayers) {
				if (player != excluding && (String.valueOf(player.getNumber()).equals(line) || ("Player " + player.getNumber()).equals(line))) return player;
			}

			System.out.println("No player with number'" + line + "'.");
		}
	}

	/**
	 * @return true-t ad vissza, ha az összes játékos a mezőn tartózkodik, különben false-ot.
	 */
	public boolean hasAllPlayers() {
		System.out.println("Tile hasAllPlayers");
		if(gameController.getPlayerCount() == players.size()) return true;
		return false;
	}

	/**
	 *  A medve rálép a Tile-ra.
	 * @param pb A medve
	 * @param prevTile A medve előző Tile-ja
	 */
	public abstract void stepOnPolarBear(PolarBear pb, Tile prevTile);

	/**
	 * Kiírja a Tile egy reprezentációját a standard outputra
	 */
	public abstract void serialize();

	/** Ez a függvény hívódik meg minden kör végén, hogy a mezőn lévő sátor letörlődjön, ha van a mezőn. */
	public abstract void turnEnd();
}
