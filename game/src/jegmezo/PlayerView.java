package jegmezo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class PlayerView extends View {

    protected Player player;
    protected int x,y;

    protected PlayerToolTipView tooltip;

    public PlayerView(ImageManager imageManager) {
        super(imageManager);

    }

    @Override
    public boolean isMouseOver(int x, int y) {
            return new Rectangle(this.x, this.y, 100, 100).contains(x, y);

    }

    public boolean clicked(MouseEvent event){
        return false;
    }

    public void MouseLeave(MouseEvent event){
        this.children.remove(tooltip);
    }

    public void mouseEnter(MouseEvent event){
        this.children.add(tooltip);
    }

    public abstract void draw (Graphics2D graphics, boolean overlay);

}
