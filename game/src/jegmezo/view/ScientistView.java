package jegmezo.view;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ScientistView extends PlayerView{

    public ScientistView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("scientist"), x-20, y-20, 25, 25, null);
    }

    public void mouseEnter(MouseEvent event){
        toolTip.setText("Player " + player.getNumber() + " (Scientist)");
        toolTip.setShow(true);
    }
}
