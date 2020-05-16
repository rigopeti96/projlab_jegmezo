package jegmezo.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MenuView extends View {
    private int x, y;
    private Runnable closeCallback;

    public MenuView(GameWindow gameWindow, AssetManager assetManager, ArrayList<MenuAction> items, Runnable closeCallback) {
        super(gameWindow, assetManager);
        this.closeCallback = closeCallback;
        int y = 0;
        for (MenuAction item : items) {
            children.add(new MenuItemView(gameWindow, assetManager, () -> new Point(x, this.y), 0, y, item.getText(), () -> {
                item.getCallback().run();
                closeCallback.run();
            }));
            y += 30;
        }
    }

    public void setX(int x) {
        this.x = x - 100;
    }

    public void setY(int y){
        this.y = y - children.size() * 30;
    }

    @Override
    public void windowClicked(MouseEvent event) {
        super.windowClicked(event);
        if (!hovered) {
            this.closeCallback.run();
        }
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 100, children.size() * 30).contains(x, y);
    }
}
