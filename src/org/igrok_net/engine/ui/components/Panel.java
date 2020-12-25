package org.igrok_net.engine.ui.components;

import java.util.ArrayList;
import java.util.List;

import org.igrok_net.engine.IGNColor;
import org.igrok_net.engine.IGNEngine;
import org.igrok_net.engine.ui.interfaces.Component;
import org.igrok_net.engine.ui.interfaces.ComponentContainer;

/**
 * Basic ui panel implementation
 * This component is also a container for other components
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class Panel extends AbstractComponent implements ComponentContainer {

    private  List<Component> components;

    /**
     * Creates default instance of panel
     */
    public Panel() {
        super();
        this.components = new ArrayList<Component>();
        this.background = IGNColor.GRAY_COLOR;
        this.border = IGNColor.BLACK_COLOR;
    }

    /*
     * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Disposable#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        for(Component component: this.components){
            component.dispose();
        }
        this.removeAll();
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.ComponentContainer#addChild(org.igrok_net.engine.ui.interfaces.Component)
    */
    @Override
    public void addChild(Component component) {
        this.components.add(component);
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.ComponentContainer#removeChild(org.igrok_net.engine.ui.interfaces.Component)
    */
    @Override
    public void removeChild(Component component) {
        this.components.add(component);
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.ComponentContainer#removeAll()
    */
    @Override
    public void removeAll() {
        this.components.clear();
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Component#render()
    */
    @Override
    public void render() {
        for (Component component : this.components) {
            component.render();
        }
        IGNEngine.RenderQuad(
            x, 
            y, 
            width, 
            height, 
            this.background.getRed(), 
            this.background.getGreen(), 
            this.background.getBlue(), 
            this.background.getAlpha(), 
            this.border.getRed(), 
            this.border.getGreen(), 
            this.border.getBlue(), 
            this.border.getAlpha());
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Component#renderAtOffset()
    */
    @Override
    public void renderAtOffset(int xOffset, int yOffset) {
        for (Component component : this.components) {
            component.renderAtOffset(this.x, this.y);
        }
        IGNEngine.RenderQuad(
            x, 
            y, 
            width, 
            height, 
            this.background.getRed(), 
            this.background.getGreen(), 
            this.background.getBlue(), 
            this.background.getAlpha(), 
            this.border.getRed(), 
            this.border.getGreen(), 
            this.border.getBlue(), 
            this.border.getAlpha());
    }
    
}
