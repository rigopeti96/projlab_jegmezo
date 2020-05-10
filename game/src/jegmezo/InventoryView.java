package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class InventoryView extends View {

    protected List<ItemView> itemViews;
    private Inventory inventory;
    private int x, y;

    InventoryView(ImageManager imageManager, int x, int y, Inventory inventory) {
        super(imageManager);
        this.x = x;
        this.y = y;
        this.inventory = inventory;
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

        if(inventory.getScubaCount()!=0)
            itemViews.add(new ItemView(imageManager, x+5, y+5, inventory.getItem("scuba gear")));

        if(inventory.getRopeCount()!=0)
            itemViews.add(new ItemView(imageManager, x+5, y+5,inventory.getItem("rope") ));

        if(inventory.getWinItemCount()!=0)
            itemViews.add(new ItemView(imageManager, x+5, y+5, inventory.getItem("scuba gear")));

        if(inventory.getTentCount()!=0)
            itemViews.add(new ItemView(imageManager, x+5, y+5,inventory.getItem("scuba gear") ));

        if(inventory.getShovelCount()!=0)
            itemViews.add(new ItemView(imageManager, x+5, y+5,inventory.getItem("scuba gear")));

        if(inventory.getBreakableShovelCount()!=0)
            itemViews.add(new ItemView(imageManager, x+5, y+5, inventory.getItem("scuba gear")));

        if(inventory.getFoodCount()!=0)
            itemViews.add(new ItemView(imageManager, x+5, y+5, new Food()));
    }
}
