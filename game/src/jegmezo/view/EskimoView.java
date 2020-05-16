package jegmezo.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EskimoView extends PlayerView {

    public EskimoView(AssetManager assetManager) {
        super(assetManager);
        tooltip = new TooltipView(assetManager, "Player " + player.getNumber() + " (Eskimo)");
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("asdasda"), x, y, 25, 50, null);
    }
}
