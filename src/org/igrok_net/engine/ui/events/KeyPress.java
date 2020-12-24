package org.igrok_net.engine.ui.events;

/**
 * KeyPress event arguments
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class KeyPress {
    private long key, modifier;
    private KeyPress(long key, long modifier) {
        this.key = key;
        this.modifier = modifier;
    }

    /**
     * Gets Key code
     * @return key code
     */
    public long getKey(){
        return this.key;
    }

    /**
     * Gets Key modifier code (ctrl, alt, shift, ...)
     * @return key modifier code
     */
    public long getModifier(){
        return this.modifier;
    }

    /**
     * Creates new KeyPress object
     * @param key key code
     * @param mod key modifier
     * @return keycode object with given parameters
     */
    public static KeyPress create(long key, long mod){
        return new KeyPress(key, mod);
    }
}
