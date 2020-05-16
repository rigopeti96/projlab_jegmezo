package jegmezo.view;

import java.awt.*;

public class EndOverlayView extends View {
    public EndOverlayView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if(true){
            graphics.drawImage(assetManager.getImage("gameWin"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        }
        else{
            graphics.drawImage(assetManager.getImage("gameOver"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        }
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
