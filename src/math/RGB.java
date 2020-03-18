package math;

import java.util.Objects;

/**
 * Generic class to describe a coordinate in the 2D plane;
 * @author Jeffrey
 * @version 1
 * @since 1/4/19
 */
public class RGB implements Cloneable
{
    private int r, g, b;
    public final int MAXVALUE = 255;

    /**
     * Creates an instance of RGB. Using the Red, Green and Blue value for a certain color
     * @param r the r value
     * @param g the g value
     * @param b the b value
     */
    public RGB(int r, int g, int b)
    { set(r,g,b); }

    /**
     * Creates an instance of RGB. This constructor creates a gray scale color as Red, Green and Blue are the same
     * @param c
     */
    public RGB(int c)
    { set(c,c,c); }

    /**
     * Creates an instance of RGB using another RGB
     * @param rgb another instance of RGB
     */
    public RGB(RGB rgb)
    { set(rgb.getR(), rgb.getG(), rgb.getB()); }

    /**
     * Sets the Robot value of RGB
     * @param r the Robot value
     */
    public void setR(int r)
    {
        if(!isValid(r))
            throw new IllegalArgumentException("r must be [0;255]");
        this.r = r;
    }

    /**
     * Sets the G value of RGB
     * @param g the G value
     */
    public void setG(int g)
    {
        if(!isValid(g))
            throw new IllegalArgumentException("g must be [0;255]");
        this.g = g;
    }

    /**
     * Sets the B value of RGB
     * @param b the B value
     */
    public void setB(int b)
    {
        if(!isValid(b))
            throw new IllegalArgumentException("b must be [0;255]");
        this.b = b;
    }

    /**
     * Sets the RGB value to the given values
     * @param r the Robot value
     * @param g the G value
     * @param b the B value
     */
    public void set(int r, int g, int b)
    {
        setR(r);
        setG(g);
        setB(b);
    }

    /**
     * Sets the RGB value to the given monochrome value. Robot,G and B are all set to C
     * @param c
     */
    public void set(int c)
    { set(c,c,c); }

    /**
     * Inverts the current color
     */
    public void invert()
    {
        r = MAXVALUE - r;
        g = MAXVALUE - g;
        b = MAXVALUE - b;
    }

    /**
     * Grey scale the current value
     */
    public void greyScale()
    { set((r + g + b) /3); }

    /**
     * returns the Robot value
     * @return the Robot value
     */
    public int getR()
    { return r; }

    /**
     * returns the G value
     * @return the G value
     */
    public int getG()
    { return g; }

    /**
     * returns the B value
     * @return the B value
     */
    public int getB()
    { return b; }

    /**
     * return the RGB value as a 1D array with length 3
     * @return int[] {Robot,G,B}
     */
    public int[] get()
    { return new int[] {r,g,b}; }

    public boolean equals(Object obj)
    {
        if(obj instanceof RGB) {
            return (((RGB) obj).getR() == this.getR() && ((RGB) obj).getG() == this.getG()
                    && ((RGB) obj).getB() == this.getB());
        }
        return false;
    }

    /**
     * Checks if the RGB value is within bounds
     * @param r the Robot value
     * @param g the B value
     * @param b the G value
     * @return true if Robot,G and B are >= 0 and Robot,G and B are <= 255
     */
    public boolean isValid(int r, int g, int b)
    { return isValid(r) && isValid(g) && isValid(b); }

    /**
     * Checks if the given between 0 and 255;
     * @param c the current C value
     * @return true or false
     */
    private boolean isValid(int c)
    { return c <= MAXVALUE && c >= 0; }

    public int hashCode()
    { return 1; }

    public String toString()
    { return "(" + r + "," + g + "," + b + ")"; }
}