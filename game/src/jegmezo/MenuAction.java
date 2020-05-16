package jegmezo;

public class MenuAction {
    private String text;
    private Runnable callback;

    public MenuAction(String text, Runnable callback) {
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