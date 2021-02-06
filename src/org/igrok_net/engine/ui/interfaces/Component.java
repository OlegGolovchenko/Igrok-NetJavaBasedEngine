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
     * Sends mouse presses event
     * @param sender sender
     * @param button button code
     */
    void sendMousePressEvent(Object sender, long button);

    /**
     * Sends mouse presses event
     * @param sender sender
     * @param button button code
     */
    void sendMouseReleaseEvent(Object sender, long button);

    /**
     * Checks if mouse is inside
     * @param mX mouse pointer x
     * @param mY mouse pointer y
     * @param pX parent x
     * @param pY parent y
     * @return true if inside false otherwise
     */
    boolean isMouseInside(int mX, int mY, int pX, int pY);

    /**
     * Checks if component is focusable (cfr. TextBox)
     * @return true if focusable, false otherwise
     */
    boolean isFocuseable();
}
