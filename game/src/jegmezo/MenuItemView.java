package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;

public class MenuItemView extends View {
    private int x, y;
    private String text;

    public MenuItemView(AssetManager assetManager) {
        super(assetManager);
    }

    @Override
    public boolean clicked(MouseEvent event) {
        return super.clicked(event);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if (!overlay) return;

        graphics.setColor(Color.WHITE);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        graphics.fillRect(x,y, 100, 30);

        graphics.setColor(Color.DARK_GRAY);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        FontRenderContext frc = new FontRenderContext(null, true, true);
        graphics.setFont(assetManager.getFont());
        graphics.drawString(text, x, y);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
