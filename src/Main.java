import actor.Barrier;
import actor.Robot;
import actor.SimpleMover;
import grid.Field;
import math.DiscreteCoordinate;
import pathFinders.AStar;
import pathFinders.DStarLite;
import processing.core.PApplet;

import java.util.ArrayList;

public class Main extends PApplet
{
    public static void main(String[] args)
    {
        PApplet.main("Main");
    }

    Field field = new Field(15,15);
    Robot robot =new Robot(field, new DiscreteCoordinate(0,0), new DStarLite(field));
    ArrayList<Barrier> obstacles = new ArrayList<>();
    Barrier appearingBarrier;

    public void settings()
    {
        size(600,600);
        field.initRendering(this,new DiscreteCoordinate(0,0),width,height);
        robot.initPathFinder(new DiscreteCoordinate(14,14));
        robot.placeSelfInGrid();
//        for(int i=0; i<25; i++)
//        {
//           DiscreteCoordinate c;
//            do{
//                c = new DiscreteCoordinate((int)random(0, field.getRows()-1), (int)random(0,(int)field.getCols()-1));
//            }while(!field.isEmptyPosition(c));
//            Barrier b = new Barrier(field,c);
//            b.placeSelfInGrid();
//            obstacles.add(b);
//        }
//        appearingBarrier = new Barrier(field, new DiscreteCoordinate(5,5));
        System.out.println("settings completed");
    }

    public void setup()
    {
        robot.generatePath();
        robot.colorPath();
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
        appearingBarrier.placeSelfInGrid();
        field.renderActor();
    }
}
