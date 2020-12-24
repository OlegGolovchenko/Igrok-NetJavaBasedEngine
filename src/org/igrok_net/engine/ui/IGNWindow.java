package org.igrok_net.engine.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.igrok_net.engine.IGNEngine;
import org.igrok_net.engine.ui.interfaces.Component;
import org.igrok_net.engine.ui.interfaces.ComponentContainer;
import org.igrok_net.engine.ui.interfaces.Disposable;
import org.igrok_net.engine.ui.events.*;

public class IGNWindow implements Disposable, ComponentContainer {
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

    private List<Component> components;

    private native long createNativeWindow(String title, int x, int y, int width, int height);

    private native void destroyWindow(long wndPtr);

    private native void mainLoop(long wndPtr);

    private int isUpdateNeeded() {
        var currentTime = System.currentTimeMillis();
        var delta = currentTime - this.lastUpdate;
        var deltaInitial = currentTime - initialTime;
        if (delta > 16) {
            this.lastUpdate = currentTime;
            this.frameCounter++;
            return 1;
        }
        if (deltaInitial > 1000) {
            this.currentFps = frameCounter;
            this.frameCounter = 0;
            this.initialTime = currentTime;
        }
        return 0;
    }

    private int getFrameCounter() {
        return this.currentFps;
    }

    private void renderUIElements() {
        IGNEngine.printString2D(10, 20, 0f, 1f, 0f, 1f, getFrameCounter() + " fps");
        for(Component component : this.components){
            component.render();
        }
    }

    private void render3D() {

    }

    private void setKeyPress(long key, long mod) {
        onKeyPress(this, KeyPress.create(key, mod));
    }

    private void setKeyRelease(long key, long mod) {
        onKeyRelease(this, KeyPress.create(key, mod));
    }

    private void setMousePress(long button) {
        onMousePress(this, button);
    }

    private void setMouseMoved(int x, int y) {
        onMouseMoved(this, MouseMoved.create(x, y));
    }

    private void onMousePress(IGNWindow ignWindow, long button) {
    }

    protected void onKeyPress(Object sender, KeyPress eventArgs) {
    }

    protected void onKeyRelease(Object sender, KeyPress eventArgs) {
    }

    protected void onMouseMoved(Object sender, MouseMoved eventArgs) {
    }

    public IGNWindow() {
        this("Igrok-NET Engine");
    }

    public IGNWindow(String title) {
        this(title, 0, 0);
    }

    public IGNWindow(String title, int x, int y) {
        this(title, x, y, 800, 600);
        this.components = new ArrayList<Component>();
    }

    public IGNWindow(String title, int x, int y, int width, int height) {
        this.wndPtr = createNativeWindow(title, x, y, width, height);
        this.lastUpdate = System.currentTimeMillis();
    }

    public void run() {
        this.mainLoop(this.wndPtr);
    }

    @Override
    public void dispose() {
        if (this.wndPtr > 0) {
            destroyWindow(this.wndPtr);
        }
        this.wndPtr = 0;
    }

    @Override
    public void addChild(Component component) {
        this.components.add(component);
    }

    @Override
    public void removeChild(Component component) {
        this.components.add(component);
    }
}
