package jegmezo;

import javafx.scene.control.Tooltip;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ItemView extends View {
    private ItemToolTipView toolTip;
    private Item item;
    private int x, y;
    private int toolTipX, toolTipY;
    private boolean clicked = false;

    ItemView(ImageManager imageManager, int x, int y, Item item) {
        super(imageManager);
        this.x = x;
        this.y = y;
        this.toolTip = new ItemToolTipView(imageManager, toolTipX, toolTipY, item.getDescription());
        this.item = item;
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 60, 50).contains(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        this.toolTipX = event.getX();
        this.toolTipY = event.getY();
    }

    @Override
    public void mouseEnter(MouseEvent event) {
        this.children.add(toolTip);
    }

    @Override
    public void mouseLeave(MouseEvent event) {
        this.children.remove(toolTip);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (overlay) return;

        if(!item.isUseable()){
            float opacity = 0.2f;
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        }

        this.imageManager.loadImage(item.getName(), item.getFileName());
        graphics.drawImage(this.imageManager.getImage(item.getName()), x+5, y+5, 40, 40, null);

        //hogy a későbbi rajzolásokba ne szóljon bele az előbbi opacity
        float opacity = 1.0f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
    }
}
