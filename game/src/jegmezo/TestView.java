package jegmezo;

import java.awt.*;

public class TestView extends View {
    private int x, y;

    TestView(ImageManager imageManager, int x, int y) {
        super(imageManager);
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 100, 100).contains(x, y);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (overlay) return;
        graphics.setColor(hovered ? Color.RED : Color.GREEN);
        graphics.fill(new Rectangle(this.x, this.y, 100, 100));
    }
}
