package jegmezo;

/**
 * A belépési pontja a programnak
 */
public class Program {
    /**
     * A gameController
     */
    static GameController gameController = new GameController();

    /**
     * A main függvénye a programnak
     * @param args
     */
    public static void main(String[] args) {
        gameController.startGame();
    }
}