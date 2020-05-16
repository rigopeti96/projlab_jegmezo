package jegmezo.view;

import jegmezo.model.Player;
import jegmezo.model.Tile;

import java.awt.*;
import java.util.List;

public class IceSheetView extends TileView {


    public IceSheetView(GameWindow gameWindow, AssetManager assetManager, int x, int y, Tile tile) {
        super(gameWindow, assetManager,x,y,tile);
    }


    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics,overlay);
        if (overlay) return;
        //graphics.drawImage(imageManager.getImage("testImage"), this.x, this.y, 200, 100, null);

        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (x + 50 *size* Math.cos(i * 2 * Math.PI / 6)),
                    (int) (y + 50 * size*Math.sin(i * 2 * Math.PI / 6)));
        graphics.setColor(Color.WHITE);
        graphics.fillPolygon(p);
        for (int i = 0; i < 6; i++)
            p.addPoint((int) (x + 50*size * Math.cos(i * 2 * Math.PI / 6)),
                    (int) (y + 50 *size* Math.sin(i * 2 * Math.PI / 6)));
        graphics.setColor(Color.GRAY);
        graphics.drawPolygon(p);

        //lekérjük az aktuális játékosok listáját akik a mezőn állnak
        List<Player> players = tile.getPlayers();
        //ezekhez player View-kat hozunk létre
        for (Player player: players
             ) {
            gameWindow.setXYToPlayer(player.getNumber(), x, y);
        }

        for (View child : children)
            child.draw(graphics, overlay);
    }
}


