package pathFinders.pathFindingTileStates;

import math.geometry.coordinates.DiscreteCoordinate;

public class ThetaStarState
{
    private DiscreteCoordinate coordinate;
    private double gScore = Double.POSITIVE_INFINITY;

    public ThetaStarState(DiscreteCoordinate coordinate)
    { this.coordinate = coordinate; }

    public void setGScore(double gScore)
    { this.gScore = gScore; }

    public double getGScore()
    { return gScore; }

    public DiscreteCoordinate getCoordinate()
    { return coordinate; }

    public int compareTo(ThetaStarState other)
    { return (int) (this.getGScore() - other.getGScore()); }

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
        return coordinate.toString() + " G: " + gScore;
    }
}
