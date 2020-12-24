package org.igrok_net.engine.ui.events;

public class KeyPress {
    private long key, modifier;
    private KeyPress(long key, long modifier) {
        this.key = key;
        this.modifier = modifier;
    }

    public long getKey(){
        return this.key;
    }

    public long getModifier(){
        return this.modifier;
    }

    public static KeyPress create(long key, long mod){
        return new KeyPress(key, mod);
    }
}
