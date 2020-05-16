package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.function.Supplier;

public class MenuItemView extends View {
    private Supplier<Point> parentPointGetter;
    private int x, y;
    private String text;
    Runnable callback;

    public MenuItemView(AssetManager assetManager, Supplier<Point> parentPointGetter, int x, int y, String text, Runnable  callback) {
        super(assetManager);
        this.parentPointGetter = parentPointGetter;
        this.x = x;
        this.y = y;
        this.text = text;
        this.callback = callback;
    }

    @Override
    public boolean clicked(MouseEvent event) {
        this.callback.run();
        return true;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if (!overlay) return;

        Point parentPoint = this.parentPointGetter.get();
        DrawUtils drawUtils = new DrawUtils(graphics);
        graphics.setColor(Color.WHITE);
        int actualX = (int)parentPoint.getX() + x;
        int actualY = (int)parentPoint.getY() + y;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        graphics.fillRect(actualX, actualY, 100, 30);

        graphics.setColor(Color.DARK_GRAY);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        drawUtils.drawStringRectangle(text, assetManager.getFont(), 1.2f, new Rectangle(actualX, actualY, 100, 30), VerticalAlignment.Center, HorizontalAlignment.Center);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        Point parentPoint = this.parentPointGetter.get();
        return new Rectangle((int)parentPoint.getX() + this.x, (int)parentPoint.getY() + this.y, 100, 30).contains(x, y);
    }
}
