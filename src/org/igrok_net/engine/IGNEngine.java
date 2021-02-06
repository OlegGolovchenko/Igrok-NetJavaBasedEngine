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
     * GLUT font Stroke roman
     * @return pointer to font
     */
    public static native long  GLUT_STROKE_ROMAN();
    /**
     * GLUT font Stroke mono roman
     * @return pointer to font
     */
    public static native long  GLUT_STROKE_MONO_ROMAN();
    /**
     * GLUT font 9/15
     * @return pointer to font
     */
    public static native long  GLUT_BITMAP_9_BY_15();
    /**
     * GLUT font 8/13
     * @return pointer to font
     */
    public static native long  GLUT_BITMAP_8_BY_13();
    /**
     * GLUT font times new roman 10
     * @return pointer to font
     */
    public static native long  GLUT_BITMAP_TIMES_ROMAN_10();
    /**
     * GLUT font times new roman 24
     * @return pointer to font
     */
    public static native long  GLUT_BITMAP_TIMES_ROMAN_24();
    /**
     * GLUT font helvetica 10
     * @return pointer to font
     */
    public static native long  GLUT_BITMAP_HELVETICA_10();
    /**
     * GLUT font helvetica 12
     * @return pointer to font
     */
    public static native long  GLUT_BITMAP_HELVETICA_12();
    /**
     * GLUT font helvetica 18
     * @return pointer to font
     */
    public static native long  GLUT_BITMAP_HELVETICA_18();

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
     * @param font font to use for drawing
     */
    public static native void printString2D(
        int x, 
        int y, 
        float red, 
        float green, 
        float blue, 
        float alpha, 
        String text,
        long font
    );

    /**
     * Renders 2D rectangle
     * @param x top-left x
     * @param y top-left y
     * @param width width
     * @param height height
     * @param red background red
     * @param green background green
     * @param blue background blue
     * @param alpha background alpha
     * @param borderRed border red
     * @param borderGreen border green
     * @param borderBlue border blue
     * @param borderAlpha border alpha
     */
    public static native void RenderQuad(
        int x,
        int y,
        int width,
        int height,
        float red,
        float green,
        float blue,
        float alpha,
        float borderRed,
        float borderGreen,
        float borderBlue,
        float borderAlpha
    );

    /**
     * Measures rendered String
     * @param font font used to render
     * @param text string that is rendered
     * @return length of string
     */
    public static native int measureString(long font, String text);
}