package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ItemView extends View {
    private ItemToolTipView toolTip;
    private Item item;
    private int x, y;
    private int toolTipX, toolTipY;
    private int itemCount;

    ItemView(AssetManager assetManager, int x, int y, Item item, int itemCount) {
        super(assetManager);
        this.x = x;
        this.y = y;
        this.toolTip = new ItemToolTipView(assetManager, toolTipX, toolTipY, item.getName()); // item.getDescription());
        this.item = item;
        this.itemCount = itemCount;
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
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            graphics.drawImage(assetManager.getImageGrayScale(item.getName()), x, y, 40, 40, null);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        else {
            graphics.drawImage(assetManager.getImage(item.getName()), x, y, 40, 40, null);
        }

        if(item.getName().equals("winitem")|| item.getName().equals("food")){
            graphics.setColor(Color.DARK_GRAY);
            Font font = new Font("Calibri", Font.BOLD, 20);
            graphics.setFont(font);
            graphics.drawString(itemCount + "x", x+20, y+40);
        }
    }
}
