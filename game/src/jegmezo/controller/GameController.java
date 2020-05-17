package jegmezo.controller;

import jegmezo.model.*;
import jegmezo.view.ConsoleView;
import jegmezo.view.GameWindow;
import jegmezo.view.OverlayType;

import java.util.Random;

public class GameController {
    private GameWindow gameWindow;
    private ConsoleView consoleView;
    private GameState gameState = GameState.Select;

    private Level level;
    private int playerIndex = 0;

    private Player activePlayer;
    private Item selectedItem;
    private Random random = new Random();

    public GameController(GameWindow gameWindow, ConsoleView consoleView, Level level) {
        this.gameWindow = gameWindow;
        this.consoleView = consoleView;
        this.level = level;
    }

    public ConsoleView getConsoleView() {
        return consoleView;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void start() {
        playerIndex = 0;
        activePlayer = this.level.getPlayers().get(playerIndex);
        activePlayer.resetActions();
        gameState = GameState.Select;
    }

    public void tradeRequest(Item selectedItem) {
        gameWindow.schedule(() -> {
            this.gameState = GameState.Trade;
            this.selectedItem = selectedItem;
        }, 100);
    }

    public void tradeFinish(Player selectedPlayer) {
        if (this.activePlayer.trade(selectedItem, selectedPlayer)) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
        this.selectedItem = null;
        this.gameState = GameState.Select;
    }

    public void tradeCancel() {
        this.selectedItem = null;
        this.gameState = GameState.Select;
    }

    public void pass() {
        activePlayer.loseAP();
        if (activePlayer.getAP() == 0) turnEnd();
    }

    public void useItem(Item selectedItem) {
        if (selectedItem.use(activePlayer)) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    public void move(Tile selectedTile) {
        if (activePlayer.move(selectedTile)) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    public void examine(Tile selectedTile) {
        if (((Scientist)activePlayer).examine(selectedTile)) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    public void buildIgloo() {
        if (((Eskimo)activePlayer).buildIgloo()) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    public void dig() {
        if (activePlayer.digWithHands()) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    public void pickup() {
        if (activePlayer.pickup()) {
            activePlayer.loseAP();
            if (activePlayer.getAP() == 0) turnEnd();
        }
    }

    public void turnEnd() {
        playerIndex++;
        if (playerIndex >= this.level.getPlayerCount()) {
            int blizzard_is_coming = random.nextInt(2); //random sorsoljuk, hogy egyáltalán jön-e hóvihar bárhova vagy sem
            if (blizzard_is_coming == 1) {
                level.blizzard(this);
                setOverlayType(OverlayType.Blizzard);
                gameState = GameState.Idle;
                gameWindow.schedule(() -> {
                    setOverlayType(OverlayType.None);
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

    public void win() {
        gameWindow.schedule(() -> {
            gameState = GameState.Over;
        }, 100);
        setOverlayType(OverlayType.GameWin);
    }

    public void lose() {
        gameWindow.schedule(() -> {
            gameState = GameState.Over;
        }, 100);
        setOverlayType(OverlayType.GameOver);
    }

    public void setOverlayType(OverlayType overlayType) {
        gameWindow.setOverlayType(overlayType);
    }

    public Level getLevel() {
        return level;
    }

    public Random getRandom() {
        return random;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Item getSelectedItem() { return selectedItem; }
}
