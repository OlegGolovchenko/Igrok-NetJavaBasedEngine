package org.igrok_net.engine.ui.components;

import org.igrok_net.engine.IGNColor;
import org.igrok_net.engine.ui.events.KeyPress;
import org.igrok_net.engine.ui.events.MouseMoved;
import org.igrok_net.engine.ui.interfaces.Component;

/**
 * Represents base class for any ui component
 * 
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public abstract class AbstractComponent implements Component {
    protected int x, y, width, height, mouseX, mouseY;
    protected IGNColor background, border;

    protected void onMousePress(Object sender, long button) {
    }

    protected void onKeyPress(Object sender, KeyPress eventArgs) {
    }

    protected void onKeyRelease(Object sender, KeyPress eventArgs) {
    }

    protected void onMouseMoved(Object sender, MouseMoved eventArgs) {
    }

    /**
     * Initialises abstract component
     */
    public AbstractComponent() {
        super();
    }

    /**
     * Initialises abstract component with given size and position
     * 
     * @param x      top-left x
     * @param y      top-left y
     * @param width  width
     * @param height height
     */
    public AbstractComponent(int x, int y, int width, int height) {
        super();
        this.reposition(x, y);
        this.resize(width, height);
    }

    @Override
    public void dispose() {
        this.reposition(0, 0);
        this.resize(0, 0);
    }

    @Override
    public void reposition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setColors(IGNColor background, IGNColor border) {
        this.background = background;
        this.border = border;
    }

    @Override
    public boolean isMouseInside(int mX, int mY) {
        if (this.x < mX && (this.x + this.width > mX)) {
            if (this.y < mY && (this.y + this.height > mY)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void sendKeyEvent(Object sender, KeyPress args) {
        this.onKeyPress(sender, args);
    }

    @Override
    public void sendKeyReleaseEvent(Object sender, KeyPress args) {
        this.onKeyRelease(sender, args);
    }

    @Override
    public void sendMouseMovedEvent(Object sender, MouseMoved args) {
        this.mouseX = args.getX();
        this.mouseY = args.getY();
        this.onMouseMoved(sender, args);
    }
}
