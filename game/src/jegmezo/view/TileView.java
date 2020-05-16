package jegmezo.view;

import jegmezo.model.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class TileView extends View{
    protected int x,y, size;
    protected Tile tile;
    private TooltipView tooltip;
    private boolean clicked = false;

    public TileView(GameWindow gameWindow, AssetManager assetManager, int x, int y, Tile tile) {
        super(gameWindow, assetManager);
        this.x=x;
        this.y=y;
        this.tile=tile;
        size=2;
        tooltip= new TooltipView(gameWindow, assetManager, tile.toLongString());
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x-27, this.y, 60*size, 60*size).contains(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        tooltip.setX(event.getX());
        tooltip.setY(event.getY());
    }

    public void mouseEnter(MouseEvent event) {
        this.children.add(tooltip);
    }

    public void mouseLeave(MouseEvent event) {
        this.children.remove(tooltip);
    }


    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics,overlay);
        if (overlay) return;
            for (View child : children)
                child.draw(graphics, overlay);
    }
}
