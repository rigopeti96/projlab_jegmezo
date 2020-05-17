package jegmezo.view;

import jegmezo.model.Player;
import jegmezo.model.Tile;

import java.awt.*;
import java.util.List;

public class IceSheetView extends TileView {

    public IceSheetView(GameWindow gameWindow, AssetManager assetManager, LevelView levelView, int x, int y, Tile tile) {
        super(gameWindow, assetManager, levelView, x, y, tile);
    }

    @Override
    public boolean isAffectedByTransformation() {
        return true;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (x + 50 * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (y + 50 *Math.sin(i * 2 * Math.PI / 6)));

        if (overlay && hasActivePlayer()) {
            graphics.setStroke(new BasicStroke(4f));
            graphics.setColor(assetManager.getColor("highlight"));
            graphics.drawPolygon(p);
            graphics.setStroke(new BasicStroke(1f));
        }
        if (!overlay) {
            //graphics.drawImage(imageManager.getImage("testImage"), this.x, this.y, 200, 100, null);


            graphics.setColor(assetManager.getColor("Snow"));
            graphics.fillPolygon(p);
            for (int i = 0; i < 6; i++)
                p.addPoint((int) (x + 50 * Math.cos(i * 2 * Math.PI / 6)),
                        (int) (y + 50 * Math.sin(i * 2 * Math.PI / 6)));
            graphics.setColor(Color.gray);
            graphics.drawPolygon(p);

            if (tile.getPolarBear() != null) {
                graphics.setColor(Color.white);
                graphics.fillOval(x - 10 + 20, y - 10 + 20, 20, 25);
                graphics.setColor(Color.lightGray);
                graphics.drawOval(x - 10 + 20, y - 10 + 20, 20, 25);
            }
        }

        super.draw(graphics,overlay);
    }
}


