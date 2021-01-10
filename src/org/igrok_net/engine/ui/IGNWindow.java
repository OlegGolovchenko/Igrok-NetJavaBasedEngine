package org.igrok_net.engine.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.igrok_net.engine.ui.interfaces.Component;
import org.igrok_net.engine.ui.interfaces.ComponentContainer;
import org.igrok_net.engine.ui.interfaces.Disposable;
import org.igrok_net.engine.IGNEngine;
import org.igrok_net.engine.ui.components.Label;
import org.igrok_net.engine.ui.events.*;

/**
 * Main application window object.
 * You should only open one.
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class IGNWindow implements Disposable, ComponentContainer {
    static {
        File nativeLib = new File("lib/libIGNUI.so");
        System.load(nativeLib.getAbsolutePath());
    }

    private int x, y, width, height, mouseX, mouseY;

    private long wndPtr;

    private long initialTime = System.currentTimeMillis();

    private long lastUpdate;

    private int frameCounter;

    private int currentFps;

    private List<Component> components;

    private Label lblFps;

    private native long createNativeWindow(String title, int x, int y, int width, int height);

    private native void destroyWindow(long wndPtr);

    private native void mainLoop(long wndPtr);

    private native void augmentFps(long wndPtr, int fps);

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
            augmentFps(wndPtr, this.currentFps);
            this.initialTime = currentTime;
        }
        return 0;
    }

    private int getFrameCounter() {
        return this.currentFps;
    }

    private void renderUIElements() {
        ((Label) this.components.get(0)).setText(getFrameCounter() + " fps");
        for (Component component : this.components) {
            component.renderAtOffset(this.x, this.y);
        }
    }

    private void render3D() {

    }

    private void setKeyPress(long key, long mod, String keyChar) {
        onKeyPress(this, KeyPress.create(key, mod, keyChar));
    }

    private void setKeyRelease(long key, long mod, String keyChar) {
        onKeyRelease(this, KeyPress.create(key, mod, keyChar));
    }

    private void setMousePress(long button) {
        onMousePress(this, button);
    }

    private void setMouseMoved(int x, int y) {
        onMouseMoved(this, MouseMoved.create(x, y));
    }

    protected void onMousePress(IGNWindow ignWindow, long button) {
    }

    protected void onKeyPress(Object sender, KeyPress eventArgs) {
        for(Component component:this.components){
            if(component.isMouseInside(this.mouseX, this.mouseY)){
                component.sendKeyEvent(sender, eventArgs);
            }
        }
    }

    protected void onKeyRelease(Object sender, KeyPress eventArgs) {
        for(Component component:this.components){
            if(component.isMouseInside(this.mouseX, this.mouseY)){
                component.sendKeyReleaseEvent(sender, eventArgs);
            }
        }
    }

    protected void onMouseMoved(Object sender, MouseMoved eventArgs) {
        this.mouseX = eventArgs.getX();
        this.mouseY = eventArgs.getY();
        for(Component component:this.components){
            if(component.isMouseInside(this.mouseX, this.mouseY)){
                component.sendMouseMovedEvent(sender, eventArgs);
            }
        }
    }

    /**
     * Creates window with default parameters
     */
    public IGNWindow() {
        this("Igrok-NET Engine");
    }

    /**
     * Creates window with given title positioned on 0:0 and size of 800x600
     * @param title title
     */
    public IGNWindow(String title) {
        this(title, 0, 0);
    }

    /**
     * Creates window with given parameters and size of 800x600
     * @param title title
     * @param x top-left x
     * @param y top-left y
     */
    public IGNWindow(String title, int x, int y) {
        this(title, x, y, 800, 600);
        this.components = new ArrayList<Component>();
        this.lblFps = new Label(10, 0, getFrameCounter() + " fps", IGNEngine.GLUT_BITMAP_9_BY_15());
        this.lblFps.resize(100, 50);
        this.addChild(lblFps);
    }

    /**
     * Creates window with given parameters
     * @param title title
     * @param x top-left x
     * @param y top-left y
     * @param width width
     * @param height height
     */
    public IGNWindow(String title, int x, int y, int width, int height) {
        this.wndPtr = createNativeWindow(title, x, y, width, height);
        this.lastUpdate = System.currentTimeMillis();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Window main loop
     */
    public void run() {
        this.mainLoop(this.wndPtr);
    }

    /*
     * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.Disposable#dispose()
     */
    @Override
    public void dispose() {
        for (Component component : this.components) {
            component.dispose();
        }
        this.components.clear();
        if (this.wndPtr > 0) {
            destroyWindow(this.wndPtr);
        }
        this.wndPtr = 0;
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.ComponentContainer#addChild(org.igrok_net.engine.ui.interfaces.Component)
    */
    @Override
    public void addChild(Component component) {
        this.components.add(component);
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.ComponentContainer#removeChild(org.igrok_net.engine.ui.interfaces.Component)
    */
    @Override
    public void removeChild(Component component) {
        this.components.add(component);
    }

    /*
    * (non-Javadoc) @see org.igrok_net.engine.ui.interfaces.ComponentContainer#removeAll()
    */
    @Override
    public void removeAll() {
        this.components.clear();
    }
}
