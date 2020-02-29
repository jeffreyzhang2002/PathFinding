package pathFinders;

import grid.Field;
import math.DiscreteCoordinate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class AStar extends PathFinder
{
    HashSet<DiscreteCoordinate> openSet = new HashSet<>();
    HashSet<DiscreteCoordinate> closedSet = new HashSet<>();
    HashMap<DiscreteCoordinate, DiscreteCoordinate> cameFrom = new HashMap<>();
    HashMap<DiscreteCoordinate, Double[]> score = new HashMap<>();

    public AStar(Field field)
    {
        super(field);
    }

    public AStar(Field field, DiscreteCoordinate start, DiscreteCoordinate end)
    {
        super(field,start,end);
    }

    public ArrayList<DiscreteCoordinate> genPath (boolean containCorners)
    {
        openSet   = new HashSet<>();
        closedSet = new HashSet<>();
        cameFrom  = new HashMap<>();
        score     = new HashMap<>();

        for(int i=0; i < super.getField().getRows(); i++)
            for(int j=0; j < super.getField().getCols(); j++)
                score.put(new DiscreteCoordinate(i,j), new Double[] { Double.MAX_VALUE,Double.MAX_VALUE } );

        openSet.add(super.getStart());
        score.put(super.getStart(), new Double[] {heuristicCost(super.getStart(),super.getEnd()), 0.0});

        while(!openSet.isEmpty())
        {
            DiscreteCoordinate current = findMin();

            if(current.equals(super.getEnd()))
                return reconstructPath(current, cameFrom);

            openSet.remove(current);
            closedSet.add(current);

            HashSet<DiscreteCoordinate> neighbors = super.getField().getEmptyNeighboringCoordinates(current,containCorners);

            for(DiscreteCoordinate n : neighbors)
            {
                if(n == null || closedSet.contains(n))
                    continue;

                double tempG = score.get(current)[1] + heuristicCost(n,current);

                if(!openSet.add(n) && tempG >= score.get(n)[1])
                    continue;

                cameFrom.put(n, current);
                score.put(n, new Double[] {tempG + heuristicCost(n,current), tempG });
            }
        }
        return null;
    }

    private ArrayList<DiscreteCoordinate> reconstructPath(DiscreteCoordinate current, HashMap<DiscreteCoordinate, DiscreteCoordinate> cameFrom)
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

    private double heuristicCost(DiscreteCoordinate a, DiscreteCoordinate b)
    {
        //return Math.abs(a.getX()-b.getX()) + Math.abs(a.getY()-b.getY());
        return a.distance(b);
    }

    private DiscreteCoordinate findMin()
    {
        DiscreteCoordinate current = null;
        double min = Double.MAX_VALUE;
        for(DiscreteCoordinate c : openSet)
        {
            double fScoreNow = score.get(c)[0];
            if(fScoreNow < min)
            {
                min = fScoreNow;
                current = c;
            }
        }
        return current;
    }
}
