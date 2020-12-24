package org.igrok_net.engine;

import java.io.File;

/**
 * Common place for engine specific functions related to internal working
 * You need to call init before running any of text rendering
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class IGNEngine {
    static {
        File nativeLib = new File("lib/libIGNEngine.so");
        System.load(nativeLib.getAbsolutePath());
    }

    /**
     * Initializes subroutines needed for ign engine to work properly
     * @param args command line arguments form main function
     */
    public static native void init(String[]args);

    /**
     * Prints text in opengl context
     * @param x top-left x
     * @param y top-left y
     * @param red red pixel 0-1
     * @param green green pixel 0-1
     * @param blue blue pixel 0-1
     * @param alpha alpha pixel 0-1
     * @param text text to draw
     */
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