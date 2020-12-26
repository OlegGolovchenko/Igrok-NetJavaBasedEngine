package org.igrok_net.engine;

/**
 * Representation of color object
 */
public class IGNColor {
    private float red;
    private float green;
    private float blue;
    private float alpha;

    /**
     * named white color
     */
    public static final IGNColor WHITE_COLOR = new IGNColor(1f, 1f, 1f, 1f);

    /**
     * named black color
     */
    public static final IGNColor BLACK_COLOR = new IGNColor(0, 0, 0, 1f);

    /**
     * named red color
     */
    public static final IGNColor RED_COLOR = new IGNColor(1f, 0, 0, 1f);

    /**
     * named green color
     */
    public static final IGNColor GREEN_COLOR = new IGNColor(0, 1f, 0, 1f);
    
    /**
     * named blue color
     */
    public static final IGNColor BLUE_COLOR = new IGNColor(0, 0, 1f, 1f);
    
    /**
     * named gray color
     */
    public static final IGNColor GRAY_COLOR = new IGNColor(0.5f, 0.5f, 0.5f, 1f);

    /**
     * Creates RGBA color object with following parameters
     * @param r red pixel 0-1
     * @param g green pixel 0-1
     * @param b blue pixel 0-1
     * @param a alpha pixel 0-1
     */
    public IGNColor(float r,float g,float b,float a) {
        super();
        this.red = r;
        this.green = g;
        this.blue = b;
        this.alpha = a;
    }

    /**
     * Creates RGBA color object with RGB parameters of specified color
     * Alpha parameter will be at it's max value of 1f
     * @param r red pixel 0-1
     * @param g green pixel 0-1
     * @param b blue pixel 0-1
     */
    public IGNColor(float r, float g, float b) {
        this(r,g,b,1f);
    }

    /**
     * Gets red pixel value
     * @return red pixel value
     */
    public float getRed(){
        return this.red;
    }

    /**
     * Gets blue pixel value
     * @return blue pixel value
     */
    public float getBlue(){
        return this.blue;
    }

    /**
     * Gets green pixel value
     * @return green pixel value
     */
    public float getGreen(){
        return this.green;
    }

    /**
     * Gets alpha pixel value
     * @return alpha pixel value
     */
    public float getAlpha(){
        return this.alpha;
    }
}
