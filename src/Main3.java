import grid.Plane;
import math.geometry.coordinates.Point;
import math.geometry.line.Line;
import pathFinders.LOSStar;
import processing.core.PApplet;

import java.util.ArrayList;

public class Main3 extends PApplet
{
    public static void main(String[] args)
    {

        PApplet.main("Main3");
//        Line l = new Line<Double>(new Point<Double>(-10.0,0.0), new Point<Double>(11.0,0.0));
//        Line k = new Line (new Point<Double> (0.0,-10.), new Point<Double>(0.0,10.0));
//
//        Plane<Double> plane = new Plane<>();
//        plane.addGeometricObject(l);
//
////        System.out.println(plane.lineOfSight(k.getPointOne(), k.getPointTwo()));
////        System.out.println(l.intersects(k));
//
//
//        LOSStar<Double> pathFinder = new LOSStar<>(plane, new Point<Double> (0.0,-10.), new Point<Double>(0.0,10.0));
//        ArrayList<Point<Double>> path = pathFinder.generatePath();
//        System.out.println(path);
    }

    Line l = new Line (new Point(200.0,0.0), new Point(400.0,500.0));
    Line k = new Line (new Point(0.0,100.0), new Point(500.0,400.0));
    Line z = new Line(new Point(200, 0), new Point(0, 400));
    Plane<Double> plane = new Plane<>();

    public void settings()
    {
        size(500,500);
    }

    public void setup()
    {
        background(0);
        plane.addGeometricObject(l);
        plane.addGeometricObject(k);
    }

    public void draw()
    {
        background(0);
        if(keyPressed) {
           z.setPointOne(new Point(mouseX ,mouseY));
        } else if (mousePressed) {
            z.setPointTwo(new Point(mouseX ,mouseY));
        }

        stroke(0,255, 0);
        line(l);
        line(k);
        stroke(255);
        line(z);
        Point<Double> point = plane.intersectionPoint(z.getPointOne(),z.getPointTwo());
        fill(255);
        if(point!=null)
            ellipse(point.getX().floatValue() ,point.getY().floatValue(),10,10);
        text(point + " ", 100, 100);

    }

    public void line(Line l)
    {
        line(l.getPointOne().getX().floatValue(),
                l.getPointOne().getY().floatValue(),
                l.getPointTwo().getX().floatValue(), l.getPointTwo().getY().floatValue());
    }

}
