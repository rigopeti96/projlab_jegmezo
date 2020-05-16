package jegmezo.view;

import jegmezo.controller.MenuAction;
import jegmezo.model.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public abstract class TileView extends View{
    protected int x,y, size;
    protected Tile tile;
    private TooltipView toolTip;
    private boolean clicked = false;
    private MenuView menu;

    public TileView(GameWindow gameWindow, AssetManager assetManager, int x, int y, Tile tile) {
        super(gameWindow, assetManager);
        this.x=x;
        this.y=y;
        this.tile=tile;
        size=2;
        toolTip = gameWindow.getTooltipView();
        ArrayList<MenuAction> actionList = new ArrayList<>();
        actionList.add(new MenuAction("Step", () -> System.out.println("Should step")));
        actionList.add(new MenuAction("Dig", () -> System.out.println("Should dig if on it")));
        actionList.add(new MenuAction("Pick up", () -> System.out.println("Should pick up if has item and has no HOE")));
        this.menu = new MenuView(gameWindow, assetManager, actionList);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x-30,this.y-40,60,70).contains(x,y);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        toolTip.setX(event.getX());
        toolTip.setY(event.getY());
    }

    public void mouseEnter(MouseEvent event) {

        if (!this.children.contains(menu)) {
            toolTip.setText(tile.getDescription());
            toolTip.setShow(true);
        }
    }

    public void mouseLeave(MouseEvent event) {
        toolTip.setShow(false);
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
        super.draw(graphics,overlay);
        if (overlay) return;
            for (View child : children)
                child.draw(graphics, overlay);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }


}
