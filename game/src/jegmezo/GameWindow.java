package jegmezo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
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

    private ImageManager imageManager = new ImageManager();
    private Random r = new Random();

    public void start() {
        JFrame frame= new JFrame();
        frame.setTitle("Jégmező");
        frame.setSize(800, 480);
        frame.setVisible(true);
        frame.setContentPane(new GameCanvas());
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
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
        executor.scheduleAtFixedRate(() -> {
            frame.repaint();
        }, 0, 16, TimeUnit.MILLISECONDS);
    }

    private void initialize() {

    }

    private void handleClick(MouseEvent event) {

    }

    private void handleMouseMove(MouseEvent event) {

    }

    private void handleMouseWheelMove(MouseWheelEvent event) {

    }

    private void draw(Graphics2D graphics) {
        Shape rootRect = graphics.getClipBounds();
        graphics.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),r.nextInt(256)));
        graphics.fill(rootRect);
    }

    private void handleClose() {
    }
}
