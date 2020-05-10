package jegmezo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class View {
    protected boolean hovered;
    protected List<View> children = new ArrayList<>();

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
        return (event.getButton() == MouseEvent.BUTTON1 && clicked()) || (event.getButton() == MouseEvent.BUTTON2 && rightClicked());
    }

    public void handleMouseMove(MouseEvent event) {
        if (isMouseOver(event.getX(), event.getY())) {
            if (!hovered) {
                mouseEnter();
                hovered = true;
            }
            mouseMoved();
        } else {
            if (hovered) {
                mouseLeave();
                hovered = false;
            }
        }

        for (View child: children) {
            child.handleMouseMove(event);
        }
    }

    public boolean clicked() {
        return false;
    }

    public boolean rightClicked() {
        return false;
    }

    public void windowClicked() {
        for (View child: children) {
            child.windowClicked();
        }
    }

    public void mouseMoved() {

    }

    public void mouseEnter() {

    }

    public void mouseLeave() {

    }

    public boolean isHovered() {
        return hovered;
    }

    public abstract boolean isMouseOver(int x, int y);

    public void draw(Graphics2D graphics, boolean overlay) {
        for (View child: children) {
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

