package pathFinders;

import grid.Field;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Abstract implementation of a path finding algorithm. This class is meant to be a base for creating paths for robots to follow
 */
public abstract class PathFinder
{
    private Point start, end;
    private Field field;
    private ArrayList<Point> path;

    /**
     * Constructor for creating pathFinder by using only a field
     * @param field
     */
    public PathFinder(Field field)
    { this.field = field; }

    /**
     * Constructor for creating pathFinder using a field, a starting point and an ending point
     * @param field
     * @param start
     * @param end
     */
    public PathFinder(Field field, Point start, Point end)
    {
        this.field = field;
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a pathFinder from another pathFinder
     * @param other another pathFinder
     */
    public PathFinder(PathFinder other)
    {
        this.start = new Point(other.getStart());
        this.end = new Point(other.getEnd());
        this.field = other.getField();
    }

    /**
     * This method must be overridden. This method generates the path.
     * @param containCorners chooses if corner are included in the neighbors
     * @return the Path that is generated. Null if path does not exist or it fails
     */
    public abstract ArrayList<Point> genPath(boolean containCorners);

    /**
     * This method generates the path and return true or false depending on if the path is generated or not
     * @param containCorners chooses if corners are included in the neighbors
     * @return true if path is generates false otherwise
     */
    public final boolean generatePath(boolean containCorners)
    {
        path = genPath(containCorners);
        return path != null;
    }

    /**
     * This method is run for dynamic replanning. This method should be run if the original path fails.
     * @param currentPosition The position dynamic replanning will start
     * @param containCorners chooses if corners are included in the neighbors
     * @return the new path generates
     */
    public ArrayList<Point> replan(Point currentPosition, boolean containCorners)
    {
        Point placeHolder = start;
        this.setStart(currentPosition);
        ArrayList<Point> newPath = genPath(containCorners);
        this.setStart(start);
        return newPath;
    }

    /**
     * This method is run for replanning the path. This method should be ran if the original pathencounterss new unknown obstacles
     * @param currentPosition the position the robot is currently on
     * @param containCorners chooses if corners are included in the neighbors
     * @return true or false
     */
    public final boolean dynamicReplan(Point currentPosition, boolean containCorners)
    {
        ArrayList<Point> newPath = replan(currentPosition, containCorners);
        if(newPath == null)
            return false;
        int index = path.indexOf(currentPosition);
        mergePath(index, newPath);
        return true;
    }

    /**
     * Sets the start of the pathfinder
     * @param start the start position
     */
    public final void setStart(Point start)
    { this.start = start; }

    /**
     * Sets the end of the pathfinder
     * @param end the end position
     */
    public final void setEnd(Point end)
    { this.end = end; }

    /**
     * returns the start of the pathfinder
     * @return the start position
     */
    public final Point getStart()
    { return start; }

    /**
     * returns the end of the pathfinder
     * @return the end of the position
     */
    public final Point getEnd()
    { return end; }

    /**
     * returns the field the pathfinder will run on
     * @return the Field
     */
    public final Field getField()
    { return field; }

    /**
     * returns the path the pathfinder generated
     * @return the path that was created
     */
    public final ArrayList<Point> getPath()
    { return path; }

    /**
     * merges the current path with another arraylist
     * @param index the index it will be merged at
     * @param otherPath another path
     */
    private final void mergePath(int index, ArrayList<Point> otherPath)
    {
        ArrayList<Point> newPath = new ArrayList<>();
        for(int i=0; i<index; i++)
            newPath.add(path.get(i));
        newPath.addAll(otherPath);
        this.path = newPath;
    }

    /**
     * returns the length of the path
     * @return the length of the path
     */
    public final int getPathLength()
    { return path.size(); }

    /**
     * returns true or false if the path exists or not. If it is null the path does not exist
     * @return true if not null. False if it is null
     */
    public final boolean validPath()
    { return path!=null; }

    /**
     * reverses the current path so that the start is now the end and the end is now the start
     */
    public final void reversePath()
    { Collections.reverse(path); }

    public String toString()
    {
        return start.toString() + " -> " + end.toString() + " path: " + path.toString();
    }
}
