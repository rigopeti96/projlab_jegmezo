package jegmezo.view;

import jegmezo.model.NamedAction;

import java.util.List;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Kiírja a játékos eseményeit egy menülistában
 */
public class MenuView extends View {
    /**
     * A kirajzolás koordinátái
     */
    private int x, y;

    /**
     * Konstruktor
     * @param gameWindow játékablak
     * @param assetManager képkirajzoló
     */
    public MenuView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
    }

    /**
     * beállítja a kiírandó menüelemeket
     * @param items a kiírandó menüelemek
     */
    public void setActionList(List<NamedAction> items) {
        int y = 0;
        children.clear();
        for (NamedAction item : items) {
            children.add(new MenuItemView(gameWindow, assetManager, () -> new Point(x, this.y), 0, y, item.getText(), () -> {
                item.getCallback().run();
                this.gameWindow.closeMenu();
            }));
            y += 30;
        }
    }

    /**
     * beálítja az x értéket
     * @param x a beállítandó érték
     */
    public void setX(int x) {
        this.x = x - 100;
    }

    /**
     * beálítja az y értéket
     * @param y a beállítandó érték
     */
    public void setY(int y){
        this.y = y - children.size() * 30;
    }

    /**
     * klikk event hatására meghívódik a függvény
     * @param event az egéresemény
     */
    @Override
    public void windowClicked(MouseEvent event) {
        super.windowClicked(event);
        if (!hovered) this.gameWindow.closeMenu();
    }

    /**
     * A kirajzolást végzá függvény
     * @param graphics a grafikát megvalósítő osztály
     * @param overlay ha átfedésben van, akkor visszatér, ha nincs, akkor rajzol ki bármit
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
    }

    /**
     * A klikkeléskor kirajzolást valósítja meg
     * @param x x koordináta
     * @param y y koordináta
     * @return visszatér a kirajzolással (true esetén kirajzolt valamit, egyébként false)
     */
    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 100, children.size() * 30).contains(x, y);
    }

    /**
     * Történt-e transzformáció - mindig false
     * @return mindig false
     */
    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }
}
