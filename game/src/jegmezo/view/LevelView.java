package jegmezo.view;

import jegmezo.model.Level;
import jegmezo.model.Tile;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelView extends View {
    private List<TileView> tileViews=new ArrayList<>();


    public LevelView(GameWindow gameWindow, AssetManager assetManager, Level level) {
        super(gameWindow, assetManager);
        this.tileViews = tileViews;
        for(TileView tv: tileViews){
            children.add(tv);
        }
        //49,33
        //25,13
        //p*3*2+1, p*2*2+1
        //y=Y

        for(int i = 0; i < tiles.size(); i++){
            tileViews.add(tiles.get(i).createView(gameWindow, assetManager, 100,100, tiles.get(i)));
        }

    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}


