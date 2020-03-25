package render.ObjectRenderings;

import actor.Actor;
import grid.Field;
import math.RGB;
import math.geometry.coordinates.Coordinate;
import math.geometry.coordinates.DiscreteCoordinate;
import math.geometry.coordinates.Point;
import processing.core.PApplet;
import render.Renderable;

public class FieldRendering extends Renderable
{
    private Field field;
    private float tileWidth, tileHeight;
    private boolean renderTiles = true;
    private boolean renderActors = true;

    public FieldRendering(Field field, Point<Float> origin, float width, float height)
    {
        super(origin, width, height);
        this.field = field;
        tileWidth = width / field.getRows();
        tileHeight = height / field.getCols();
    }

    public void renderSettings(PApplet processing)
    {
        processing.rectMode(PApplet.CORNER);
    }

    public void renderDraw(PApplet processing)
    {

    }

    private void renderBackground(PApplet processing)
    {
        processing.strokeWeight(3);
        processing.stroke(0);
        processing.fill(255);
        processing.rect(super.getOrigin().getX(), super.getOrigin().getY(), super.getWidth(), super.getHeight());
    }

    private void renderTiles(PApplet processing)
    {
        for (int i = 0; i < field.getRows(); i++)
        {
            for (int j = 0; j < field.getCols(); j++)
            {
                RGB color = field.getTileColorTracker().get(new DiscreteCoordinate(i, j));
                if (color == null)
                    processing.fill(255);
                else
                    processing.fill(color.getR(), color.getG(), color.getB());

                if(renderTiles)
                    processing.stroke(0);
                else
                    processing.stroke(color.getR(), color.getG(), color.getB());

                processing.rect((float) (super.getOrigin().getX() + i * tileWidth),
                        (float) (super.getOrigin().getY() + j * tileHeight), (float) tileWidth, (float) tileHeight);
            }
        }
    }

    private void renderActors(PApplet processing)
    {
        for(Actor actor: field.getAllObjects())
            actor.render(processing, new Coordinate(super.getOrigin().getX() + actor.getPosition().getX() * tileWidth ,
                    super.getOrigin().getY() + actor.getPosition().getY() * tileHeight), tileWidth,tileHeight);
    }


}
