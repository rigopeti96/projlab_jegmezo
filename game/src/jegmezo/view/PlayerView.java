package jegmezo.view;

import jegmezo.controller.GameState;
import jegmezo.model.Player;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A játékos megjelenítéséért felelős osztály. Abstract osztály.
 */
public abstract class PlayerView extends View {

    /**
     * A Player akihez a View tartozik
     */
    protected Player player;
    /**
     * A player x,y koordinátája
     */
    protected int x,y;
    /**
     * A player tooltip-je
     */
    protected TooltipView toolTip;
    /**
     * Annak a tile-nak a view-ja, amin a player áll.
     */
    protected TileView tileView;

    /**
     * PlayerView konstruktora. Beállítja a playert és a tooltipet, meghivja a ős konstruktorát.
     * @param gameWindow a játék gameWindow-ja
     * @param assetManager a játék assetManager-je
     * @param player a player, akié a view
     */
    public PlayerView(GameWindow gameWindow, AssetManager assetManager, Player player) {
        super(gameWindow, assetManager);
        this.player = player;
        toolTip = gameWindow.getTooltipView();
    }

    /**
     * Megnézi, hogy az egér a player ikonja fölött van e
     * @param x az egér x koordinátája
     * @param y az egér y koordinátája
     * @return true ha fölötte van az egér
     */
    @Override
    public boolean isMouseOver(int x, int y) {
            return new Rectangle(this.x -20, this.y -20, 25, 25).contains(x, y);
    }

    /**
     * Az egér elmozdul a player ikonja fölött, arréb mozdítja a tooltip-et is.
     * @param event a mouseEvent
     */
    @Override
    public void mouseMoved(MouseEvent event) {
        toolTip.setShow(true);
        Point transformed = new Point();
        gameWindow.getTransformation().transform(new Point(event.getX(), event.getY()), transformed);
        toolTip.setX((int)transformed.getX());
        toolTip.setY((int)transformed.getY());
    }

    /**
     * Kattintanak a player ikonján. Ha trade zajlik épp, akkor a player megkapja az itemet, máskülönben nem történik semmi.
     * @param event a mouseEvent
     */
    public boolean clicked(MouseEvent event){
        if (gameWindow.getGameController().getGameState() == GameState.Trade && player != gameWindow.getGameController().getActivePlayer()) {
            if(player.getTile() == gameWindow.getGameController().getActivePlayer().getTile()){
                gameWindow.getGameController().tradeFinish(player);
            }
        }
        return true;
    }

    /**
     * Az egér kimozdul a karakter ikonja fölül, eltünteti a tooltip-et.
     * @param event
     */
    public void mouseLeave(MouseEvent event){
        toolTip.setShow(false);
    }

    /**
     * Az x koordinátát állítja be.
     * @param x
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Az y koordinátát állítja be.
     * @param y
     */
    public void setY(int y){
        this.y =y;
    }

    /**
     * Visszaadja a player-t
     * @return a player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Átállítja a tileView, amellyen a player ikonja van.
     * @param tileView
     */
    public void setTileView(TileView tileView){
        this.tileView = tileView;
        this.x = tileView.getX();
        this.y = tileView.getY();
    }

}
