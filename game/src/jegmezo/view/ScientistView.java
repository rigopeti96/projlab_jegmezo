package jegmezo.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScientistView extends PlayerView{

    public ScientistView(GameWindow gameWindow, AssetManager assetManager, int x, int y) {
        super(gameWindow, assetManager, x, y);
        tooltip = new TooltipView(gameWindow, assetManager, "Player " + player.getNumber() + " (Scientist)");
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("scientist"), x, y, 100, 100, null);
    }
}
