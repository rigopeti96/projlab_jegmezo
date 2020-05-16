package jegmezo;

import java.awt.*;

//ezt az osztályt még tanácsos lenne szétszedni - Blizzard Overlay és GameOver Overlay
public class OverlayView extends View{
    private String type;
    public OverlayView(AssetManager assetManager, String _type) {
        super(assetManager);
        type = _type;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("blizzardOverlay"), 0, 0, 25, 50, null);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
