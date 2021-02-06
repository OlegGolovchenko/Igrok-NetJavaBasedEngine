package org.igrok_net.engine.ui.components;

import org.igrok_net.engine.ui.interfaces.Focusable;

/**
 * Base class for focuseable component
 * 
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public abstract class AbstractFocusableComponent extends AbstractComponent implements Focusable {
    
    @Override
    public boolean isFocuseable() {
        return true;
    }

    @Override
    public void focus() {
    }

    @Override
    public void unFocus() {
    }
}
