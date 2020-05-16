package jegmezo.view;

import jegmezo.model.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class PlayerView extends View {

    protected Player player;
    protected int x,y;

    protected TooltipView tooltip;

    public PlayerView(GameWindow gameWindow, AssetManager assetManager, int x, int y) {
        super(gameWindow, assetManager);
        this.x=x; this.y=y;
    }

    @Override
    public boolean isMouseOver(int x, int y) {
            return new Rectangle(this.x, this.y, 100, 100).contains(x, y);

    }

    @Override
    public void mouseMoved(MouseEvent event) {
        tooltip.setX(event.getX());
        tooltip.setY(event.getY());
    }

    public boolean clicked(MouseEvent event){
        return false;
    }

    public void mouseLeave(MouseEvent event){
        this.children.remove(tooltip);
    }

    public void mouseEnter(MouseEvent event){
        this.children.add(tooltip);
    }

    public abstract void draw (Graphics2D graphics, boolean overlay);

}
