package jegmezo.view;

import jegmezo.model.Tile;

import java.awt.*;

public class HoleView extends TileView {

    public HoleView(GameWindow gameWindow, AssetManager assetManager, int x, int y, Tile tile) {
        super(gameWindow, assetManager, x, y, tile);
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics,overlay);
        if (overlay) return;

        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (x + 50 * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (y + 50 * Math.sin(i * 2 * Math.PI / 6)));
        if(this.tile.getSnow()==0)
            graphics.setColor(Color.BLUE);
        else{
            graphics.setColor(Color.WHITE);
        }
        graphics.fillPolygon(p);
        for (int i = 0; i < 6; i++)
            p.addPoint((int)( x + 50 * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (y + 50 * Math.sin(i * 2 * Math.PI / 6)));
        graphics.setColor(Color.GRAY);
        graphics.drawPolygon(p);
        for (View child : children)
            child.draw(graphics, overlay);
    }

}
