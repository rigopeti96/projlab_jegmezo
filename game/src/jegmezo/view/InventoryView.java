package jegmezo.view;

import jegmezo.model.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * InventoryView osztály, azért felel, hogy a játékos inventory-ját megjelenítse
 */
public class InventoryView extends View {
    /**
     * az inventory x és y koordinátája
     */
    private int x, y;
    /**
     * a búvárruhához tartozó View
     */
    ItemView scubaView;
    /**
     * a kötélhez tartozó View
     */
    ItemView ropeView;
    /**
     * a sátorhoz tartozó View
     */
    ItemView tentView;
    /**
     * az ételhez tartozó View
     */
    ItemView foodView;
    /**
     * a győzelmitárgyhoz tartozó View
     */
    ItemView winItemView;
    /**
     * az ásóhoz tartozó View
     */
    ItemView shovelView;
    /**
     * a shovel
     */
    private Shovel viewShovel;

    /**
     * az inventoryView konstruktora
     * @param gameWindow - kell a view-nak a gameWindow
     * @param assetManager - kell a view-nak az assetManager
     * @param inventory - az inventory
     */
    InventoryView(GameWindow gameWindow, AssetManager assetManager, Inventory inventory) {
        super(gameWindow, assetManager);
        this.x = GameWindow.windowWidth-430;
        this.y = GameWindow.windowHeight-100;
        viewShovel = new Shovel(gameWindow.getGameController());

        scubaView = new ItemView(gameWindow, assetManager, x+70*0, y, new ScubaGear(gameWindow.getGameController()), 0);
        children.add(scubaView);

        ropeView = new ItemView(gameWindow, assetManager, x+70*1, y, new Rope(gameWindow.getGameController()), 0);
        children.add(ropeView);

        winItemView = new ItemView(gameWindow, assetManager, x+70*2, y, new WinItem(gameWindow.getGameController(), "Win item"), 0);
        children.add(winItemView);

        tentView = new ItemView(gameWindow, assetManager, x+70*3, y, new Tent(gameWindow.getGameController()), 0);
        children.add(tentView);

        shovelView = new ItemView(gameWindow, assetManager, x+70*4, y, viewShovel, 0);
        children.add(shovelView);

        foodView = new ItemView(gameWindow, assetManager, x+70*5, y, new Food(gameWindow.getGameController()), 0);
        children.add(foodView);
    }

    /**
     * visszaadja, hogy a kurzor az inventoryView felett van-e
     * @param x - az egér x koordinátája
     * @param y - az egér y koordinátája
     * @return - boolean, ezt jelzi
     */
    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 420, 50).contains(x, y);
    }

    /**
     * visszaadja, hogy a transzformáció hat-e rá
     * @return
     */
    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }

    /**
     * a kirajzoló függvénye
     * @param graphics - a kirajzoláshoz szükséges graphics
     * @param overlay - a kirajzoláshoz szükséges overlay-t jelző bool
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (!overlay) return;

        Inventory inventory = gameWindow.getGameController().getActivePlayer().getInventory();
        foodView.setItemCount(inventory.getFoodCount());
        winItemView.setItemCount(inventory.getWinItemCount());
        Item shovel = inventory.getItem("Breakable shovel");
        if (shovel == null) shovel = viewShovel;
        shovelView.setItem(shovel);
        shovelView.setItemCount(inventory.getShovelCount());
        tentView.setItemCount(inventory.getTentCount());
        ropeView.setItemCount(inventory.getRopeCount());
        scubaView.setItemCount(inventory.getScubaCount());
    }
}
