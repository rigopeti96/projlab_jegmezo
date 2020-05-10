package jegmezo;

import java.awt.*;

public class ItemToolTipView extends View {
    private int x, y;
    private String text;

    ItemToolTipView(ImageManager imageManager, int x, int y, String text) {
        super(imageManager);
        this.x = x;
        this.y = y;
        this.text = text;
    }


    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }


    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (overlay) return;

        graphics.setColor(Color.WHITE);
        float opacity = 0.8f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        graphics.fillRect(x,y, 80, 65);

        graphics.setColor(Color.DARK_GRAY);
        opacity = 1.0f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        Font font = new Font("Serif", Font.BOLD, 11);
        graphics.setFont(font);
        graphics.drawString(text,x+5,y+5);
    }
}
