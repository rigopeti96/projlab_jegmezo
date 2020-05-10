package jegmezo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ImageManager {
    Map<String, Image> imageMap = new HashMap<>();
    public void loadImage(String name, String fileName) {
        try {
            imageMap.put(name, ImageIO.read(Path.of(fileName).toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getImage(String name) {
        return imageMap.get(name);
    }
}
