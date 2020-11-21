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

    private native long CreateNativeWindow(String title, int x, int y, int width, int height);

    private native void DestroyWindow(long wndPtr);

    private native void MainLoop(long wndPtr);

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
