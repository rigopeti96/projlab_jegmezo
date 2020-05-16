package jegmezo.view;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelView extends View {
    private List<TileView> tileViews=new ArrayList<>();


    public LevelView(AssetManager assetManager, PlayerStatusView playerStatusView, List<TileView> tileViews) {
            super(assetManager);
        this.tileViews = tileViews;
        for(TileView tv: tileViews){
            children.add(tv);
        }
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}


