package LOSstar;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class LOSStar
{
    private Plane plane;
    private Point2D.Double start, end;
    
    private PriorityQueue<LOSStarGroup> openSet;
    private HashSet<Point2D.Double> closedSet;
    private HashMap<Point2D.Double, Point2D.Double> cameFrom;

    public LOSStar(Plane plane, Point2D.Double start, Point2D.Double end)
    {
        this.plane = plane;
        this.start = start;
        this.end = end;
    }

    public ArrayList<Point2D.Double> generatePath(double mag)
    {
        openSet = new PriorityQueue<>();
        closedSet = new HashSet<>();
        cameFrom = new HashMap<>();

        ArrayList<Point2D.Double> keyPoints = plane.getKeyPoints(mag);
        keyPoints.add(start);
        keyPoints.add(end);

        LOSStarGroup startGroup = new LOSStarGroup(start);
        startGroup.setGScore(0);
        startGroup.setFScore(heuristic(start,end));
        openSet.add(startGroup);

        while(!openSet.isEmpty())
        {
            LOSStarGroup currentGroup = openSet.poll();
            closedSet.add(currentGroup.getPoint());

            if(currentGroup.getPoint().equals(end))
                return reconstructPath();

            for(Point2D.Double currentKeyPoint : keyPoints)
            {
                if(!closedSet.contains(currentKeyPoint) && !openSetContains(currentKeyPoint) && plane.LineOfSight(currentGroup.getPoint(), currentKeyPoint))
                {
                    openSet.add(new LOSStarGroup(currentKeyPoint,
                            heuristic(currentGroup.getPoint(),currentKeyPoint) + currentGroup.getGScore(),
                            heuristic(currentKeyPoint, end)));
                    cameFrom.put(currentKeyPoint,currentGroup.getPoint());
                }
            }
        }
        return null;
    }

    private ArrayList<Point2D.Double> reconstructPath()
    {
        System.out.println("reconstruct path");
        Point2D.Double current = end;
        ArrayList<Point2D.Double> path = new ArrayList<>();
        path.add(end);
        while(cameFrom.get(current) != null)
        {
            current = cameFrom.get(current);
            path.add(current);
        }
        return path;
    }

    private boolean openSetContains(Point2D.Double point)
    {
        for(LOSStarGroup group : openSet)
            if(group.equals(point))
                return true;
        return false;
    }

    public double heuristic(Point2D start, Point2D end)
    { return start.distance(end); }

    private class LOSStarGroup implements Comparable<LOSStarGroup>
    {
        private Point2D.Double point;
        private double gScore = Double.POSITIVE_INFINITY;
        private double fScore = Double.POSITIVE_INFINITY;

        public LOSStarGroup(Point2D.Double point)
        { this.point = point; }

        public LOSStarGroup(Point2D.Double point, double gScore, double fScore)
        {
            this.point = point;
            this.gScore = gScore;
            this.fScore = fScore;
        }

        public double getGScore()
        { return gScore; }

        public void setGScore(double gScore)
        { this.gScore = gScore; }

        public double getFScore()
        { return fScore; }

        public void setFScore(double fScore)
        { this.fScore = fScore; }

        public Point2D.Double getPoint()
        { return point; }

        public int compareTo(LOSStarGroup other)
        { return (int) (this.getGScore() + this.fScore - (other.getGScore() + other.fScore)); }

        public boolean equals(Object o) {
            if(o instanceof  Point2D.Double)
                return ((Point2D.Double) o).equals(this.getPoint());
            else if(o instanceof LOSStarGroup)
                return((LOSStarGroup)o).getPoint().equals(this.getPoint());
            return false;
        }

        public int hashCode()
        { return 1; }

        public String toString()
        { return point.toString(); }
    }
}
