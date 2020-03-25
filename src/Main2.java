
import actor.Barrier;
import actor.Robot;
import grid.Field;
import math.*;
import math.geometry.coordinates.DiscreteCoordinate;
import math.geometry.coordinates.Point;
import pathFinders.*;
import processing.core.PApplet;
import render.ObjectRenderings.FieldRendering;

import java.util.ArrayList;

public class Main2 extends PApplet
{
    Field field = new Field(144,144);
    FieldRendering fieldrender = new FieldRendering(field, new Point<Float>(0f, 0f), 800,800);
    Robot robot = new Robot(field, new DiscreteCoordinate(0,0), new DStarLite(field));
    ArrayList<Barrier> barriers = new ArrayList<>();

    public static void main(String[] args)
    { PApplet.main("Main2"); }

    public void settings()
    {
        size(800,800);
        //field.initRendering(this, new DiscreteCoordinate(0,0), width, height);
        robot.initPathFinder(new DiscreteCoordinate(field.getRows() - 1,field.getCols() - 1));
        robot.placeSelfInGrid();

        for(int b = 0; b < 100; b++)
        {
            barriers.add(new Barrier(field,new DiscreteCoordinate((int)random(0,field.getRows()-1),(int)random(0,field.getRows()-1))));
            barriers.get(b).placeSelfInGrid();
        }

        robot.generatePath();
        robot.colorPath(new RGB(0,255,0));
    }

    public void setup()
    {
        fieldrender.render(this);
    }

    public void draw()
    {

    }

    public void mousePressed()
    {
        robot.step();
        robot.colorPath(new RGB(0,0,255));
        fieldrender.render(this);
    }

    public void keyPressed()
    {
        Barrier appearing = new Barrier(field,(DiscreteCoordinate) robot.getNextCoordinates().toArray()[0]);
        appearing.placeSelfInGrid();
        barriers.add(appearing);

        fieldrender.render(this);
    }
}
