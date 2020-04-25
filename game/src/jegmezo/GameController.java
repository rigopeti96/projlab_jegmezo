package jegmezo;


import java.util.*;

/** A játék lefolytatásáért felel, tratalmazza a játékosokat és a táblát*/
public class GameController {
	private  Map<Integer, Tile> tiles = new HashMap<>();;
	private List<Player> players = new ArrayList<>();
	private PolarBear polarBear = new PolarBear();
	private GameState gameState = GameState.Creating;

	/** Ez a függvény visszaadja az összes játékosok számát*/
	public int getPlayerCount(){
		return players.size();
	}

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
			tiles.clear();
			// TODO: Set players up from input
			players.add(new Eskimo(this, 1));
			players.add(new Scientist(this, 2));
			players.add(new Eskimo(this, 3));
			LevelGenerator generator = new LevelGenerator(this, players.size());

			for (Tile tile: generator.generate()) {
				tiles.put(tile.getId(), tile);
			}
			for (Player player: players) {
				player.spawnOnto(tiles.get(0));
			}
			polarBear = generator.getPolarBear();

			players.get(0).takeItem(new Food());
			players.get(0).takeItem(new Shovel());
			players.get(1).takeItem(new Shovel());
			players.get(2).takeItem(new Food());
			players.get(2).takeItem(new Food());
			players.get(2).takeItem(new WinItem("flare"));

