package jegmezo;

public class TileToolTipView extends View {
    private String text;

    public TileToolTipView(ImageManager imageManager) {
        super(imageManager);
    }


    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
