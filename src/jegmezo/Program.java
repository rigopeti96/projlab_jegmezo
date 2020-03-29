package jegmezo;

import java.util.Scanner;

public class Program {
    static GameController gameController = new GameController();
    public static void main(String[] args) {
        System.out.println("Select action");
        switch (new Scanner(System.in).nextLine()) {
            case "move":
                move();
                break;
            case "trade":
                trade();
                break;
            case "use item":
                useItem();
                break;
            case "pickup":
                pickup();
                break;
            case "dig with hands":
                digWithHands();
                break;
            case "blizzard":
                blizzard();
                break;
            case "build igloo":
                buildIgloo();
                break;
            case "examine":
                examine();
                break;
        }
    }

    private static void move() {
        Tile tile = new IceSheet(gameController, 4);
        tile.connectTile(new IceSheet(gameController, 2));
        tile.connectTile(new Hole(gameController));
        Eskimo eskimo = new Eskimo(gameController, tile);
        eskimo.move();
    }

    private static void trade() {
        Tile tile = new IceSheet(gameController, 4);
        tile.connectTile(new IceSheet(gameController, 2));
        tile.connectTile(new Hole(gameController));
        Eskimo eskimo = new Eskimo(gameController, tile);
        eskimo.trade();
    }

    private static void useItem() {
        Tile tile = new IceSheet(gameController, 4);
        Eskimo eskimo = new Eskimo(gameController, tile);
        eskimo.useItem();
    }

    private static void pickup() {
        Tile tile = new IceSheet(gameController, 4);
        tile.connectTile(new IceSheet(gameController, 2));
        tile.connectTile(new Hole(gameController));
        Eskimo eskimo = new Eskimo(gameController, tile);
        eskimo.pickup();
    }

    private static void digWithHands() {
        Tile tile = new IceSheet(gameController, 4);
        tile.connectTile(new IceSheet(gameController, 2));
        tile.connectTile(new Hole(gameController));;
        Eskimo eskimo = new Eskimo(gameController, tile);
        eskimo.digWithHands();
    }

    private static void blizzard() {
        Tile tile = new IceSheet(gameController, 4);
        tile.connectTile(new IceSheet(gameController, 2));
        tile.connectTile(new Hole(gameController));
        Eskimo eskimo = new Eskimo(gameController, tile);
        gameController.blizzard();
    }

    private static void buildIgloo() {
        Tile tile = new IceSheet(gameController, 4);
        tile.connectTile(new IceSheet(gameController, 2));
        tile.connectTile(new Hole(gameController));
        Eskimo eskimo = new Eskimo(gameController, tile);
        eskimo.buildIgloo();
    }

    private static void examine() {
        Tile tile = new IceSheet(gameController, 4);
        tile.connectTile(new IceSheet(gameController, 2));
        tile.connectTile(new Hole(gameController));
        Scientist scientist = new Scientist(gameController, tile);
        scientist.examine();
    }
}