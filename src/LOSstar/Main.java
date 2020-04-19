package LOSstar;

import processing.core.PApplet;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Main extends PApplet
{
    Plane p = new Plane();
    LOSStar pathfinder;
    ArrayList<Point2D.Double> path;
    Point2D.Double startp;
    Point2D.Double endp;
    boolean canRun = false;


    public static void main(String[] args)
    {
        PApplet.main("LOSstar.Main");
    }

    public void settings()
    {

        p.addBarrier(new Line2D.Double(new Point2D.Double(100, 100), new Point2D.Double(400, 400)));
        p.addBarrier(new Line2D.Double(new Point2D.Double(100, 400), new Point2D.Double(400, 100)));
        p.addBarrier(new Line2D.Double(new Point2D.Double(0,250), new Point2D.Double(200,250)));
        pathfinder = new LOSStar(p, new Point2D.Double(250,100), new Point2D.Double(250,400));
        path = new ArrayList<>();
        size(500,500);
    }

    public void setup()
    {

    }

    public void draw()
    {
        background(0);
        for(Line2D.Double l : p.getBarriers())
            line(l);

        for(Point2D.Double p : p.getKeyPoints(10))
            point(p);

        for(int i=1; i< path.size(); i++)
            line(path.get(i-1),path.get(i));
    }

    public void mousePressed()
    {
        startp = new Point2D.Double(mouseX,mouseY);
        System.out.println(startp);
    }

    public void mouseReleased()
    {
        canRun = true;
        endp = new Point2D.Double(mouseX,mouseY);
        System.out.println(endp);
    }

    public void keyPressed() {
        if (key =='a' && canRun == true) {
            p.addBarrier(new Line2D.Double(startp, endp));
            canRun = false;
        }
        else if(key == 'r') {
            path = pathfinder.generatePath(10);
            System.out.println(path);
        }
    }


    public void line(Line2D.Double l)
    {
        stroke(0, 255, 0);
        line((float)l.getX1(), (float)l.getY1(), (float)l.getX2(), (float)l.getY2());
    }

    public void line(Point2D.Double p1, Point2D.Double p2)
    {
        stroke(0, 255, 0);
        line((float)p1.getX(), (float)p1.getY(), (float)p2.getX(), (float)p2.getY());
    }

    public void point(Point2D.Double l)
    {
        stroke(255);
        fill(255);
        ellipse((float)l.getX(), (float)l.getY(), 2, 2);
    }
}
