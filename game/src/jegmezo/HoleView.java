package jegmezo;

import java.awt.*;

public class HoleView extends TileView {

    public HoleView(ImageManager imageManager,int x,int y,Tile tile) {
        super(imageManager,x,y,tile);
    }




    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics,overlay);
        if (overlay) return;
        //graphics.drawImage(imageManager.getImage("testImage"), this.x, this.y, 100, 100, null);
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (x + 50*size * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (y + 50*size * Math.sin(i * 2 * Math.PI / 6)));
        if(this.tile.getSnow()==0)
            graphics.setColor(Color.BLUE);
        else{
            graphics.setColor(Color.WHITE);
        }
        graphics.fillPolygon(p);
        for (int i = 0; i < 6; i++)
            p.addPoint((int)( x + 50*size * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (y + 50*size * Math.sin(i * 2 * Math.PI / 6)));
        graphics.setColor(Color.GRAY);
        graphics.drawPolygon(p);
        for (View child : children)
            child.draw(graphics, overlay);
    }

}
