package jegmezo.view;

import java.awt.*;

/**
 * A Blizzard esetén ez az overlay jelenik meg
 */
public class BlizzardOverlayView extends View {
    /**
     * Konstruktor
     * @param gameWindow játékablak
     * @param assetManager képkirajzoló
     */
    public BlizzardOverlayView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
    }

    /**
     * A kirajzolást végzá függvény
     * @param graphics a grafikát megvalósítő osztály
     * @param overlay ha átfedésben van, akkor visszatér, ha nincs, akkor rajzol ki bármit
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("blizzardOverlay1"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        graphics.drawImage(assetManager.getImage("blizzardOverlay2"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);

        graphics.setColor(Color.BLUE);
        graphics.setFont(assetManager.getFont("Blizzard"));
        graphics.drawString("Jön a HAV!", GameWindow.windowWidth/2-200, GameWindow.windowHeight/2-5);
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
