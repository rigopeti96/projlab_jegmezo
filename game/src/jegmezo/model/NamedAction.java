package jegmezo.model;

public class NamedAction {
    private String text;
    private Runnable callback;

    public NamedAction(String text, Runnable callback) {
        this.text = text;
        this.callback = callback;
    }

    public String getText() {
        return text;
    }

    public Runnable getCallback() {
        return callback;
    }
}