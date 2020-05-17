package jegmezo.model;


import jegmezo.controller.GameController;
import jegmezo.view.*;

/**
 * A lyukat megvalósítítja meg. Ha beleesik egy játékos és nem tud magától vagy segítséggel kijönni, vége a játéknak.
 */
public class Hole extends Tile {
	/**
	 * Konstruktor. Beállítja az attribútumokat.
	 * @param gameController a gamecontroller
	 * @param id a hole id-ja
	 * @param snow A snow mennyisége a hole-on
	 */
	public Hole(GameController gameController, int id, int snow) {
		super(gameController, id, snow);
	}

	/** A játékos rálép a mezőre*/
	public void stepOnto(Player player, Tile prevTile) {
		discovered=true;
		gameController.getConsoleView().writeLine("Player "+player.getNumber()+" fell into "+this.toShortString() + ".");
		if(player.canSurvive()) {
			gameController.getConsoleView().writeLine("Player "+player.getNumber()+" had Scuba Gear, survived and moved back to " + prevTile.toShortString() + ".");
			return;
		}
		for(int i=0;i<neighbours.size();i++){
			if(neighbours.get(i).canSave()) {
				gameController.getConsoleView().writeLine("Someone on "+neighbours.get(i).toShortString()+" has a rope.");
				neighbours.get(i).stepOnto(player, prevTile);
				return;
			}
		}
		gameController.getConsoleView().writeLine("No neighbouring players with rope.");
		player.drown();
	}
	
	/** A játékoslétszám lekérdezése
	 * @return játékoslétszám
	 */
	public int examinePlayerLimit() {
		this.discovered = true;
		return 0;
	}

	/**
	 * a limitet adja vissza, hogy mennyi játékost bír el
	 * @return - int, ennek az értéke
	 */
	public int getPlayerLimit() {
		return 0;
	}

	/** A mezőn található tárgy lekérdezése
	 * @return a mezőn lévő tárgy vagy null, ha nincs
	 */
	public Item getItem() {
		return null;
	}

	/**
	 * Egy épület építése a hole-on. (mindig false)
	 * @param building Az építendő épület tipusa.
	 * @return Megépült-e
	 */
	@Override
	public boolean build(Building building) {
		return false;
	}

	/** A mezőn lévő tárgy levétele (nem lehet tárgy a hole-on)*/
	public void removeItem() {

	}
	
	/** Van-e olyan játékos a mezőn, aki meg tud menteni másik játékost (van kötele)
	 * @return mindig false, mert a lyukon nem lehet több játékos*/
	public boolean canSave() {
		return false;
	}

	/**
	 * Kiírja a Tile egy reprezentációját a standard outputra
	 */
	@Override
	public void serialize() {
		System.out.println("Hole(ID=" + id + ",snow=" + snow + ",discovered=" + (discovered ? "true" : "false") + ")");
	}

	/**
	 * Visszaadja a Hole-t és az ID-t Stringként. (kiíráshoz)
	 * @return A kiírandó String
	 */
	@Override
	public String toShortString() { return "Hole"; }

	/**
	 * Visszaadja a Hole-t és az adatait Stringként (kiíráshoz)
	 * Csak minimális adatokat ad meg, ha még nincs felfedezve
	 * @return A kiírandó String
	 */
	@Override
	public String toLongString() {
		if(!discovered)
			return "Tile(ID="+id+")";
		return "Hole(ID=" + id + ",snow=" + snow + ")";
	}

	/**
	 * adja a hole leírását
	 * @return - a leírást tartalmazó string
	 */
	@Override
	public String getDescription() {
		if(!discovered)
			return "Tile";
		return "Hole\nSnow: " + snow;
	}

	/**
	 * A medve a Hole-ra lép. (a medve sosem lép a hole-ra)
	 * @param pb A medve
	 * @param prevTile A medve előző Tile-ja
	 */
	@Override
	public void stepOnPolarBear(PolarBear pb, Tile prevTile){
	}

	/**
	 * Sátor törlése a Hole-ról
	 */
	@Override
	public void destroyTent(){
	}

	/**
	 * A hole-on vihar tör ki
	 */
	@Override
	public void blizzard(boolean increaseSnow){
		if (increaseSnow) increaseSnow();
	}

	/**
	 * Létrehozza a lyukhoz tartozó nézetet
	 * @param gameWindow a játék ablak
	 * @param assetManager a viewnak szüksége lesz az assetManagerre
	 * @param levelView a játék pályának tárolója
	 * @param x a mező x koordinátája
	 * @param y a mező y koordinátája
	 * @return
	 */
	public TileView createView(GameWindow gameWindow, AssetManager assetManager, LevelView levelView, int x, int y){
		return new HoleView(gameWindow, assetManager, levelView, x, y, this);
	}

	/**
	 * visszaadná a polarbeart, de null-t ad vissza, mert ez egy lyuk
	 * @return
	 */
	@Override
	public PolarBear getPolarBear() {
		return null;
	}
}
