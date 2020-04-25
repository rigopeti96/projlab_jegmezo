package jegmezo;

public class Discoverable<T> {
    String nullString;
    boolean discovered;
    T element;

    public Discoverable(T element) {
        this.element = element;
        this.nullString = "";
    }

    public Discoverable(T element, String nullString) {
        this.element = element;
    }

    public String toString() {
        return discovered ? toDiscoveredString() : "?";
    }

    public String toDiscoveredString() {
        return element != null ? element.toString() : this.nullString;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void discover() {
        discovered = true;
    }

    public void discover(Runnable callback) {
        if (discovered) return;
        callback.run();
        discover();
    }

    public boolean isDiscovered() {
        return discovered;
    }
}
