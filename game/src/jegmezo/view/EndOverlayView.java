package jegmezo.view;

import java.awt.*;

public class EndOverlayView extends View {
    private boolean win = false;
    public EndOverlayView(GameWindow gameWindow, AssetManager assetManager, boolean win) {
        super(gameWindow, assetManager);
        this.win = win;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if(win){
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect(0, 0, gameWindow.windowWidth, gameWindow.windowHeight);
            graphics.drawImage(assetManager.getImage("gameWin"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        }
        else{
            graphics.setColor(Color.LIGHT_GRAY);
            graphics.drawImage(assetManager.getImage("gameOver"), 0, 0, GameWindow.windowWidth, GameWindow.windowHeight, null);
        }
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }

    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }
}
