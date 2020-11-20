package org.igrok_net.engine.ui;

import java.io.File;

public class IGNWindow {
    static {
        File nativeLib = new File("lib/libIGNUI.so");
        System.load(nativeLib.getAbsolutePath());
    }

    /**
     * Native window pointer
     */
    private long wndPtr;

    private native long CreateNativeWindow(String title, int x, int y, int width, int height);

    private native void DestroyWindow(long wndPtr);

    private native void MainLoop(long wndPtr);

    public IGNWindow() {
        this("Igrok-NET Engine");
    }

    public IGNWindow(String title) {
        this(title, 0, 0);
    }

    public IGNWindow(String title,int x,int y) {
        this(title, x, y, 800, 600);
    }

    public IGNWindow(String title, int x, int y, int width,int height) {
        this.wndPtr = CreateNativeWindow(title, x, y, width,height);
    }

    public void Run(){
        this.MainLoop(this.wndPtr);
    }

    public void Dispose(){
        if(this.wndPtr > 0){
            DestroyWindow(this.wndPtr);
        }
        this.wndPtr = 0;
    }
}
