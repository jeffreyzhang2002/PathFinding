package actor;

import grid.Field;
import pathFinders.DStarLite;
import processing.core.PApplet;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class DRobot extends Actor
{
    private DStarLite pathFinder;
    private ArrayList<Point> path;
    private boolean containCorners = true;
    private int index = 1;

    public DRobot(Field grid, Point position, DStarLite pathFinder)
    {
        super(grid, position);
        this.pathFinder = pathFinder;
    }

    public void initPathFinder(Point end)
    {
        pathFinder.setStart(super.getPosition());
        pathFinder.setEnd(end);
    }

    public boolean generatePath()
    {
        path = pathFinder.genPath(containCorners);
        this.colorPath(path, Color.GREEN);
        return path != null;
    }

    public HashSet<Point> getNextCoordinates()
    {
        HashSet<Point> temp =  new HashSet<Point>();

        if(path == null)
            return null;

        if(index >= path.size())
            index = 0;

//        if(Math.random()*100 < 20)
//            temp.addAll(super.getField().getEmptyNeighboringCoordinates(path.get(index),true));

        temp.add(path.get(index));
        return temp;
    }

    public Point chooseNextCoordinate(HashSet<Point> coordinateList)
    {
        if(coordinateList == null || coordinateList.isEmpty())
            return super.getPosition();

        Point current = (Point) coordinateList.toArray()[(int)(Math.random()*(coordinateList.size() - 1))];

        if(!current.equals(path.get(index)))
        {
            this.colorPath(path, new Color(255,255,255));
            path = pathFinder.replan(super.getPosition(),containCorners);
            this.colorPath(path, new Color(255,255,0));
            index = 0;
        }
        if(!super.getField().isEmptyPosition(current))
        {
            this.colorPath(path, new Color(255,255,255));
            path = pathFinder.replan(path.get(index-1),containCorners);
            this.colorPath(path, new Color(255,255,0));
            index = 0;
            current = (Point) getNextCoordinates().toArray()[0];
        }
        index++;
        return current;
    }

    public void renderDraw(PApplet processing, Point position, double width, double height)
    {
        processing.fill(255,0,0);
        processing.rect((float) position.getX(), (float)position.getY(), (float) width, (float) height);
    }

    public Actor droppedActor()
    {
        return null;
    }

    public ArrayList<Point> getPath()
    {
        return path;
    }

    public void colorPath(ArrayList<Point> path, Color color)
    {
        for(Point c: path)
            super.getField().getTileColorTracker().set(c, color);
    }
}
