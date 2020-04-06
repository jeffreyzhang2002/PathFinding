package grid;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;


public class ListedGrid<E> extends Grid<E>
{
    HashMap<Point, E> gridList;
    public ListedGrid(int rows, int cols)
    {
        super(rows, cols);
        gridList = new HashMap<Point, E>(rows*cols/2);
    }

    public E get(Point coordinate)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("ContinuousCoordinate " + coordinate + "is not on the grid");
        return gridList.get(coordinate);
    }

    public E set(Point coordinate, E obj)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("ContinuousCoordinate " + coordinate + "is not on the grid");
        return gridList.put(coordinate, obj);

    }

    public E remove(Point coordinate)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("ContinuousCoordinate " + coordinate + "is not on the grid");
        return gridList.remove(coordinate);
    }

    public HashSet<Point> getOccupiedCoordinates()
    { return (HashSet<Point>) gridList.keySet(); }

    public HashSet<E> getAllObjects()
    { return new HashSet<>(gridList.values()); }

    public HashSet<E> getNeighboringObjects(Point position, boolean containCorners)
    {
        HashSet<E> neighboringObjects = new HashSet<E>();
        for(Point c : gridList.keySet())
        {
            if(c.getX() == position.getX() && Math.abs(c.getY()- position.getY()) == 1
                    || c.getY() == position.getY() && Math.abs(c.getX()- position.getX()) == 1)
                neighboringObjects.add(gridList.get(c));
            else if(containCorners && Math.abs(c.getY()- position.getY()) == 1 && Math.abs(c.getX()- position.getX()) == 1)
                neighboringObjects.add(gridList.get(c));
        }
        return neighboringObjects;
    }

    public HashSet<Point> getEmptyNeighboringCoordinates(Point position, boolean containCorners)
    {
        HashSet<Point> coordinateList = getNeighborCoordinates(position,containCorners);
        return (HashSet<Point>) coordinateList.stream().filter(n -> !gridList.containsKey(n)).collect(Collectors.toSet());
    }

    public HashSet<Point> getOccupiedNeighboringCoordinates(Point position, boolean containCorners)
    {
        HashSet<Point> coordinateList = getNeighborCoordinates(position,containCorners);
        return (HashSet<Point>) coordinateList.stream().filter(n -> gridList.containsKey(n)).collect(Collectors.toSet());
    }

    public void clear()
    { gridList.clear(); }
}
