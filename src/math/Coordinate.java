package math;

import java.util.Objects;

/**
 * Generic class to describe a coordinate in the 2D plane;
 * @author Jeffrey
 * @version 1
 * @since 1/4/19
 */
public class Coordinate
{
    private double x,y;

    /**
     * creates a Coordinate at position x and y
     * @param x the x position of the coordinate
     * @param y the y position of the coordinate
     */
    public Coordinate(double x, double y)
    { set(x,y); }

    /**
     * creates a Coordinate using another Coordinate
     * @param c another Coordinate;
     */
    public Coordinate(Coordinate c)
    {
        set(c.getX(),c.getY());
    }

    /**
     * creates a Coordinate at the position (0,0)
     */
    public Coordinate()
    { set(0,0);}

    /**
     * set the value of x on the coordinate
     * @param x
     */
    public void setX(double x)
    { this.x = x; }

    /**
     * set the value of y on the coordinate
     * @param y
     */
    public void setY(double y)
    { this.y = y; }

    /**
     * set both x and y of the coordinate
     * @param x
     * @param y
     */
    public void set(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * return the value of x in the coordinate
     * @return the value of x
     */
    public double getX()
    { return x; }

    /**
     * return the value of y in the coordinate
     * @return the value of y
     */
    public double getY()
    { return y; }

    /**
     * returns x and y of the coordinate as an array;
     * @return
     */
    public double[] get()
    {
        return new double[] {x,y};
    }

    /**
     * gets the distance between to coordinates;
     * @param c another coordinate
     * @return the distance;
     */
    public double distance(Coordinate c)
    { return Math.hypot(this.x-c.x, this.y-c.y); }

    public boolean equals(Object obj)
    {
        if(obj instanceof DiscreteCoordinate) {
            return (((DiscreteCoordinate) obj).getX() == this.getX() && ((DiscreteCoordinate) obj).getY() == this.getY());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(x,y);
    }

    public DiscreteCoordinate toDiscreteCoordinate()
    {
        return new DiscreteCoordinate((int) x , (int) y);
    }
    /**
     * return the value of the x and y as a string. in the format (x,y);
     */
    public String toString()
    { return "(" + x + "," + y + ")"; }
}