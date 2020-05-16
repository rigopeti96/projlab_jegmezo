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
    private ImageManager imageManager = new ImageManager();

    public void start() {
        JFrame frame= new JFrame();
        frame.setTitle("Jégmező");
        frame.setSize(800, 480);
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
        imageManager.loadImage("missingTexture", "missing_texture.png");
        imageManager.loadImage("testImage", "test_texture.png");
        views.add(new TestView(imageManager, 50, 50));
        views.add( new TestView(imageManager, 200, 50));

        Inventory inventory = new Inventory(new GameController());
        //views.add(new InventoryView(imageManager, 200, 200, inventory));
        views.add(new IceSheetView(imageManager,150,200,new IceSheet(new GameController(),1,1,0)) );
        views.add(new HoleView(imageManager,400,200,new Hole(new GameController(),1,1)));
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
