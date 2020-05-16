package jegmezo.view;

import jegmezo.model.Player;

import java.awt.*;

public class PlayerStatusView extends View {
    private Player activePlayer;

    public PlayerStatusView(GameWindow gameWindow, AssetManager assetManager, Player player) {
        super(gameWindow, assetManager);
        activePlayer = player;
    }
    //todo:

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if(!overlay)
            return;
        super.draw(graphics, overlay);
        Font font = new Font("Calibri", Font.BOLD, 11);
        graphics.setFont(font);
        graphics.drawString("Player X's turn", 250, 200); //ide még kell a getClipBound
        graphics.drawString(activePlayer.getActions() + "step(s) remaining", 300, 200); //ide még kell a getClipBound
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
