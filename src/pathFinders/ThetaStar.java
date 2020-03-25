package pathFinders;

import grid.BoundedGrid;
import grid.Field;
import math.geometry.coordinates.DiscreteCoordinate;
import pathFinders.pathFindingTileStates.AStarState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class ThetaStar extends PathFinder
{
    PriorityQueue<AStarState> openSet;
    HashSet<AStarState> closedSet;
    BoundedGrid<AStarState> scoreLibrary;
    HashMap<AStarState, AStarState> cameFrom = new HashMap<>();

    public ThetaStar(Field field)
    { super(field); }

    public ThetaStar(Field field, DiscreteCoordinate start, DiscreteCoordinate end)
    { super(field, start, end); }

    public ArrayList<DiscreteCoordinate> genPath (boolean containCorners)
    {
        openSet = new PriorityQueue<>();
        closedSet = new HashSet<>();
        cameFrom = new HashMap<>();
        scoreLibrary = new BoundedGrid<>(super.getField().getRows(), super.getField().getCols());

        for (int i = 0; i < super.getField().getRows(); i++)
            for (int j = 0; j < super.getField().getCols(); j++) {
                DiscreteCoordinate currentCoordinate = new DiscreteCoordinate(i, j);
                scoreLibrary.set(currentCoordinate, new AStarState(currentCoordinate));
            }

        AStarState start = scoreLibrary.get(super.getStart());

        start.setGScore(0);
        start.setFScore(heuristicCost(start));
        cameFrom.put(start, null);

        while(!openSet.isEmpty())
        {
            AStarState current = openSet.poll();

            if(current.getCoordinate().equals(super.getEnd()))
                return reconstructPath(current);

            closedSet.contains(current);

            HashSet<DiscreteCoordinate> neighbors = super.getField().getEmptyNeighboringCoordinates(current.getCoordinate(), containCorners);

            for(DiscreteCoordinate n : neighbors)
                if(!closedSet.contains(n))
                    updateVertex(current, scoreLibrary.get(n));

        }
        return null;
    }

    public void updateVertex(AStarState s, AStarState neighbor)
    {
        if(true)//<-- line of sight
        {
            AStarState parentState = cameFrom.get(s);
            if(parentState.getGScore() + heuristicCost(parentState.getCoordinate(), neighbor.getCoordinate()) < neighbor.getGScore())
            {
                neighbor.setGScore( parentState.getGScore() + heuristicCost(parentState.getCoordinate(), neighbor.getCoordinate()));
                cameFrom.put(neighbor, cameFrom.get(s));
                if(!openSet.contains(neighbor))
                    openSet.remove(neighbor);
                neighbor.setFScore(neighbor.getGScore() + heuristicCost(neighbor));
                openSet.add(neighbor);
            }
        }
        else
        {
            if(s.getGScore() + heuristicCost(s.getCoordinate(), neighbor.getCoordinate()) < neighbor.getGScore()) {
                neighbor.setGScore(s.getGScore() + heuristicCost(s.getCoordinate(), neighbor.getCoordinate()));
                cameFrom.put(neighbor, s);
                if(openSet.contains(neighbor))
                    openSet.remove(neighbor);
                neighbor.setFScore(neighbor.getGScore() + heuristicCost(neighbor));
                openSet.add(neighbor);
            }
        }
    }

    public ArrayList<DiscreteCoordinate> reconstructPath(AStarState s)
    {
        ArrayList<DiscreteCoordinate> path = new ArrayList<>();
        path.add(s.getCoordinate());
        AStarState next = cameFrom.get(s);
        while(next != null)
            path.add(next.getCoordinate());
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
