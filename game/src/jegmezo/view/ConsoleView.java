package jegmezo.view;

import jegmezo.model.Player;

import java.awt.*;

public class ConsoleView extends View {
    private String[] text;
    public ConsoleView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
        this.text = new String[]{"", "", ""};
    }

    public void write(String string){
        text[0] = text[0] + string;
    }

    public void writeLine(String string){
        for(int i = 0; i < 2; i++){
            text[i+1] = text[i];
        }
        text[0] = string;
    }

    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        if(!overlay)
            return;
        DrawUtils du = new DrawUtils(graphics);
        String lines = text[0] + "\n" + text[1] + "\n" + text[2] + "\n";
        Rectangle consoleRectangle = new Rectangle(GameWindow.windowWidth - 330, 40, 300, 120);
        du.drawStringRectangle(lines, assetManager.getFont(), 1.2f, du.padding(consoleRectangle, 4), VerticalAlignment.Top, HorizontalAlignment.Right);
    }

    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }
}
