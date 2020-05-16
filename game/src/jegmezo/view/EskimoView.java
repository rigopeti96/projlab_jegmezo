package jegmezo.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EskimoView extends PlayerView {

    public EskimoView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("eskimo"), x, y, 100, 100, null);
    }

    public void mouseEnter(MouseEvent event){
        toolTip.setText("Player " + player.getNumber() + " (Eskimo)");
        toolTip.setShow(true);
    }

}
