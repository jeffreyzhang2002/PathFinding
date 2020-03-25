package gui;

import actor.Robot;
import grid.Field;
import math.geometry.coordinates.DiscreteCoordinate;
import math.geometry.coordinates.Point;
import math.RGB;
import pathFinders.AStar;
import processing.core.PApplet;
import render.ObjectRenderings.FieldRendering;

public class GUI extends PApplet
{
    Field gameField;
    FieldRendering gameFieldRender;
    Robot robot;

    public static void main(String[] args)
    {
        PApplet.main("gui.GUI");
    }

    public void settings()
    {
         size(1000,700);
         gameField = new Field(144,144);
         gameFieldRender = new FieldRendering(gameField, new Point<>(0f,0f), height, height);
         robot = new Robot(gameField, new DiscreteCoordinate(0,0), new AStar(gameField));
    }

    public void setup()
    {
        background(0);
        robot.initPathFinder(new DiscreteCoordinate(gameField.getRows()-1, gameField.getCols()-1));
        robot.generatePath();
        robot.colorPath(new RGB(255,255,0));
        robot.placeSelfInGrid();
        gameFieldRender.setRenderTiles(false);
        gameFieldRender.render(this);
    }

    public void draw()
    {

    }

}
