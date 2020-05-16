package jegmezo.view;

import jegmezo.model.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class PlayerView extends View {

    protected Player player;
    protected int x,y;
    protected TooltipView toolTip;
    protected TileView tileView;

    public PlayerView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
        toolTip = gameWindow.getTooltipView();
    }

    @Override
    public boolean isMouseOver(int x, int y) {
            return new Rectangle(this.x, this.y, 100, 100).contains(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        toolTip.setX(event.getX());
        toolTip.setY(event.getY());
    }

    public boolean clicked(MouseEvent event){
        return false;
    }

    public void mouseLeave(MouseEvent event){
        toolTip.setShow(false);
    }

    public abstract void draw (Graphics2D graphics, boolean overlay);

    public void setX(int x){
        this.x = 100;
    }

    public void setY(int y){
        this.y = 100;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public void setTileView(TileView tileView){
        this.tileView = tileView;
        this.x = tileView.getX();
        this.y = tileView.getY();
    }

}
