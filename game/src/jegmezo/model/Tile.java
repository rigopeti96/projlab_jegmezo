package jegmezo.model;


import jegmezo.controller.GameController;
import jegmezo.view.AssetManager;
import jegmezo.view.GameWindow;
import jegmezo.view.TileView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Mező, amire a játékosok léphetnek. Hóréteg kerülhet rá, lehet rajta tárgy, iglu illetve játékosok. Hóvihar hatással lehet a mezőkre, ezen kívül minden viselkedésta
 *  specializált osztályok kezelnek le. Tárolja a szomszédait és a rajta álló Player-eket.*/
public abstract class Tile {
	/**
	 * hóréteg vastagsága
	 */
	protected int snow;
	/**
	 * Gamecontroller
	 */
	protected GameController gameController;
	/**
	 * szomszédok listája
	 */
	protected List<Tile> neighbours = new ArrayList<>();
	/**
	 * a tile-on tartózkodó játékosok
	 */
	protected List<Player> players = new ArrayList<>();
	/**
	 * a tile-on lévő jegesmedve
	 */
	protected PolarBear polarBear;
	/**
	 * a tile azonosítója
	 */
	protected int id;
	/**
	 * felfedezett-e már az adott tile
	 */
	protected boolean discovered=false;

	/**
	 * A tile konstruktora
	 * @param gameController GameController változó
	 * @param id azonosító
	 * @param snow hóréteg
	 */
	public Tile(GameController gameController, int id, int snow) {
		this.gameController = gameController;
		this.id = id;
		this.snow = snow;
	}

	/**
	 * @return A Tile azonosítója
	 */
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
	public abstract int examinePlayerLimit();

	/**
	 * Visszaadja a mezőn lévő hóréteg számát.
	 * @return A hóréteg száma
	 */
	public int getSnow() {return snow;}

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
	 * @param discovered Fel van e fedezve a Tile típusa
	 */
	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	/**
	 * Hóvihar hatása az adott Tile-re, plusz hóréteg sorsolása és a Player-ek megfagyasztása (testhő csökkentése eggyel)
	 */
	public abstract void blizzard();

	/**
	 * Leszed egy bizonyos mennyiségű hóréteget a Tile-ról
	 *
	 * @param amount Hóréteg szám, amennyit le kell szedni
	 * @return true-t ad vissza, ha volt akár egy hóréteg is, amit leszedett
	 */
	public boolean removeSnow(int amount) {
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
		snow++;
	}

	/**
	 * @return true-t ad vissza, ha az összes játékos a mezőn tartózkodik, különben false-ot.
	 */
	public boolean hasAllPlayers() {
		if(gameController.getLevel().getPlayerCount() == players.size()) return true;
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


	/**
	 * Visszaadja a mező tipusát és az id-t (ID=<>) formában
	 * Kiíráshoz
	 */
	public abstract String toShortString();

	/**
	 * Kiírja a Tile adatait.
	 */
	public abstract String toLongString();

	public abstract String getDescription();


	/**
	 * Leszedi a sátrat a Tile-ról (ha van rajta)
	 */
	public abstract void destroyTent();

	public List<Player> getPlayers() {
		return players;
	}

	abstract public TileView createView(GameWindow gameWindow, AssetManager assetManager, int x, int y);
}
