package math.geometry.coordinates;

public class Point<E extends Number>
{

    private E x, y;

    /**
     * creates a DiscreteCoordinate at position x and y
     * @param x the x position of the coordinate
     * @param y the y position of the coordinate
     */
    public Point(E x, E y)
    { set(x, y); }

    /**
     * set the value of x on the coordinate
     *
     * @param x
     */
    public void setX(E x)
    { this.x = x; }

    /**
     * set the value of y on the coordinate
     *
     * @param y
     */
    public void setY(E y)
    { this.y = y; }

    /**
     * set both x and y of the coordinate
     *
     * @param x
     * @param y
     */
    public void set(E x, E y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * return the value of x in the coordinate
     *
     * @return the value of x
     */
    public E getX()
    { return x; }

    /**
     * return the value of y in the coordinate
     *
     * @return the value of y
     */
    public E getY()
    { return y; }

    /**
     * gets the distance between to coordinates;
     *
     * @param c another coordinate
     * @return the distance;
     */
    public double distance(Point<E> c)
    { return Math.hypot(x.doubleValue() - c.x.doubleValue(), y.doubleValue() - c.y.doubleValue()); }

    public static double distance(Point a, Point b)
    { return Math.hypot(a.getX().doubleValue() - b.getX().doubleValue(), a.getY().doubleValue() - b.getY().doubleValue()); }

    public double distanceSquared(Point<E> c)
    { return Math.pow(x.doubleValue() - c.getX().doubleValue(),2)+ Math.pow(y.doubleValue()- c.getY().doubleValue(),2); }

    public static double distanceSquared(Point a, Point b)
    { return Math.pow(a.getX().doubleValue() - b.getX().doubleValue(),2)+ Math.pow(a.getY().doubleValue()- b.getY().doubleValue(),2); }

    /**
     * checks if this class is equal to another object. They are equal if the x and y values are equal
     * @param obj the given Object
     * @return true or false depending if the given object is equal to the current object
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Point)
            return (((Point) obj).getX().equals(this.getX()) && ((Point) obj).getY().equals(this.getY()));
        return false;
    }

    /**
     * creates custom Hash Code for each object
     * @return the coordinate hashed;
     */
    public int hashCode()
    { return 1; }

    /**
     * return the value of the x and y as a string. in the format (x,y);
     */
    public String toString()
    { return "(" + x.toString() + "," + y.toString() + ")"; }
}
