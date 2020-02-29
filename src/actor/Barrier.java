package actor;

import grid.Field;
import math.DiscreteCoordinate;
import processing.core.PApplet;

import java.util.HashSet;

public class Barrier extends Actor
{
    public Barrier(Field grid, DiscreteCoordinate position)
    {
        super(grid, position);
    }


    public HashSet<DiscreteCoordinate> getNextCoordinates()
    {
        HashSet<DiscreteCoordinate> temp =  new HashSet<DiscreteCoordinate>();
        temp.add(super.getPosition());
        return temp;
    }

    public DiscreteCoordinate chooseNextCoordinate(HashSet<DiscreteCoordinate> coordinateList)
    {
        return super.getPosition();
    }

    public DiscreteCoordinate step()
    {
        return super.getPosition();
    }
}
