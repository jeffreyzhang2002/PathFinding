package actor;

import grid.Field;
import math.Coordinate;
import math.DiscreteCoordinate;
import math.RGB;
import pathFinders.DStarLite;
import pathFinders.PathFinder;
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

    public void generatePath()
    {
        path = pathFinder.genPath(containCorners);
        this.colorPath(path,new RGB(255,255,0));
    }

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
            System.out.println("someone blocked me");
            this.colorPath(path, new RGB(255,255,255));
            path = pathFinder.dynamicReplan(super.getPosition(),containCorners);
            this.colorPath(path, new RGB(255,255,0));
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
            super.getField().getTileDisplayerGrid().set(c, color);
    }
}
