package jegmezo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EskimoView extends PlayerView {

    public EskimoView(ImageManager imageManager) {
        super(imageManager);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        String imageURL = "test_texture.png";
        BufferedImage image= null;
        try {
            image = ImageIO.read(new File(imageURL));
        }catch(IOException ex) {

        }
        graphics.drawImage(image, x, y, 25, 50, null);
    }
}
