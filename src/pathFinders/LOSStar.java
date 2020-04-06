package pathFinders;

import grid.Plane;
import math.geometry.coordinates.Point;
import pathFinders.pathFindingTileStates.LOSStarState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class LOSStar<E extends Number>
{
    private HashMap<Point<E>, Point<E>> connector;
    private Point<E> start, end;
    private Plane<E> plane;

    public LOSStar(Plane<E> plane, Point<E> start, Point<E> end)
    {
        this.plane = plane;
        this.start = start;
        this.end = end;
    }

    public ArrayList<Point<E>> generatePath()
    {
        PriorityQueue<LOSStarState<E>> openSet = new PriorityQueue<>();
        HashSet<LOSStarState<E>> closedSet = new HashSet<>();
        connector = new HashMap<>();

        LOSStarState<E> startPair = new LOSStarState<>(start);
        startPair.setFScore(start.distanceSquared(end));
        startPair.setGScore(0.0);
        connector.put(startPair.getCoordinate(), null);
        openSet.add(startPair);


        while(!openSet.isEmpty())
        {
            LOSStarState<E> current = openSet.poll();

            if(current.equals(end))
                return reconstructPath();


            HashSet<Point<E>> keyPoints = plane.getKeyPoints();
            keyPoints.add(end);

            for(Point<E> keyPoint : keyPoints)
                if(!closedSet.contains(keyPoint) && !openSet.contains(keyPoint) && plane.lineOfSight(keyPoint, current.getCoordinate()))
                {
                    connector.put(keyPoint, current.getCoordinate());
                    System.out.println(keyPoint + " --> " + current.getCoordinate());
                    LOSStarState<E> nextPoint = new LOSStarState<>(keyPoint);
                    nextPoint.setGScore(current.getGScore() + keyPoint.distanceSquared(current.getCoordinate()));
                    nextPoint.setFScore(keyPoint.distanceSquared(end));
                    openSet.add(nextPoint);
                }
            closedSet.add(current);
        }
        return null;
    }

    public ArrayList<Point<E>> reconstructPath()
    {
        System.out.println("Start reconstructing path");
        ArrayList<Point<E>> path = new ArrayList<>();
        Point<E> current = end;
        System.out.println(current);
        System.out.println(connector.get(current));
        System.out.println(connector.get(connector.get(current)));
        //System.out.println(connector.get(current));



//        while(!current.equals(start) && current != null)
//        {
//            System.out.println(current);
//            System.out.println(connector.get(current));
//            path.add(current);
//            current = connector.get(current);
//
//        }
        System.out.println("end reconstructing path");

        return path;
    }
}
