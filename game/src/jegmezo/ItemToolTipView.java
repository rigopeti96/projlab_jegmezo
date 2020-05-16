package jegmezo;

import java.awt.*;

public class ItemToolTipView extends View {
    private int x, y;
    private String text;

    ItemToolTipView(AssetManager assetManager, int x, int y, String text) {
        super(assetManager);
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
        if (!overlay) return;

        graphics.setColor(Color.WHITE);
        float opacity = 0.8f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        graphics.fillRect(x,y, 70, 30);

        graphics.setColor(Color.DARK_GRAY);
        opacity = 1.0f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        graphics.setFont(assetManager.getFont());
        graphics.drawString(text,x+5,y+16);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
