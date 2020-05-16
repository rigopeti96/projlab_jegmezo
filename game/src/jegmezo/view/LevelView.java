package jegmezo.view;

import jegmezo.model.Level;
import jegmezo.model.Tile;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelView extends View {
    public LevelView(GameWindow gameWindow, AssetManager assetManager, Level level) {
        super(gameWindow, assetManager);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}


