package jegmezo.view;

import jegmezo.model.NamedAction;
import jegmezo.model.Tile;

import java.util.List;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public abstract class TileView extends View{
    protected int x,y, size;
    protected Tile tile;
    private TooltipView toolTip;
    protected LevelView levelView;
    private MenuView menu;
    private List<PlayerView> playerViews = new ArrayList<>();

    public TileView(GameWindow gameWindow, AssetManager assetManager, LevelView levelView, int x, int y, Tile tile) {
        super(gameWindow, assetManager);
        this.levelView = levelView;
        this.x=x;
        this.y=y;
        this.tile=tile;
        size=2;
        toolTip = gameWindow.getTooltipView();
        this.menu = new MenuView(gameWindow, assetManager);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x-30,this.y-40,60,70).contains(x,y);
    }

    protected boolean hasActivePlayer() {
        return tile.getPlayers().contains(gameWindow.getGameController().getActivePlayer());
    }

    protected boolean isNeighbourOfActivePlayer() {
        return levelView.isTileNeighbourOf(tile, gameWindow.getGameController().getActivePlayer().getTile());
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        for (View child: children) {
            if (child.isHovered()) return;
        }
        Point transformed = new Point();
        gameWindow.getTransformation().transform(new Point(event.getX(), event.getY()), transformed);
        toolTip.setX((int)transformed.getX());
        toolTip.setY((int)transformed.getY());
        toolTip.setText(tile.getDescription());
        toolTip.setShow(true);
    }

    public void mouseEnter(MouseEvent event) {

    }

    public void mouseLeave(MouseEvent event) {
        toolTip.setShow(false);
    }

    @Override
    public boolean rightClicked(MouseEvent event) {
        Point transformed = new Point();
        gameWindow.getTransformation().transform(new Point(event.getX(), event.getY()), transformed);
        menu.setX((int)transformed.getX());
        menu.setY((int)transformed.getY());
        if (isNeighbourOfActivePlayer()) {
            menu.setActionList(gameWindow.getGameController().getActivePlayer().getTileActions(tile));
        } else if (hasActivePlayer()) {
            menu.setActionList(gameWindow.getGameController().getActivePlayer().getActions());
        } else return false;

        this.gameWindow.openMenu(menu);
        return true;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (overlay) {
            for (PlayerView playerView: new ArrayList<>(playerViews)) {
                if (playerView.getPlayer().getTile() != tile) {
                    children.remove(playerView);
                    playerViews.remove(playerView);
                    levelView.attachPlayerViewToOwner(playerView);
                }
            }

            if (!tile.isDiscovered()) {
                graphics.drawImage(assetManager.getImage("fog"), x - 80, y - 50, 100, 100, null);
                graphics.drawImage(assetManager.getImage("fog"), x - 20, y - 50, 100, 100, null);
                graphics.drawImage(assetManager.getImage("fog"), x - 50, y - 50, 100, 100, null);
                graphics.drawImage(assetManager.getImage("fog"), x - 50, y - 80, 100, 100, null);
                graphics.drawImage(assetManager.getImage("fog"), x - 50, y - 20, 100, 100, null);
            }
        }
    }

    public void attachPlayerView(PlayerView playerView) {
        children.add(playerView);
        playerViews.add(playerView);
        double playerAngle = (double)playerViews.size() / gameWindow.getGameController().getLevel().getPlayerCount() * Math.PI * 2;
        playerView.setX((int)(x + Math.cos(playerAngle) * 14));
        playerView.setY((int)(y + Math.sin(playerAngle) * 14));
    }
}
