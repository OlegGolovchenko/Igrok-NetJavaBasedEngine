package org.igrok_net.engine.ui;

import java.io.File;

import org.igrok_net.engine.ui.events.*;

public class IGNWindow {
    static {
        File nativeLib = new File("lib/libIGNUI.so");
        System.load(nativeLib.getAbsolutePath());
    }

    /**
     * Native window pointer
     */
    private long wndPtr;

    private long initialTime = System.currentTimeMillis();

    private long lastUpdate;

    private int frameCounter;

    private native long CreateNativeWindow(String title, int x, int y, int width, int height);

    private native void DestroyWindow(long wndPtr);

    private native void MainLoop(long wndPtr);

    private int IsUpdateNeeded(){
        var currentTime = System.currentTimeMillis();
        var delta = currentTime - this.lastUpdate;
        var deltaInitial = currentTime - initialTime;
        if(delta > 16){
            this.lastUpdate = currentTime;
            this.frameCounter ++;
            return 1;
        }
        if(deltaInitial > 1000){
            System.out.println("Rendered: "+this.frameCounter+" frames last second.");
            this.frameCounter = 0;
            this.initialTime = currentTime;
        }
        return 0;
    }

    private void SetKeyPress(long key, long mod){
        OnKeyPress(this, KeyPress.Create(key, mod));
    }

    private void SetKeyRelease(long key, long mod){
        OnKeyRelease(this, KeyPress.Create(key, mod));
    }

    private void SetMousePress(long button){
        OnMousePress(this, button);
    }

    private void OnMousePress(IGNWindow ignWindow, long button) {
        String btnDescription = "None";
        if(button == MousePress.LMB){
            btnDescription = "Left";
        }else if(button == MousePress.MMB){
            btnDescription = "Middle";
        }else if(button == MousePress.RMB){
            btnDescription = "Right";
        }else if(button == MousePress.MMBUP){
            btnDescription = "WheelUp";
        }else if(button == MousePress.MMBDWN){
            btnDescription = "WheelDown";
        }
        System.out.println("mouse button pressed: " + btnDescription);
    }

    protected void OnKeyPress(Object sender, KeyPress eventArgs) {
        System.out.println("key pressed, code: " + eventArgs.GetKey() + " modifier: "+eventArgs.GetModifier());
    }

    protected void OnKeyRelease(Object sender, KeyPress eventArgs){
        System.out.println("key released, code: " + eventArgs.GetKey() + " modifier: "+eventArgs.GetModifier());
    }

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
        this.lastUpdate = System.currentTimeMillis();
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
