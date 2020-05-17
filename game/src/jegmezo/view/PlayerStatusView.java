package jegmezo.view;

import jegmezo.model.Player;

import java.awt.*;

/**
 * A konzol feletti játékos-státuszt kezeli, kiírja, hogy melyik játékos van épp soron és hány lépése van még
 */
public class PlayerStatusView extends View {
    /**
     * Konstruktor
     * @param gameWindow játékablak
     * @param assetManager képkirajzoló
     */
    public PlayerStatusView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
    }

    /**
     * A kirajzolást végzá függvény
     * @param graphics a grafikát megvalósítő osztály
     * @param overlay ha átfedésben van, akkor visszatér, ha nincs, akkor rajzol ki bármit
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if(!overlay) return;
        Player activePlayer = gameWindow.getGameController().getActivePlayer();
        DrawUtils du = new DrawUtils(graphics);
        graphics.setColor(Color.DARK_GRAY);
        String lines = "Player " + activePlayer.getNumber() + "'s turn\n" + activePlayer.getAP() + " step" + (activePlayer.getAP() == 1 ? "" : "s") + " remaining";
        Rectangle rectangle = new Rectangle(GameWindow.windowWidth - 330, 0, 300, 40);
        du.drawStringRectangle(lines, assetManager.getFont(24), 1.2f, du.padding(rectangle, 4), VerticalAlignment.Top, HorizontalAlignment.Right);
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
