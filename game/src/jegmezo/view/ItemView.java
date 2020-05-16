package jegmezo.view;

import jegmezo.controller.MenuAction;
import jegmezo.model.Item;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ItemView extends View {
    private TooltipView toolTip;
    private Item item;
    private int x, y;
    private int itemCount;
    private MenuView menu;

    ItemView(GameWindow gameWindow, AssetManager assetManager, int x, int y, Item item, int itemCount) {
        super(gameWindow, assetManager);
        this.x = x;
        this.y = y;

        toolTip = gameWindow.getTooltipView();
        this.item = item;
        this.itemCount = itemCount;
        ArrayList<MenuAction> actionList = new ArrayList<>();
        if (item.isUseable()) actionList.add(new MenuAction("Use", () -> {
            if (this.itemCount > 0) {
                gameWindow.getGameController().useItem(this.item);
            } else {
                gameWindow.getGameController().getConsoleView().writeLine("You have no " + this.item.getName() + ".");
            }
        }));
        actionList.add(new MenuAction("Trade", () -> System.out.println("Should trade")));
        this.menu = new MenuView(gameWindow, assetManager, actionList);
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
        this.gameWindow.openMenu(menu);
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
            graphics.drawImage(itemCount > 0 ? assetManager.getImage(item.getName()) : assetManager.getImageGrayScale(item.getName()), x + 5, y + 5, 40, 40, null);
        }

        if(item.getName().equals("Win item")|| item.getName().equals("Food")){
            DrawUtils du = new DrawUtils(graphics);
            String lines = itemCount + "x";
            graphics.setColor(Color.DARK_GRAY);
            Rectangle rectangle = new Rectangle(x, y, 60, 50);
            du.drawStringRectangle(lines, assetManager.getFont(), 1.2f, du.padding(rectangle, 4), VerticalAlignment.Bottom, HorizontalAlignment.Right);
        }
    }
}
