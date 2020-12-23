package org.igrok_net.engine;

import java.io.File;

public class IGNEngine {
    static {
        File nativeLib = new File("lib/libIGNEngine.so");
        System.load(nativeLib.getAbsolutePath());
    }

    public static native void init(String[]args);

    public static native void printString2D(
        int x, 
        int y, 
        float red, 
        float green, 
        float blue, 
        float alpha, 
        String text
    );
}