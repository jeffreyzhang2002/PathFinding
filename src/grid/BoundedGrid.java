package grid;

import java.awt.*;

public class BoundedGrid<E> extends Grid<E>
{
    private Object[][] grid;

    public BoundedGrid(int rows, int cols)
    {
        super(rows, cols);
        grid = new Object[rows][cols];
    }

    public E get(Point coordinate)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("ContinuousCoordinate " + coordinate + "is not on the grid");
        return (E) grid[coordinate.x][coordinate.y];
    }

    public E set(Point coordinate, E obj)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("ContinuousCoordinate " + coordinate + "is not on the grid");
        Object temp = grid[coordinate.x][coordinate.y];
        grid[coordinate.x][coordinate.y] = obj;
        return (E) temp;
    }

    //TODO Why can't this be set(coordinates, null)? it throws an error for null pointer error. Remove this method because it is redundant
    public E remove(Point coordinate)
    {
        if(!isValid(coordinate))
            throw new IllegalArgumentException("ContinuousCoordinate " + coordinate + "is not on the grid");
        Object temp = grid[coordinate.x][coordinate.y];
        grid[coordinate.x][coordinate.y] = null;
        return (E) temp;
    }

    public void clear()
    {
        for(int i=0; i < grid.length; i++)
            for(int j=0; j < grid[0].length; j++)
                grid[i][j] = null;
    }
}

