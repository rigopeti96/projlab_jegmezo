package jegmezo;

public class LevelView extends View {

    public LevelView(ImageManager imageManager) {
        super(imageManager);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
