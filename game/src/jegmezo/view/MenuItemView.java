package jegmezo.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

/**
 * a MenuItemView osztály, leszármazik a View-ból
 */
public class MenuItemView extends View {
    /**
     * a parentPointGetter
     */
    private Supplier<Point> parentPointGetter;
    /**
     * a menu x és y koordinátája
     */
    private int x, y;
    /**
     * a menü szövege
     */
    private String text;
    /**
     * a menü callback-je
     */
    Runnable callback;

    /**
     * a menü konstruktora
     * @param gameWindow - kell a view-nak a gameWindow
     * @param assetManager - kell a view-nak a assetManager
     * @param parentPointGetter - a parentPointGetter
     * @param x - az x koordináta
     * @param y - az y koordináta
     * @param text - a szöveg
     * @param callback - a callback
     */
    public MenuItemView(GameWindow gameWindow, AssetManager assetManager, Supplier<Point> parentPointGetter, int x, int y, String text, Runnable  callback) {
        super(gameWindow, assetManager);
        this.parentPointGetter = parentPointGetter;
        this.x = x;
        this.y = y;
        this.text = text;
        this.callback = callback;
    }

    /**
     * a klikkelést lekezelő függvény
     * @param event - az esemény
     * @return - boolean
     */
    @Override
    public boolean clicked(MouseEvent event) {
        this.callback.run();
        return true;
    }

    /**
     * a kirajzolást lebonyolító függvény
     * @param graphics - a graphics
     * @param overlay - az overlayt jelző boolean
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if (!overlay) return;

        Point parentPoint = this.parentPointGetter.get();
        DrawUtils drawUtils = new DrawUtils(graphics);
        graphics.setColor(Color.WHITE);
        int actualX = (int)parentPoint.getX() + x;
        int actualY = (int)parentPoint.getY() + y;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        graphics.fillRect(actualX, actualY, 100, 30);

        graphics.setColor(Color.DARK_GRAY);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        drawUtils.drawStringRectangle(text, assetManager.getFont(), 1.2f, new Rectangle(actualX, actualY, 100, 30), VerticalAlignment.Center, HorizontalAlignment.Center);
    }

    /**
     * visszaadja, hogy felette van-e a kurzor
     * @param x - a kurzor x koordinátája
     * @param y - a kurzor y koordinátája
     * @return - boolean
     */
    @Override
    public boolean isMouseOver(int x, int y) {
        Point parentPoint = this.parentPointGetter.get();
        return new Rectangle((int)parentPoint.getX() + this.x, (int)parentPoint.getY() + this.y, 100, 30).contains(x, y);
    }

    /**
     * visszaadja hogy hat-e rá a transzformáció
     * @return - boolean
     */
    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }
}
