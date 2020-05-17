package jegmezo.model;

/**
 * NamedAction osztálya
 */
public class NamedAction {
    /**
     * eltárolt szöveg
     */
    private String text;
    /**
     * a callback-je
     */
    private Runnable callback;

    /**
     * A NamedAction konstruktora
     * @param text - megkapja a szöveget
     * @param callback - megkapja a callback-jét
     */
    public NamedAction(String text, Runnable callback) {
        this.text = text;
        this.callback = callback;
    }

    /**
     * visszaadja az eltárolt szöveget
     * @return - String, a szöveg
     */
    public String getText() {
        return text;
    }

    /**
     * visszaadja az eltárolt callbacket
     * @return - runnable, a callback
     */
    public Runnable getCallback() {
        return callback;
    }
}