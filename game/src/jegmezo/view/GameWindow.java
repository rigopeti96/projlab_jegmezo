package jegmezo.view;

import jegmezo.controller.GameController;
import jegmezo.controller.GameState;
import jegmezo.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
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
        assetManager.loadImage("missingTexture", "images/missing_texture.png");
        assetManager.loadImage("Rope", "images/rope.jpg");
        assetManager.loadImage("Breakable shovel", "images/breakableshovel.png");
        assetManager.loadImage("Food", "images/food.jpg");
        assetManager.loadImage("Scuba gear", "images/scubagear.jpg");
        assetManager.loadImage("Shovel", "images/shovel.jpg");
        assetManager.loadImage("Win item", "images/winitem.jpg");
        assetManager.loadImage("Tent", "images/tent.png");
        assetManager.loadImage("blizzardOverlay1", "images/snow1.png");
        assetManager.loadImage("blizzardOverlay2", "images/snow2.png");
        assetManager.loadImage("eskimo", "images/eskimo-003-512.png");
        assetManager.loadImage("scientist", "images/species-researcher-005-512.png");
        assetManager.loadImage("gameOver", "images/lose.png");
        assetManager.loadImage("gameWin", "images/win.jpg");

        tooltipView = new TooltipView(this, assetManager, "");

        ConsoleView consoleView = new ConsoleView(this, assetManager);
        Level level = new Level();
        gameController = new GameController(this, consoleView, level);
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Eskimo(gameController, 1));
        players.add(new Scientist(gameController, 2));
        players.add(new Eskimo(gameController, 3));
        level.generate(gameController, players);
        LevelView levelView = new LevelView(this, assetManager, level);
        views.add(levelView);

        views.add(new InventoryView(this, assetManager, players.get(0).getInventory()));
        views.add(new PlayerStatusView(this, assetManager));
        views.add(consoleView);
        views.add(tooltipView);
        gameController.start();
    }

    public void schedule(Runnable runnable, int time) {
        executor.schedule(runnable, time, TimeUnit.MILLISECONDS);
    }

    private void handleClick(MouseEvent event) {
        event = remapMouseEvent(event);
        for (View view: new ArrayList<>(views)) {
            view.windowClicked(event);
        }

        for (View view: new ArrayList<>(views)) {
            view.handleClick(event);
        }
        if(gameController.getGameState() == GameState.Over)
            System.exit(0);
    }

    private void handleMouseMove(MouseEvent event) {
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
