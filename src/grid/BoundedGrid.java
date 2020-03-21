package grid;

import math.geometry.coordinates.DiscreteCoordinate;

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
            throw new IllegalArgumentException("ContinuousCoordinate " + coordinate + "is not on the grid");
        return (E) grid[coordinate.getX()][coordinate.getY()];
    }

    public E set(DiscreteCoordinate coordinate, E obj)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("ContinuousCoordinate " + coordinate + "is not on the grid");
        Object temp = grid[coordinate.getX()][coordinate.getY()];
        grid[coordinate.getX()][coordinate.getY()] = obj;
        return (E) temp;
    }

    //TODO Why can't this be set(coordinates, null)? it throws an error for null pointer error. Remove this method because it is redundant
    public E remove(DiscreteCoordinate coordinate)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("ContinuousCoordinate " + coordinate + "is not on the grid");
        Object temp = grid[coordinate.getX()][coordinate.getY()];
        grid[coordinate.getX()][coordinate.getY()] = null;
        return (E) temp;
    }

    public void clear()
    {
        for(int i=0; i < grid.length; i++)
            for(int j=0; j < grid[0].length; j++)
                grid[i][j] = null;
    }
}

