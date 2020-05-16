package jegmezo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryView extends View {

    protected List<ItemView> itemViews = new ArrayList<>();
    private int x, y;

    InventoryView(AssetManager assetManager, Inventory inventory) {
        super(assetManager);
        this.x = GameWindow.windowWidth-430;
        this.y = GameWindow.windowHeight-100;

        children.add(new ItemView(assetManager, x+70*0, y, new ScubaGear(),inventory.getScubaCount()));

        children.add(new ItemView(assetManager, x+70*1, y, new Rope(), inventory.getRopeCount()));

        children.add(new ItemView(assetManager, x+70*2, y, new WinItem("Win item"), inventory.getWinItemCount()));

        children.add(new ItemView(assetManager, x+70*3, y, new Tent(), inventory.getTentCount()));

        if(inventory.getBreakableShovelCount() !=0)
            children.add(new ItemView(assetManager, x+70*4, y, inventory.getItem("Breakable shovel"), inventory.getBreakableShovelCount()));
        else
            children.add(new ItemView(assetManager, x+70*4, y, new Shovel(), inventory.getShovelCount()));

        children.add(new ItemView(assetManager, x+70*5, y, new Food(), inventory.getFoodCount()));
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return new Rectangle(this.x, this.y, 420, 50).contains(x, y);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if (overlay) return;

        graphics.setColor(Color.DARK_GRAY);
        float opacity = 1.0f;
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        Stroke stroke = new BasicStroke(2f);
        graphics.setStroke(stroke);
        graphics.drawRect(x+70*0, y, 60, 50);
        graphics.drawRect(x+70*1, y, 60, 50);
        graphics.drawRect(x+70*2, y, 60, 50);
        graphics.drawRect(x+70*3, y, 60, 50);
        graphics.drawRect(x+70*4, y, 60, 50);
        graphics.drawRect(x+70*5, y, 60, 50);


    }
}
