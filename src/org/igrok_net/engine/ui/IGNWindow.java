package org.igrok_net.engine.ui;

import java.io.File;

import org.igrok_net.engine.IGNEngine;
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

    private int currentFps;

    private native long createNativeWindow(String title, int x, int y, int width, int height);

    private native void destroyWindow(long wndPtr);

    private native void mainLoop(long wndPtr);

    private int isUpdateNeeded(){
        var currentTime = System.currentTimeMillis();
        var delta = currentTime - this.lastUpdate;
        var deltaInitial = currentTime - initialTime;
        if(delta > 16){
            this.lastUpdate = currentTime;
            this.frameCounter ++;
            return 1;
        }
        if(deltaInitial > 1000){
            this.currentFps = frameCounter;
            this.frameCounter = 0;
            this.initialTime = currentTime;
        }
        return 0;
    }

    private int getFrameCounter(){
        return this.currentFps;
    }

    private void renderUIElements(){
        IGNEngine.printString2D(10, 20, 0f, 1f, 0f, 1f, getFrameCounter() + " fps");
    }

    private void render3D(){

    }

    private void setKeyPress(long key, long mod){
        onKeyPress(this, KeyPress.Create(key, mod));
    }

    private void setKeyRelease(long key, long mod){
        onKeyRelease(this, KeyPress.Create(key, mod));
    }

    private void setMousePress(long button){
        onMousePress(this, button);
    }

    private void onMousePress(IGNWindow ignWindow, long button) {
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

    protected void onKeyPress(Object sender, KeyPress eventArgs) {
        System.out.println("key pressed, code: " + eventArgs.GetKey() + " modifier: "+eventArgs.GetModifier());
    }

    protected void onKeyRelease(Object sender, KeyPress eventArgs){
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
        this.wndPtr = createNativeWindow(title, x, y, width,height);
        this.lastUpdate = System.currentTimeMillis();
    }

    public void run(){
        this.mainLoop(this.wndPtr);
    }

    public void dispose(){
        if(this.wndPtr > 0){
            destroyWindow(this.wndPtr);
        }
        this.wndPtr = 0;
    }
}
