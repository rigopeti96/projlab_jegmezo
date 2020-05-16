package jegmezo;

import jegmezo.view.GameWindow;

/**
 * A program
 */
public class Program {
    /**
     * A belépési pontja a programnak
     * @param args program agrugmentumok (a program nem vár el illetve használ paramétert)
     */
    public static void main(String[] args) {
        new GameWindow().start();
    }
}