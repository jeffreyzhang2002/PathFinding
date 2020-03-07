import actor.Barrier;
import actor.DRobot;
import grid.Field;
import math.DiscreteCoordinate;
import pathFinders.DStarLite;
import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet
{
    public static void main(String[] args)
    {
        PApplet.main("Main");
    }

    Field field = new Field(20,20);
    DRobot robot =new DRobot(field, new DiscreteCoordinate(0,0), new DStarLite(field));
    ArrayList<Barrier> barriers = new ArrayList<>();
    Barrier ABarrier;


    public void settings()
    {
        size(600,600);
        field.initRendering(this,new DiscreteCoordinate(0,0),width,height);
        robot.initPathFinder(new DiscreteCoordinate(19,19));
        robot.placeSelfInGrid();
        for(int y=0; y<60; y++)
        {
            barriers.add(new Barrier(field,new DiscreteCoordinate((int)random(0,field.getRows()-1),(int)random(0,field.getRows()-1))));
            barriers.get(y).placeSelfInGrid();
        }
        System.out.println("settings completed");
    }

    public void setup()
    {
        robot.generatePath();
        field.render();
        System.out.println("initialization completed. Path: " + robot.getPath());
    }

    public void draw()
    {

    }

    public void mousePressed()
    {
        robot.step();
        field.render();
    }

    public void keyPressed()
    {
        ABarrier = new Barrier(field,(DiscreteCoordinate) robot.getNextCoordinates().toArray()[0]);
        ABarrier.placeSelfInGrid();
        field.render();
        field.renderActor();
    }
}
