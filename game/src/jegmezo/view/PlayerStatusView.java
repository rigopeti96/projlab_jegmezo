package jegmezo.view;

import jegmezo.model.Player;

import java.awt.*;

public class PlayerStatusView extends View {
    public PlayerStatusView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
    }
    //todo:

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        if(!overlay) return;
        Player activePlayer = gameWindow.getGameController().getActivePlayer();
        DrawUtils du = new DrawUtils(graphics);
        graphics.setColor(Color.DARK_GRAY);
        String lines = "Player " + activePlayer.getNumber() + "'s turn\n" + activePlayer.getAP() + " step" + (activePlayer.getAP() == 1 ? "" : "s") + " remaining";
        Rectangle rectangle = new Rectangle(GameWindow.windowWidth - 330, 0, 300, 40);
        du.drawStringRectangle(lines, assetManager.getFont(24), 1.2f, du.padding(rectangle, 4), VerticalAlignment.Top, HorizontalAlignment.Right);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
