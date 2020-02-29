package math;

import java.util.Objects;

/**
 * Generic class to describe a coordinate in the 2D plane;
 * @author Jeffrey
 * @version 1
 * @since 1/4/19
 */
public class DiscreteCoordinate
{
    private int x,y;

    /**
     * creates a DiscreteCoordinate at position x and y
     * @param x the x position of the coordinate
     * @param y the y position of the coordinate
     */
    public DiscreteCoordinate(int x, int y)
    { set(x,y); }

    /**
     * creates a DiscreteCoordinate using another DiscreteCoordinate
     * @param c another DiscreteCoordinate;
     */
    public DiscreteCoordinate(DiscreteCoordinate c)
    {
        set(c.getX(),c.getY());
    }

    /**
     * creates a DiscreteCoordinate at the position (0,0)
     */
    public DiscreteCoordinate()
    { set(0,0);}

    /**
     * set the value of x on the coordinate
     * @param x
     */
    public void setX(int x)
    { this.x = x; }

    /**
     * set the value of y on the coordinate
     * @param y
     */
    public void setY(int y)
    { this.y = y; }

    /**
     * set both x and y of the coordinate
     * @param x
     * @param y
     */
    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * return the value of x in the coordinate
     * @return the value of x
     */
    public int getX()
    { return x; }

    /**
     * return the value of y in the coordinate
     * @return the value of y
     */
    public int getY()
    { return y; }

    /**
     * returns x and y of the coordinate as an array;
     * @return
     */
    public int[] get()
    {
        return new int[] {x,y};
    }

    /**
     * gets the distance between to coordinates;
     * @param c another coordinate
     * @return the distance;
     */
    public double distance(DiscreteCoordinate c)
    { return Math.hypot(this.x-c.x, this.y-c.y); }

    /**
     * checks if this class is equal to another object;
     * @param obj
     * @return
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof DiscreteCoordinate) {
            return (((DiscreteCoordinate) obj).getX() == this.getX() && ((DiscreteCoordinate) obj).getY() == this.getY());
        }
        return false;
    }

    public Coordinate toCoordinate()
    {
        return new Coordinate((double) x, (double) y);
    }

    /**
     * creates custom Hash Code for each object
     * @return
     */
    public int hashCode() { return 1; }

    /**
     * return the value of the x and y as a string. in the format (x,y);
     */
    public String toString()
    { return "(" + x + "," + y + ")"; }

}