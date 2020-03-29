package jegmezo;

/** Eszkimó létrehozó, a jégmezőn a mentéshez kell a skeleton folyamán*/
public class EskimoCreator {

    public static Eskimo create(Tile t){
        return new Eskimo(new GameController(),t);
    }
}
