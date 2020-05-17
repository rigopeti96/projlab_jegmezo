package jegmezo.view;

import jegmezo.model.Player;
import jegmezo.model.Scientist;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ScientistView extends PlayerView{

    public ScientistView(GameWindow gameWindow, AssetManager assetManager, Scientist player) {
        super(gameWindow, assetManager, player);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("scientist"), x-20, y-20, 25, 25, null);
    }

    public void mouseMoved(MouseEvent event){
        super.mouseMoved(event);
        toolTip.setText("Player " + player.getNumber() + " (Scientist)\nBody heat: " + player.getHeat());
    }
}
