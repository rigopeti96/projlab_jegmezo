package jegmezo;

public class HoleView extends View {

    public HoleView(ImageManager imageManager) {
        super(imageManager);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
