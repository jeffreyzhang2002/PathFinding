package pathFinders;

import grid.Field;
import math.DiscreteCoordinate;
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

    public final boolean generatePath(boolean containCorners)
    {
        path = genPath(containCorners);
        return path != null;
    }

    public abstract ArrayList<DiscreteCoordinate> genPath(boolean containCorners);

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

    public final int getPathLength()
    { return path.size(); }

    public final boolean validPath()
    { return path!=null; }

    public final void reversePath()
    { Collections.reverse(path); }
}
