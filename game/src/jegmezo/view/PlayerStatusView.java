package jegmezo.view;

import jegmezo.model.Player;

import java.awt.*;

public class PlayerStatusView extends View {
    private Player activePlayer;

    public PlayerStatusView(AssetManager assetManager, Player player) {
        super(assetManager);
        activePlayer = player;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
        Font font = new Font("Serif", Font.BOLD, 11);
        graphics.setFont(font);
        graphics.drawString("Player X's turn", 600, 400); //ide még kell a getClipBound
        graphics.drawString(activePlayer.getActions() + "step(s) remaining", 600, 400); //ide még kell a getClipBound
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
