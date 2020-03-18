
import actor.Barrier;
import actor.Robot;
import grid.Field;
import math.*;
import pathFinders.*;
import processing.core.PApplet;

import java.util.ArrayList;

public class Main2 extends PApplet
{
    Field field = new Field(50,50);
    Robot robot = new Robot(field, new DiscreteCoordinate(0,0), new AStar(field));
    ArrayList<Barrier> barriers = new ArrayList<>();

    public static void main(String[] args)
    { PApplet.main("Main2"); }

    public void settings()
    {
        size(600,600);
        field.initRendering(this, new DiscreteCoordinate(0,0), width, height);
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
        field.render();
    }

    public void draw()
    {

    }

    public void mousePressed()
    {
        robot.step();
        robot.colorPath(new RGB(0,0,255));
        field.render();
    }

    public void keyPressed()
    {
        Barrier appearing = new Barrier(field,(DiscreteCoordinate) robot.getNextCoordinates().toArray()[0]);
        appearing.placeSelfInGrid();
        barriers.add(appearing);

        field.render();
        field.renderActor();
    }
}
