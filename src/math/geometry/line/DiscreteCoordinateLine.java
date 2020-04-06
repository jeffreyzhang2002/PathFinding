package math.geometry.line;

import math.geometry.coordinates.DiscreteCoordinate;

public class DiscreteCoordinateLine extends Line<Integer>
{
    public DiscreteCoordinateLine(DiscreteCoordinate pointOne, DiscreteCoordinate pointTwo)
    { super(pointOne,pointTwo); }

    public DiscreteCoordinateLine()
    { super(new DiscreteCoordinate(0,0), new DiscreteCoordinate(0,0)); }

}
