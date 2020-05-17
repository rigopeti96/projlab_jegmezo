package jegmezo.view;

import jegmezo.model.NamedAction;
import jegmezo.model.Tile;

import java.util.List;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A mező nézete
 */
public abstract class TileView extends View{
    /**
     * a koordináták és a méret
     */
    protected int x,y, size;
    /**
     * a kirajzolandő mező
     */
    protected Tile tile;
    /**
     * a mezőhöz tartozó tooltip
     */
    private TooltipView toolTip;
    /**
     * A mezőhöz tartozó level nézet
     */
    protected LevelView levelView;
    /**
     * A mezőre klikkeléskor kirajzolandó menü
     */
    private MenuView menu;
    /**
     * A mezőn álló játékosok listája
     */
    private List<PlayerView> playerViews = new ArrayList<>();

    /**
     * Konstruktor
     * @param gameWindow játékablak
     * @param assetManager képkirajzoló
     * @param levelView pályanézet
     * @param x x koordináat
     * @param y y koordináta
     * @param tile kirajzolandó mező
     */
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

    /**
     * A klikkeléskor kirajzolást valósítja meg
     * @param x x koordináta
     * @param y y koordináta
     * @return visszatér a kirajzolással (true esetén kirajzolt valamit, egyébként false)
     */
    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x-30,this.y-40,60,70).contains(x,y);
    }

    /**
     * visszaadja, hogy az adott tile-on van-e aktív játékos
     * @return True, ha van aktív játékos, egyébként false
     */
    protected boolean hasActivePlayer() {
        return tile.getPlayers().contains(gameWindow.getGameController().getActivePlayer());
    }

    /**
     * Visszadja, hogy a mező aktív játékos mellett van-e
     * @return TRUE ha igen, egyébként false
     */
    protected boolean isNeighbourOfActivePlayer() {
        return levelView.isTileNeighbourOf(tile, gameWindow.getGameController().getActivePlayer().getTile());
    }

    /**
     * Az egér mozgásához tartozó függvény
     * @param event esemény
     */
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

    /**
     * Az egér belépésekor
     * @param event egéresemény
     */
    //public void mouseEnter(MouseEvent event) { }

    /**
     * A mező elhagyásakor a tooltip eltűnik
     * @param event esemény
     */
    public void mouseLeave(MouseEvent event) {
        toolTip.setShow(false);
    }

    /**
     * A jobb-klikk esemény kezelőfüggvénye
     * @param event egéresemény
     * @return visszaadja az eseményt: Történt esemény - TRUE, egyébként FALSE
     */
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

    /**
     * Visszaadja az x értékét
     * @return x értéke
     */
    public int getX(){
        return x;
    }

    /**
     * Visszaadja az y értékét
     * @return y értéke
     */
    public int getY(){
        return y;
    }

    /**
     * A kirajzolást végzá függvény
     * @param graphics a grafikát megvalósítő osztály
     * @param overlay ha átfedésben van, akkor visszatér, ha nincs, akkor rajzol ki bármit
     */
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

    /**
     * hozzákapcsol a playerhez egy playerview-t
     * @param playerView a kapcsolandó view
     */
    public void attachPlayerView(PlayerView playerView) {
        children.add(playerView);
        playerViews.add(playerView);
        double playerAngle = (double)playerViews.size() / gameWindow.getGameController().getLevel().getPlayerCount() * Math.PI * 2;
        playerView.setX((int)(x + Math.cos(playerAngle) * 14));
        playerView.setY((int)(y + Math.sin(playerAngle) * 14));
    }
}
