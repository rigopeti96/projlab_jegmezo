package jegmezo.view;

import java.awt.*;

/**
 * Ez az osztály valósítja meg a játékon belül a konzolt, amin a játék állapotai olvashatóak
 */
public class ConsoleView extends View {
    /**
     * Eltárolja az utolsó 10 console üzenetet
     */
    private String[] text;

    /**
     * Az osztály konstruktora
     * @param gameWindow a játékablak paraméter
     * @param assetManager a képkezeléshez szükséges
     */
    public ConsoleView(GameWindow gameWindow, AssetManager assetManager) {
        super(gameWindow, assetManager);
        this.text = new String[]{"", "", "","", "", "","", "", "",""};
    }

    /**
     * Meglévő stringhez hozzáfűz egy másik stringet
     * @param string a hozzáfűzendő szöveg
     */
    public void write(String string){
        text[0] += string;
    }

    /**
     * Meglévő stringhez hozzáfűz egy másik stringet, majd egy sorral mindegyik kiírást feljebb tolja
     * @param string a hozzáfűzendő szöveg
     */
    public void writeLine(String string){
        text[0] += string;
        for(int i = this.text.length - 2; i >= 0; i--){
            text[i+1] = text[i];
        }
        text[0] = "";
    }

    /**
     * A kirajzolást végzá függvény
     * @param graphics a grafikát megvalósítő osztály
     * @param overlay ha átfedésben van, akkor visszatér, ha nincs, akkor rajzol ki bármit
     */
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

    /**
     *Rajta van-e az egér - ennél az osztálynál nem releváns, mindig false értékkel tér vissza
     * @param x x koordináta
     * @param y x koordináta
     * @return mindig false
     */
    @Override
    public boolean isMouseOver(int x, int y) {
        return false;
    }

    /**
     * Történt-e transzformáció - mindig false
     * @return mindig false
     */
    @Override
    public boolean isAffectedByTransformation() {
        return false;
    }
}