			gameState = GameState.Stopped;
		} else if (command.equals("load game")) {
			System.out.println("load game command ran");
			tiles.clear();
			players.clear();
			this.loadGame();
			gameState = GameState.Stopped;
		} else if (command.equals("save game")) {
			saveGame();
		} else {
			System.out.println("No such command '" + command + "'");
		}
	}

	public GameState getGameState() {
		return gameState;
	}

	/** A játékosok elvesztik a játékot*/
	public void gameOver() {
		System.out.println("Game over.");
		System.exit(0);
	}
	
	/** A játékosok megnyerik a játékot*/
	public void win() {
		System.out.println("The players win the game.");
		System.exit(0);
	}
	
	/** A hóvihar lebonyolításáért felel*/
	public void blizzard() {
		System.out.println("Blizzard");
		for (Tile tile : tiles.values()) {
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

		//a kör végén minden sátor törlődik, ami fel volt építve
		for(Tile tile: tiles.values()){
			tile.destroyTent();
		}
	}

	public void saveGame() {
		ArrayList<Tile> sortedTiles = new ArrayList<>(tiles.values());
		sortedTiles.sort(Comparator.comparingInt(Tile::getId));
		for (Tile tile: sortedTiles) {
			tile.serialize();
		}
		System.out.println();

		Set<String> serializedConnections = new HashSet<>();
		for (Tile tile: sortedTiles) {
			ArrayList<Tile> sortedNeighbours = new ArrayList<>(tile.getNeighbours());
			sortedNeighbours.sort(Comparator.comparingInt(Tile::getId));
			for (Tile otherTile: sortedNeighbours) {
				String key = tile.getId() + "." + otherTile.getId();
				if (!serializedConnections.contains(key)) {
					serializedConnections.add(key);
					System.out.println("Tile-Tile(ID1=" + tile.getId() + ",ID2=" + otherTile.getId() + ")");
				}
			}
		}
		System.out.println();

		for (Player player: players) {
			player.serialize();
		}
		System.out.println();

		for (Player player: players) {
			player.getInventory().serialize(player.getNumber());
		}
		System.out.println();

		polarBear.serialize();
		System.out.println();
	}

	public void loadGame() {
		String line;
		Scanner scanner = new Scanner(System.in);
		line = scanner.nextLine().trim();
		while (!line.equals("")) {
			if (line.startsWith("#")) continue;
			DeserializedLine desLine = DeserializedLine.fromLine(line, new String[] { "Hole", "Sheet" });
			if (desLine == null) {
				System.out.println("Invalid input format!");
				System.exit(0);
			}
			deserializeTile(desLine);
			line = scanner.nextLine().trim();
		}
		line = scanner.nextLine().trim();
		while (!line.equals("")) {
			if (line.startsWith("#")) continue;
			DeserializedLine desLine = DeserializedLine.fromLine(line, new String[] { "Tile-Tile" });
			if (desLine == null) {
				System.out.println("Invalid input format!");
				System.exit(0);
			}
			deserializeTileConnection(desLine);
			line = scanner.nextLine().trim();
		}
		line = scanner.nextLine().trim();
		while (!line.equals("")) {
			if (line.startsWith("#")) continue;
			DeserializedLine desLine = DeserializedLine.fromLine(line, new String[] { "Eskimo", "Scientist" });
			if (desLine == null) {
				System.out.println("Invalid input format!");
				System.exit(0);
			}
			deserializePlayer(desLine);
			line = scanner.nextLine().trim();
		}
		line = scanner.nextLine().trim();
		while (!line.equals("")) {
			if (line.startsWith("#")) continue;
			DeserializedLine desLine = DeserializedLine.fromLine(line, new String[] { "Player-Item" });
			if (desLine == null) {
				System.out.println("Invalid input format!");
				System.exit(0);
			}
			deserializePlayerItemConnection(desLine);
			line = scanner.nextLine().trim();
		}
		line = scanner.nextLine().trim();
		while (!line.equals("")) {
			if (line.startsWith("#")) continue;
			DeserializedLine desLine = DeserializedLine.fromLine(line, new String[] { "PolarBear" });
			if (desLine == null) {
				System.out.println("Invalid input format!");
				System.exit(0);
			}
			deserializePolarBear(desLine);
			line = scanner.nextLine().trim();
		}
	}

	private void deserializeTile(DeserializedLine line) {
		String idStr = line.getParameters().get("ID");
		String snow = line.getParameters().get("snow");
		String playerLimit = line.getParameters().get("playerLimit");
		try {
			int id = Integer.parseInt(idStr);
			if (line.getName().equals("Hole")) tiles.put(id, new Hole(this, id, Integer.parseInt(snow)));
			else {
				Item item = deserializeItem(line.getParameters().get("item"));
				IceSheet sheet = new IceSheet(this, id, Integer.parseInt(playerLimit), Integer.parseInt(snow));
				tiles.put(id, sheet);
				sheet.setItem(item);
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input format!");
			System.exit(0);
		}
	}

	private Item deserializeItem(String name) {
		if (name == null) return null;
		switch (name) {
			case "flare":
				return new WinItem("flare");
			case "flare gun":
				return new WinItem("flare gun");
			case "cartridge":
				return new WinItem("cartridge");
			case "food":
				return new Food();
			case "shovel":
				return new Shovel();
			case "breakable shovel":
				return new BreakableShovel();
			case "tent":
				return new Tent();
			case "scuba gear":
				return new ScubaGear();
			case "rope":
				return new Rope();
			default:
				return null;
		}
	}

	private void deserializeTileConnection(DeserializedLine line) {
		String id1Str = line.getParameters().get("ID1");
		String id2Str = line.getParameters().get("ID2");
		try {
			int id1 = Integer.parseInt(id1Str);
			int id2 = Integer.parseInt(id2Str);
			tiles.get(id1).connectTile(tiles.get(id2));
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input format!");
			System.exit(0);
		}
	}

	private void deserializePlayer(DeserializedLine line) {
		String numStr = line.getParameters().get("number");
		String heatStr = line.getParameters().get("heat");
		String tileIdStr = line.getParameters().get("tile");
		try {
			int num = Integer.parseInt(numStr);
			int heat = Integer.parseInt(heatStr);
			int tileId = Integer.parseInt(tileIdStr);
			Player player = line.getName().equals("Scientist") ? new Scientist(this, num, heat) : new Eskimo(this, num, heat);
			if (tiles.get(tileId) == null) {
				System.out.println("Invalid input format!");
				System.exit(0);
			}
			players.add(player);
			player.spawnOnto(tiles.get(tileId));
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input format!");
			System.exit(0);
		}
	}

	private void deserializePlayerItemConnection(DeserializedLine line) {
		try {
			int num = Integer.parseInt(line.getParameters().get("number"));
			int count = Integer.parseInt(line.getParameters().get("count"));
			Item item = deserializeItem(line.getParameters().get("item"));
			for (int i = 0; i < count; i++) {
				players.get(num - 1).takeItem(item);
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input format!");
			System.exit(0);
		}
	}

	private void deserializePolarBear(DeserializedLine line) {
		String tileIdStr = line.getParameters().get("tile");
		try {
			int tileId = Integer.parseInt(tileIdStr);
			polarBear = new PolarBear();
			if (tiles.get(tileId) == null) {
				System.out.println("Invalid input format!");
				System.exit(0);
			}
			polarBear.spawnOnto(tiles.get(tileId));
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input format!");
			System.exit(0);
		}
	}
}
