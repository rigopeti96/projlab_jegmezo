package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;

public class TestView extends View {
    private int x, y;
    private boolean canHover;
    private TestView tooltip;

    TestView(ImageManager imageManager, int x, int y) {
        this(imageManager, x, y, true);
    }

    TestView(ImageManager imageManager, int x, int y, boolean canHover) {
        super(imageManager);
        this.x = x;
        this.y = y;
        this.canHover = canHover;
    }

    @Override
    public void windowClicked(MouseEvent event) {

    }

    @Override
    public boolean clicked() {
        return super.clicked();
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 100, 100).contains(x, y);
    }

    @Override
    public void mouseEnter() {
        if (!canHover) return;
        tooltip = new TestView(imageManager, x + 50, y + 50, false);
        this.children.add(tooltip);
    }

    @Override
    public void mouseMoved() {
        super.mouseMoved();
    }

    @Override
    public void mouseLeave() {
        if (!canHover) return;
        this.children.remove(tooltip);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (overlay) return;
        graphics.setColor(hovered ? Color.RED : Color.GREEN);
        graphics.fill(new Rectangle(this.x, this.y, 100, 100));
    }
}
