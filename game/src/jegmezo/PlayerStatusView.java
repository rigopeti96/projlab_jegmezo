package jegmezo;

import java.awt.*;
import java.io.IOException;

public class PlayerStatusView extends View {
    private Player activeplayer;

    public PlayerStatusView(ImageManager imageManager, Player player) {
        super(imageManager);
        activeplayer = player;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) throws IOException {
        super.draw(graphics, overlay);
        Font font = new Font("Serif", Font.BOLD, 11);
        graphics.setFont(font);
        graphics.drawString("Player X's turn", 600, 400); //ide még kell a getClipBound
        graphics.drawString(activeplayer.actions + "step(s) remaining", 600, 400); //ide még kell a getClipBound
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
