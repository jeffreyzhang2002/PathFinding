package actor;

import grid.Field;
import math.*;
import math.geometry.coordinates.Coordinate;
import math.geometry.coordinates.DiscreteCoordinate;
import pathFinders.PathFinder;
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Creates an instance of a Robot
 */
public class Robot extends Actor
{
    private PathFinder pathFinder;
    private boolean containCorners = true;
    private int index = 1;
    public RGB color;

    /**
     * Creates a Robot by giving its field, its position and its pathFinding engine
     * @param field
     * @param position
     * @param pathFinder
     */
    public Robot(Field field, DiscreteCoordinate position, PathFinder pathFinder)
    {
        super(field, position);
        this.pathFinder = pathFinder;
        color = new RGB(255, 0, 0);
    }

    /**
     * initializes path finding. This method should be run before generate path
     * @param end the goal coordinate
     */
    public void initPathFinder(DiscreteCoordinate end)
    {
        pathFinder.setStart(super.getPosition());
        pathFinder.setEnd(end);
    }

    /**
     * Generates the path the robot will follow. This method can not be run before initializing the path Finder.
     * @return true if their is a valid path. False otherwise
     */
    public boolean generatePath()
    { return pathFinder.generatePath(containCorners); }

    /**
     * This method returns a List of all possible next positions. Because this robot follows a path this will return the next position on the path
     * @return the next position on the path
     */
    public HashSet<DiscreteCoordinate> getNextCoordinates()
    {
        if(pathFinder.getPath() == null)
            return super.getNextCoordinates();
        else if(index >= pathFinder.getPath().size())
            index = 0;

        HashSet<DiscreteCoordinate> temp =  new HashSet<DiscreteCoordinate>();

        temp.add(pathFinder.getPath().get(index));

        return temp;
    }

    /**
     * This method returns the next position on the path. If the path is null this will return the current position
     * @param coordinateList a HashSet of possible coordinate this actor can go to
     * @return the next position
     */
    public DiscreteCoordinate chooseNextCoordinate(HashSet<DiscreteCoordinate> coordinateList)
    {
        if(coordinateList == null || coordinateList.isEmpty())
            return super.getPosition();

        DiscreteCoordinate current = (DiscreteCoordinate) coordinateList.toArray()[0];

        if(!super.getField().isEmptyPosition(current))
        {
           pathFinder.dynamicReplan(super.getPosition(), containCorners);
            return super.getPosition();
        }

        index++;
        return current;
    }

    /**
     * returns the path the robot will follow
     * @return the Path
     */
    public ArrayList<DiscreteCoordinate> getPath()
    { return pathFinder.getPath(); }

    /**
     * This is the settings for the robot to render
     * @param processing the Rendering Engine
     */
    public void renderSettings(PApplet processing)
    { processing.rectMode(PApplet.CENTER); }

    /**
     * This method is meant to draw the entire robot.
     * @param processing the Rendering Engine
     * @param position the position it will drawn at
     * @param width the Width
     * @param height the Height
     */
    public void draw(PApplet processing, Coordinate position, double width, double height)
    {
        processing.fill(color.getR(), color.getG(), color.getB());
        //processing.rect((float) (position.getX() + width/2) ,(float) (position.getY() + height/2),(float) width*18 ,(float) height*18);
        processing.rect((float) (position.getX() + width/2) ,(float) (position.getY() + height/2),(float) width,(float) height);
    }

    /**
     * colors the path the robot will follow
     * @param color
     */
    public void colorPath(RGB color)
    {
        if(pathFinder.getPath() != null)
            for(DiscreteCoordinate current : pathFinder.getPath())
                super.getField().setTileColor(current, color);
    }

    /**
     * gets the color of the robot
     * @return the color of the robot
     */
    public RGB getColor()
    { return color; }

}
