package pathFinders;

import grid.Field;
import math.DiscreteCoordinate;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public abstract class PathFinder
{
    private DiscreteCoordinate start, end;
    private Field field;
    private ArrayList<DiscreteCoordinate> path;

    public PathFinder(Field field)
    {
        this.field = field;
    }

    public PathFinder(Field field, DiscreteCoordinate start, DiscreteCoordinate end)
    {
        this.field = field;
        this.start = start;
        this.end = end;
    }

    public PathFinder(PathFinder other)
    {
        this.start = new DiscreteCoordinate(other.getStart());
        this.end = new DiscreteCoordinate(other.getEnd());
        this.field = other.getField();
    }

    public abstract ArrayList<DiscreteCoordinate> genPath(boolean containCorners);

    public ArrayList<DiscreteCoordinate> dynamicReplan(DiscreteCoordinate currentPosition, boolean containCorners)
    {
       path = genPath(containCorners);
       return path;
    }

    public final void setStart(DiscreteCoordinate start)
    { this.start = start; }

    public final void setEnd(DiscreteCoordinate end)
    { this.end = end; }

    public final DiscreteCoordinate getStart()
    { return start; }

    public final DiscreteCoordinate getEnd()
    { return end; }

    public final Field getField()
    { return field; }

    public final ArrayList<DiscreteCoordinate> getPath()
    { return path; }

    public final void mergePath(int index, ArrayList<DiscreteCoordinate> otherPath)
    {
        ArrayList<DiscreteCoordinate> newPath = new ArrayList<>();
        for(int i=0; i<index; i++)
        {
            newPath.add(path.get(i));
        }
        newPath.addAll(otherPath);
        path = newPath;
    }

    public final int getPathLength()
    { return path.size(); }

    public final boolean validPath()
    { return path!=null; }

    public final void reversePath()
    { Collections.reverse(path); }

    public String toString()
    {
        return start.toString() + " -> " + end.toString() + " path: " + path.toString();
    }
}
