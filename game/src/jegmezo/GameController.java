package jegmezo;


import java.util.*;

/** A játék lefolytatásáért felel, tratalmazza a játékosokat és a táblát*/
public class GameController {
	/**
	 * a játékban lévő mezőket tartalmazó map
	 */
	private Map<Integer, Tile> tiles = new HashMap<>();
	/**
	 * játékosokat tartalmazó list
	 */
	private List<Player> players = new ArrayList<>();
	/**
	 * a játékban egy jegesmedve van
	 */
	private PolarBear polarBear = new PolarBear(this);
	/**
	 * a játék állapotát tároló enum
	 */
	private GameState gameState = GameState.Creating;
	/**
	 * a játékot lehet controlled és random módban is futtatni, ezt tárolja ez a bool
	 */
	private boolean controlledRandomness = false;
	/**
	 * beolvasó
	 */
	private Scanner scanner = new Scanner(System.in);

	/** Ez a függvény visszaadja az összes játékos számát*/
	public int getPlayerCount(){
		return players.size();
	}

	/**
	 * Ez a függvény visszaadja a scannert
	 * @return this.scanner - a scanner
	 */
	public Scanner getScanner() {
		return this.scanner;
	}

	/**
	 * Ez a függvény visszaad egy mezőt a mezők közül az indexével indexelve
	 * @param id - a mező indexe
	 * @return tile - az indexelt mező
	 */
	public Tile getTileById(Integer id) {
		return tiles.get(id);
	}

	/** Elindítja a játékot */
	public void startGame() {
		while (gameState == GameState.Creating) { //amíg a menüben vagyunk és míg nem indult el a játék
			String command = scanner.nextLine(); //beolvasunk egy parancsot
			handleControlCommand(command); //a parancsot átadjuk a handleControlCommand függvénynek
		}

		while (true) { //a játék elindulása után a játék két féle képpen állhat le, ha nyernek vagy ha vesztenek a játékosok
			gameState = GameState.Running; //a játék állapota futó lesz
			while (gameState == GameState.Running) {
				turn(); //folyamatosan meghívjuk a köröket kezelő turn() függvényt
			}
		}
	}

	/**
	 * Kézi vezérlést ad a játéknak
	 * @param command a beérkező parancs
	 */
	public void handleControlCommand(String command) {
		if (gameState == GameState.Creating && command.equals("init game")) { //init game parancs beírása után tudjuk inicializálni a játékot
			boolean valid = false;
			while (!valid) {
				valid = true;
				System.out.println("Type of randomness (controlled/random):"); //megkérdezzük, hogy a játékot controlled vagy random módban szeretnénk futtatni
				String type = scanner.nextLine(); //beolvassuk a választ
				if (type.equals("controlled")) controlledRandomness = true; // a bool true értéke jelzi ha controlled módban fut a játék
				else if (type.equals("random")) controlledRandomness = false; // a bool false értéke jelzi ha random módban fut a játék
				else valid = false;
			}
		} else if (gameState == GameState.Creating && command.equals("start game")) { //játék elindítása után lehet megadni a játékosok számát
			players.clear(); // a játékosok számát most fogjuk bekérni
			tiles.clear(); //a játékosok számától fog függni a mezők száma
			System.out.println("Number of players (3-8):"); //bekérdezzük a játékosok számát
			int number_of_player;
			do{
				number_of_player = scanner.nextInt(); //beolvassuk a játékosok számát
				scanner.nextLine();
				if(number_of_player < 3 || number_of_player > 8)
					System.out.println("Invalid value, try again!"); // ha nem jó értéket kaptunk, újra kérjük
			} while(number_of_player < 3 || number_of_player > 8);
			for(int i = 0; i< number_of_player; i++){ //a játékosoknál sorban kiválasztható hogy eskimo vagy scientist lesz a játékos
				int playernumber = i+1;
				System.out.println("Player "+ playernumber + " class (eskimo/scientist):");  //megkérdezzük melyik akar lenni
				String type = scanner.nextLine().toLowerCase(); // beolvassuk a választott játékos típust
				if(type.equals("eskimo")){ //ha eskimo, eskimora állítjuk a játékost
					players.add(new Eskimo(this, i));
				} else if (type.equals("scientist")){ //ha scientist, akkor scientist-re
					players.add(new Scientist(this, i));
				} else { //egyébként újra kérjük
					System.out.println("Wrong class, try again!");
					i--;
				}
			}
			LevelGenerator generator = new LevelGenerator(this, players.size()); //legeneráljuk a játékosok számától függően a pályát

			for (Tile tile: generator.generate()) {
				tiles.put(tile.getId(), tile); //legeneráljuk a mezőket
			}
			for (Player player: players) {
				player.spawnOnto(tiles.get(0)); // a játékosokat a nullás mezőre helyezzük
			}
			polarBear = generator.getPolarBear(); //legeneráljuk a jegesmedvét

			//minden játékos kap két food itemet, hogy könnyebb legyen a játék
			players.get(0).takeItem(new Food());
			players.get(0).takeItem(new Food());
			players.get(1).takeItem(new Food());
			players.get(1).takeItem(new Food());
			players.get(2).takeItem(new Food());
			players.get(2).takeItem(new Food());

			gameState = GameState.Stopped;
		} else if (command.equals("load game")) { //ha load game parancsot adtunk ki, akkor be lehet tölteni egy pályát
			tiles.clear();
			players.clear();
			this.loadGame(); // meghívjuk erre a load game függvényt
			gameState = GameState.Stopped;
		} else if (command.equals("save game")) { //ha save game parancsot adtunk ki, akkor meghívjuk a saveGame() függvényt
			saveGame();
		} else if (command.equals("exit")) { //ha exit parancsot adtunk ki, kilép a program
			System.exit(0);
		} else {
			System.out.println("No such command '" + command + "'"); // ha nem ismerjük fel a parancsot akkor újra kérjük
		}
	}

