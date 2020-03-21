package actor;

import grid.Field;
import math.geometry.coordinates.DiscreteCoordinate;

/**
 * Creates a Barrier Actor
 */
public class Barrier extends Actor
{
    public Barrier(Field grid, DiscreteCoordinate position)
    { super(grid, position); }

    public DiscreteCoordinate step()
    { return super.getPosition(); }
}
