package jegmezo.view;

import jegmezo.model.Eskimo;
import jegmezo.model.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EskimoView extends PlayerView {

    public EskimoView(GameWindow gameWindow, AssetManager assetManager, Eskimo player) {
        super(gameWindow, assetManager, player);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if (overlay) return;
        graphics.drawImage(assetManager.getImage("eskimo"), x -20, y -20, 25, 25, null);
    }

    public void mouseMoved(MouseEvent event){
        super.mouseMoved(event);
        toolTip.setText("Player " + player.getNumber() + " (Eskimo)\nBody heat: " + player.getHeat());
    }

}