	/**
	 * Visszaadja, hogy a játék random vagy kézi
	 * @return controlledRandomness értéke
	 */
	public boolean isControlledRandomness() {
		return this.controlledRandomness;
	}

	/**
	 * Ez a függvény visszaadja a játék állapotát
	 * @return gameState - enum, a játék állapotát tárolja
	 */
	public GameState getGameState() {
		return gameState;
	}

	/** A játékosok elvesztik a játékot*/
	public void gameOver() {
		System.out.println("Game over."); //kiírja, hogy vesztettek a játékosok
		System.exit(0); //kilép a program
	}
	
	/** A játékosok megnyerik a játékot*/
	public void win() {
		System.out.println("The players win the game."); //kiírja, hogy nyertek a játékosok
		System.exit(0); //kilép a program
	}
	
	/** A hóvihar lebonyolításáért felel*/
	public void blizzard() {
		System.out.println("Blizzard is coming."); //kiírja, hogy jön a hóvihar
		int blizzard_tiles = 0; //a hóvihar súlytotta mezők számát tároló számláló
		int isBlizzardOnTile; //azt tárolja, hogy az aktuális mezőn jön-e a hóvihar
		Random r = new Random();
		if(!isControlledRandomness()){ //ha random módban fut a játék
			for (Tile tile : tiles.values()) { //végigmegy az összes mezőn
				isBlizzardOnTile = r.nextInt(2); //véletlenszerűen sorsolja, hogy a mezőn lesz-e hóvihar vagy sem
				if(isBlizzardOnTile == 1){ //ha 1 az értéke, akkor jön hóvihar a mezőre
					tile.blizzard(); //ekkor meghívjuk az aktuális mezőn a mező blizzard függvényét
					System.out.println("Snowfall on Tile(ID=" + tile.getId() + ")."); //ekkor kiírjuk, hogy hóvihar jön az aktuális mezőre
					blizzard_tiles++; //növeljük a hóvihar súlytotta mezők számát tároló számlálót
				}
				if(blizzard_tiles == tiles.size()/2) // ha a hóvihar által súlytott mezők száma elérte a mezők számának felét, akkor nem súlyt több mezőt hóvihar
					break;
			}
		} else { //ha controlled módban fut a játék
			for (Tile tile : tiles.values()) { //végigmegy az összes mezőn
				boolean sikeres_command = false; //sikeresen beolvasott parancsot jelentő bool
				while(!sikeres_command){ //amíg nem olvastunk be sikeres parancsot
					System.out.println("[controlled randomness] Snowfall on Tile(ID=" + tile.getId() + ") (yes/no):"); //megkérdezzük, hogy az aktuális mezőn jöjjön-e hóvihar
					String isBlizzard = scanner.nextLine().toLowerCase(); //beolvassuk a választ
					if(isBlizzard.equals("yes")){ //ha jön hóvihar az aktuális mezőn
						sikeres_command = true;
						tile.blizzard(); //meghívjuk az aktuális mezőn a mező blizzard() függvényét
						blizzard_tiles++; //növeljük a hóvihar súlytotta mezők számát tároló számlálót
					}
					if(isBlizzard.equals("no")){ //ha nem jön hóvihar, akkor is sikeres a parancs beolvasása
						sikeres_command = true;
					}
					else if ( !isBlizzard.equals("yes") ){ //egyéb esetben beolvassuk újra a parancsot
						System.out.println("Please type 'yes' for snowfall and 'no' for no snowfall."); // értesítjük erről a parancs kiadóját
					}
				}
			}
		}

	}
	
