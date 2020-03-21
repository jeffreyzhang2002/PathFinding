package pathFinders;

import grid.BoundedGrid;
import grid.Field;
import math.geometry.coordinates.DiscreteCoordinate;
import pathFinders.pathFindingTileStates.AStarState;

import java.util.*;

public class AStar extends PathFinder
{
    PriorityQueue<AStarState> openSet;
    HashSet<AStarState> closedSet;
    BoundedGrid<AStarState> scoreLibrary;
    HashMap<DiscreteCoordinate, DiscreteCoordinate> cameFrom = new HashMap<>();

    public AStar(Field field)
    { super(field); }

    public AStar(Field field, DiscreteCoordinate start, DiscreteCoordinate end)
    { super(field,start,end); }

    public ArrayList<DiscreteCoordinate> genPath (boolean containCorners)
    {
        openSet   = new PriorityQueue<>();
        closedSet = new HashSet<>();
        cameFrom  = new HashMap<>();
        scoreLibrary = new BoundedGrid<>(super.getField().getRows(), super.getField().getCols());

        for(int i=0; i<super.getField().getRows(); i++)
            for(int j=0; j<super.getField().getCols(); j++) {
                DiscreteCoordinate currentCoordinate = new DiscreteCoordinate(i,j);
                scoreLibrary.set(currentCoordinate, new AStarState(currentCoordinate));
            }

        AStarState start = scoreLibrary.get(super.getStart());
        start.setGScore(0);
        start.setFScore(heuristicCost(super.getStart(), super.getEnd()));
        openSet.add(start);

        while(!openSet.isEmpty())
        {
            AStarState current = openSet.poll();

            if(current.getCoordinate().equals(super.getEnd()))
                return reconstructPath(current.getCoordinate());

            closedSet.add(current);
            HashSet<DiscreteCoordinate> neighbors = super.getField().getEmptyNeighboringCoordinates(current.getCoordinate(), containCorners);

            for(DiscreteCoordinate n : neighbors)
            {
                if(closedSet.contains(n))
                    continue;

                double tempG = current.getGScore() + heuristicCost(current.getCoordinate(), n);
                AStarState neighborState = scoreLibrary.get(n);

                if(tempG < neighborState.getGScore())
                {
                    cameFrom.put(n, current.getCoordinate());
                    neighborState.setGScore(tempG);
                    neighborState.setFScore(tempG + heuristicCost(neighborState));

                    if(!openSet.contains(neighborState))
                        openSet.add(neighborState);
                }
            }
        }
        return null;
    }

    private ArrayList<DiscreteCoordinate> reconstructPath(DiscreteCoordinate current)
    {
        ArrayList<DiscreteCoordinate> path = new ArrayList<>();
        path.add(current);
        while(cameFrom.containsKey(current))
        {
            path.add(cameFrom.get(current));
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    private double heuristicCost(AStarState state)
    { return state.getCoordinate().distance(super.getEnd()); }

    private double heuristicCost(DiscreteCoordinate a, DiscreteCoordinate b)
    {
        //return Math.abs(a.getX()-b.getX()) + Math.abs(a.getY()-b.getY());
        return a.distance(b);
    }
}
