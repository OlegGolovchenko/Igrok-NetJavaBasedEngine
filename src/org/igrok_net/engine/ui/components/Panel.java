package org.igrok_net.engine.ui.components;

import org.igrok_net.engine.IGNColor;
import org.igrok_net.engine.IGNEngine;
import org.igrok_net.engine.ui.interfaces.Component;

/**
 * Basic ui panel implementation This component is also a container for other
 * components
 * 
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class Panel extends AbstractFocusableContainerComponent {

    /**
     * Creates default instance of panel
     */
    public Panel() {
        super();
        this.background = IGNColor.GRAY_COLOR;
        this.border = IGNColor.BLACK_COLOR;
    }

    /*
     * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Component#render()
     */
    @Override
    public void render() {
        for (Component component : this.components) {
            component.render();
        }
        IGNEngine.RenderQuad(x, y, width, height, this.background.getRed(), this.background.getGreen(),
                this.background.getBlue(), this.background.getAlpha(), this.border.getRed(), this.border.getGreen(),
                this.border.getBlue(), this.border.getAlpha());
    }

    /*
     * (non-Javadoc) @see
     * org.igrok_net.engine.ui.interfaces.Component#renderAtOffset()
     */
    @Override
    public void renderAtOffset(int xOffset, int yOffset) {
        for (Component component : this.components) {
            component.renderAtOffset(this.x + xOffset, this.y + yOffset);
        }
        IGNEngine.RenderQuad(x + xOffset, y + yOffset, width, height, this.background.getRed(),
                this.background.getGreen(),
                this.background.getBlue(), this.background.getAlpha(), this.border.getRed(), this.border.getGreen(),
                this.border.getBlue(), this.border.getAlpha());
    }
    
    /**
     * Sets background color for panel
     * @param background new background color
     */
    public void setBackground(IGNColor background){
        this.background = background;
    }

    /**
     * resets background color to default
     */
    public void resetBackground(){
        this.background = IGNColor.GRAY_COLOR;
    }
}
