package jegmezo.view;

import java.awt.*;

public class TooltipView extends View {
    private int x, y;
    private String text;
    boolean show = false;

    TooltipView(GameWindow gameWindow, AssetManager assetManager, String text) {
        super(gameWindow, assetManager);
        this.text = text;
    }

    void setText(String text){
        this.text = text;
    }
    void setShow(boolean show){
        this.show = show;
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }

    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }


    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if(show){
            super.draw(graphics, overlay);
            if (!overlay) return;

            DrawUtils drawUtils = new DrawUtils(graphics);
            Rectangle rectangle = drawUtils.calculateStringBounds(text.split("\n"), assetManager.getFont(), 1.2f);
            rectangle.setSize((int)rectangle.getWidth() + 8, (int)rectangle.getHeight() + 8);
            rectangle.setLocation(x - (int)rectangle.getWidth(), y - (int)rectangle.getHeight());
            graphics.setColor(Color.WHITE);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            graphics.fill(rectangle);

            graphics.setColor(Color.DARK_GRAY);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            drawUtils.drawStringRectangle(text, assetManager.getFont(), 1.2f, drawUtils.padding(rectangle, 4), VerticalAlignment.Top, HorizontalAlignment.Left);

        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
