package gui;

import actor.Actor;
import actor.Barrier;
import grid.Field;
import math.geometry.coordinates.DiscreteCoordinate;
import processing.core.PApplet;

public class drawGUI extends PApplet
{
    Field field = new Field(144,144);
    DiscreteCoordinate closestPoint;

    public static void main(String[] args)
    {
        PApplet.main("gui.drawGUI");
    }

    public void settings()
    {
        size(600,600);
    }

    public void setup()
    {
        field.initRendering(this, new DiscreteCoordinate(0,0), width, height);
        background(0);
        field.render();
    }

    public void draw()
    {
    }

    public void mousePressed()
    {
        closestPoint = closeSpot();
        if(closestPoint != null)
        {
            if(field.get(closestPoint) == null) {
                Actor a = new Barrier(field, closestPoint);
                a.placeSelfInGrid();
            } else {
                field.remove(closestPoint);
            }
        }
        field.render();
    }

    public void keyPressed()
    {
        if(key == 'd')
            field.clear();
        else if(key == 'c')
        {
            closestPoint = closeSpot();
            System.out.println(closestPoint);
        }
        else if(closestPoint != null) {
            if (keyCode == UP)
                closestPoint = new DiscreteCoordinate(closestPoint.getX(), closestPoint.getY() - 1);
            else if (keyCode == DOWN)
                closestPoint = new DiscreteCoordinate(closestPoint.getX(), closestPoint.getY() + 1);
            else if (keyCode == RIGHT)
                closestPoint = new DiscreteCoordinate(closestPoint.getX() + 1, closestPoint.getY());
            else if (keyCode == LEFT)
                closestPoint = new DiscreteCoordinate(closestPoint.getX() - 1, closestPoint.getY());
            if(field.get(closestPoint) == null)
            {
                Actor a = new Barrier(field, closestPoint);
                a.placeSelfInGrid();
            } else {
                field.remove(closestPoint);
            }
        }
        field.render();
    }

    public DiscreteCoordinate closeSpot()
    {
        if(mouseX >= field.getOrigin().getX() && mouseX < (field.getOrigin().getX() + field.getWidth())
                && mouseY >= field.getOrigin().getY() && mouseY < field.getOrigin().getY() + field.getHeight())
        {
            for(int i=0; i<field.getRows(); i++)
            {
                for(int j=0; j<field.getCols(); j++)
                {
                    double xPoint = field.getOrigin().getX() + (field.getTileWidth() * i);
                    double yPoint = field.getOrigin().getY() + (field.getTileHeight()* j);
                    if(mouseX > xPoint && mouseX < xPoint + field.getTileWidth() && mouseY > yPoint && mouseY < yPoint + field.getTileHeight())
                        return new DiscreteCoordinate(i,j);
                }
            }
        }
        return null;
    }
}
