package math.geometry.line;

import math.geometry.coordinates.Point;

public class Ray extends Line<Double>
{
    private double angle = 0;
    private double mag = 10;

    public Ray(Point<Double> startPoint, double angle)
    {
        super(new Point<Double>(startPoint.getX().doubleValue(), startPoint.getY().doubleValue()), null);
        this.angle = angle;
        super.setPointTwo(new Point<Double>(get2ndCoordinateX(), get2ndCoordinateY()));
    }

    private double get2ndCoordinateX()
    { return ((Math.cos(Math.toRadians(angle))*mag) + super.getPointOne().getX().doubleValue());}

    private double get2ndCoordinateY()
    { return ((Math.cos(Math.toRadians(angle))*mag) + super.getPointOne().getY().doubleValue());}

    private void update2ndCoordinate()
    { super.setPointTwo(new Point<Double>(get2ndCoordinateX(), get2ndCoordinateY())); }

    private void updateAngle()
    {
        angle = Math.atan2(super.getPointOne().getY().doubleValue() - super.getPointTwo().getY().doubleValue(),
                super.getPointOne().getX().doubleValue() - super.getPointTwo().getX().doubleValue());
    }

    private void updateMag()
    {
        mag = Math.hypot(super.getPointOne().getY().doubleValue() - super.getPointTwo().getY().doubleValue(),
                super.getPointOne().getX().doubleValue() - super.getPointTwo().getX().doubleValue());
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
        update2ndCoordinate();
    }

    public void setMag(double mag)
    {
        this.mag = mag;
        update2ndCoordinate();
    }

    public void incrementAngle(double amount)
    {
        angle += amount;
        while(angle > 360)
            angle -= 360;
        update2ndCoordinate();
    }

    public void setPointOne(Point<Double> pointOne)
    {
        super.setPointOne(pointOne);
        update2ndCoordinate();
    }

    public void setPointTwo(Point<Double> pointTwo)
    {
        super.setPointTwo(pointTwo);
        updateAngle();
        updateMag();
    }

    public void set(Point<Double> pointOne, Point<Double> pointTwo)
    {
        super.setPointOne(pointOne);
        super.setPointTwo(pointTwo);
        updateAngle();
        updateMag();
    }

    //TODO
    public Point<Double> intersectsAt(Line<Double> other)
    {
        if(!(other instanceof Ray))
            return super.intersectsAt(other);

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

    public boolean equals(Object o)
    {
        if(o instanceof Ray)
        {
            if(this.getPointOne().equals(((Ray)o).getPointOne()))
            {
                Ray r = (Ray)o;
                ((Ray) o).setMag(1);
                this.setMag(1);
                return this.getPointTwo().equals(((Ray)o).getPointTwo());
            }
        }
        return false;
    }

    public String toString()
    { return super.toString() + " " + mag + " " + angle; }
}
