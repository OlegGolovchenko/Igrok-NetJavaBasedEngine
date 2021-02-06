package org.igrok_net.engine.ui.events;

/**
 * KeyPress event arguments
 * 
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class KeyPress {
    private long key, modifier;
    private String keyChar;

    private KeyPress(long key, long modifier, String keyChar) {
        this.key = key;
        this.modifier = modifier;
        this.keyChar = keyChar;
    }

    /**
     * Gets Key code
     * 
     * @return key code
     */
    public long getKey() {
        return this.key;
    }

    /**
     * Gets Key modifier code (ctrl, alt, shift, ...)
     * 
     * @return key modifier code
     */
    public long getModifier() {
        return this.modifier;
    }

    /**
     * Gets key description as characters
     * @return key description
     */
    public String getKeyChars(){
        return this.keyChar;
    }

    /**
     * Creates new KeyPress object
     * 
     * @param key key code
     * @param mod key modifier
     * @param keyChar key character
     * @return keycode object with given parameters
     */
    public static KeyPress create(long key, long mod, String keyChar) {
        return new KeyPress(key, mod, keyChar);
    }
}
