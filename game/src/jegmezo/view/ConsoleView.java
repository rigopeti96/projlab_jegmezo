package jegmezo.view;

import java.awt.*;

public class ConsoleView extends View {
    private String[] text;
    public ConsoleView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
        this.text = new String[]{"", "", "","", "", "","", "", "",""};
    }

    public void write(String string){
        text[0] += string;
    }

    public void writeLine(String string){
        text[0] += string;
        for(int i = this.text.length - 2; i >= 0; i--){
            text[i+1] = text[i];
        }
        text[0] = "";
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if(!overlay)
            return;
        DrawUtils du = new DrawUtils(graphics);
        graphics.setColor(Color.DARK_GRAY);
        StringBuilder lines = new StringBuilder();
        for (int i = this.text.length - 1; i >= 0; i--) {
            lines.append(text[i]).append("\n");
        }
        Rectangle consoleRectangle = new Rectangle(GameWindow.windowWidth - 330, 120, 300, 120);
        du.drawStringRectangle(lines.toString(), assetManager.getFont(), 1.2f, du.padding(consoleRectangle, 4), VerticalAlignment.Top, HorizontalAlignment.Right);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }

    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }
}
