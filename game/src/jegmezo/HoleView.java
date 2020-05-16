package jegmezo;

public class HoleView extends View {

    public HoleView(AssetManager assetManager) {
        super(assetManager);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
