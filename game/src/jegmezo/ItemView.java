package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ItemView extends View {
    private ItemToolTipView toolTip;
    private Item item;
    private int x, y;
    private int itemCount;
    private MenuView menu;

    ItemView(AssetManager assetManager, int x, int y, Item item, int itemCount) {
        super(assetManager);
        this.x = x;
        this.y = y;
        this.toolTip = new ItemToolTipView(assetManager, item.getDescription());
        this.item = item;
        this.itemCount = itemCount;
        ArrayList<MenuAction> actionList = new ArrayList<>();
        if (item.isUseable()) actionList.add(new MenuAction("Use", () -> System.out.println("Should use")));
        actionList.add(new MenuAction("Trade", () -> System.out.println("Should trade")));
        this.menu = new MenuView(assetManager, actionList, () -> {
            removeChild(this.menu);
        });
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
        if (!this.children.contains(menu)) this.children.add(toolTip);
    }

    @Override
    public void mouseLeave(MouseEvent event) {
        this.children.remove(toolTip);
    }

    @Override
    public boolean rightClicked(MouseEvent event) {
        menu.setX(event.getX());
        menu.setY(event.getY());
        addChild(menu);
        this.children.remove(toolTip);
        return true;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (overlay) return;

        if(!item.isUseable()){
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            graphics.drawImage(assetManager.getImageGrayScale(item.getName()), x + 5, y + 5, 40, 40, null);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        else {
            graphics.drawImage(assetManager.getImage(item.getName()), x + 5, y + 5, 40, 40, null);
        }

        if(item.getName().equals("Win item")|| item.getName().equals("Food")){
            graphics.setColor(Color.DARK_GRAY);
            graphics.setFont(assetManager.getFont());
            graphics.drawString(itemCount + "x", x+20, y+40);
        }
    }
}
