
import actor.Barrier;
import actor.DRobot;
import grid.Field;
import math.geometry.coordinates.DiscreteCoordinate;
import pathFinders.DStarLite;
import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet
{
    Field field = new Field(50,50);
    DRobot robot =new DRobot(field, new DiscreteCoordinate(0,0), new DStarLite(field));
    ArrayList<Barrier> barriers = new ArrayList<>();

    public static void main(String[] args)
    { PApplet.main("Main"); }

    public void settings()
    {
        size(600,600);
        field.initRendering(this, new DiscreteCoordinate(0,0), width, height);
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
        field.render();
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
        Barrier appearing = new Barrier(field,(DiscreteCoordinate) robot.getNextCoordinates().toArray()[0]);
        appearing.placeSelfInGrid();
        barriers.add(appearing);

        field.render();
        field.renderActor();
    }
}
