package org.igrok_net.engine.ui.interfaces;

/**
 * Marks Object as Components container
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public interface ComponentContainer {

    /**
     * Adds new component to container
     * @param component component to add
     */
    void addChild(Component component);

    /**
     * removes component from container
     * @param component component to remove
     */
    void removeChild(Component component);

    /**
     * Removes all components from container
     */
    void removeAll();
}
