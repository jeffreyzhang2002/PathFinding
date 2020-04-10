package actor;

import grid.Field;
import pathFinders.PathFinder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Creates an instance of a Robot
 */
public class Robot extends Actor
{
    private PathFinder pathFinder;
    private boolean containCorners = true;
    private int index = 1;

    /**
     * Creates a Robot by giving its field, its position and its pathFinding engine
     * @param field the Field it will run on
     * @param position The position it will be at
     * @param pathFinder the PathFinder it will use to pathFind
     */
    public Robot(Field field, Point position, PathFinder pathFinder)
    {
        super(field, position);
        this.pathFinder = pathFinder;
    }

    /**
     * initializes path finding algorithum. This method should be run before generating path
     * @param end the goal coordinate for algorithum to generate the path
     */
    public void initPathFinder(Point end)
    {
        pathFinder.setStart(super.getPosition());
        pathFinder.setEnd(end);
    }

    /**
     * Generates the path the robot will follow. This method can not be run before initializing the path Finder.
     * @return true if their is a valid path. False otherwise
     */
    public boolean generatePath()
    { return pathFinder.generatePath(containCorners); }

    /**
     * This method returns a List of all possible next positions. Because this robot follows a path this will return the next position on the path
     * @return the next position on the path
     */
    public HashSet<Point> getNextCoordinates()
    {
        if(pathFinder.getPath() == null)
            return super.getNextCoordinates();
        else if(index >= pathFinder.getPath().size())
            index = 0;

        HashSet<Point> temp =  new HashSet<>();

        temp.add(pathFinder.getPath().get(index));

        return temp;
    }

    /**
     * This method returns the next position on the path. If the path is null this will return the current position
     * @param coordinateList a HashSet of possible coordinate this actor can go to
     * @return the next position
     */
    public Point chooseNextCoordinate(HashSet<Point> coordinateList)
    {
        if(coordinateList == null || coordinateList.isEmpty())
            return super.getPosition();

        Point current = (Point) coordinateList.toArray()[0];

        if(!super.getField().isEmptyPosition(current))
        {
           pathFinder.dynamicReplan(super.getPosition(), containCorners);
            return super.getPosition();
        }

        index++;
        return current;
    }

    /**
     * returns the path the robot will follow
     * @return the Path
     */
    public ArrayList<Point> getPath()
    { return pathFinder.getPath(); }

    /**
     * colors the path the robot will follow
     * @param color
     */
    public void colorPath(Color color)
    {
        if(pathFinder.getPath() != null)
            for(Point current : pathFinder.getPath())
                super.getField().setTileColor(current, color);
    }

    public void paint(Graphics g, Point origin, int width)
    {
        g.setColor(Color.GREEN);
        g.fillRect(origin.x,origin.y,width,width);
    }
}