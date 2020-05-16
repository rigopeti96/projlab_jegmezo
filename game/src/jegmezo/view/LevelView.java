package jegmezo.view;

import jegmezo.model.Level;
import jegmezo.model.Tile;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LevelView extends View {
    List<TileView> tiles=new ArrayList<>();
    PlayerStatusView playerStatus;
    public LevelView(GameWindow gameWindow, AssetManager assetManager, Level level) {
        super(gameWindow, assetManager);
        Collection<Tile> tilescoll=level.getTiles();
        for(Tile tile: tilescoll){
            int levelTilex=level.getLevelTileX(level.getLevelTileFor(tile));
            int levelTiley=level.getLevelTileY(level.getLevelTileFor(tile));
            if (Math.abs(levelTilex % 2) == 1) {
                tiles.add(tile.createView(gameWindow,assetManager,gameWindow.windowWidth/2 + (levelTilex / 2) * (50 * 3), (int)(gameWindow.windowHeight/2 + (levelTiley - 0.5) * 2 * Math.cos(Math.PI / 6) * 50)));
            } else {
                tiles.add(tile.createView(gameWindow,assetManager,gameWindow.windowWidth/2 + (levelTilex / 2) * (50 * 3)-75, (int)(gameWindow.windowHeight/2 + levelTiley * 2 * Math.cos(Math.PI / 6) * 50)));
            }
        }
        for(TileView tv: tiles)
            children.add(tv);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics,overlay);
        if(overlay)
            return;
        for (View child: new ArrayList<>(children)) {
            child.draw(graphics, overlay);
        }
    }
}


