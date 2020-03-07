package pathFinders;

import grid.Field;
import math.DiscreteCoordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class DStarLite extends PathFinder
{
    private DiscreteCoordinate start;
    private HashMap<DiscreteCoordinate,State> openList;
    private PriorityQueue<State> openQueue;
    private double km = 0;

    public DStarLite(Field field)
    { super(field); }

    public DStarLite(Field field, DiscreteCoordinate start, DiscreteCoordinate end)
    { super(field,start,end); }

    public ArrayList<DiscreteCoordinate> genPath(boolean containCorners)
    {
        init();
        computeShortestPath(containCorners);
        return rebuildPath(containCorners);
    }

    public double heuristic(DiscreteCoordinate start, DiscreteCoordinate end)
    { return start.distance(end); }

    public double calculatePrimaryKey(State currentState)
    { return Math.min(currentState.getG(), currentState.getRHS() + heuristic(super.getStart(),currentState.getCoordinate()) + km); }

    public double calculateSecondaryKey(State currentState)
    { return Math.min(currentState.getG(), currentState.getRHS()); }

    public void init()
    {
        openQueue = new PriorityQueue<>();
        openList = new HashMap<>();
        km = 0;

        for(int i=0; i < super.getField().getRows(); i++)
        {
            for(int j=0; j < super.getField().getCols(); j++)
            {
                DiscreteCoordinate currentCoordinate = new DiscreteCoordinate(i,j);
                openList.put(currentCoordinate, new State(currentCoordinate));
            }
        }
        State endState = openList.get(super.getEnd());
        endState.setRHS(0.0);
        endState.setKeys(calculatePrimaryKey(endState), calculateSecondaryKey(endState));
        openQueue.add(endState);
    }

    public void updateVertex(State currentState, boolean containCorners)
    {
        if(!currentState.getCoordinate().equals(super.getEnd()))
        {
            double minSuccessorValue = Double.POSITIVE_INFINITY;
            HashSet<DiscreteCoordinate> successors = super.getField().getEmptyNeighboringCoordinates(currentState.getCoordinate(), containCorners);
            for(DiscreteCoordinate s : successors)
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
            currentState.setKeys(calculatePrimaryKey(currentState), calculateSecondaryKey(currentState));
            openQueue.add(currentState);
        }
    }

    public void computeShortestPath(boolean containCorners)
    {
        State startState = openList.get(super.getStart());
        while(!openQueue.isEmpty() && openQueue.peek().getPrimaryKey() < calculatePrimaryKey(startState) || startState.getRHS() != startState.getG())
        {
            State currentState = openQueue.poll();
            double kOld = currentState.getPrimaryKey();
            if(kOld < calculatePrimaryKey(currentState))
            {
                currentState.setKeys(calculatePrimaryKey(currentState), calculateSecondaryKey(currentState));
                openQueue.add(currentState);
            }
            else if(currentState.getG() > currentState.getRHS())
            {
                currentState.setG(currentState.getRHS());
                HashSet<DiscreteCoordinate> predecessors = super.getField().getEmptyNeighboringCoordinates(currentState.getCoordinate(), containCorners);
                for(DiscreteCoordinate c : predecessors)
                {
                   updateVertex(openList.get(c),containCorners);
                }
            }
            else
            {
                currentState.setG(Double.POSITIVE_INFINITY);
                HashSet<DiscreteCoordinate> predecessors = super.getField().getEmptyNeighboringCoordinates(currentState.getCoordinate(), containCorners);
                for(DiscreteCoordinate c : predecessors)
                {
                    updateVertex(openList.get(c),containCorners);
                }
                updateVertex(currentState,containCorners);
            }
        }
        display();
    }

    public ArrayList<DiscreteCoordinate> rebuildPath(boolean containCorners)
    {
        ArrayList<DiscreteCoordinate> path = new ArrayList<>();
        DiscreteCoordinate current = super.getStart();
        while(!current.equals(super.getEnd()))
        {
            path.add(current);
            HashSet<DiscreteCoordinate> successor = super.getField().getNeighborCoordinates(current, containCorners);
            double minSuccessorScore = Double.POSITIVE_INFINITY;
            DiscreteCoordinate minSuccessor = null;
            for (DiscreteCoordinate s : successor)
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

    public ArrayList<DiscreteCoordinate> dynamicReplan(DiscreteCoordinate currentPos, boolean containCorners)
    {
        //super.setStart(currentPos);
        State currentState = openList.get(currentPos);
        km = km + heuristic(super.getEnd(), super.getStart());
        HashSet<DiscreteCoordinate> blockedNeighbors = super.getField().getOccupiedNeighboringCoordinates(currentPos, containCorners);

        for(DiscreteCoordinate b : blockedNeighbors)
        {
            State blockedState = openList.get(b);
            blockedState.setG(Double.POSITIVE_INFINITY);
            blockedState.setRHS(Double.POSITIVE_INFINITY);
            HashSet<DiscreteCoordinate> neighbors = super.getField().getEmptyNeighboringCoordinates(b,containCorners);
            for(DiscreteCoordinate c : neighbors)
                updateVertex(openList.get(c),containCorners);
        }
        computeShortestPath(containCorners);
        ArrayList<DiscreteCoordinate> tmp = rebuildPath(containCorners);
        return tmp;
    }

    private void display()
    {
        String s = "";
        for(int i=0; i<super.getField().getRows(); i++)
        {
            for(int j=0; j<super.getField().getCols(); j++)
            {
                s += openList.get(new DiscreteCoordinate(i,j)) + " ";
            }
            s += "\n";
        }
        System.out.println(s);
    }

    public void setkm(double km)
    {
        this.km = km;
    }

    public double getkm()
    {
        return km;
    }
}
