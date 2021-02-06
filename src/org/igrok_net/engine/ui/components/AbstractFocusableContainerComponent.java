package org.igrok_net.engine.ui.components;

import org.igrok_net.engine.ui.interfaces.*;

/**
 * 
 */
public class AbstractFocusableContainerComponent extends AbstractContainerComponent implements Focusable {

    @Override
    public boolean isFocuseable() {
        return true;
    }

    @Override
    public void focus() {
        for (Component component : this.components) {
            if (component.isMouseInside(this.mouseX, this.mouseY, this.x + this.parentX, this.y + this.parentY)) {
                if (component.isFocuseable()) {
                    ((Focusable) component).focus();
                }
            } else {
                if (component.isFocuseable()) {
                    ((Focusable) component).unFocus();
                }
            }
        }
    }

    @Override
    public void unFocus() {
        for (Component component : this.components) {
            if (component.isMouseInside(this.mouseX, this.mouseY, this.x + this.parentX, this.y + this.parentY)) {
                if (component.isFocuseable()) {
                    ((Focusable) component).unFocus();
                }
            }
        }
    }

}
