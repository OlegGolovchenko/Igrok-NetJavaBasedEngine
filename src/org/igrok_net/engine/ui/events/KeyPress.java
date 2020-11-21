package org.igrok_net.engine.ui.events;

public class KeyPress {
    private long key, modifier;
    private KeyPress(long key, long modifier) {
        this.key = key;
        this.modifier = modifier;
    }

    public long GetKey(){
        return this.key;
    }

    public long GetModifier(){
        return this.modifier;
    }

    public static KeyPress Create(long key, long mod){
        return new KeyPress(key, mod);
    }
}
