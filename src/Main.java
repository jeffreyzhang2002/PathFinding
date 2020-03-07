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

    Field field = new Field(9,10);
    DRobot robot =new DRobot(field, new DiscreteCoordinate(0,0), new DStarLite(field));
    ArrayList<Barrier> barriers = new ArrayList<>();
    Barrier ABarrier = new Barrier(field,new DiscreteCoordinate(5,field.getCols()-2));


    public void settings()
    {
        size(600,600);
        field.initRendering(this,new DiscreteCoordinate(0,0),width,height);
        robot.initPathFinder(new DiscreteCoordinate(8,9));
        robot.placeSelfInGrid();
        for(int y=0; y<field.getCols()-2; y++)
        {
            barriers.add(new Barrier(field,new DiscreteCoordinate(5,y)));
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
        //barriers.get(0).removeSelfFromGrid();
        ABarrier.placeSelfInGrid();
        field.render();
        field.renderActor();
    }
}
