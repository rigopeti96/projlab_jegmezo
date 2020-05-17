package jegmezo.view;

import java.util.List;
import jegmezo.model.Item;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ItemView extends View {
    private TooltipView toolTip;
    private Item item;
    private int x, y;
    private int itemCount;
    private MenuView menu;
    private int size_x = 40;
    private int size_y = 40;
    private boolean showBorder = true;

    ItemView(GameWindow gameWindow, AssetManager assetManager, int x, int y, Item item, int itemCount) {
        super(gameWindow, assetManager);
        this.x = x;
        this.y = y;

        toolTip = gameWindow.getTooltipView();
        this.item = item;
        this.itemCount = itemCount;
        this.menu = new MenuView(gameWindow, assetManager);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 60, 50).contains(x, y);
    }

    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }

    @Override
    public void mouseMoved(MouseEvent event) {
       toolTip.setX(event.getX());
       toolTip.setY(event.getY());
    }

    @Override
    public void mouseEnter(MouseEvent event) {
        if (!this.children.contains(menu)) {
            toolTip.setText(item.getDescription());
            toolTip.setShow(true);
        }
    }

    @Override
    public void mouseLeave(MouseEvent event) {
        toolTip.setShow(false);
    }

    public void setItem(Item item){
        this.item = item;
    }

    public void setItemCount(int itemCount){
        this.itemCount = itemCount;
    }

    @Override
    public boolean rightClicked(MouseEvent event) {
        menu.setX(event.getX());
        menu.setY(event.getY());
        menu.setActionList(gameWindow.getGameController().getActivePlayer().getItemActions(this.item, this.itemCount));
        this.gameWindow.openMenu(menu);
        return true;
    }

    public void setSize(int x, int y){
        size_x = x;
        size_y = y;
    }

    public void setShow(boolean show){
        this.showBorder = show;
    }
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (!overlay) return;

        Rectangle rectangle = new Rectangle(x, y, 60, 50);
        graphics.setColor(Color.WHITE);
        if(showBorder){
            graphics.fill(rectangle);
        }


        if(itemCount > 0){
            graphics.drawImage(assetManager.getImage(item.getName()), x + 5, y + 5, size_x, size_y, null);
        }
        else {
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            graphics.drawImage(assetManager.getImageGrayScale(item.getName()), x + 5, y + 5, size_x, size_y, null);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        }

        if(item.getName().equals("Win item")|| item.getName().equals("Food")){
            DrawUtils du = new DrawUtils(graphics);
            String lines = itemCount + "x";
            graphics.setColor(Color.DARK_GRAY);
            du.drawStringRectangle(lines, assetManager.getFont(), 1.2f, du.padding(rectangle, 4), VerticalAlignment.Bottom, HorizontalAlignment.Right);
        }

        graphics.setColor((gameWindow.getGameController().getSelectedItem() != null && gameWindow.getGameController().getSelectedItem().getName() == item.getName())
                ? assetManager.getColor("highlight") :  Color.DARK_GRAY);
        graphics.setStroke(new BasicStroke(2f));
        if(showBorder){
            graphics.draw(rectangle);
        }
        graphics.setStroke(new BasicStroke(1f));
    }
}
