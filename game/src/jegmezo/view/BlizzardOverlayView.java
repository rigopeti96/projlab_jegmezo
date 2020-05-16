package jegmezo.view;

import java.awt.*;

//ezt az osztályt még tanácsos lenne szétszedni - Blizzard Overlay és GameOver Overlay
public class BlizzardOverlayView extends View {
    private String type;
    public BlizzardOverlayView(AssetManager assetManager, String type) {
        super(assetManager);
        this.type = type;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("blizzardOverlay1"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        graphics.drawImage(assetManager.getImage("blizzardOverlay2"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
