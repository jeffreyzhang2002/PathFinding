package actor;

import grid.Field;
import math.Coordinate;
import math.DiscreteCoordinate;
import processing.core.PApplet;

import java.util.Collections;
import java.util.HashSet;

public class SimpleMover extends Actor
{
    public SimpleMover(Field grid, DiscreteCoordinate position)
    {
        super(grid, position);
    }

    public HashSet<DiscreteCoordinate> getNextCoordinates()
    {
        return super.getField().getEmptyNeighboringCoordinates(super.getPosition(),false);
    }

    public DiscreteCoordinate chooseNextCoordinate(HashSet<DiscreteCoordinate> coordinateList)
    {
        if(coordinateList.isEmpty())
            return super.getPosition();
        int i = (int)(Math.random() * coordinateList.size());
        return (DiscreteCoordinate) coordinateList.toArray()[i];
    }

    public void draw(PApplet processing, Coordinate position, double width, double height)
    {
        processing.fill(255,0,0);
        processing.rect((float)position.getX(),(float)position.getY(), (float) width, (float) height);
    }

    public Actor droppedActor()
    {
        if((int)(Math.random()*10) == 1)
            return new SimpleMover(super.getField(),super.getPosition());
        return null;
    }
}
