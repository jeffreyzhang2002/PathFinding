package actor;

import grid.Field;
import math.Coordinate;
import math.DiscreteCoordinate;
import math.RGB;
import pathFinders.DStarLite;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashSet;

public class DRobot extends Actor
{
    private DStarLite pathFinder;
    private ArrayList<DiscreteCoordinate> path;
    private boolean containCorners = true;
    private int index = 1;

    public DRobot(Field grid, DiscreteCoordinate position, DStarLite pathFinder)
    {
        super(grid, position);
        this.pathFinder = pathFinder;
    }

    public void initPathFinder(DiscreteCoordinate end)
    {
        pathFinder.setStart(super.getPosition());
        pathFinder.setEnd(end);
    }

    public boolean generatePath()
    {
        path = pathFinder.genPath(containCorners);
        this.colorPath(path,new RGB(255,255,0));
        return path != null;
    }

    public HashSet<DiscreteCoordinate> getNextCoordinates()
    {
        HashSet<DiscreteCoordinate> temp =  new HashSet<DiscreteCoordinate>();

        if(path == null)
            return null;

        if(index >= path.size())
            index = 0;

        if(Math.random()*100 < 20)
            temp.addAll(super.getField().getEmptyNeighboringCoordinates(path.get(index),true));

        temp.add(path.get(index));
        return temp;
    }

    public DiscreteCoordinate chooseNextCoordinate(HashSet<DiscreteCoordinate> coordinateList)
    {
        if(coordinateList == null || coordinateList.isEmpty())
            return super.getPosition();

        DiscreteCoordinate current = (DiscreteCoordinate) coordinateList.toArray()[(int)(Math.random()*(coordinateList.size() - 1))];

        if(!current.equals(path.get(index)))
        {
            this.colorPath(path, new RGB(255,255,255));
            path = pathFinder.dynamicReplan(super.getPosition(),containCorners);
            this.colorPath(path, new RGB(255,255,0));
            index = 0;
        }
        if(!super.getField().isEmptyPosition(current))
        {
            this.colorPath(path, new RGB(255,255,255));
            path = pathFinder.dynamicReplan(super.getPosition(),containCorners);
            this.colorPath(path, new RGB(255,255,0));
            index = 0;
            current = (DiscreteCoordinate) getNextCoordinates().toArray()[0];
        }
        index++;
        return current;
    }

    public void draw(PApplet processing, Coordinate position, double width, double height)
    {
        processing.fill(255,0,0);
        processing.rect((float)position.getX(),(float)position.getY(), (float) width, (float) height);
    }

    public Actor droppedActor()
    {
        return null;
    }

    public ArrayList<DiscreteCoordinate> getPath()
    {
        return path;
    }

    public void colorPath(ArrayList<DiscreteCoordinate> path, RGB color)
    {
        for(DiscreteCoordinate c: path)
            super.getField().getTileColorTracker().set(c, color);
    }
}
