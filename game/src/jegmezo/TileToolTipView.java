package jegmezo;

public class TileToolTipView extends View {
    private String text;

    public TileToolTipView(AssetManager assetManager) {
        super(assetManager);
    }


    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
