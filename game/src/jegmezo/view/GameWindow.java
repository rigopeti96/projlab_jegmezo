package jegmezo.view;

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

    public static int windowWidth = 800;
    public static int windowHeight = 480;
    TooltipView tooltipView;
    MenuView menuView;
    ArrayList<PlayerView> playerViews = new ArrayList<PlayerView>();

    public void setXYToPlayer(int number, int x, int y){
        for (PlayerView playerView: playerViews
             ) {
            if(playerView.getPlayer().getNumber() == number){
                playerView.setX(x);
                playerView.setY(y);
            }
        }
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

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(frame::repaint, 0, 16, TimeUnit.MILLISECONDS);
        initialize();
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

        tooltipView = new TooltipView(this, assetManager, "");

        Inventory inventory = new Inventory(new GameController());
        inventory.equipBreakableShovel(new BreakableShovel());
        views.add(new InventoryView(this, assetManager, inventory));
        views.add(tooltipView);
        IceSheet iceSheet = new IceSheet(new GameController(), 0,3,0);

        //teszt játékosok
        Eskimo eskimo = new Eskimo(new GameController(), 0, 4);
        EskimoView eskimoView = new EskimoView(this, assetManager);
        eskimoView.setPlayer(eskimo);
        iceSheet.stepOnto(eskimo,iceSheet);
        views.add(new IceSheetView(this, assetManager, 200,200, iceSheet));
        playerViews.add(eskimoView);
        views.add(eskimoView);
    }

    private void handleClick(MouseEvent event) {
        event = remapMouseEvent(event);
        for (View view: new ArrayList<>(views)) {
            view.windowClicked(event);
        }

        for (View view: new ArrayList<>(views)) {
            view.handleClick(event);
        }
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
}
