package org.igrok_net.ignfreeglut;

import java.io.File;

public class IGNFreeGlut {

    static {
        File lib = new File("lib/libIgnFreeGlut.so");
        System.load(lib.getAbsolutePath());
    }

    private static native void glutInitNative(String[] argcp);

    private static native void glutInitWindowPositionNative(int x, int y);

    private static native void glutInitWindowSizeNative(int width, int height);

    public static void glutInit(String[] args) {
        glutInitNative(args);
    }

    public static void glutInitWindowPosition(int x, int y) {
        glutInitWindowPositionNative(x, y);
    }

    public static void glutInitWindowSize(int width, int height) {
        glutInitWindowSizeNative(width, height);
    }
}
