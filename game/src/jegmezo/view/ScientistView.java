package jegmezo.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScientistView extends PlayerView{

    public ScientistView(AssetManager assetManager) {
        super(assetManager);
        tooltip = new TooltipView(assetManager, "Player " + player.getNumber() + " (Scientist)");
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        // TODO
    }
}
