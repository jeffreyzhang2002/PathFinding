package math;

import java.util.Objects;

/**
 * Generic class to describe a coordinate in the 2D plane;
 * @author Jeffrey
 * @version 1
 * @since 1/4/19
 */
public class RGB
{
    private int r,g,b;
    public final int maxValue = 255;

    public RGB(int r, int g, int b)
    { set(r,g,b); }

    public RGB(int c)
    { set(c,c,c); }

    public RGB(RGB rgb)
    {
        set(rgb.getR(),rgb.getG(),rgb.getB());
    }

    public RGB()
    { set(0,0, 0);}

    public void setR(int r)
    {
        if(!isValid(r))
        throw new IllegalArgumentException("r must be [0;255]");
        this.r = r;
    }

    public void setG(int g)
    {
        if(!isValid(g))
            throw new IllegalArgumentException("g must be [0;255]");
        this.g = g;
    }

    public void setB(int b)
    {
        if(!isValid(b))
            throw new IllegalArgumentException("b must be [0;255]");
        this.b = b;
    }

    public void set(int r, int g, int b)
    {
        setR(r);
        setG(g);
        setB(b);
    }

    public void set(int c)
    { set(c,c,c); }

    public void invert()
    {
        r = maxValue - r;
        g = maxValue - g;
        b = maxValue - b;
    }

    public void greyScale()
    {
        int avg = (r + g + b)/3;
        r = avg;
        g = avg;
        b = avg;
    }

    public int getR()
    { return r; }

    public int getG()
    { return g; }

    public int getB()
    { return b; }

    public int[] get()
    {
        return new int[] {r,g,b};
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof RGB) {
            return (((RGB) obj).getR() == this.getR() && ((RGB) obj).getG() == this.getG()
                    && ((RGB) obj).getB() == this.getB());
        }
        return false;
    }

    public boolean isValid(int r, int g, int b)
    { return isValid(r) && isValid(g) && isValid(b); }

    private boolean isValid(int c)
    {
        return c <= maxValue && c >= 0;
    }

    public int hashCode() { return 1; }

    public String toString()
    { return "(" + r + "," + g + "," + b + ")"; }
}