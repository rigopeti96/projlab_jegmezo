package jegmezo;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class DrawUtils {
    Graphics2D graphics;
    public DrawUtils(Graphics2D graphics) {
        this.graphics = graphics;
    }

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

    public Rectangle padding(Rectangle original, int padding) {
        return new Rectangle((int)original.getX() + padding, (int)original.getY() + padding, (int)original.getWidth() - 2 * padding, (int)original.getHeight() - 2 * padding);
    }
}
