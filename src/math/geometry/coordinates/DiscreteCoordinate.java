package math.geometry.coordinates;

import java.util.Dictionary;

/**
 * Generic class to describe a coordinate in the 2D plane;
 * @author Jeffrey
 * @version 1
 * @since 1/4/19
 */
public class DiscreteCoordinate extends Point<Integer>
{

    /**
     * creates a DiscreteCoordinate at position x and y
     * @param x the x position of the coordinate
     * @param y the y position of the coordinate
     */
    public DiscreteCoordinate(int x, int y)
    { super(x,y); }

    public DiscreteCoordinate(DiscreteCoordinate other)
    { super(other.getX(), other.getY()); }

    public DiscreteCoordinate()
    { super(0,0); }

    /**
     * returns x and y of the coordinate as an array;
     * @return
     */
    public int[] get()
    { return new int[] {super.getX().intValue(), super.getY().intValue()}; }

    /**
     * converts the current DiscreteCoordinate to a continuous coordinate that stores doubles
     * @return a ContinuousCoordinate
     */
    public Coordinate toCoordinate()
    { return new Coordinate(super.getX().doubleValue(), super.getY().doubleValue()); }
}