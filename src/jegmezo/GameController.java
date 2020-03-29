package jegmezo;


import java.util.ArrayList;
import java.util.List;

/** A játék lefolytatásáért felel, tratalmazza a játékosokat és a táblát*/
public class GameController {
	private  List<Tile> tiles = new ArrayList<>();;
	private List<Player> players=new ArrayList<>();
	/** Elindítja a játékot */
	public void startGame() {
		System.out.println("GameController startGame");
	}
	
	/** A játékosok elvesztik a játékot*/
	public void gameOver() {
		System.out.println("GameController gameOver");
	}
	
	/** A játékosok megnyerik a játékot*/
	public void win() {
		System.out.println("GameController win");
	}
	
	/** A hóvihar lebonyolításáért felel*/
	public void blizzard() {
		System.out.println("GameController blizzard");
	}
	
	/** A körök kezelése*/
	public void turn() {
		System.out.println("GameController turn");
	}
}
