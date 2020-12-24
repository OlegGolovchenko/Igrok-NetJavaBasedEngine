package org.igrok_net.engine.ui.events;

/**
 * Mouse moved event
 * @author Oleg Golovchenko
 * @version 0.0.1
 */
public class MouseMoved {

    private int x,y;

    private MouseMoved(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x coordinate associated with this event args object
     * @return value of x coord
     */
    public int getX(){
        return this.x;
    }

    /**
     * Gets y coordinate associated with this event args object
     * @return value of y coord
     */
    public int getY(){
        return this.y;
    }


    /**
     * Creates mouse moved event args with given position
     * Position is the one inside the window not relative to screen
     * @param x x coord
     * @param y y coord
     * @return MouseMoved event args
     */
    public static MouseMoved create(int x, int y){
        return new MouseMoved(x, y);
    }
}
