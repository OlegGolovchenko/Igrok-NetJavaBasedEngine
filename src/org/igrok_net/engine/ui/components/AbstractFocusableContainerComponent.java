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
        System.out.println("container focus recieved");
        for (Component component : this.components) {
            if (component.isMouseInside(this.mouseX, this.mouseY)) {
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
        System.out.println("container focus lost");
        for (Component component : this.components) {
            if (component.isMouseInside(this.mouseX, this.mouseY)) {
                if (component.isFocuseable()) {
                    ((Focusable) component).unFocus();
                }
            }
        }
    }

}
