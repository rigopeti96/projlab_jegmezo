package jegmezo.view;

import jegmezo.model.Level;
import jegmezo.model.LevelTile;
import jegmezo.model.Player;
import jegmezo.model.Tile;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * A térképet megvalósító osztály
 */
public class LevelView extends View {
    /**
     * a pálya változó
     */
    private Level level;
    /**
     * egy pálya szomszédjai
     */
    Map<Tile, TileView> tiles =new HashMap<>();

    /**
     * Konstruktor
     * @param gameWindow játékablak
     * @param assetManager képkirajzoló
     * @param level pálya
     */
    public LevelView(GameWindow gameWindow, AssetManager assetManager, Level level) {
        super(gameWindow, assetManager);
        this.level = level;
        Collection<Tile> levelTiles = level.getTiles();
        for(Tile tile: levelTiles) {
            int x = level.getLevelTileFor(tile).getX();
            int y = level.getLevelTileFor(tile).getY();
            if (Math.abs(x % 2) == 1) {
                this.tiles.put(tile, tile.createView(gameWindow,assetManager, this, (int)(GameWindow.windowWidth /2 + (x / 2.0) * (50 * 3)), (int)(GameWindow.windowHeight / 2+ (y - 0.5) * 2 * Math.cos(Math.PI / 6) * 50)));
            } else {
                this.tiles.put(tile, tile.createView(gameWindow,assetManager, this, (int)(GameWindow.windowWidth /2 + (x / 2.0) * (50 * 3)), (int)(GameWindow.windowHeight /2 + y * 2 * Math.cos(Math.PI / 6) * 50)));
            }
        }
        for (Player player: level.getPlayers()) {
            this.attachPlayerViewToOwner(player.createView(gameWindow, assetManager));
        }
        children.addAll(this.tiles.values());
    }

    /**
     * visszaadja, hogy szomszédos-e két tile
     * @param a egyik tile
     * @param b másik tile
     * @return Ha szomszédosak, akkor true, egyébként false
     */
    public boolean isTileNeighbourOf(Tile a, Tile b) {
        LevelTile la = level.getLevelTileFor(a);
        LevelTile lb = level.getLevelTileFor(b);

        if (Math.abs(la.getX() - lb.getX()) + Math.abs(la.getY() - lb.getY()) == 1) return true;
        if (Math.abs(la.getX() % 2) == 0) {
            return Math.abs(la.getX() - lb.getX()) == 1 && la.getY() + 1 == lb.getY();
        } else {
            return Math.abs(la.getX() - lb.getX()) == 1 && la.getY() - 1 == lb.getY();
        }
    }

    /**
     * hozzácsatol egy playerview-t a megfelelő playerhez
     * @param playerView a csatolandó nézet
     */
    public void attachPlayerViewToOwner(PlayerView playerView) {
        tiles.get(playerView.getPlayer().getTile()).attachPlayerView(playerView);
    }

    /**
     * rajta van-e az egér - nem releváns
     * @param x x koordináta
     * @param y y koordináta
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
        return true;
    }

    /**
     * A kirajzolást végzá függvény
     * @param graphics a grafikát megvalósítő osztály
     * @param overlay ha átfedésben van, akkor visszatér, ha nincs, akkor rajzol ki bármit
     */
    @Override
    public void draw(Graphics2D graphics, boolean overlay) {
        super.draw(graphics,overlay);
    }
}


