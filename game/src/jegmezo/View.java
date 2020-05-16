package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class View {
    protected boolean hovered;
    protected List<View> children = new ArrayList<>();
    protected AssetManager assetManager;

    public View(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public boolean handleClick(MouseEvent event) {
        if (isMouseOver(event.getX(), event.getY()) && helperClicked(event)) {
            return true;
        }

        for (View child: children) {
            if (child.handleClick(event)) return true;
        }

        return false;
    }

    private boolean helperClicked(MouseEvent event) {
        return (event.getButton() == MouseEvent.BUTTON1 && clicked(event)) || (event.getButton() == MouseEvent.BUTTON3 && rightClicked(event));
    }

    public void handleMouseMove(MouseEvent event) {
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
            child.handleMouseMove(event);
        }
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

    public void draw(Graphics2D graphics, boolean overlay) {
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

