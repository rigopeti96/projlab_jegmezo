package jegmezo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//ezt az osztályt még tanácsos lenne szétszedni - Blizzard Overlay és GameOver Overlay
public class BlizzardOverlayView extends View{
    private String type;
    public BlizzardOverlayView(ImageManager imageManager, String _type) {
        super(imageManager);
        type = _type;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) throws IOException {
        String blizzardImageURL = "blizzard.png";
        BufferedImage blizzardOverlay = null;
        blizzardOverlay = ImageIO.read(new File(blizzardImageURL));
        graphics.drawImage(imageManager.getImage("blizzardOverlay"), 0, 0, 25, 50, null);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
