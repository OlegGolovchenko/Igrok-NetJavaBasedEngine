package org.igrok_net.engine.ui.interfaces;

/**
 * Represents component that can be focused and will recieve mouse press and mouse release event
 * 
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public interface Focusable {
    /**
     * Focuses current object
     */
    void focus();

    /**
     * Releases focus from current object
     */
    void unFocus();
}
