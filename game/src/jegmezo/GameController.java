package jegmezo;


import java.util.*;

/** A játék lefolytatásáért felel, tratalmazza a játékosokat és a táblát*/
public class GameController {
	private  Map<Integer, Tile> tiles = new HashMap<>();
	private List<Player> players = new ArrayList<>();
	private PolarBear polarBear = new PolarBear(this);
	private GameState gameState = GameState.Creating;
	private boolean controlledRandomness = false;
	private Scanner scanner = new Scanner(System.in);

	/** Ez a függvény visszaadja az összes játékos számát*/
	public int getPlayerCount(){
		return players.size();
	}

	/** Elindítja a játékot */
	public void startGame() {
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

	/**
	 * Kézi vezérlést ad a játéknak
	 * @param command a beérkező parancs
	 */
	public void handleControlCommand(String command) {
		if (gameState == GameState.Creating && command.equals("init game")) {
			boolean valid = false;
			while (!valid) {
				valid = true;
				System.out.println("Type of randomness (controlled/random): ");
				String type = scanner.nextLine();
				if (type.equals("controlled")) controlledRandomness = true;
				else if (type.equals("random")) controlledRandomness = false;
				else valid = false;
			}
		} else if (gameState == GameState.Creating && command.equals("start game")) {
			players.clear();
			tiles.clear();
			System.out.println("Number of players (3-8): ");
			int number_of_player;
			do{
				number_of_player = scanner.nextInt();
				scanner.nextLine();
				if(number_of_player < 3 || number_of_player > 8)
					System.out.println("Invalid value, try again!");
			} while(number_of_player < 3 || number_of_player > 8);
			for(int i = 0; i< number_of_player; i++){
				int playernumber = i+1;
				System.out.println("Player "+ playernumber + " class (eskimo/scientist): ");
				String type = scanner.nextLine().toLowerCase();
				if(type.equals("eskimo")){
					players.add(new Eskimo(this, i));
				} else if (type.equals("scientist")){
					players.add(new Scientist(this, i));
				} else {
					System.out.println("Wrong class, try again!");
					i--;
				}
			}
			LevelGenerator generator = new LevelGenerator(this, players.size());

			for (Tile tile: generator.generate()) {
				tiles.put(tile.getId(), tile);
			}
			for (Player player: players) {
				player.spawnOnto(tiles.get(0));
			}
			polarBear = generator.getPolarBear();

			tiles.get(0).snow=20;

			players.get(0).takeItem(new Food());
			players.get(0).takeItem(new Shovel());
			players.get(1).takeItem(new Shovel());
			players.get(2).takeItem(new Food());
			players.get(2).takeItem(new Food());
			players.get(2).takeItem(new WinItem("flare"));

			gameState = GameState.Stopped;
		} else if (command.equals("load game")) {
			tiles.clear();
			players.clear();
			this.loadGame();
			gameState = GameState.Stopped;
		} else if (command.equals("save game")) {
			saveGame();
		} else if (command.equals("exit")) {
			System.exit(0);
		} else {
			System.out.println("No such command '" + command + "'");
		}
	}

	/**
	 * Visszaadja, hogy a játék random vagy kézi
	 * @return controlledRandomness értéke
	 */
	public boolean isControlledRandomness() {
		return this.controlledRandomness;
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
		int blizzard_tiles = 0;
		int isBlizzardOnTile;
		Random r = new Random();
		if(!isControlledRandomness()){ //random
			for (Tile tile : tiles.values()) {
				isBlizzardOnTile = r.nextInt(2);
				if(isBlizzardOnTile == 1){
					tile.blizzard();
					System.out.println("Snowfall on Tile(ID=" + tile.getId() + ").");
					blizzard_tiles++;
				}
				if(blizzard_tiles == tiles.size())
					break;
			}
		} else {
			for (Tile tile : tiles.values()) {
				boolean sikeres_command = false;
				while(!sikeres_command){
					System.out.println("Snowfall on Tile(ID=" + tile.getId() + ") (yes/no):");
					String isBlizzard = scanner.nextLine().toLowerCase();
					if(isBlizzard.equals("yes")){
						sikeres_command = true;
						tile.blizzard();
						blizzard_tiles++;
					}
					if(isBlizzard.equals("no")){
						sikeres_command = true;
					}
					else if ( !isBlizzard.equals("yes") ){
						System.out.println("Please type 'yes' for snowfall and 'no' for no snowfall.");
					}
				}
				if (blizzard_tiles == tiles.size())
					break;
			}
		}

	}
	
	/** A körök kezelése*/
	public void turn() {
		for (Player player: players) {
			player.takeTurn();
			if (gameState != GameState.Running) return;
		}
		if(!isControlledRandomness()){
			Random r = new Random();
			int blizzard_is_coming = r.nextInt(2);
			if(blizzard_is_coming == 1){
				blizzard();
			}
		} else {
			boolean sikeres_command = false;
			while(!sikeres_command){
				System.out.println("Blizzard (yes / no)");
				String isBlizzard = scanner.nextLine().toLowerCase();
				if(isBlizzard.equals("yes")){
					blizzard();
					sikeres_command = true;
				}
				if(isBlizzard.equals("no")){
					sikeres_command = true;
				}
				else if ( !isBlizzard.equals("yes") ){
					System.out.println("Please type 'yes' for blizzard and 'no' for no blizzard.");
				}
			}

		}

		polarBear.move();

		//a kör végén minden sátor törlődik, ami fel volt építve
		for(Tile tile: tiles.values()){
			tile.destroyTent();
		}
	}

	/**
	 *
	 */
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
		try {
			int id = Integer.parseInt(idStr);
			if (line.getName().equals("Hole")) tiles.put(id, new Hole(this, id, Integer.parseInt(snow)));
			else {
				Item item = deserializeItem(line.getParameters().get("item"));
				String playerLimit = line.getParameters().get("playerLimit");
				boolean itemDiscovered = line.getParameters().get("itemDiscovered").equals("true");
				boolean playerLimitDiscovered = line.getParameters().get("playerLimitDiscovered").equals("true");
				Building building = Enum.valueOf(Building.class, line.getParameters().get("building"));
				IceSheet sheet = new IceSheet(this, id, Integer.parseInt(playerLimit), Integer.parseInt(snow));
				tiles.put(id, sheet);
				sheet.setItem(item);
				if (itemDiscovered) sheet.discoverItem();
				if (playerLimitDiscovered) sheet.discoverPlayerLimit();
				if (building != Building.none) sheet.build(building);
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
			polarBear = new PolarBear(this);
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
