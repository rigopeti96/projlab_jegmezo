package jegmezo.model;

import jegmezo.controller.GameController;

import java.util.*;

public class Level {
    /**
     * a játékban lévő mezőket tartalmazó map
     */
    private Map<Integer, Tile> tiles = new HashMap<>();
    private Map<Tile, LevelTile> levelTiles = new HashMap<>();
    /**
     * játékosokat tartalmazó list
     */
    private List<Player> players;
    /**
     * a játékban egy jegesmedve van
     */
    private PolarBear polarBear;
    /**
     * a játék controller
     */

    public void generate(GameController gameController, List<Player> players) {
        tiles.clear(); //a játékosok számától fog függni a mezők száma
        this.players = players;
        LevelGenerator generator = new LevelGenerator(gameController, players.size()); //legeneráljuk a játékosok számától függően a pályát
        for (Tile tile: generator.generate()) {
            tiles.put(tile.getId(), tile); //legeneráljuk a mezőket
        }
        this.levelTiles = generator.getLevelTiles();
        for (Player player: players) {
            player.spawnOnto(tiles.get(0)); // a játékosokat a nullás mezőre helyezzük
        }
        polarBear = generator.getPolarBear(); //legeneráljuk a jegesmedvét

        //minden játékos kap két food itemet, hogy könnyebb legyen a játék
        for (Player player: players) {
            player.takeItem(new Food(gameController));
            player.takeItem(new Food(gameController));
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
    public void loadGame(GameController gameController) {
        Scanner scanner = new Scanner(System.in);
        String line;
        line = scanner.nextLine().trim();
        while (!line.equals("")) {
            if (line.startsWith("#")) continue;
            DeserializedLine desLine = DeserializedLine.fromLine(line, new String[] { "Hole", "Sheet" });
            if (desLine == null) {
                System.out.println("Invalid input format (tile)!");
                System.exit(0);
            }
            deserializeTile(gameController, desLine);
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
            deserializeTileConnection(gameController, desLine);
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
            deserializePlayer(gameController, desLine);
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
            deserializePlayerItemConnection(gameController, desLine);
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
            deserializePolarBear(gameController, desLine);
            line = scanner.nextLine().trim();
        }
    }

    /**
     * A tile sorok beolvasása
     */
    private void deserializeTile(GameController gameController, DeserializedLine line) {
        String idStr = line.getParameters().get("ID");
        String snow = line.getParameters().get("snow");
        String discoveredStr = line.getParameters().get("discovered");
        try {
            int id = Integer.parseInt(idStr);
            if (line.getName().equals("Hole")) tiles.put(id, new Hole(gameController, id, Integer.parseInt(snow)));
            else {
                Item item = deserializeItem(gameController, line.getParameters().get("item"));
                String playerLimit = line.getParameters().get("playerLimit");
                boolean itemDiscovered = line.getParameters().get("itemDiscovered").equals("true");
                boolean playerLimitDiscovered = line.getParameters().get("playerLimitDiscovered").equals("true");
                Building building = Enum.valueOf(Building.class, line.getParameters().get("building"));
                IceSheet sheet = new IceSheet(gameController, id, Integer.parseInt(playerLimit), Integer.parseInt(snow));
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
    private Item deserializeItem(GameController gameController, String name) {
        if (name == null) return null;
        switch (name) {
            case "flare":
                return new WinItem(gameController, "Flare");
            case "flare gun":
                return new WinItem(gameController, "Flare gun");
            case "cartridge":
                return new WinItem(gameController, "Cartridge");
            case "food":
                return new Food(gameController);
            case "shovel":
                return new Shovel(gameController);
            case "breakable shovel":
                return new BreakableShovel(gameController);
            case "tent":
                return new Tent(gameController);
            case "scuba gear":
                return new ScubaGear(gameController);
            case "rope":
                return new Rope(gameController);
            default:
                return null;
        }
    }

    /**
     * A tile connection beolvasása
     */
    private void deserializeTileConnection(GameController gameController, DeserializedLine line) {
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
    private void deserializePlayer(GameController gameController, DeserializedLine line) {
        String numStr = line.getParameters().get("number");
        String heatStr = line.getParameters().get("heat");
        String tileIdStr = line.getParameters().get("tile");
        try {
            int num = Integer.parseInt(numStr);
            int heat = Integer.parseInt(heatStr);
            int tileId = Integer.parseInt(tileIdStr);
            Player player = line.getName().equals("Scientist") ? new Scientist(gameController, num, heat) : new Eskimo(gameController, num, heat);
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
    private void deserializePlayerItemConnection(GameController gameController, DeserializedLine line) {
        try {
            int num = Integer.parseInt(line.getParameters().get("number"));
            int count = Integer.parseInt(line.getParameters().get("count"));
            Item item = deserializeItem(gameController, line.getParameters().get("item"));
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
    private void deserializePolarBear(GameController gameController, DeserializedLine line) {
        String tileIdStr = line.getParameters().get("tile");
        try {
            int tileId = Integer.parseInt(tileIdStr);
            polarBear = new PolarBear(gameController);
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

    public int getPlayerCount() {
        return players.size();
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void blizzard(GameController gameController) {
        gameController.getConsoleView().writeLine("Blizzard is coming."); //kiírja, hogy jön a hóvihar
        int blizzard_tiles = 0; //a hóvihar súlytotta mezők számát tároló számláló
        int isBlizzardOnTile; //azt tárolja, hogy az aktuális mezőn jön-e a hóvihar
        for (Tile tile : tiles.values()) { //végigmegy az összes mezőn
            isBlizzardOnTile = gameController.getRandom().nextInt(2); //véletlenszerűen sorsolja, hogy a mezőn lesz-e hóvihar vagy sem
            if (isBlizzardOnTile == 1) { //ha 1 az értéke, akkor jön hóvihar a mezőre
                tile.blizzard(); //ekkor meghívjuk az aktuális mezőn a mező blizzard függvényét
                gameController.getConsoleView().writeLine("Snowfall on Tile(ID=" + tile.getId() + ")."); //ekkor kiírjuk, hogy hóvihar jön az aktuális mezőre
                blizzard_tiles++; //növeljük a hóvihar súlytotta mezők számát tároló számlálót
            }
            if (blizzard_tiles == tiles.size() / 2) // ha a hóvihar által súlytott mezők száma elérte a mezők számának felét, akkor nem súlyt több mezőt hóvihar
                break;
        }
    }

    public void movePolarBear() {
        this.polarBear.move();
    }

    public void destroyTiles() {
        for(Tile tile: tiles.values()){
            tile.destroyTent();
        }
    }

    public LevelTile getLevelTileFor(Tile tile) {
        return levelTiles.get(tile);
    }

    public Collection<Tile> getTiles(){
        return tiles.values();
    }

    public int getLevelTileX(LevelTile levelTile){
        return levelTile.x;
    }

    public int getLevelTileY(LevelTile levelTile){
        return levelTile.y;
    }

}
