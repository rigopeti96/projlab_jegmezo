package jegmezo.view;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * A DrawUtils osztály, a kirajzoláshoz szükséges dolgokat valósítja meg
 */
public class DrawUtils {
    /**
     * a graphics a kirajzoláshoz
     */
    Graphics2D graphics;

    /**
     * az osztály konstruktora
     * @param graphics - a graphics
     */
    public DrawUtils(Graphics2D graphics) {
        this.graphics = graphics;
    }

    /**
     * ez a függvény szöveget rajzol ki
     * @param text - a szöveg
     * @param font - a betűtípus
     * @param lineHeight - a magassága
     * @param bounds - a bounds
     * @param verticalAlignment - az függőleges igazítás
     * @param horizontalAlignment - az vízszintes igazítás
     */
    public void drawStringRectangle(String text, Font font, float lineHeight, Rectangle bounds, VerticalAlignment verticalAlignment, HorizontalAlignment horizontalAlignment) {
        String[] lines = text.split("\n");
        Rectangle stringBounds = calculateStringBounds(lines, font, lineHeight);
        float y = (float)(bounds.getY() + Math.round(font.getSize() * (lineHeight - 1) / 2));
        float lineStep = Math.round(font.getSize() * lineHeight);

        if (verticalAlignment == VerticalAlignment.Bottom) {
            y += bounds.getHeight() - stringBounds.getHeight();
        } else if (verticalAlignment == VerticalAlignment.Center) {
            y += bounds.getHeight() / 2.0f - stringBounds.getHeight() / 2.0f - lineStep / 4;
        }

        graphics.setFont(font);
        for (String line: lines) {
            float x = (float)bounds.getX();

            if (horizontalAlignment == HorizontalAlignment.Right) {
                x += bounds.getWidth() - stringBounds.getWidth();
            } else if (horizontalAlignment == HorizontalAlignment.Center) {
                x += bounds.getWidth() / 2 - stringBounds.getWidth() / 2;
            }

            y += lineStep;
            graphics.drawString(line, x, y);
        }
    }

    /**
     * kiszámolja a szöveghez tartozó bound-ot
     * @param lines - egy sor
     * @param font - a betűtípus
     * @param lineHeight - a sor magassága
     * @return - a kiszámolt rectangle
     */
    public Rectangle calculateStringBounds(String[] lines, Font font, float lineHeight) {
        FontRenderContext frc = new FontRenderContext(null, true, true);
        int stringWidth = 0;
        int stringHeight = 0;
        for (String line: lines) {
            Rectangle2D stringBounds = font.getStringBounds(line, frc);
            stringWidth = Math.max(stringWidth, (int) Math.round(stringBounds.getWidth()));
            stringHeight += Math.round(font.getSize() * lineHeight);
        }
        return new Rectangle(0, 0, stringWidth, stringHeight);
    }

    /**
     * a padding-et számoló függvény
     * @param original - az eredeti rectangle
     * @param padding - a padding mérete
     * @return - a padding-elt rectangle
     */
    public Rectangle padding(Rectangle original, int padding) {
        return new Rectangle((int)original.getX() + padding, (int)original.getY() + padding, (int)original.getWidth() - 2 * padding, (int)original.getHeight() - 2 * padding);
    }
}
