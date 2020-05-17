package jegmezo.view;

import jegmezo.controller.GameState;
import jegmezo.model.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class PlayerView extends View {

    protected Player player;
    protected int x,y;
    protected TooltipView toolTip;
    protected TileView tileView;

    public PlayerView(GameWindow gameWindow, AssetManager assetManager, Player player) {
        super(gameWindow, assetManager);
        this.player = player;
        toolTip = gameWindow.getTooltipView();
    }

    @Override
    public boolean isMouseOver(int x, int y) {
            return new Rectangle(this.x -20, this.y -20, 25, 25).contains(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        toolTip.setShow(true);
        Point transformed = new Point();
        gameWindow.getTransformation().transform(new Point(event.getX(), event.getY()), transformed);
        toolTip.setX((int)transformed.getX());
        toolTip.setY((int)transformed.getY());
    }

    public boolean clicked(MouseEvent event){
        if (gameWindow.getGameController().getGameState() == GameState.Trade && player != gameWindow.getGameController().getActivePlayer()) {
            gameWindow.getGameController().tradeFinish(player);
        }
        return true;
    }

    public void mouseLeave(MouseEvent event){
        toolTip.setShow(false);
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y =y;
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
