package jegmezo.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.List;

public abstract class View {
    protected boolean hovered;
    protected List<View> children = new ArrayList<>();
    protected AssetManager assetManager;
    GameWindow gameWindow;

    public View(GameWindow gameWindow, AssetManager assetManager) {
        this.gameWindow = gameWindow;
        this.assetManager = assetManager;
    }

    public boolean handleClick(MouseEvent event) {
        MouseEvent originalEvent = event;
        event = remapMouseEvent(event);
        if (isMouseOver(event.getX(), event.getY()) && helperClicked(event)) {
            return true;
        }

        for (View child: children) {
            if (child.handleClick(originalEvent)) return true;
        }

        return false;
    }

    private boolean helperClicked(MouseEvent event) {
        return (event.getButton() == MouseEvent.BUTTON1 && clicked(event)) || (event.getButton() == MouseEvent.BUTTON3 && rightClicked(event));
    }

    public void handleMouseMove(MouseEvent event) {
        MouseEvent originalEvent = event;
        event = remapMouseEvent(event);
        if (isMouseOver(event.getX(), event.getY())) {
            if (!hovered) {
                mouseEnter(event);
                hovered = true;
            }
            mouseMoved(event);
        } else {
            if (hovered) {
                mouseLeave(event);
                hovered = false;
            }
        }

        for (View child: new ArrayList<>(children)) {
            child.handleMouseMove(originalEvent);
        }
    }

    private MouseEvent remapMouseEvent(MouseEvent event) {
        Point transformed = new Point(event.getX(), event.getY());
        if (isAffectedByTransformation()) {
            try {
                gameWindow.getTransformation().inverseTransform(new Point(event.getX(), event.getY()), transformed);
            } catch (NoninvertibleTransformException e) {

            }
        }
        return new MouseEvent(event.getComponent(), event.getID(), event.getWhen(), event.getModifiersEx(), (int)transformed.getX(), (int)transformed.getY(), event.getClickCount(), false, event.getButton());
    }

    public boolean clicked(MouseEvent event) {
        return false;
    }

    public boolean rightClicked(MouseEvent event) {
        return false;
    }

    public void windowClicked(MouseEvent event) {
        for (View child: new ArrayList<>(children)) {
            child.windowClicked(event);
        }
    }

    public void mouseMoved(MouseEvent event) {

    }

    public void mouseWheelMoved(MouseWheelEvent event) {
        for (View child: new ArrayList<>(children)) {
            child.mouseWheelMoved(event);
        }
    }

    public void mouseEnter(MouseEvent event) {

    }

    public void mouseLeave(MouseEvent event) {

    }

    public boolean isHovered() {
        return hovered;
    }

    public abstract boolean isMouseOver(int x, int y);

    public abstract boolean isAffectedByTransformation();

    public void draw(Graphics2D graphics, boolean overlay) {
        if (isAffectedByTransformation()) graphics.setTransform(gameWindow.getTransformation());
        else graphics.setTransform(new AffineTransform());
        for (View child: new ArrayList<>(children)) {
            child.draw(graphics, overlay);
        }
    }

    public void addChild(View child) {
        children.add(child);
    }

    public void removeChild(View child) {
        children.remove(child);
    }
}

