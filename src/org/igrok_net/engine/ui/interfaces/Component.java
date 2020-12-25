package org.igrok_net.engine.ui.interfaces;

import org.igrok_net.engine.IGNColor;

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
}
