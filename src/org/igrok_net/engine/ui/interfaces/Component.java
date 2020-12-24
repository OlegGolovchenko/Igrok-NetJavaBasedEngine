package org.igrok_net.engine.ui.interfaces;

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
}
