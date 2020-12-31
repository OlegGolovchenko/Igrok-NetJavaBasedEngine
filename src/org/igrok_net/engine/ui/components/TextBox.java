package org.igrok_net.engine.ui.components;

import org.igrok_net.engine.IGNColor;
import org.igrok_net.engine.IGNEngine;
import org.igrok_net.engine.ui.events.KeyCodes;
import org.igrok_net.engine.ui.events.KeyPress;

public class TextBox extends AbstractContainerComponent {

    private String text;
    private long font;
    private Label lblText;

    public TextBox(String text, long font) {
        super();
        this.text = text;
        this.font = font;
        lblText = new Label(0, 0, this.text, this.font, IGNColor.BLACK_COLOR);
        this.addChild(this.lblText);
    }

    @Override
    protected void onKeyPress(Object sender, KeyPress args){
        long backSpaceCode = KeyCodes.XK_BackSpace;
        long deleteCode = KeyCodes.XK_Delete;
        String newText = text;
        if(!args.getKeyChars().isBlank()&&!args.getKeyChars().isEmpty()){            
            newText+=args.getKeyChars();
            if(IGNEngine.MeasureString(this.font, newText) < width){
                this.text = newText;
            }
        }else{
            if(args.getKey() == backSpaceCode){
                text = text.substring(0,text.length()-2);
            }
            if(args.getKey() == deleteCode){
                text = "";
            }
        }
    }
}
