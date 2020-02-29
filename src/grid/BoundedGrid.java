package grid;

import math.DiscreteCoordinate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class BoundedGrid<E> extends Grid<E>
{
    private Object[][] grid;

    public BoundedGrid(int rows, int cols)
    {
        super(rows, cols);
        grid = new Object[rows][cols];
    }

    public E get(DiscreteCoordinate coordinate)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("Coordinate " + coordinate + "is not on the grid");
        return (E) grid[coordinate.getX()][coordinate.getY()];
    }

    public E set(DiscreteCoordinate coordinate, E obj)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("Coordinate " + coordinate + "is not on the grid");
        Object foo = grid[coordinate.getX()][coordinate.getY()];
        grid[coordinate.getX()][coordinate.getY()] = obj;
        return (E) foo;
    }

    public E remove(DiscreteCoordinate coordinate)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("Coordinate " + coordinate + "is not on the grid");
        Object foo = grid[coordinate.getX()][coordinate.getY()];
        grid[coordinate.getX()][coordinate.getY()] = null;
        return (E) foo;
    }

    public HashSet<DiscreteCoordinate> getOccupiedCoordinates()
    {
        HashSet<DiscreteCoordinate> coordinates = new HashSet<>();
        for(int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if(grid[i][j] != null)
                    coordinates.add(new DiscreteCoordinate(i,j));
            }
        }
        return coordinates;
    }

    public HashSet<E> getAllObjects()
    {
        HashSet<E> objectList = new HashSet<>();
        for(int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if(grid[i][j] != null)
                    objectList.add((E)grid[i][j]);
            }
        }
        return objectList;
    }

    public HashSet<E> getNeighboringObjects(DiscreteCoordinate coordinate, boolean containCorners)
    {
        HashSet<DiscreteCoordinate> neighboringCoordinate = getNeighborCoordinates(coordinate,containCorners);
        HashSet<E> actorList = new HashSet<>();

        for(DiscreteCoordinate c: neighboringCoordinate)
            if(c != null)
                actorList.add((E)grid[c.getX()][c.getY()]);
        return actorList;
    }

    public HashSet<DiscreteCoordinate> getEmptyNeighboringCoordinates(DiscreteCoordinate current, boolean containCorners)
    {
        HashSet<DiscreteCoordinate> coordinatelist = getNeighborCoordinates(current,containCorners);
        HashSet<DiscreteCoordinate> returnList = new HashSet<DiscreteCoordinate>();
        for(DiscreteCoordinate c : coordinatelist)
            if(grid[c.getX()][c.getY()] == null)
                returnList.add(c);
        return returnList;
    }

    public HashSet<DiscreteCoordinate> getOccupiedNeighboringCoordinates(DiscreteCoordinate current, boolean containCorners)
    {
        HashSet<DiscreteCoordinate> coordinatelist = getNeighborCoordinates(current,containCorners);
        HashSet<DiscreteCoordinate> returnList = new HashSet<DiscreteCoordinate>();
        for(DiscreteCoordinate c : coordinatelist)
            if(grid[c.getX()][c.getY()] != null)
                returnList.add(c);
        return returnList;
    }
}

