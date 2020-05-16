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
        activePlayer = this.level.getPlayer(playerIndex);
        activePlayer.resetActions();
    }

    public void tradeRequest(Player activePlayer, Item selectedItem) {
        this.gameState = GameState.Trade;
        this.activePlayer = activePlayer;
        this.selectedItem = selectedItem;
    }

    public void tradeFinish(Player selectedPlayer) {
        this.activePlayer.trade(selectedItem, selectedPlayer);
        this.gameState = GameState.Select;
    }

    public void cancel() {
        this.activePlayer = null;
        this.selectedItem = null;
        this.gameState = GameState.Select;
    }

    public void useItem(Player activePlayer, Item selectedItem) {
        if (selectedItem.use(activePlayer)) {
            activePlayer.loseAP();
            if (activePlayer.getActions() == 0) turnEnd();
        }
    }

    public void move(Player activePlayer, Tile selectedTile) {
        if (activePlayer.move(selectedTile)) {
            activePlayer.loseAP();
            if (activePlayer.getActions() == 0) turnEnd();
        }
    }

    public void examine(Scientist activePlayer, Tile selectedTile) {
        if (activePlayer.examine(selectedTile)) {
            activePlayer.loseAP();
            if (activePlayer.getActions() == 0) turnEnd();
        }
    }

    public void buildIgloo(Eskimo activePlayer) {
        if (activePlayer.buildIgloo()) {
            activePlayer.loseAP();
            if (activePlayer.getActions() == 0) turnEnd();
        }
    }

    public void dig(Player activePlayer) {
        if (activePlayer.digWithHands()) {
            activePlayer.loseAP();
            if (activePlayer.getActions() == 0) turnEnd();
        }
    }

    public void turnEnd() {
        int blizzard_is_coming = random.nextInt(2); //random sorsoljuk, hogy egyáltalán jön-e hóvihar bárhova vagy sem
        if (blizzard_is_coming == 1) {
            level.blizzard(this);
            setOverlayType(OverlayType.Blizzard);
            gameState = GameState.Idle;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        level.movePolarBear();
        level.destroyTiles();
        playerIndex++;
        if (playerIndex >= this.level.getPlayerCount()) playerIndex = 0;
        activePlayer = this.level.getPlayer(playerIndex);
        activePlayer.resetActions();
        gameState = GameState.Select;
    }

    public void win() {
        gameState = GameState.Over;
        setOverlayType(OverlayType.GameWin);
    }

    public void lose() {
        gameState = GameState.Over;
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
}
