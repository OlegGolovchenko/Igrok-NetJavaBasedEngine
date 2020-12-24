package org.igrok_net.engine.ui.interfaces;

/**
 * Marks object as Disposable component that can be cleaned up
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public interface Disposable {
    /**
     * Method used to clean up
     */
    public void dispose();
}
