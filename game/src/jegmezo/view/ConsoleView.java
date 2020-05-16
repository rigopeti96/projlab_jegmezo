package jegmezo.view;

import jegmezo.model.Player;

import java.awt.*;

public class ConsoleView extends View {
    private Player activeplayer;
    public ConsoleView(GameWindow gameWindow, AssetManager assetManager, Player player) {
        super(gameWindow, assetManager);
        activeplayer = player;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        Font font = new Font("Serif", Font.BOLD, 11);
        graphics.setFont(font);
        graphics.drawString("Player X's turn", 600, 400); //ide még kell a getClipBound
        graphics.drawString(activeplayer.getActions() + "step(s) remaining", 600, 400); //ide még kell a getClipBound
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
