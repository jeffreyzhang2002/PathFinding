package actor;

import grid.Field;
import math.*;
import pathFinders.PathFinder;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.HashSet;

public class Robot extends Actor
{
    private PathFinder pathFinder;
    private ArrayList<DiscreteCoordinate> path;
    private boolean doDynamicReplanning = true;
    private boolean containCorners = true;
    private int index = 1;

    public Robot(Field grid, DiscreteCoordinate position, PathFinder pathFinder)
    {
        super(grid, position);
        this.pathFinder = pathFinder;
    }

    public void initPathFinder(DiscreteCoordinate end)
    {
        pathFinder.setStart(super.getPosition());
        pathFinder.setEnd(end);
    }

    public void generatePath()
    { path = pathFinder.genPath(containCorners); }

    public HashSet<DiscreteCoordinate> getNextCoordinates()
    {
        HashSet<DiscreteCoordinate> temp =  new HashSet<DiscreteCoordinate>();

        if(path == null)
            return null;

        if(index >= path.size())
            index = 0;

        temp.add(path.get(index));

        return temp;
    }

    public DiscreteCoordinate chooseNextCoordinate(HashSet<DiscreteCoordinate> coordinateList)
    {
        if(coordinateList == null || coordinateList.isEmpty())
            return super.getPosition();

        DiscreteCoordinate current = (DiscreteCoordinate) coordinateList.toArray()[0];

        if(!super.getField().isEmptyPosition(current))
        {
            pathFinder.setStart(super.getPosition());
            ArrayList<DiscreteCoordinate> newPath = pathFinder.genPath(containCorners);
            if(newPath == null) {
                path = null;
                return super.getPosition();
            }

            for(int i = index; i > 0; i--)
                newPath.add(0, path.get(i));

            path = newPath;

            return super.getPosition();
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
    { return null; }

    public ArrayList<DiscreteCoordinate> getPath()
    { return path; }

    public void colorPath()
    {
        for(DiscreteCoordinate c: path)
            super.getField().getTileColorTracker().set(c, new RGB(255,255,0));
    }
}
