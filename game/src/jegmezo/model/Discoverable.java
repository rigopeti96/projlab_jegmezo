package jegmezo.model;

/**
 * Generikus osztály, aminek a belső eleme felfedezhető
 * A felfedezhetőség kiírásában segít
 * @param <T> Belső elem (element) típusa
 */
public class Discoverable<T> {
    /**
     * Null esetén használt string (toString és toDisconveredString)-ben
     */
    String nullString;
    /**
     * Fel van-e fedezve
     */
    boolean discovered;
    /**
     * A belső elem
     */
    T element;

    /**
     * @param element A belső elem
     */
    public Discoverable(T element) {
        this(element, "");
    }

    /**
     * @param element A belső elem
     * @param nullString Null esetén használt string (toString és toDisconveredString)-ben
     */
    public Discoverable(T element, String nullString) {
        this.element = element;
        this.nullString = nullString;
    }

    /**
     * Standard stringgé konvertálás
     * @return Ha discovered akkor toDiscoveredString) egyébként ?
     */
    @Override
    public String toString() {
        return discovered ? toDiscoveredString() : "?";
    }

    /**
     * A belső elem stringgé konvertálása (kiegészítve a null stringgel)
     * @return Ha nem null a belső elem egyébként nullString
     */
    public String toDiscoveredString() {
        return element != null ? element.toString() : this.nullString;
    }

    /**
     * @return A belső elem
     */
    public T getElement() {
        return this.element;
    }

    /**
     * @param element A belső elem
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Felfedezi a Discoverablet
     */
    public void discover() {
        discovered = true;
    }

    /**
     * Feldefedi a discoverablet
     * @param callback Ha még nem volt felfedezve illetve volt mit, akkor meghívodik
     */
    public void discover(Runnable callback) {
        if (discovered || element == null) return;
        callback.run();
        discover();
    }

    /**
     * @return Fel van-e fedezve
     */
    public boolean isDiscovered() {
        return discovered;
    }
}
