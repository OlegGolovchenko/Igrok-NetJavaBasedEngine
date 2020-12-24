package org.igrok_net.engine.ui.events;

public class MouseMoved {
    private int x,y;
    private MouseMoved(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public static MouseMoved create(int x, int y){
        return new MouseMoved(x, y);
    }
}
