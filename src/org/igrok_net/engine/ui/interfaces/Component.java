package org.igrok_net.engine.ui.interfaces;

import org.igrok_net.engine.IGNColor;
import org.igrok_net.engine.ui.events.KeyPress;
import org.igrok_net.engine.ui.events.MouseMoved;

/**
 * Marks object as Dsiposable Component
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public interface Component extends Disposable {

    /**
     * Renders contents of this component in 2D opengl context of the window
     */
    void render();

    /**
     * Repositions component to given x:y
     * @param x x coord
     * @param y y coord
     */
    void reposition(int x, int y);

    /**
     * Resizes component to given w:h
     * @param width width
     * @param height height
     */
    void resize(int width, int height);

    /**
     * Changes background and border color
     * @param background background
     * @param border border
     */
    void setColors(IGNColor background, IGNColor border);
    
    /**
     * offsets and renders component
     * @param xOffset x offset
     * @param yOffset y offset
     */
    void renderAtOffset(int xOffset, int yOffset);

    /**
     * Sends key press event
     * @param sender sender
     * @param args arguments
     */
    void sendKeyEvent(Object sender, KeyPress args);

    /**
     * Sends key released event
     * @param sender sender
     * @param args arguments
     */
    void sendKeyReleaseEvent(Object sender, KeyPress args);
    
    /**
     * Sends mouse move event
     * @param sender sender
     * @param args arguments
     */
    void sendMouseMovedEvent(Object sender, MouseMoved args);

    /**
     * Checks if mouse is inside
     * @param mX mouse pointer x
     * @param mY mouse pointer y
     * @return true if inside false otherwise
     */
    boolean isMouseInside(int mX, int mY);
}
