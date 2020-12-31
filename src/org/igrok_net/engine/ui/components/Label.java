package org.igrok_net.engine.ui.components;

import org.igrok_net.engine.IGNColor;
import org.igrok_net.engine.IGNEngine;

/**
 * Label component
 * 
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class Label extends AbstractComponent {

    private String text;
    private IGNColor color;
    private long font;
    private int fontOffset;

    /**
     * Creates label with Black text at position 10,20
     * 
     * @param text text to print
     */
    public Label(String text, long font) {
        this(10, 20, text, font);
    }

    /**
     * Creates label with Black text at position x,y
     * 
     * @param x    x of top-left corner
     * @param y    y of top-left corner
     * @param text text to print
     */
    public Label(int x, int y, String text, long font) {
        this(x, y, text, font, IGNColor.BLACK_COLOR);
    }

    /**
     * Creates label with text of selected color at position x,y
     * 
     * @param x     x of top-left corner
     * @param y     y of top-left corner
     * @param text  text to print
     * @param color color of text to print
     */
    public Label(int x, int y, String text, long font, IGNColor color) {
        super(x,y,0,0);
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.font = font;
        this.calcFontOffset(font);
        this.height = this.fontOffset + 10;
        this.width = IGNEngine.MeasureString(font, text);
    }

    private void calcFontOffset(long font) {
        this.fontOffset = 0;
        if (font == IGNEngine.GLUT_STROKE_ROMAN() || font == IGNEngine.GLUT_STROKE_MONO_ROMAN()) {
            this.fontOffset = 20;
        }
        if (font == IGNEngine.GLUT_BITMAP_8_BY_13()) {
            this.fontOffset = 13;
        }
        if (font == IGNEngine.GLUT_BITMAP_9_BY_15()) {
            this.fontOffset = 15;
        }
        if (font == IGNEngine.GLUT_BITMAP_HELVETICA_10() || font == IGNEngine.GLUT_BITMAP_TIMES_ROMAN_10()) {
            this.fontOffset = 10;
        }
        if (font == IGNEngine.GLUT_BITMAP_HELVETICA_12()) {
            this.fontOffset = 12;
        }
        if (font == IGNEngine.GLUT_BITMAP_HELVETICA_18()) {
            this.fontOffset = 18;
        }
        if (font == IGNEngine.GLUT_BITMAP_TIMES_ROMAN_24()) {
            this.fontOffset = 24;
        }
    }

    /**
     * Changes text for label
     * 
     * @param text text to print
     */
    public void setText(String text) {
        this.text = text;
    }

    /*
     * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Component#render()
     */
    @Override
    public void render() {
        IGNEngine.printString2D(this.x, this.y, this.color.getRed(), this.color.getGreen(), this.color.getBlue(),
                this.color.getAlpha(), this.text, this.font);
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
     * (non-Javadoc) @see
     * org.igrok_net.engine.ui.interfaces.Component#renderAtOffset()
     */
    @Override
    public void renderAtOffset(int xOffset, int yOffset) {
        IGNEngine.printString2D(this.x + xOffset, this.y + yOffset + this.fontOffset, this.color.getRed(),
                this.color.getGreen(), this.color.getBlue(), this.color.getAlpha(), this.text, this.font);
    }
}
