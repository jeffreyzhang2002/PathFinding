package pathFinders;

import grid.BoundedGrid;
import grid.Field;
import pathFinders.pathFindingTileStates.AStarState;
import java.awt.*;
import java.util.*;

public class AStar extends PathFinder
{
    PriorityQueue<AStarState> openSet;
    HashSet<AStarState> closedSet;
    BoundedGrid<AStarState> scoreLibrary;
    HashMap<Point, Point> cameFrom = new HashMap<>();

    public AStar(Field field)
    { super(field); }

    public AStar(Field field, Point start, Point end)
    { super(field,start,end); }

    public ArrayList<Point> genPath (boolean containCorners)
    {
        openSet   = new PriorityQueue<>();
        closedSet = new HashSet<>();
        cameFrom  = new HashMap<>();
        scoreLibrary = new BoundedGrid<>(super.getField().getRows(), super.getField().getCols());

        for(int i=0; i<super.getField().getRows(); i++)
            for(int j=0; j<super.getField().getCols(); j++) {
                Point currentCoordinate = new Point(i,j);
                scoreLibrary.set(currentCoordinate, new AStarState(currentCoordinate));
            }

        AStarState start = scoreLibrary.get(super.getStart());
        start.setGScore(0);
        start.setFScore(heuristicCost(start));
        openSet.add(start);

        while(!openSet.isEmpty())
        {
            AStarState current = openSet.poll();

            if(current.getCoordinate().equals(super.getEnd()))
                return reconstructPath(current.getCoordinate());

            closedSet.add(current);
            HashSet<Point> neighbors = super.getField().getEmptyNeighboringCoordinates(current.getCoordinate(), containCorners);

            for(Point n : neighbors)
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

    private ArrayList<Point> reconstructPath(Point current)
    {
        ArrayList<Point> path = new ArrayList<>();
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
    { return heuristicCost(state.getCoordinate(), super.getEnd()); }

    private double heuristicCost(Point a, Point b)
    { return Math.pow(a.getX()-b.getX(),2) + Math.pow(a.getY()-b.getY(),2); }
}
