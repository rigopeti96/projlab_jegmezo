package jegmezo;

import java.awt.*;

public class PlayerToolTipView extends View{

    private String text;
    private int x,y;

    public PlayerToolTipView(ImageManager imageManager) {
        super(imageManager);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (!overlay) return;

        graphics.setColor(Color.WHITE);
        float opacity = 0.8f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        graphics.fillRect(x,y, 70, 30);

        graphics.setColor(Color.DARK_GRAY);
        opacity = 1.0f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        Font font = new Font("Calibri", Font.BOLD, 11);
        graphics.setFont(font);
        graphics.drawString(text,x+5,y+16);
    }
}
