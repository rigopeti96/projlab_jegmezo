package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class TileView extends View{
    protected int x,y, size;
    protected Tile tile;
    private TileToolTipView tooltip;
    // private List<PlayerView> playerViews;
    private boolean clicked = false;

    public TileView(ImageManager imageManager,int x,int y,Tile tile) {
        super(imageManager);
        this.x=x;
        this.y=y;
        this.tile=tile;
        size=2;
        tooltip=new TileToolTipView(imageManager,x+10,y+10, new String(tile.toLongString()));
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x-27, this.y, 60*size, 60*size).contains(x, y);
    }

    public void mouseEnter(MouseEvent event) {
        this.children.add(tooltip);
    }

    public void mouseLeave(MouseEvent event) {
        this.children.remove(tooltip);
    }

    public void resize(int new_size){
        size=new_size;
    }

    public int getsize(){return size;}

    /*void Addplayer(PlayerView uj_player){
        children.add(uj_player);
    }*/


    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics,overlay);
        if (overlay) return;
            for (View child : children)
                child.draw(graphics, overlay);
    }
}
