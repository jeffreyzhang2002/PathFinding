package gui;

import actor.Robot;
import grid.Field;
import math.RGB;
import math.geometry.coordinates.DiscreteCoordinate;
import pathFinders.DStarLite;
import processing.core.PApplet;

public class GUI extends PApplet
{
    Field field = new Field(144, 144);
    Robot robot = new Robot(field, new DiscreteCoordinate(0,0), new DStarLite(field));

    public static void main(String[] args)
    { PApplet.main("gui.GUI"); }

    public void settings()
    {
        fullScreen();
        field.initRendering(this, new DiscreteCoordinate(0,0), displayHeight, displayHeight);
        robot.initPathFinder(new DiscreteCoordinate(field.getRows()-1, field.getCols()-1));
        robot.placeSelfInGrid();
        robot.generatePath();
        robot.colorPath(new RGB(255, 255, 0));
    }

    public void setup()
    {
        background(0);
        field.renderBackgroundTile();
        field.renderActor();
    }

    public void draw()
    {

    }

    public void mousePressed()
    {
        field.step();
        field.renderBackgroundTile();
        field.renderActor();
    }

    public void keyPressed()
    {

    }

}
