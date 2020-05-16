package jegmezo;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelView extends View {
    private List<TileView> tileViews=new ArrayList<>();


    public LevelView(ImageManager imageManager,PlayerStatusView playerStatusView,List<TileView> tileViews) {
            super(imageManager);
            this.tileViews=tileViews;
            for(TileView tv: tileViews){
            children.add(tv);
        }
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        if(event.getWheelRotation()<0) {
            System.out.println(" fel "+event.getWheelRotation());
            /*for (TileView tv : tileViews)
                tv.resize(tv.getsize() + 1);*/
        }
        else{
            System.out.println(" fel "+event.getWheelRotation());
            /*for (TileView tv : tileViews)
                tv.resize(tv.getsize() - 1);*/
        }
    }
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        //super.draw(graphics,overlay);
        if(overlay)
            return;
        /*for (View child: children) {
            child.draw(graphics, overlay);
        }*/
    }

}


