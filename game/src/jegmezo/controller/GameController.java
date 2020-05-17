package jegmezo.controller;

import jegmezo.model.*;
import jegmezo.view.ConsoleView;
import jegmezo.view.GameWindow;
import jegmezo.view.OverlayType;

import java.util.Random;

/**
 * A gamecontroller osztály, a játékot vezérli
 */
public class GameController {
    /**
     * a játékhoz tartozó ablak, a gamewindow
     */
    private GameWindow gameWindow;
    /**
     * a consoleView a játékhoz
     */
    private ConsoleView consoleView;
    /**
     * a játék állapotát tároló gameState
     */
    private GameState gameState = GameState.Select;
    /**
     * a level
     */
    private Level level;
    /**
     * a játékos index
     */
    private int playerIndex = 0;
    /**
     * az aktív játékos
     */
    private Player activePlayer;
    /**
     * a kiválasztott tárgy
     */
    private Item selectedItem;
    /**
     * a random tag
     */
    private Random random = new Random();

    /**
     * A GameController konstruktora
     * @param gameWindow - az eltárolt gamewindow
     * @param consoleView - az eltárolt consoleView
     * @param level - az eltárolt level
     */
    public GameController(GameWindow gameWindow, ConsoleView consoleView, Level level) {
        this.gameWindow = gameWindow;
        this.consoleView = consoleView;
        this.level = level;
    }

    /**
     * visszaadja az eltárolt consoleView
     * @return - az eltárolt consoleView
     */
    public ConsoleView getConsoleView() {
        return consoleView;
    }

    /**
     * a játék állapotát adja vissza
     * @return - a játék állapota
     */
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * a játékot indítja el
     */
    public void start() {
        playerIndex = 0;
        activePlayer = this.level.getPlayers().get(playerIndex);
        activePlayer.resetActions();
        gameState = GameState.Select;
    }

    /**
     * a trade-et kezeli le
     * @param selectedItem - a kiválaszott tárgy a trade-elésre
     */
    public void tradeRequest(Item selectedItem) {
        gameWindow.schedule(() -> {
            this.gameState = GameState.Trade;
            this.selectedItem = selectedItem;
        }, 100);
    }

    /**
     * a trade lezárását kezeli
     * @param selectedPlayer - a kiválasztott játékos
     */
    public void tradeFinish(Player selectedPlayer) {
        if (this.activePlayer.trade(selectedItem, selectedPlayer)) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
        this.selectedItem = null;
        this.gameState = GameState.Select;
    }

    /**
     * a trade visszavonását kezeli le
     */
    public void tradeCancel() {
        this.selectedItem = null;
        this.gameState = GameState.Select;
    }

    /**
     * a passzolást kezeli le
     */
    public void pass() {
        activePlayer.loseAP();
        if (activePlayer.getAP() == 0) turnEnd();
    }

    /**
     * a tárgy használatát kezeli le
     * @param selectedItem - a kiválaszott tárgy
     */
    public void useItem(Item selectedItem) {
        if (selectedItem.use(activePlayer)) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    /**
     * a lépést kezeli le
     * @param selectedTile - a kiválaszott mező, amire lép a játékos
     */
    public void move(Tile selectedTile) {
        if (activePlayer.move(selectedTile)) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    /**
     * a kutató examine akcióját kezeli le
     * @param selectedTile
     */
    public void examine(Tile selectedTile) {
        if (((Scientist)activePlayer).examine(selectedTile)) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    /**
     * az igloo építését kezeli le
     */
    public void buildIgloo() {
        if (((Eskimo)activePlayer).buildIgloo()) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    /**
     * az ásást kezeli le
     */
    public void dig() {
        if (activePlayer.digWithHands()) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    /**
     * a tárgy felvételét kezeli le
     */
    public void pickup() {
        if (activePlayer.pickup()) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    /**
     * a kör végét kezeli le
     */
    public void turnEnd() {
        playerIndex++;
        if (playerIndex >= this.level.getPlayerCount()) {
            int blizzard_is_coming = random.nextInt(2); //random sorsoljuk, hogy egyáltalán jön-e hóvihar bárhova vagy sem
            if (blizzard_is_coming == 1) {
                setOverlayType(OverlayType.Blizzard);
                gameState = GameState.Idle;
                gameWindow.schedule(() -> {
                    setOverlayType(OverlayType.None);
                    level.blizzard(this);
                    level.movePolarBear();
                    level.destroyTiles();
                    playerIndex = 0;
                    activePlayer = this.level.getPlayers().get(playerIndex);
                    activePlayer.resetActions();
                    gameState = GameState.Select;
                }, 3000);
                return;
            }

            level.movePolarBear();
            level.destroyTiles();
            playerIndex = 0;
            activePlayer = this.level.getPlayers().get(playerIndex);
            activePlayer.resetActions();
            return;
        }
        activePlayer = this.level.getPlayers().get(playerIndex);
        activePlayer.resetActions();
    }

    /**
     * a játék megnyerését kezeli le
     */
    public void win() {
        gameWindow.schedule(() -> {
            gameState = GameState.Over;
        }, 100);
        setOverlayType(OverlayType.GameWin);
    }

    /**
     * a játék vesztését kezeli le
     */
    public void lose() {
        gameWindow.schedule(() -> {
            gameState = GameState.Over;
        }, 100);
        setOverlayType(OverlayType.GameOver);
    }

    /**
     * az overlay típusát állítja be
     * @param overlayType - a kiválasztott overlay
     */
    public void setOverlayType(OverlayType overlayType) {
        gameWindow.setOverlayType(overlayType);
    }

    /**
     * a levelt adja vissza
     * @return - a level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * a random tagot adja vissza
     * @return - a random tag
     */
    public Random getRandom() {
        return random;
    }

    /**
     * az aktív játékost adja vissza
     * @return - az aktív játékos
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * a kiválasztott tárgyat adja vissza
     * @return - a kiválasztott tárgy
     */
    public Item getSelectedItem() { return selectedItem; }
}
