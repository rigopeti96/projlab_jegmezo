package jegmezo;

import java.awt.*;

public class ItemToolTipView extends View {
    private int x, y;
    private String text;

    ItemToolTipView(AssetManager assetManager, String text) {
        super(assetManager);
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

        DrawUtils drawUtils = new DrawUtils(graphics);
        graphics.setColor(Color.WHITE);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        graphics.fillRect(x, y, 100, 30);

        graphics.setColor(Color.DARK_GRAY);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        drawUtils.drawStringRectangle(text, assetManager.getFont(), 1.2f, drawUtils.padding(new Rectangle(x, y, 100, 30), 4), VerticalAlignment.Center, HorizontalAlignment.Left);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
