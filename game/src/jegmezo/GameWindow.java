package jegmezo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameWindow {

    public static int windowWidth = 800;
    public static int windowHeight = 480;

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

    private List<View> views = new ArrayList<>();
    private AssetManager assetManager = new AssetManager();

    public void start() {
        JFrame frame= new JFrame();
        frame.setTitle("Jégmező");
        frame.setSize(windowWidth, windowHeight);
        frame.setVisible(true);
        frame.setContentPane(new GameCanvas());
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
        assetManager.loadImage("testImage", "images/test_texture.png");
        assetManager.loadImage("rope", "images/rope.jpg");
        assetManager.loadImage("breakableshovel", "images/breakableshovel.png");
        assetManager.loadImage("food", "images/food.jpg");
        assetManager.loadImage("scubagear", "images/scubagear.jpg");
        assetManager.loadImage("shovel", "images/shovel.jpg");
        assetManager.loadImage("winitem", "images/winitem.jpg");
        assetManager.loadImage("tent", "images/tent.png");

        Inventory inventory = new Inventory(new GameController());
        views.add(new InventoryView(assetManager, 200, 200, inventory));
    }

    private void handleClick(MouseEvent event) {
        for (View view: views) {
            view.windowClicked(event);
        }

        for (View view: views) {
            view.handleClick(event);
        }
    }

    private void handleMouseMove(MouseEvent event) {
        for (View view: views) {
            view.handleMouseMove(event);
        }
    }

    private void handleMouseWheelMove(MouseWheelEvent event) {
        for (View view: views) {
            view.mouseWheelMoved(event);
        }
    }

    private void draw(Graphics2D graphics) {
        for (View view: views) {
            view.draw(graphics, false);
        }

        for (View view: views) {
            view.draw(graphics, true);
        }
    }

    private void handleClose() {
    }
}
