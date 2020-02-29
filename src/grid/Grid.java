package grid;

import math.DiscreteCoordinate;

import java.util.HashSet;

public abstract class Grid<E>
{
    private final int rows, cols;

    public Grid(int rows, int cols)
    {
        if(rows <= 0 || cols <= 0 )
            throw new IllegalArgumentException("arguments must be > 0");
        this.rows = rows;
        this.cols = cols;
    }

    public final int getRows()
    { return rows; }

    public final int getCols()
    { return cols; }

    public final boolean isValid(DiscreteCoordinate position)
    { return position.getX() >=0 && position.getX() < getRows() && position.getY() >=0 && position.getY() < getCols(); }

    public final boolean isValid(int x, int y)
    { return x >=0 && x < getRows() && y >=0 && y < getCols(); }

    public HashSet<DiscreteCoordinate> getNeighborCoordinates(DiscreteCoordinate coordinate, boolean containCorners)
    {
        HashSet<DiscreteCoordinate> coordinates;
        if(containCorners)
            coordinates = new HashSet<>(8);
        else
            coordinates = new HashSet<>(4);

        if(isValid(coordinate.getX() + 1, coordinate.getY()))
            coordinates.add(new DiscreteCoordinate(coordinate.getX() + 1, coordinate.getY()));
        if(isValid(coordinate.getX() - 1, coordinate.getY()))
            coordinates.add(new DiscreteCoordinate(coordinate.getX() - 1, coordinate.getY()));
        if(isValid(coordinate.getX(), coordinate.getY() + 1))
            coordinates.add(new DiscreteCoordinate(coordinate.getX(), coordinate.getY() + 1));
        if(isValid(coordinate.getX(), coordinate.getY() - 1))
            coordinates.add(new DiscreteCoordinate(coordinate.getX(), coordinate.getY() - 1));

        if(containCorners)
        {
            if(isValid(coordinate.getX() + 1, coordinate.getY() + 1))
                coordinates.add(new DiscreteCoordinate(coordinate.getX() + 1, coordinate.getY() + 1));
            if(isValid(coordinate.getX() + 1, coordinate.getY() - 1))
                coordinates.add(new DiscreteCoordinate(coordinate.getX() + 1, coordinate.getY() - 1));
            if(isValid(coordinate.getX() - 1, coordinate.getY() + 1))
                coordinates.add(new DiscreteCoordinate(coordinate.getX() - 1, coordinate.getY() + 1));
            if(isValid(coordinate.getX() - 1, coordinate.getY() - 1))
                coordinates.add(new DiscreteCoordinate(coordinate.getX() - 1, coordinate.getY() - 1));
        }
        return coordinates;
    }

    public Grid getGrid()
    {
        return this;
    }

    public boolean isEmpty()
    {
        return getAllObjects().isEmpty();
    }

    public boolean isEmptyPosition(DiscreteCoordinate position)
    {
        return get(position) == null;
    }

    public abstract E get(DiscreteCoordinate position);

    public abstract E set(DiscreteCoordinate position, E obj);

    public abstract  E remove(DiscreteCoordinate position);

    public abstract HashSet<DiscreteCoordinate> getOccupiedCoordinates();

    public abstract HashSet<E> getAllObjects();

    public abstract HashSet<E> getNeighboringObjects(DiscreteCoordinate coordinate, boolean containCorners);

    public abstract HashSet<DiscreteCoordinate> getEmptyNeighboringCoordinates(DiscreteCoordinate coordinate, boolean containCorners);

    public abstract HashSet<DiscreteCoordinate> getOccupiedNeighboringCoordinates(DiscreteCoordinate coordinate, boolean containCorners);
}
