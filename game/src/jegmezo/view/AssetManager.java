package jegmezo.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    Font font = new Font("Calibri", Font.BOLD, 11);
    Map<String, BufferedImage> imageMap = new HashMap<>();
    public void loadImage(String name, String fileName) {
        try {
            imageMap.put(name, ImageIO.read(Path.of(fileName).toFile()));
            imageMap.put(name + "-grayscale", toGrayScale(ImageIO.read(Path.of(fileName).toFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Font getFont() {
        return font;
    }

    public Font getFont(float size) {
        return font.deriveFont(size);
    }

    public BufferedImage getImage(String name) {
        return imageMap.containsKey(name) ? imageMap.get(name) :  this.getImage("missingTexture");
    }

    public BufferedImage getImageGrayScale(String name) {
        return imageMap.containsKey(name) ? imageMap.get(name + "-grayscale") :  this.getImage("missingTexture-grayscale");
    }

    private BufferedImage toGrayScale(BufferedImage master) {
        if (master == null) master = this.getImage("missingTexture");
        BufferedImage gray = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(master, gray);

        return gray;
    }
}
