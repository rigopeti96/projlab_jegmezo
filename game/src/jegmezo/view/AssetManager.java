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

/**
 * AssetManager osztály, képek, színek, betűtípusok és egyéb kirajzoláshoz szükséges dolgokat valósít meg
 */
public class AssetManager {
    /**
     * a használatban levő betűtípus
     */
    Map<String, Font> fontMap = new HashMap<>();
    /**
     * a tárolt színeket tartalmazó map
     */
    Map<String, Color> colorMap = new HashMap<>();
    /**
     * a tárolt képeket tartalmazó map
     */
    Map<String, BufferedImage> imageMap = new HashMap<>();

    /**
     * betölti a használni kívánt asset-eket, betűtípus, stb.
     */
    public void loadAssets() {
        fontMap.put("Default", new Font("Calibri", Font.BOLD, 14));
        fontMap.put("Blizzard", new Font("Serif", Font.BOLD, 70));
        colorMap.put("Sea", new Color(137, 178, 238));
        colorMap.put("Snow", new Color(230, 230, 230));
        colorMap.put("highlight", new Color(30, 160, 30));

        loadImage("missingTexture", "images/missing_texture.png");
        loadImage("Rope", "images/rope.jpg");
        loadImage("Breakable shovel", "images/breakableshovel.png");
        loadImage("Food", "images/food.jpg");
        loadImage("Scuba gear", "images/scubagear.jpg");
        loadImage("Shovel", "images/shovel.jpg");
        loadImage("Win item", "images/winitem.jpg");
        loadImage("Tent", "images/tent.png");
        loadImage("blizzardOverlay1", "images/snow1.png");
        loadImage("blizzardOverlay2", "images/snow2.png");
        loadImage("eskimo", "images/eskimo-003-512.png");
        loadImage("scientist", "images/species-researcher-005-512.png");
        loadImage("gameOver", "images/lose.png");
        loadImage("gameWin", "images/win.jpg");
        loadImage("fog", "images/Fog-icon.png");
    }

    /**
     * egy képet tölt be
     * @param name - a tárolt kép neve
     * @param fileName - a kép elérési neve
     */
    private void loadImage(String name, String fileName) {
        try {
            imageMap.put(name, ImageIO.read(Path.of(fileName).toFile()));
            imageMap.put(name + "-grayscale", toGrayScale(ImageIO.read(Path.of(fileName).toFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * visszaadja az alap használt betűtípust
     * @return - a betűtípus
     */
    public Font getFont() {
        return getFont("Default");
    }

    /**
     * visszaadja az alap betűtípust, megadott betűmérettel
     * @param size - a betűméret
     * @return - a betűtípus
     */
    public Font getFont(float size) {
        return getFont("Default", size);
    }

    /**
     * visszaadja a kért betűtípust név szerint
     * @param name - a betűtípus neve
     * @return - a kért betűtípus
     */
    public Font getFont(String name) {
        return fontMap.containsKey(name) ? fontMap.get(name) : fontMap.get("Default");
    }

    /**
     * visszaadja a kért betűtípust a kért betűmérettel
     * @param name - a betűtípus neve
     * @param size - a betűméret
     * @return - a kért betűtípus
     */
    public Font getFont(String name, float size) {
        return fontMap.containsKey(name) ? fontMap.get(name).deriveFont(size) : fontMap.get("Default").deriveFont(size);
    }

    /**
     * visszaadja a kért színt, név szerint
     * @param name - a szín neve
     * @return - a kért szín
     */
    public Color getColor(String name) {
        return colorMap.getOrDefault(name, Color.black);
    }

    /**
     * visszaadja a képet, név szerint
     * @param name - a kép neve
     * @return - a kép
     */
    public BufferedImage getImage(String name) {
        return imageMap.containsKey(name) ? imageMap.get(name) : imageMap.get("missingTexture");
    }

    /**
     * visszaadja a képet, név szerint, fekete-fehér színekkel
     * @param name - a kép neve
     * @return - a fekete-fehér kép
     */
    public BufferedImage getImageGrayScale(String name) {
        return imageMap.containsKey(name) ? imageMap.get(name + "-grayscale") : imageMap.get("missingTexture-grayscale");
    }

    /**
     * egy képet fekete-fehérré alakít
     * @param master - az eredeti kép
     * @return - a fekete-fehér kép
     */
    private BufferedImage toGrayScale(BufferedImage master) {
        if (master == null) master = this.getImage("missingTexture");
        BufferedImage gray = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(master, gray);

        return gray;
    }
}
