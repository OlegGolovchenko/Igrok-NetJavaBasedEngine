package org.igrok_net.engine.ui.components;

import org.igrok_net.engine.IGNColor;
import org.igrok_net.engine.IGNEngine;

/**
 * Label component
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class Label extends AbstractComponent {

    private int x,y;
    private String text;
    private IGNColor color;

    /**
     * Creates label with Black text at position 10,20
     * @param text text to print
     */
    public Label(String text) {
        this(10, 20, text);
    }

    /**
     * Creates label with Black text at position x,y
     * @param x x of top-left corner
     * @param y y of top-left corner
     * @param text text to print
     */
    public Label(int x, int y, String text) {
        this(x, y, text, IGNColor.BLACK_COLOR);
    }

    /**
     * Creates label with text of selected color at position x,y
     * @param x x of top-left corner
     * @param y y of top-left corner
     * @param text text to print
     * @param color color of text to print
     */
    public Label(int x,int y, String text, IGNColor color) {
        super();
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
    }

    /**
     * Changes text for label
     * @param text text to print
     */
    public void setText(String text){
        this.text = text;
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Component#render()
    */
    @Override
    public void render() {
        IGNEngine.printString2D(
            this.x, 
            this.y, 
            this.color.getRed(), 
            this.color.getGreen(), 
            this.color.getBlue(),
            this.color.getAlpha(), 
            text);
    }

    /*
     * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Disposable#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        this.text = null;
        this.color = null;
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Component#renderAtOffset()
    */
    @Override
    public void renderAtOffset(int xOffset, int yOffset) {
        IGNEngine.printString2D(
            this.x + xOffset, 
            this.y + yOffset + 20, 
            this.color.getRed(), 
            this.color.getGreen(), 
            this.color.getBlue(),
            this.color.getAlpha(), 
            text);
    }
    
}
