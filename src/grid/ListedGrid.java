package grid;

import math.DiscreteCoordinate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;


public class ListedGrid<E> extends Grid<E>
{
    HashMap<DiscreteCoordinate, E> gridList;
    public ListedGrid(int rows, int cols)
    {
        super(rows, cols);
        gridList = new HashMap<DiscreteCoordinate, E>(rows*cols/2);
    }

    public E get(DiscreteCoordinate coordinate)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("Coordinate " + coordinate + "is not on the grid");
        return gridList.get(coordinate);
    }

    public E set(DiscreteCoordinate coordinate, E obj)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("Coordinate " + coordinate + "is not on the grid");
        return gridList.put(coordinate, obj);

    }

    public E remove(DiscreteCoordinate coordinate)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("Coordinate " + coordinate + "is not on the grid");
        return gridList.remove(coordinate);
    }

    public HashSet<DiscreteCoordinate> getOccupiedCoordinates()
    { return (HashSet<DiscreteCoordinate>) gridList.keySet(); }

    public HashSet<E> getAllObjects()
    { return new HashSet<>(gridList.values()); }

    public HashSet<E> getNeighboringObjects(DiscreteCoordinate position, boolean containCorners)
    {
        HashSet<E> neighboringObjects = new HashSet<E>();
        for(DiscreteCoordinate c : gridList.keySet())
        {
            if(c.getX() == position.getX() && Math.abs(c.getY()- position.getY()) == 1
                    || c.getY() == position.getY() && Math.abs(c.getX()- position.getX()) == 1)
                neighboringObjects.add(gridList.get(c));
            else if(containCorners && Math.abs(c.getY()- position.getY()) == 1 && Math.abs(c.getX()- position.getX()) == 1)
                neighboringObjects.add(gridList.get(c));
        }
        return neighboringObjects;
    }

    public HashSet<DiscreteCoordinate> getEmptyNeighboringCoordinates(DiscreteCoordinate position, boolean containCorners)
    {
        HashSet<DiscreteCoordinate> coordinateList = getNeighborCoordinates(position,containCorners);
        return (HashSet<DiscreteCoordinate>) coordinateList.stream().filter(n -> !gridList.containsKey(n)).collect(Collectors.toSet());
    }

    public HashSet<DiscreteCoordinate> getOccupiedNeighboringCoordinates(DiscreteCoordinate position, boolean containCorners)
    {
        HashSet<DiscreteCoordinate> coordinateList = getNeighborCoordinates(position,containCorners);
        return (HashSet<DiscreteCoordinate>) coordinateList.stream().filter(n -> gridList.containsKey(n)).collect(Collectors.toSet());
    }
}
