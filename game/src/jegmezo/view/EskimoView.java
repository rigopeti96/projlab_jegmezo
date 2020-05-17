package jegmezo.view;

import jegmezo.model.Eskimo;
import jegmezo.model.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
/**
 * Az Eskimo megjelenítéséért felelős osztály.
 */
public class EskimoView extends PlayerView {

    /**
     * A EskimoView konstruktora. Meghivja az ős konstruktorát.
     * @param gameWindow a játék gameWindow-ja
     * @param assetManager a játék assetManager-je
     * @param player a player, akié a view
     */
    public EskimoView(GameWindow gameWindow, AssetManager assetManager, Eskimo player) {
        super(gameWindow, assetManager, player);
    }

    /**
     * Kirajzolja az Eskimo ikonját a TileView koordinátáitól függöen, és ha nem takarja el semmi más az ikont.
     * @param graphics a kirajzolást végző osztály
     * @param overlay van-e valami az ikon "fölött"
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if (overlay) return;
        graphics.drawImage(assetManager.getImage("eskimo"), x -20, y -20, 25, 25, null);
    }

    /**
     * Az egér elmozdul a player ikonja fölött, arréb mozdítja a tooltip-et is.
     * @param event a mouseEvent
     */
    public void mouseMoved(MouseEvent event){
        super.mouseMoved(event);
        toolTip.setText("Player " + player.getNumber() + " (Eskimo)\nBody heat: " + player.getHeat());
    }

    /**
     * A transformációk befolyásolják e a View-t.
     * @return mindig igaz
     */
    @Override
    public boolean isAffectedByTransformation() {
        return true;
    }

}
