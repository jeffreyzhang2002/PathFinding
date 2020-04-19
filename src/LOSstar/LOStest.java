package LOSstar;

import processing.core.PApplet;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class LOStest extends PApplet
{
    public static void main(String[] args)
    {
        PApplet.main("LOSstar.LOStest");
    }

    Plane p = new Plane();

    public void settings()
    {
        size(500,500);
    }

    public void setup()
    {
        p.addBarrier(new Line2D.Double(new Point2D.Double(200, 400), new Point2D.Double(400, 400)));
        p.addBarrier(new Line2D.Double(new Point2D.Double(300, 400), new Point2D.Double(200, 100)));
    }

    public void draw()
    {
        background(0);
        for(Line2D.Double l : p.getBarriers())
            line(l);

        for(Point2D.Double p : p.getKeyPoints(10))
            point(p);


        Line2D.Double LOS = new Line2D.Double(new Point2D.Double(mouseX,mouseY), new Point2D.Double(200,400));
        line(LOS);
        if(mousePressed)
            System.out.println(p.LineOfSight(LOS.getP1(), LOS.getP2()));

        if( keyPressed)
            System.out.println(p.getBarriers().get(0).getP1() + " " + p.getBarriers().get(0).getP2());


    }

    public void line(Line2D.Double l)
    {
        stroke(0, 255, 0);
        line((float)l.getX1(), (float)l.getY1(), (float)l.getX2(), (float)l.getY2());
    }

    public void point(Point2D.Double l)
    {
        stroke(255);
        fill(255);
        ellipse((float)l.getX(), (float)l.getY(), 2, 2);
    }

}
