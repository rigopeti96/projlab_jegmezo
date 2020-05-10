package jegmezo;

import java.awt.*;

public class ConsoleView extends View {
    public ConsoleView(ImageManager imageManager) {
        super(imageManager);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics, overlay);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
