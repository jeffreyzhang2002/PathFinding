package pathFinders.pathFindingTileStates;
import math.geometry.coordinates.Point;

public class LOSStarState<E extends Number> implements Comparable<LOSStarState<E>>
{
    private double fScore = Double.POSITIVE_INFINITY;
    private double gScore = Double.POSITIVE_INFINITY;
    private Point<E> coordinate;

    public LOSStarState(Point<E> coordinate)
    { this.coordinate = coordinate; }

    public void setFScore(double score)
    { fScore = score; }

    public void setGScore(double score)
    { gScore = score; }

    public double getFScore()
    { return fScore; }

    public double getGScore()
    { return gScore; }

    public Point<E> getCoordinate()
    { return coordinate; }

    public int compareTo(LOSStarState<E> o)
    { return (int)(fScore + gScore - o.getFScore() + o.getGScore()); }

    public boolean equals(Object o)
    {
        if(o instanceof LOSStarState)
            return (((LOSStarState)o)).getCoordinate().equals(coordinate);
        return o.equals(coordinate);
    }

    public String toString()
    { return coordinate.toString() + " F Score: " + fScore + " G Score: " + getGScore(); }
}
