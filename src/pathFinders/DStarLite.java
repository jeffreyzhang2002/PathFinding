package pathFinders;

import grid.BoundedGrid;
import grid.Field;
import pathFinders.pathFindingTileStates.DStarState;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Implementation of DStarLite Path Finding
 * @author Jeffrey
 */
public class DStarLite extends PathFinder
{
    private BoundedGrid<DStarState> openList;
    private PriorityQueue<DStarState> openQueue;
    private double km = 0;

    public DStarLite(Field field)
    { super(field); }

    public DStarLite(Field field, Point start, Point end)
    { super(field,start,end); }

    public ArrayList<Point> genPath(boolean containCorners)
    {
        init();
        computeShortestPath(containCorners);
        return rebuildPath(containCorners);
    }

    public double heuristic(Point start, Point end)
    { return start.distance(end); }

    public double calculatePrimaryKey(DStarState currentState)
    { return Math.min(currentState.getG(), currentState.getRHS() + heuristic(super.getStart(),currentState.getCoordinate()) + km); }

    public void init()
    {
        openQueue = new PriorityQueue<>();
        openList = new BoundedGrid<>(super.getField().getRows(),super.getField().getCols());
        km = 0;

        for(int i=0; i < super.getField().getRows(); i++)
        {
            for(int j=0; j < super.getField().getCols(); j++)
            {
                Point currentCoordinate = new Point(i,j);
                openList.set(currentCoordinate, new DStarState(currentCoordinate));
            }
        }
        DStarState endState = openList.get(super.getEnd());
        endState.setRHS(0.0);
        endState.setPrimaryKey(calculatePrimaryKey(endState));
        openQueue.add(endState);
    }

    public void updateVertex(DStarState currentState, boolean containCorners)
    {
        if(!currentState.getCoordinate().equals(super.getEnd()))
        {
            double minSuccessorValue = Double.POSITIVE_INFINITY;
            HashSet<Point> successors = super.getField().getEmptyNeighboringCoordinates(currentState.getCoordinate(), containCorners);
            for(Point s : successors)
            {
                double successorValue = heuristic(currentState.getCoordinate(), s) + openList.get(s).getG();
                if(successorValue < minSuccessorValue)
                    minSuccessorValue = successorValue;
            }
             currentState.setRHS(minSuccessorValue);
        }
        if(openQueue.contains(currentState))
        {
            openQueue.remove(currentState);
        }
        if(currentState.getG() != currentState.getRHS())
        {
            currentState.setPrimaryKey(calculatePrimaryKey(currentState));
            openQueue.add(currentState);
        }
    }

    public void computeShortestPath(boolean containCorners)
    {
        DStarState startState = openList.get(super.getStart());
        while(!openQueue.isEmpty() && openQueue.peek().getPrimaryKey() < calculatePrimaryKey(startState) || startState.getRHS() != startState.getG())
        {
            DStarState currentState = openQueue.poll();
            double kOld = currentState.getPrimaryKey();
            if(kOld < calculatePrimaryKey(currentState))
            {
                currentState.setPrimaryKey(calculatePrimaryKey(currentState));
                openQueue.add(currentState);
            }
            else if(currentState.getG() > currentState.getRHS())
            {
                currentState.setG(currentState.getRHS());
                HashSet<Point> predecessors = super.getField().getEmptyNeighboringCoordinates(currentState.getCoordinate(), containCorners);
                for(Point c : predecessors)
                {
                   updateVertex(openList.get(c),containCorners);
                }
            }
            else
            {
                currentState.setG(Double.POSITIVE_INFINITY);
                HashSet<Point> predecessors = super.getField().getEmptyNeighboringCoordinates(currentState.getCoordinate(), containCorners);
                for(Point c : predecessors)
                {
                    updateVertex(openList.get(c),containCorners);
                }
                updateVertex(currentState,containCorners);
            }
        }
    }

    public ArrayList<Point> rebuildPath(boolean containCorners)
    {
        ArrayList<Point> path = new ArrayList<>();
        Point current = super.getStart();
        while(!current.equals(super.getEnd()))
        {
            path.add(current);
            HashSet<Point> successor = super.getField().getNeighborCoordinates(current, containCorners);
            double minSuccessorScore = Double.POSITIVE_INFINITY;
            Point minSuccessor = null;
            for (Point s : successor)
            {
                double successorScore = openList.get(s).getG();
                if (successorScore < minSuccessorScore) {
                    minSuccessorScore = successorScore;
                    minSuccessor = s;
                }
            }
            current = minSuccessor;
        }
        path.add(super.getEnd());

        return path;
    }

    public ArrayList<Point> replan(Point currentPos, boolean containCorners)
    {
        km = km + heuristic(super.getEnd(), super.getStart());
        HashSet<Point> blockedNeighbors = super.getField().getOccupiedNeighboringCoordinates(currentPos, containCorners);

        for(Point b : blockedNeighbors)
        {
            DStarState blockedState = openList.get(b);
            blockedState.setG(Double.POSITIVE_INFINITY);
            blockedState.setRHS(Double.POSITIVE_INFINITY);
            HashSet<Point> neighbors = super.getField().getEmptyNeighboringCoordinates(b,containCorners);
            for(Point c : neighbors)
                updateVertex(openList.get(c),containCorners);
        }
        computeShortestPath(containCorners);
        super.setStart(currentPos);
        ArrayList<Point> tmp = rebuildPath(containCorners);
        return tmp;
    }

    public void display()
    {
        String s = "";
        for(int i=0; i<super.getField().getRows(); i++)
        {
            for(int j=0; j<super.getField().getCols(); j++)
                s += openList.get(new Point(i,j)) + " ";

            s += "\n";
        }
        System.out.println(s);
    }

    public void setkm(double km)
    { this.km = km; }

    public double getkm()
    { return km; }
}
