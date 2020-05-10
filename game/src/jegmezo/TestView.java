package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class TestView extends View {
    private int x, y;
    private boolean clicked = false;

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
    public void windowClicked(MouseEvent event) {
        clicked = false;
    }

    @Override
    public boolean clicked() {
        clicked = true;
        return true;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (overlay) return;
        graphics.setColor(clicked ? Color.RED : Color.GREEN);
        graphics.fill(new Rectangle(this.x, this.y, 100, 100));
    }
}