	/** A körök kezelése*/
	public void turn() {
		for (Player player: players) { // végigmegy az összes játékoson
			player.takeTurn(); //sorban meghívja az aktuális játékosok takeTurn() függvényét
			if (gameState != GameState.Running) return; //ha már nem fut a játék, mert időközben a játékosok nyertek vagy vesztettek, akkor nem folytatódnak a körök
		}
		if(!isControlledRandomness()){ //ha random módban fut a játék
			Random r = new Random();
			int blizzard_is_coming = r.nextInt(2); //random sorsoljuk, hogy egyáltalán jön-e hóvihar bárhova vagy sem
			if(blizzard_is_coming == 1){
				blizzard(); //ha jön hóvihar, meghívódik a blizzart() függvény
			}
		} else { //controlled esetben
			boolean sikeres_command = false;
			while(!sikeres_command){ //amíg sikeresen nem olvasunk be parancsot
				System.out.println("[controlled randomness] Blizzard (yes/no):"); //megkérdezzük, hogy jöjjön-e hóvihar vagy sem
				String isBlizzard = scanner.nextLine().toLowerCase(); //beolvassuk a választ
				if(isBlizzard.equals("yes")){ //ha igen, jün a hóvihar
					blizzard(); //meghívjuk a blizzard() függvényt
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

		polarBear.move(); // a kör végén lép a medve

		//a kör végén minden sátor törlődik, ami fel volt építve
		for(Tile tile: tiles.values()){
			tile.destroyTent(); //összes mezőre meghívjuk a destroyTent()-t
		}
	}

	/**
	 * A játék állapotát írja ki
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

	/**
	 * load game parancs esetén ez a függvény hívódik meg és meg lehet adni a játék adatait (mezők, játékosok, tárgyak)
	 */
	public void loadGame() {
		String line;
		line = scanner.nextLine().trim();
		while (!line.equals("")) {
			if (line.startsWith("#")) continue;
			DeserializedLine desLine = DeserializedLine.fromLine(line, new String[] { "Hole", "Sheet" });
			if (desLine == null) {
				System.out.println("Invalid input format (tile)!");
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
				System.out.println("Invalid input format (tile-tile)!");
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
				System.out.println("Invalid input format (player)!");
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
				System.out.println("Invalid input format (player-item)!");
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
				System.out.println("Invalid input format (polar bear)!");
				System.exit(0);
			}
			deserializePolarBear(desLine);
			line = scanner.nextLine().trim();
		}
	}

	/**
	 * A tile sorok beolvasása
	 */
	private void deserializeTile(DeserializedLine line) {
		String idStr = line.getParameters().get("ID");
		String snow = line.getParameters().get("snow");
		String discoveredStr = line.getParameters().get("discovered");
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
			if (discoveredStr != null) tiles.get(id).setDiscovered(discoveredStr.equals("true"));
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input format (tile)!");
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * A játék sorok beolvasása
	 */
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

	/**
	 * A tile connection beolvasása
	 */
	private void deserializeTileConnection(DeserializedLine line) {
		String id1Str = line.getParameters().get("ID1");
		String id2Str = line.getParameters().get("ID2");
		try {
			int id1 = Integer.parseInt(id1Str);
			int id2 = Integer.parseInt(id2Str);
			tiles.get(id1).connectTile(tiles.get(id2));
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input format (tile-tile)!");
			System.exit(0);
		}
	}

	/**
	 * A játékosok beolvasása
	 */
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
				System.out.println("Invalid input format (player)!");
				System.exit(0);
			}
			players.add(player);
			player.spawnOnto(tiles.get(tileId));
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input format (player)!");
			System.exit(0);
		}
	}

	/**
	 * A játékosok tárgyainak beolvasása
	 */
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
			System.out.println("Invalid input format (player-item)!");
			System.exit(0);
		}
	}

	/**
	 * A jegesmedve beolvasása
	 */
	private void deserializePolarBear(DeserializedLine line) {
		String tileIdStr = line.getParameters().get("tile");
		try {
			int tileId = Integer.parseInt(tileIdStr);
			polarBear = new PolarBear(this);
			if (tiles.get(tileId) == null) {
				System.out.println("Invalid input format (polar bear)!");
				System.exit(0);
			}
			polarBear.spawnOnto(tiles.get(tileId));
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid input format (polar bear)!");
			System.exit(0);
		}
	}
}
