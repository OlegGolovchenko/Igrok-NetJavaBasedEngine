package org.igrok_net.engine.ui.components;

import java.util.ArrayList;
import java.util.List;

import org.igrok_net.engine.ui.events.KeyPress;
import org.igrok_net.engine.ui.events.MouseMoved;
import org.igrok_net.engine.ui.interfaces.Component;
import org.igrok_net.engine.ui.interfaces.ComponentContainer;

/**
 * Base class for container component
 * 
 * @version 0.0.1
 * @author Oleg Golovchenko
 */
public abstract class AbstractContainerComponent extends AbstractComponent implements ComponentContainer {

    protected List<Component> components;

    /**
     * Default constructor for abstract container component
     */
    public AbstractContainerComponent() {
        super();
        this.components = new ArrayList<Component>();
    }

    /**
     * Creates abstract container component with given params
     * @param x top-left x
     * @param y top-left y
     * @param width width
     * @param height height
     */
    public AbstractContainerComponent(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.components = new ArrayList<Component>();
    }

    @Override
    public void render() {
        for (Component component : this.components) {
            component.renderAtOffset(this.x, this.y);
        }
    }

    @Override
    public void renderAtOffset(int xOffset, int yOffset) {
        for (Component component : this.components) {
            component.renderAtOffset(this.x + xOffset, this.y + yOffset);
        }
    }

    @Override
    public void addChild(Component component) {
        this.components.add(component);
    }

    @Override
    public void removeChild(Component component) {
        this.components.remove(component);
    }

    @Override
    public void removeAll() {
        this.components.clear();
    }

    @Override
    public void sendKeyEvent(Object sender, KeyPress args) {
        super.sendKeyEvent(sender, args);
        for (Component component : this.components) {
            if (component.isMouseInside(this.mouseX - this.x, this.mouseY - this.y)) {
                component.sendKeyEvent(sender, args);
            }
        }
    }

    @Override
    public void sendKeyReleaseEvent(Object sender, KeyPress args) {
        super.sendKeyReleaseEvent(sender, args);
        for (Component component : this.components) {
            if (component.isMouseInside(this.mouseX - this.x, this.mouseY - this.y)) {
                component.sendKeyReleaseEvent(sender, args);
            }
        }
    }

    @Override
    public void sendMouseMovedEvent(Object sender, MouseMoved args) {
        super.sendMouseMovedEvent(sender, args);
        for (Component component : this.components) {
            if (component.isMouseInside(this.mouseX - this.x, this.mouseY - this.y)) {
                component.sendMouseMovedEvent(sender, args);
            }
        }
    }

    @Override
    public void sendMousePressEvent(Object sender, long button) {
        super.sendMousePressEvent(sender, button);
        for (Component component : this.components) {
            if (component.isMouseInside(this.mouseX - this.x, this.mouseY - this.y)) {
                component.sendMousePressEvent(sender, button);
            }
        }
    }

    @Override
    public void sendMouseReleaseEvent(Object sender, long button) {
        super.sendMouseReleaseEvent(sender, button);
        for (Component component : this.components) {
            if (component.isMouseInside(this.mouseX - this.x, this.mouseY - this.y)) {
                component.sendMouseReleaseEvent(sender, button);
            }
        }
    }

    /*
     * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Disposable#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        for (Component component : this.components) {
            component.dispose();
        }
        this.removeAll();
    }
}
