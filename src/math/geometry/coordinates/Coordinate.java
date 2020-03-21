package math.geometry.coordinates;

/**
 * Generic class to describe a coordinate in the 2D plane;
 * @author Jeffrey
 * @version 1
 * @since 1/4/19
 */
public class Coordinate extends Point<Double>
{
    /**
     * creates a ContinuousCoordinate at position x and y
     * @param x the x position of the coordinate
     * @param y the y position of the coordinate
     */
    public Coordinate(double x, double y)
    { super(x,y); }

    /**
     * creates a ContinuousCoordinate using another ContinuousCoordinate
     * @param c another ContinuousCoordinate;
     */
    public Coordinate(Coordinate c)
    { super(c.getX(),c.getY()); }

    /**
     * creates a ContinuousCoordinate at the position (0,0)
     */
    public Coordinate()
    { super(0.0,0.0);}

    /**
     * returns x and y of the coordinate as an array;
     * @return
     */
    public double[] get()
    { return new double[] {super.getX().doubleValue(), super.getY().doubleValue()}; }

    /**
     * converts the current ContinuousCoordinate to a continuous DiscreteCoordinate that stores doubles
     * @return a DiscreteCoordinate
     */
    public DiscreteCoordinate toDiscreteCoordinate()
    { return new DiscreteCoordinate(super.getX().intValue(), super.getY().intValue()); }


}