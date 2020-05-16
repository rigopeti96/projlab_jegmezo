package jegmezo;

import java.awt.*;

public class TileToolTipView extends View {
    private int x,y;
    private String text;

    public TileToolTipView(ImageManager imageManager,int px, int py, String tx) {
        super(imageManager);
        x=px;
        y=py;
        text=tx;
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }


    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if(overlay)
            return;
        graphics.setColor(Color.WHITE);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.8f));
        graphics.fillRect(x,y,7*text.length(),20);

        graphics.setColor(Color.DARK_GRAY);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
        Font font=new Font("Serif",Font.BOLD,11);
        graphics.setFont(font);
        graphics.drawString(text,x+10,y+10);
    }
}
