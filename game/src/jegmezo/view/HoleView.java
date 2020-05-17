package jegmezo.view;

import jegmezo.model.Tile;

import java.awt.*;
import java.awt.event.HierarchyBoundsAdapter;

public class HoleView extends TileView {

    public HoleView(GameWindow gameWindow, AssetManager assetManager, LevelView levelView, int x, int y, Tile tile) {
        super(gameWindow, assetManager, levelView, x, y, tile);
    }

    @Override
    public boolean isAffectedByTransformation() {
        return true;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if (!overlay) {

            Polygon p = new Polygon();
            for (int i = 0; i < 6; i++)
                p.addPoint((int) (x + 50 * Math.cos(i * 2 * Math.PI / 6)),
                        (int) (y + 50 * Math.sin(i * 2 * Math.PI / 6)));
            if (this.tile.getSnow() == 0)
                graphics.setColor(assetManager.getColor(tile.isDiscovered() ? "Sea" : "Snow"));
            else {
                graphics.setColor(assetManager.getColor("Snow"));
            }
            graphics.fillPolygon(p);
            if(tile.isDiscovered()) {
                p.reset();
                graphics.setColor(assetManager.getColor("Sea"));
                for (int i = 0; i < 6; i++)
                    p.addPoint((int) (x + 25 * Math.cos(i * 2 * Math.PI / 6)),
                            (int) (y + 25 * Math.sin(i * 2 * Math.PI / 6)));
                graphics.fillPolygon(p);
            }
            p.reset();
            for (int i = 0; i < 6; i++)
                p.addPoint((int) (x + 50 * Math.cos(i * 2 * Math.PI / 6)),
                        (int) (y + 50 * Math.sin(i * 2 * Math.PI / 6)));
            graphics.setColor(Color.GRAY);
            graphics.drawPolygon(p);
        }
        super.draw(graphics,overlay);
    }

}
