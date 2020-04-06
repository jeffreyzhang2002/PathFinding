package grid;

import math.geometry.coordinates.Point;
import math.geometry.line.Line;
import java.util.ArrayList;
import java.util.HashSet;

public class Plane<E extends Number>
{
    HashSet<Line<E>> geometricObjects;

    public Plane()
    {
        geometricObjects = new HashSet<>();
    }

    public void addGeometricObject(Line<E> line)
    { geometricObjects.add(line); }

    public Point<Double> intersectionPoint(Point<E> start, Point<E> end)
    {
        Line<E> l = new Line(start,end);
        for(Line<E> line : geometricObjects)
        {
            Point<Double> p = l.intersectsAt(line);
            if(p != null)
                return p;
        }
        return null;
    }

    public boolean lineOfSight(Point<E> start, Point<E> end)
    { return intersectionPoint(start, end) == null; }

    public HashSet<Point<E>> getKeyPoints()
    {
        HashSet<Point<E>> keyPoints = new HashSet<>();
        for(Line<E> line: geometricObjects)
        {
            keyPoints.add(line.getPointOne());
            keyPoints.add(line.getPointTwo());
        }
        return keyPoints;
    }

    public HashSet<Line<E>> getGeometricObjects()
    { return geometricObjects; }

    public boolean isEmpty()
    { return geometricObjects.isEmpty(); }
}
