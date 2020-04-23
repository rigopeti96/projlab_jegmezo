package jegmezo;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** A játék lefolytatásáért felel, tratalmazza a játékosokat és a táblát*/
public class GameController {
	private  List<Tile> tiles = new ArrayList<>();;
	private List<Player> players = new ArrayList<>();
	private PolarBear polarBear = new PolarBear();
	private GameState gameState = GameState.Creating;

	/** Elindítja a játékot */
	public void startGame() {
		Scanner scanner = new Scanner(System.in);
		while (gameState == GameState.Creating) {
			String command = scanner.nextLine();
			handleControlCommand(command);
		}

		while (true) {
			gameState = GameState.Running;
			while (gameState == GameState.Running) {
				turn();
			}
		}
	}

	public void handleControlCommand(String command) {
		if (gameState == GameState.Creating && command.equals("init game")) {
			// TODO: Sets up flags (controlledRandomness)
		} else if (gameState == GameState.Creating && command.equals("start game")) {
			System.out.println("start game command ran");
			players.clear();
			// TODO: Set players up from input
			players.add(new Eskimo(this, 1));
			players.add(new Scientist(this, 2));
			players.add(new Eskimo(this, 3));
			LevelGenerator generator = new LevelGenerator(this, players.size());
			tiles = generator.generate();
			polarBear = generator.getPolarBear();
			gameState = GameState.Stopped;
		} else if (command.equals("load game")) {
			System.out.println("load game command ran");
			tiles.clear();
			players.clear();
			gameState = GameState.Stopped;
		} else if (command.equals("save game")) {
			System.out.println("save game command ran");
		} else {
			System.out.println("No such command '" + command + "'");
		}
	}

	public GameState getGameState() {
		return gameState;
	}

	/** A játékosok elvesztik a játékot*/
	public void gameOver() {
		System.out.println("GameController gameOver");
		System.exit(0);
	}
	
	/** A játékosok megnyerik a játékot*/
	public void win() {
		System.out.println("GameController win");
		System.exit(0);
	}
	
	/** A hóvihar lebonyolításáért felel*/
	public void blizzard() {
		System.out.println("GameController blizzard");
		for (Tile tile : tiles) {
			tile.blizzard();
		}
	}
	
	/** A körök kezelése*/
	public void turn() {
		for (Player player: players) {
			player.takeTurn();
			if (gameState != GameState.Running) return;
		}
		blizzard();
		polarBear.move();
	}

	public void serialize() {

	}
}
