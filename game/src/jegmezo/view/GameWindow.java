package jegmezo.view;

import jegmezo.controller.GameController;
import jegmezo.controller.GameState;
import jegmezo.model.Eskimo;
import jegmezo.model.Level;
import jegmezo.model.Player;
import jegmezo.model.Scientist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameWindow {
    class GameCanvas extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            draw(g2d);
        }
    }

    public static int windowWidth = 1600;
    public static int windowHeight = 900;
    TooltipView tooltipView;
    MenuView menuView;
    View overlayView;
    private GameController gameController;
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public GameController getGameController() {
        return gameController;
    }

    public TooltipView getTooltipView(){
        return tooltipView;
    }

    public void openMenu(MenuView newMenu) {
        views.remove(menuView);
        views.add(newMenu);
        menuView = newMenu;
        views.remove(tooltipView);
    }

    public void closeMenu() {
        views.remove(menuView);
        views.add(tooltipView);
    }

    private List<View> views = new ArrayList<>();
    private AssetManager assetManager = new AssetManager();

    public void start() {
        JFrame frame= new JFrame();
        frame.setTitle("Jégmező");
        frame.setSize(windowWidth, windowHeight);
        frame.setContentPane(new GameCanvas());
        initialize();
        frame.setVisible(true);
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent event) {
                handleClick(event);
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent event) {
                handleMouseMove(event);
            }
        });

        frame.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent event) {
                handleMouseWheelMove(event);
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
                handleClose();
            }
        });

        executor.scheduleAtFixedRate(frame::repaint, 0, 16, TimeUnit.MILLISECONDS);
    }

    private void initialize() {
        assetManager.loadAssets();
        tooltipView = new TooltipView(this, assetManager, "");

        ConsoleView consoleView = new ConsoleView(this, assetManager);
        Level level = new Level();
        gameController = new GameController(this, consoleView, level);
        ArrayList<Player> players = new ArrayList<>();
        int playerCount = getPlayerCountPopup();
        for (int i = 1; i <= playerCount; i++) {
            if (getPlayerClassPopup(i).equals("eskimo")) {
                players.add(new Eskimo(gameController, i));
            } else {
                players.add(new Scientist(gameController, i));
            }
        }
        level.generate(gameController, players);
        LevelView levelView = new LevelView(this, assetManager, level);
        views.add(levelView);

        views.add(new InventoryView(this, assetManager, players.get(0).getInventory()));
        views.add(new PlayerStatusView(this, assetManager));
        views.add(consoleView);
        views.add(tooltipView);
        gameController.start();
    }

    private int getPlayerCountPopup() {
        while (true) {
            try {
                String playerCount = JOptionPane.showInputDialog("How many players (3-8)?");
                if (playerCount == null) System.exit(0);
                int count = Integer.parseInt(playerCount);
                if (count >= 3 && count <= 8) return count;
            } catch (NumberFormatException ignored) {

            }
        }
    }

    private String getPlayerClassPopup(int number) {
        while (true) {
            String playerClass = JOptionPane.showInputDialog("Player " + number + "'s class (Eskimo/Scientist)");
            if (playerClass == null) System.exit(0);
            playerClass = playerClass.toLowerCase();
            if (playerClass.equals("eskimo") || playerClass.equals("scientist")) return playerClass;
        }
    }

    public void schedule(Runnable runnable, int time) {
        executor.schedule(runnable, time, TimeUnit.MILLISECONDS);
    }

    private void handleClick(MouseEvent event) {
        if(gameController.getGameState() == GameState.Idle)
            return;
        event = remapMouseEvent(event);
        for (View view: new ArrayList<>(views)) {
            view.windowClicked(event);
        }

        for (View view: new ArrayList<>(views)) {
            view.handleClick(event);
        }
        if(gameController.getGameState() == GameState.Over)
            System.exit(0);
        if(gameController.getGameState() == GameState.Trade)
            gameController.tradeCancel();
    }

    private void handleMouseMove(MouseEvent event) {
        if(gameController.getGameState() == GameState.Over)
            return;
        event = remapMouseEvent(event);
        for (View view: new ArrayList<>(views)) {
            view.handleMouseMove(event);
        }
    }

    private void handleMouseWheelMove(MouseWheelEvent event) {
        for (View view: new ArrayList<>(views)) {
            view.mouseWheelMoved(event);
        }
    }

    private MouseEvent remapMouseEvent(MouseEvent event) {
        return new MouseEvent(event.getComponent(), event.getID(), event.getWhen(), event.getModifiersEx(), event.getX() - 8, event.getY() - 32, event.getClickCount(), false, event.getButton());
    }

    private void draw(Graphics2D graphics) {
        graphics.setColor(assetManager.getColor("Sea"));
        graphics.fill(graphics.getClipBounds());

        for (View view: new ArrayList<>(views)) {
            view.draw(graphics, false);
        }
        for (View view: new ArrayList<>(views)) {
            view.draw(graphics, true);
        }
    }

    private void handleClose() {
    }

    public void setOverlayType(OverlayType overlayType) {
        if (this.overlayView != null) views.remove(this.overlayView);
        if (overlayType == OverlayType.GameWin) {
            overlayView = new EndOverlayView(this, assetManager, true);
            views.add(overlayView);
        } else if (overlayType == OverlayType.GameOver) {
            overlayView = new EndOverlayView(this, assetManager, false);
            views.add(overlayView);
        } else if (overlayType == OverlayType.Blizzard) {
            overlayView = new BlizzardOverlayView(this, assetManager);
            views.add(overlayView);
        } else {
            overlayView = null;
        }
    }
}
