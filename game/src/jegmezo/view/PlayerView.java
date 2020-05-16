package jegmezo.view;

import jegmezo.model.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class PlayerView extends View {

    protected Player player;
    protected int x,y;

    protected TooltipView tooltip;

    public PlayerView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
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

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y =y;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

}
