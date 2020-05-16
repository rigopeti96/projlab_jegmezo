package jegmezo.view;

import java.awt.*;

public class ScientistView extends PlayerView{

    public ScientistView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
        toolTip = new TooltipView(gameWindow, assetManager, "Player " + player.getNumber() + " (Scientist)");
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        graphics.drawImage(assetManager.getImage("scientist"), x, y, 100, 100, null);
    }
}
