package org.igrok_net.engine.ui.components;

import org.igrok_net.engine.IGNColor;
import org.igrok_net.engine.ui.interfaces.Component;

/**
 * Represents base class for any ui component
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public abstract class AbstractComponent implements Component {
    protected int x, y, width, height;
    protected IGNColor background, border;

    /**
     * Initialises abstract component
     */
    public AbstractComponent() {
        super();
    }

    /**
     * Initialises abstract component with given size and position
     * @param x top-left x
     * @param y top-left y
     * @param width width
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
    public void setColors(IGNColor background, IGNColor border){
        this.background = background;
        this.border = border;
    }

}
