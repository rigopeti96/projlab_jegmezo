package jegmezo;

public class Discoverable<T> {
    boolean discovered;
    T element;

    public Discoverable(T element) {
        this.element = element;
    }

    public String toString(String nullString) {
        return discovered ? (element != null ? element.toString() : nullString) : "?";
    }

    public T getElement() {
        return this.element;
    }

    public void discover() {
        discovered = true;
    }

    public void discover(Runnable callback) {
        if (discovered) return;
        callback.run();
        discover();
    }
}
