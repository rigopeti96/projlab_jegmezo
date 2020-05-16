package jegmezo;

import java.awt.*;

public class EndOverlayView extends View {
    public EndOverlayView(ImageManager imageManager) {
        super(imageManager);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(imageManager.getImage("endOverlay"), 0, 0, 25, 50, null);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
