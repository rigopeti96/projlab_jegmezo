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
        Rectangle rectangle = drawUtils.calculateStringBounds(text.split("\n"), assetManager.getFont(), 1.2f);
        rectangle.setSize((int)rectangle.getWidth() + 8, (int)rectangle.getHeight() + 8);
        rectangle.setLocation(x - (int)rectangle.getWidth(), y - (int)rectangle.getHeight());
        graphics.setColor(Color.WHITE);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        graphics.fill(rectangle);

        graphics.setColor(Color.DARK_GRAY);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        drawUtils.drawStringRectangle(text, assetManager.getFont(), 1.2f, drawUtils.padding(rectangle, 4), VerticalAlignment.Top, HorizontalAlignment.Left);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
