package jegmezo;

public class IceSheetView extends View {


    public IceSheetView(ImageManager imageManager) {
        super(imageManager);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
