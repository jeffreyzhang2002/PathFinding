package LOSstar;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Plane
{
    private ArrayList<Line2D.Double> barriers;
    public Plane()
    { barriers = new ArrayList<>(); }

    public void addBarrier(Line2D.Double line)
    { barriers.add(line); }

    public void addBarrier(ArrayList<Line2D.Double> lines)
    { barriers.addAll(lines); }

    public boolean LineOfSight(Point2D startPoint, Point2D endPoint)
    {
        Line2D.Double intersectionLine = new Line2D.Double(startPoint,endPoint);

        for(Line2D.Double currentLine : barriers)
            if(currentLine.intersectsLine(intersectionLine))
                return false;
        return true;
    }

    public ArrayList<Line2D.Double> getBarriers()
    { return barriers; }

    public ArrayList<Point2D.Double> getKeyPoints(double mag)
    {
        ArrayList<Point2D.Double> keyPoints = new ArrayList<>();
        for(Line2D.Double currentLine: barriers)
            keyPoints.addAll(getPropagatedPoint(currentLine,mag));
        return keyPoints;
    }

    private ArrayList<Point2D.Double> getPropagatedPoint(Line2D.Double line, double mag) {
        ArrayList<Point2D.Double> propagatedPoint = new ArrayList<>();

        double angle = Math.atan2(line.getY2() - line.getY1(), line.getX2() - line.getX1());

        Point2D.Double point1 = new Point2D.Double();
        Point2D.Double point2 = new Point2D.Double();


        if (line.getX1() < line.getX2()) {
            point1.setLocation(line.getX1() - Math.abs(Math.cos(angle)) * mag, 0);
            point2.setLocation(line.getX2() + Math.abs(Math.cos(angle)) * mag, 0);
        } else {
            point1.setLocation(line.getX1() + Math.abs(Math.cos(angle)) * mag, 0);
            point2.setLocation(line.getX2() - Math.abs(Math.cos(angle)) * mag, 0);
        }

        if (line.getY1() < line.getY2())
        {
            point1.setLocation(point1.getX(), line.getY1() - Math.abs(Math.sin(angle)) * mag);
            point2.setLocation(point2.getX(), line.getY2() + Math.abs(Math.sin(angle)) * mag);
        } else {
            point1.setLocation(point1.getX(), line.getY1() + Math.abs(Math.sin(angle)) * mag);
            point2.setLocation(point2.getX(), line.getY2() - Math.abs(Math.sin(angle)) * mag);
        }

        propagatedPoint.add(point1);
        propagatedPoint.add(point2);

        return propagatedPoint;
    }
}
