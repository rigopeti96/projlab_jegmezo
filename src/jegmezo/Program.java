package jegmezo;

public class Program {
    public static void main(String[] args) {
        String choice=System.console().readLine();
        GameController gameController = new GameController();
        Tile tile = new IceSheet(4);
        tile.connectTile(new IceSheet(2));
        tile.connectTile(new Hole());
        Eskimo eskimo = new Eskimo(gameController, tile);
        switch (choice) {
            case "move":
                eskimo.move();
                break;
            case "trade":
                eskimo.trade();
                break;
            case "use item":
                eskimo.useItem();
                break;
            case "pickup":
                eskimo.pickup();
                break;
            case "dig with hands":
                eskimo.digWithHands();
                break;
            case "blizzard":
                gameController.blizzard();
                break;
            case "build igloo":
                eskimo.buildIgloo();
                break;
            case "examine":
                Scientist scientist = new Scientist(gameController, tile);
                scientist.examine();
                break;
        }
    }
}