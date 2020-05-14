package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class TileView extends View{
    private int x,y;
    private Tile tile;
    //private TileToolTipView tooltip;
    // private List<PlayerView> playerViews;
    private boolean clicked = false;

    public TileView(ImageManager imageManager,int x,int y) {
        super(imageManager);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 100, 100).contains(x, y);
    }

    @Override
    public void windowClicked(MouseEvent event) {
        clicked = false;
    }

    @Override
    public boolean clicked(MouseEvent event) {
        clicked = true;
        return true;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (overlay) return;
        graphics.drawImage(imageManager.getImage("testImage"), x, y, 100, 100, null);
    }
}
