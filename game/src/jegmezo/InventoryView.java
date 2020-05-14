package jegmezo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryView extends View {

    protected List<ItemView> itemViews = new ArrayList<>();
    private Inventory inventory;
    private int x, y;

    InventoryView(ImageManager imageManager, int x, int y, Inventory inventory) {
        super(imageManager);
        this.x = x;
        this.y = y;
        this.inventory = inventory;

        children.add(new ItemView(imageManager, x+70*0+5, y+5, new ScubaGear(),inventory.getScubaCount()));

        children.add(new ItemView(imageManager, x+70*1+5, y+5, new Rope(), inventory.getRopeCount()));

        children.add(new ItemView(imageManager, x+70*2+5, y+5, new WinItem("winitem"), inventory.getWinItemCount()));

        children.add(new ItemView(imageManager, x+70*3+5, y+5, new Tent(), inventory.getTentCount()));

        if(inventory.getBreakableShovelCount() !=0)
            children.add(new ItemView(imageManager, x+70*4+5, y+5, inventory.getItem("breakableshovel"), inventory.getBreakableShovelCount()));
        else
            children.add(new ItemView(imageManager, x+70*4+5, y+5, new Shovel(), inventory.getShovelCount()));

        children.add(new ItemView(imageManager, x+70*5+5, y+5, new Food(), inventory.getFoodCount()));
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
