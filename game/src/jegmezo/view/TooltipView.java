package jegmezo.view;

import java.awt.*;

/**
 * Az eszközök esetében, ha a kép fölé mozgatjuk az egeret, látható, hogy mire való
 */
public class TooltipView extends View {
    /**
     * az egér pozíciója
     */
    private int x, y;
    /**
     * A tooltip szövege
     */
    private String text;
    /**
     * kimutassa-e a tooltipet - default: nem
     */
    boolean show = false;

    /**
     * Konstruktor
     * @param gameWindow játékablak
     * @param assetManager képkirajzoló
     * @param text kiírandó szöveg
     */
    TooltipView(GameWindow gameWindow, AssetManager assetManager, String text) {
        super(gameWindow, assetManager);
        this.text = text;
    }

    /**
     * Beállítja a szöveget
     * @param text kiírandó szöveg
     */
    void setText(String text){
        this.text = text;
    }

    /**
     * Beállítja, hogy mutassa-e a tooltipet
     * @param show beállítandó érték
     */
    void setShow(boolean show){
        this.show = show;
    }

    /**
     *Rajta van-e az egér - ennél az osztálynál nem releváns, mindig false értékkel tér vissza
     * @param x x koordináta
     * @param y x koordináta
     * @return mindig false
     */
    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }

    /**
     * Történt-e transzformáció - mindig false
     * @return mindig false
     */
    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }

    /**
     * A kirajzolást végzá függvény
     * @param graphics a grafikát megvalósítő osztály
     * @param overlay ha átfedésben van, akkor visszatér, ha nincs, akkor rajzol ki bármit
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if(show){
            super.draw(graphics, overlay);
            if (!overlay) return;

            DrawUtils drawUtils = new DrawUtils(graphics);
            Rectangle rectangle = drawUtils.calculateStringBounds(text.split("\n"), assetManager.getFont(), 1.2f);
            rectangle.setSize((int)rectangle.getWidth() + 8, (int)rectangle.getHeight() + 8);
            rectangle.setLocation(x - (int)rectangle.getWidth(), y - (int)rectangle.getHeight());
            graphics.setColor(Color.WHITE);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            graphics.fill(rectangle);

            graphics.setColor(Color.DARK_GRAY);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            drawUtils.drawStringRectangle(text, assetManager.getFont(), 1.2f, drawUtils.padding(rectangle, 4), VerticalAlignment.Top, HorizontalAlignment.Left);

        }
    }

    /**
     * Beállítja az x értékét
     * @param x beállítandó érték
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Beállítja az y értékét
     * @param y beállítandó érték
     */
    public void setY(int y){
        this.y = y;
    }
}
