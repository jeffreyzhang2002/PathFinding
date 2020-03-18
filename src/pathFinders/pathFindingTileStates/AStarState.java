package pathFinders.pathFindingTileStates;

import math.DiscreteCoordinate;

public class AStarState implements Comparable<AStarState>
{
    private double gScore = Double.POSITIVE_INFINITY;
    private double fScore = Double.POSITIVE_INFINITY;
    private DiscreteCoordinate coordinate;

    public AStarState(DiscreteCoordinate coordinate)
    { this.coordinate = coordinate; }

    public void setGScore(double gScore)
    { this.gScore = gScore; }

    public void setFScore(double fScore)
    { this.fScore = fScore; }

    public double getGScore()
    { return gScore; }

    public double getFScore()
    { return fScore; }

    public DiscreteCoordinate getCoordinate()
    { return coordinate; }

    public int compareTo(AStarState other)
    { return (int) (this.getFScore() - other.getFScore()); }

    public int hashCode()
    {
        return 1;
    }

    public boolean equals(Object other)
    {
        if(other instanceof AStarState)
            return(((AStarState) other).getCoordinate().equals(coordinate));
        return false;
    }

    public String toString()
    {
        return coordinate.toString() + "-> F: " + fScore + " G: " + gScore;
    }
}
