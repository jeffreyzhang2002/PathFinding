package math.geometry.line;

import math.geometry.coordinates.Point;

public class Line<E extends Number>
{
    private Point<E> pointOne, pointTwo;

    public Line(Point<E> pointOne, Point<E> pointTwo)
    {
        this.pointOne = pointOne;
        this.pointTwo = pointTwo;
    }

    public Point<E> getPointOne()
    { return pointOne; }

    public Point<E> getPointTwo()
    { return  pointTwo; }

    public void setPointOne(Point<E> pointOne)
    { this.pointOne = pointOne; }

    public void setPointTwo(Point<E> pointTwo)
    { this.pointTwo = pointTwo; }

    public void set(Point<E> pointOne, Point<E> pointTwo)
    {
        this.pointOne = pointOne;
        this.pointTwo = pointTwo;
    }

    public Point<Double> intersectsAt(Line<E> other)
    {
        double x1 = other.getPointOne().getX().doubleValue();
        double y1 = other.getPointOne().getY().doubleValue();
        double x2 = other.getPointTwo().getX().doubleValue();
        double y2 = other.getPointTwo().getY().doubleValue();
        double x3 = this.getPointOne().getX().doubleValue();
        double y3 = this.getPointOne().getY().doubleValue();
        double x4 = this.getPointTwo().getX().doubleValue();
        double y4 = this.getPointTwo().getY().doubleValue();

        double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        if(den == 0)
            return null;

        double t   = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        double u   = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;

        if(t > 0 && t < 1 && u > 0)
        {
            double intersectionX = x1 + t * (x2 - x1);
            double intersectionY = y1 + t * (y2 - y1);
            return new Point<Double>(intersectionX,intersectionY);
        }
        else
            return null;
    }

    public boolean intersects(Line<E> other)
    { return intersectsAt(other) != null; }

    public boolean equals(Object o)
    { return(o instanceof Line && ((Line) o).getPointOne().equals(this.getPointOne()) && ((Line) o).getPointTwo().equals(this.getPointTwo())); }

    public int hashCode()
    { return 1; }

    public String toString()
    { return pointOne.toString() + " --> " + pointTwo.toString(); }
}
