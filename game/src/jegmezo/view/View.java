package jegmezo.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.List;

/**
 * A Különböző view-k ősosztálya. Abstarct osztály
 */
public abstract class View {
    /**
     * Tárolja, hogy a View fölött áll e az egér.
     */
    protected boolean hovered;
    /**
     * Az adott View gyerekeit tárolja.
     */
    protected List<View> children = new ArrayList<>();

    /**
     *  Tárolja az assetManagert.
     */
    protected AssetManager assetManager;
    /**
     * A játék gameWindow-ja
     */
    GameWindow gameWindow;

    /**
     * Konstruktor. Beállítja a gameWindow-t és az assetManager-t.
     * @param gameWindow
     * @param assetManager
     */
    public View(GameWindow gameWindow, AssetManager assetManager) {
        this.gameWindow = gameWindow;
        this.assetManager = assetManager;
    }

    /**
     * Eldönti hogy ennek a View-nak kell kezelnie a kattintást, vagy valameilyk gyerekének.
     * @param event
     * @return
     */
    public boolean handleClick(MouseEvent event) {
        MouseEvent originalEvent = event;
        event = remapMouseEvent(event);
        if (isMouseOver(event.getX(), event.getY()) && helperClicked(event)) {
            return true;
        }

        for (View child: children) {
            if (child.handleClick(originalEvent)) return true;
        }

        return false;
    }

    /**
     * Megnézi hogy a megfelelő egérgombot nyomták e meg, és hogy a view használja e valamire azt a gombot.
     * @param event a MouseEvent
     * @return igaz, ha használja a view a gombot.
     */
    private boolean helperClicked(MouseEvent event) {
        return (event.getButton() == MouseEvent.BUTTON1 && clicked(event)) || (event.getButton() == MouseEvent.BUTTON3 && rightClicked(event));
    }

    /**
     * Lekezeli a egérmozgatást, illetve továbbadja a gyerekinek.
     * @param event a mouseEvent
     */
    public void handleMouseMove(MouseEvent event) {
        MouseEvent originalEvent = event;
        event = remapMouseEvent(event);
        if (isMouseOver(event.getX(), event.getY())) {
            if (!hovered) {
                mouseEnter(event);
                hovered = true;
            }
            mouseMoved(event);
        } else {
            if (hovered) {
                mouseLeave(event);
                hovered = false;
            }
        }

        for (View child: new ArrayList<>(children)) {
            child.handleMouseMove(originalEvent);
        }
    }

    /**
     * Áttalakítja a mouseEvent koordinátáit koordináta rendszerbeli koordinátákká.
     * @param event a MouseEvent
     * @return
     */
    private MouseEvent remapMouseEvent(MouseEvent event) {
        Point transformed = new Point(event.getX(), event.getY());
        if (isAffectedByTransformation()) {
            try {
                gameWindow.getTransformation().inverseTransform(new Point(event.getX(), event.getY()), transformed);
            } catch (NoninvertibleTransformException e) {

            }
        }
        return new MouseEvent(event.getComponent(), event.getID(), event.getWhen(), event.getModifiersEx(), (int)transformed.getX(), (int)transformed.getY(), event.getClickCount(), false, event.getButton());
    }

    /**
     * Kattintottak-e az egérrel
     * @param event a mouseEvent
     */
    public boolean clicked(MouseEvent event) {
        return false;
    }

    /**
     * Kattintottak-e a jobbegérrel
     * @param event a mouseEvent
     */
    public boolean rightClicked(MouseEvent event) {
        return false;
    }

    /**
     * Meghívja a gyerekei WindowClick-jét.
     * @param event a MouseEvent
     */
    public void windowClicked(MouseEvent event) {
        for (View child: new ArrayList<>(children)) {
            child.windowClicked(event);
        }
    }

    /**
     * Elmozdul a egér
     * @param event
     */
    public void mouseMoved(MouseEvent event) {

    }

    /**
     * Használták az egér görgőjét, meghívja a gyerekei mouseWheelMoved-ját.
     * @param event
     */
    public void mouseWheelMoved(MouseWheelEvent event) {
        for (View child: new ArrayList<>(children)) {
            child.mouseWheelMoved(event);
        }
    }

    /**
     * Az egér a Viewba kerül
     * @param event
     */
    public void mouseEnter(MouseEvent event) {

    }

    /**
     * Az egér kilép a viewból
     * @param event
     */
    public void mouseLeave(MouseEvent event) {

    }

    /**
     * Visszaadja a hovered-et.
     * @return
     */
    public boolean isHovered() {
        return hovered;
    }

    /**
     * Abstart fgv. Az egér a View fölött áll e.
     * @param  x az egér x koordinátája
     * @param y  egér y koordinátája
     */
    public abstract boolean isMouseOver(int x, int y);

    /**
     * Abstract fgv. Megmondja hogy a View-t befolyásolja e a transformáció.
     * @return
     */
    public abstract boolean isAffectedByTransformation();

    /**
     * Kirajzolja a View-t és meghivja a gyerekei draw-ját is.
     * @param graphics a kirajzoló osztály
     * @param overlay van-e valami a View "fölött"
     */
    public void draw(Graphics2D graphics, boolean overlay) {
        if (isAffectedByTransformation()) graphics.setTransform(gameWindow.getTransformation());
        else graphics.setTransform(new AffineTransform());
        for (View child: new ArrayList<>(children)) {
            child.draw(graphics, overlay);
        }
    }

    /**
     * Egy gyerek hozzáadása a listához.
     * @param child
     */
    public void addChild(View child) {
        children.add(child);
    }

    /**
     * Egy gyerek kivétele a listából.
     * @param child
     */
    public void removeChild(View child) {
        children.remove(child);
    }
}

