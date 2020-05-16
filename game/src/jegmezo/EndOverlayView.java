package jegmezo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EndOverlayView extends View {
    public EndOverlayView(ImageManager imageManager) {
        super(imageManager);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) throws IOException {
        String winImageURL = "win.png";
        String loseImageURL = "lose.png";
        BufferedImage image = null;
        if(true){
            //win - megvalósístandó
            image = ImageIO.read(new File(winImageURL));
            graphics.drawImage(imageManager.getImage("image"), 0, 0, 25, 50, null);
        }
        else{
            //lose - megvalósítandó
            image = ImageIO.read(new File(winImageURL));
            graphics.drawImage(imageManager.getImage("image"), 0, 0, 25, 50, null);
        }
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
