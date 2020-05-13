package jegmezo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//ezt az osztályt még tanácsos lenne szétszedni - Blizzard Overlay és GameOver Overlay
public class OverlayView extends View{
    private String type;
    public OverlayView(ImageManager imageManager, String _type) {
        super(imageManager);
        type = _type;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) throws IOException {
        String imageURL = "eskimo-003-512.png";
        BufferedImage image = null;
        image = ImageIO.read(new File(imageURL));
        graphics.drawImage(image, 0, 0, 25, 50, null);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
