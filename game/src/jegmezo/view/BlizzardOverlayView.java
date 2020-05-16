package jegmezo.view;

import java.awt.*;

//ezt az osztályt még tanácsos lenne szétszedni - Blizzard Overlay és GameOver Overlay
public class BlizzardOverlayView extends View {
    public BlizzardOverlayView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("blizzardOverlay1"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        graphics.drawImage(assetManager.getImage("blizzardOverlay2"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        Font font = new Font("Serif", Font.BOLD, 70);
        graphics.setColor(Color.BLUE);
        graphics.setFont(font);
        graphics.drawString("Jön a HAV!", GameWindow.windowWidth/2-200, GameWindow.windowHeight/2-5);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
