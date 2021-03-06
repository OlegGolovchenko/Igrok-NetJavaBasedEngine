package org.igrok_net.engine.ui.components;

import org.igrok_net.engine.IGNColor;
import org.igrok_net.engine.ui.events.KeyPress;

/**
 * Representation of Text box component
 * 
 * @version 0.0.1
 * @author Oleg Golovchenko
 */
public class TextBox extends AbstractFocusableContainerComponent {

    private String text;
    private long font;
    private Label lblText;
    private Panel pnlBackground;

    /**
     * Creates Textbox with given parameters
     * @param width width
     * @param height heignt
     * @param text initial text of textbox
     * @param font font to use for drawing
     */
    public TextBox(int width, int height, String text, long font) {
        super();
        this.text = text;
        this.font = font;
        this.lblText = new Label(0, 0, this.text, this.font, IGNColor.BLACK_COLOR);
        this.lblText.resize(width, height);
        this.pnlBackground = new Panel();
        this.pnlBackground.reposition(0, 0);
        this.pnlBackground.resize(width, height);
        this.addChild(this.lblText);
        this.addChild(this.pnlBackground);
        this.resize(width, height);
    }

    @Override
    protected void onKeyPress(Object sender, KeyPress args){
        String backSpaceCode = "\u0008";
        String deleteCode = "\u007F";
        if (!args.getKeyChars().isBlank() && !args.getKeyChars().isEmpty()) {
            if (args.getKeyChars().matches(backSpaceCode)) {
                if (text.length() > 0) {
                    text = text.substring(0, text.length() - 1);
                }
            }
            if (args.getKeyChars().matches(deleteCode)) {
                text = "";
            }
            if (!args.getKeyChars().matches(backSpaceCode) && !args.getKeyChars().matches(deleteCode)) {
                text += args.getKeyChars();
            }
        }
        if(args.getKeyChars().isBlank()){
            text += args.getKeyChars();
        }
        lblText.setText(text);
    }

    @Override
    public void focus() {
        super.focus();
        this.pnlBackground.setBackground(IGNColor.WHITE_COLOR);
    }

    @Override
    public void unFocus() {
        super.unFocus();
        this.pnlBackground.resetBackground();
    }

    @Override
    public boolean isMouseInside(int mX, int mY, int pX, int pY) {
        boolean result = super.isMouseInside(mX, mY, pX, pY);
        return result;
    }
}
