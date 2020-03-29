package jegmezo;




/** A játék lefolytatásáért felel, tratalmazza a játékosokat és a táblát*/
public class GameController {
	private Tile[] tiles;
	private Player[] players;
	/** Elindítja a játékot */
	public void startGame() {
		System.out.println("\nGameController startGame\n");
	}
	
	/** A játékosok elvesztik a játékot*/
	public void gameOver() {
		System.out.println("\nGameController gameOver\n");
	}
	
	/** A játékosok megnyerik a játékot*/
	public void win() {
		System.out.println("\nGameController win\n");
	}
	
	/** A hóvihar lebonyolításáért felel*/
	public void blizzard() {
		System.out.println("\nGameController blizzard\n");
	}
	
	/** A körök kezelése*/
	public void turn() {
		System.out.println("\nGameController turn\n");
	}
}
