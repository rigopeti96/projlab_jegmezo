package jegmezo.view;

import java.util.List;
import jegmezo.model.Item;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Az itemek nézetét valósítja meg
 */
public class ItemView extends View {
    /**
     * az itemhez tartozó tooltip
     */
    private TooltipView toolTip;
    /**
     * a item, amihez a view tartozik
     */
    private Item item;
    /**
     * x és y koordináta
     */
    private int x, y;
    /**
     * az itemek száma
     */
    private int itemCount;
    /**
     * az itemhez tartozó menü
     */
    private MenuView menu;
    /**
     * az x hossza
     */
    private int size_x = 40;
    /**
     * az y hossza
     */
    private int size_y = 40;
    /**
     * kirajzolja a bordert
     */
    private boolean showBorder = true;

    /**
     * konstruktor
     * @param gameWindow játékablak
     * @param assetManager képkirajzoló
     * @param x x koordináta
     * @param y y koordináta
     * @param item az item
     * @param itemCount az itemek száma
     */
    ItemView(GameWindow gameWindow, AssetManager assetManager, int x, int y, Item item, int itemCount) {
        super(gameWindow, assetManager);
        this.x = x;
        this.y = y;

        toolTip = gameWindow.getTooltipView();
        this.item = item;
        this.itemCount = itemCount;
        this.menu = new MenuView(gameWindow, assetManager);
    }

    /**
     * Az egérrel ráklikkeltek
     * @param x x koordináta
     * @param y y koordináta
     * @return ha volt klikkelés, akkor true, egyébként false
     */
    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 60, 50).contains(x, y);
    }

    /**
     * Történt-e transzformáció - mindig false
     * @return mindig false
     */
    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }

    /**
     * Az egér mozgását vizsgálja
     * @param event egérevent
     */
    @Override
    public void mouseMoved(MouseEvent event) {
       toolTip.setX(event.getX());
       toolTip.setY(event.getY());
    }

    /**
     * Az egér belépett a szükséges területre
     * @param event egérevent
     */
    @Override
    public void mouseEnter(MouseEvent event) {
        if (!this.children.contains(menu)) {
            toolTip.setText(item.getDescription());
            toolTip.setShow(true);
        }
    }

    /**
     * az egér elhagyta a kijelölt területet
     * @param event egérevent
     */
    @Override
    public void mouseLeave(MouseEvent event) {
        toolTip.setShow(false);
    }

    /**
     * Item-setter
     * @param item beállítandó item
     */
    public void setItem(Item item){
        this.item = item;
    }

    /**
     * itemszám setter
     * @param itemCount az itemek száma
     */
    public void setItemCount(int itemCount){
        this.itemCount = itemCount;
    }

    /**
     * jobbklikk event
     * @param event event
     * @return ha történt jobbklikk, akkor true, egyébként false
     */
    @Override
    public boolean rightClicked(MouseEvent event) {
        menu.setX(event.getX());
        menu.setY(event.getY());
        menu.setActionList(gameWindow.getGameController().getActivePlayer().getItemActions(this.item, this.itemCount));
        this.gameWindow.openMenu(menu);
        return true;
    }

    /**
     * méret beállítása
     * @param x x tengelyen való állítás
     * @param y y tengelyen való állítás
     */
    public void setSize(int x, int y){
        size_x = x;
        size_y = y;
    }

    /**
     * border setter
     * @param show beállítandó érték
     */
    public void setShow(boolean show){
        this.showBorder = show;
    }
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * A kirajzolást végzá függvény
     * @param graphics a grafikát megvalósítő osztály
     * @param overlay ha átfedésben van, akkor visszatér, ha nincs, akkor rajzol ki bármit
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (!overlay) return;

        Rectangle rectangle = new Rectangle(x, y, 60, 50);
        graphics.setColor(Color.WHITE);
        if(showBorder){
            graphics.fill(rectangle);
        }


        if(itemCount > 0){
            graphics.drawImage(assetManager.getImage(item.getName()), x + 5, y + 5, size_x, size_y, null);
        }
        else {
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            graphics.drawImage(assetManager.getImageGrayScale(item.getName()), x + 5, y + 5, size_x, size_y, null);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

        }

        if(item.getName().equals("Win item")|| item.getName().equals("Food")){
            DrawUtils du = new DrawUtils(graphics);
            String lines = itemCount + "x";
            graphics.setColor(Color.DARK_GRAY);
            du.drawStringRectangle(lines, assetManager.getFont(), 1.2f, du.padding(rectangle, 4), VerticalAlignment.Bottom, HorizontalAlignment.Right);
        }

        graphics.setColor((gameWindow.getGameController().getSelectedItem() != null && gameWindow.getGameController().getSelectedItem().getName() == item.getName())
                ? assetManager.getColor("highlight") :  Color.DARK_GRAY);
        graphics.setStroke(new BasicStroke(2f));
        if(showBorder){
            graphics.draw(rectangle);
        }
        graphics.setStroke(new BasicStroke(1f));
    }
}
