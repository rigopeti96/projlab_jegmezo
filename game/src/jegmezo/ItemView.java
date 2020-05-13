package jegmezo;

import javafx.scene.control.Tooltip;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

public class ItemView extends View {
    private ItemToolTipView toolTip;
    private Item item;
    private int x, y;
    private int toolTipX, toolTipY;
    private int itemCount;
    private BufferedImage image;

    ItemView(ImageManager imageManager, int x, int y, Item item, int itemCount) {
        super(imageManager);
        this.x = x;
        this.y = y;
        this.toolTip = new ItemToolTipView(imageManager, toolTipX, toolTipY, item.getName()); // item.getDescription());
        this.item = item;
        this.itemCount = itemCount;

        this.imageManager.loadImage(item.getName(), "Images/" + item.getName());
        image = this.imageManager.getImage(item.getName());

        if(itemCount == 0){
           // image = imageManager.toGrayScale(image);
        }
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 60, 50).contains(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        toolTip.setX(event.getX());
        toolTip.setY(event.getY());
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

        graphics.drawImage(image, x, y, 40, 40, null);

        if(item.getName().equals("winitem")|| item.getName().equals("food")){
            graphics.setColor(Color.DARK_GRAY);
            Font font = new Font("Calibri", Font.BOLD, 20);
            graphics.setFont(font);
            graphics.drawString(itemCount + "x", x+20, y+40);
        }

        //hogy a későbbi rajzolásokba ne szóljon bele az előbbi opacity
        float opacity = 1.0f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
    }
}