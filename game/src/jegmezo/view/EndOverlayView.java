package jegmezo.view;

import java.awt.*;

/**
 * A játék végén milyen képet tegyen ki a game
 */
public class EndOverlayView extends View {
    /**
     * A győzelmet jelzi, ez dönti el, hogy milyen képet kell kirajzolni
     */
    private boolean win = false;

    /**
     * Konstruktor
     * @param gameWindow játékablak
     * @param assetManager képkirajzoló
     * @param win győzelem/vereség állítása (győzelem = TRUE, vereség = FALSE)
     */
    public EndOverlayView(GameWindow gameWindow, AssetManager assetManager, boolean win) {
        super(gameWindow, assetManager);
        this.win = win;
    }

    /**
     * A kirajzolást végzá függvény
     * @param graphics a grafikát megvalósítő osztály
     * @param overlay ha átfedésben van, akkor visszatér, ha nincs, akkor rajzol ki bármit
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if(win){
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect(0, 0, gameWindow.windowWidth, gameWindow.windowHeight);
            graphics.drawImage(assetManager.getImage("gameWin"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        }
        else{
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.drawImage(assetManager.getImage("gameOver"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        }
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
}
