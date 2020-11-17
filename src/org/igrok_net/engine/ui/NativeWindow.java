package org.igrok_net.engine.ui;

public class NativeWindow {
    static{
        System.load("/usr/lib/libIgnWindow.so");
    }

    public static native long createWindow(String title, int x, int y);
}
