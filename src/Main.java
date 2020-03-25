
import actor.Barrier;
import actor.DRobot;
import grid.Field;
import math.geometry.coordinates.DiscreteCoordinate;
import math.geometry.coordinates.Point;
import pathFinders.DStarLite;
import processing.core.PApplet;
import render.ObjectRenderings.FieldRendering;

import java.util.ArrayList;

public class Main extends PApplet
{
    Field field = new Field(144,144);
    FieldRendering fieldrender = new FieldRendering(field, new Point<Float>(0f,0f), 800, 800);
    DRobot robot = new DRobot(field, new DiscreteCoordinate(0,0), new DStarLite(field));
    ArrayList<Barrier> barriers = new ArrayList<>();

    public static void main(String[] args)
    { PApplet.main("Main"); }

    public void settings()
    {
        size(800,800);
        //field.initRendering(this, new DiscreteCoordinate(0,0), width, height);
        robot.initPathFinder(new DiscreteCoordinate(field.getRows() - 1,field.getCols() - 1));
        robot.placeSelfInGrid();

        for(int y=0; y<100; y++)
        {
            barriers.add(new Barrier(field,new DiscreteCoordinate((int)random(0,field.getRows()-1),(int)random(0,field.getRows()-1))));
            barriers.get(y).placeSelfInGrid();
        }

        robot.generatePath();
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
