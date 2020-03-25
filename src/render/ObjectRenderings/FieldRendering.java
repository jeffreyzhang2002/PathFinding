package render.ObjectRenderings;

import actor.Actor;
import grid.Field;
import math.RGB;
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

    public void renderTransformation(PApplet processing)
    {

    }

    public void renderDraw(PApplet processing)
    {
        renderBackground(processing);
        renderTiles(processing);
        if(renderActors)
            renderActors(processing);
    }

    public void renderBackground(PApplet processing)
    {
        processing.strokeWeight(1);
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
                {
                    processing.stroke(0);
                }
                else
                {
                    if(color == null)
                        processing.stroke(255);
                    else
                        processing.stroke(color.getR(), color.getG(), color.getB());
                }


                processing.rect((super.getOrigin().getX() + i * tileWidth),
                        (super.getOrigin().getY() + j * tileHeight),  tileWidth, tileHeight);
            }
        }
    }

    public void renderActors(PApplet processing)
    {
        for(Actor actor: field.getAllObjects()) {
            actor.setOrigin(new Point<>((super.getOrigin().getX() + actor.getPosition().getX() * tileWidth),
                    (super.getOrigin().getY() + actor.getPosition().getY() * tileHeight)));
            actor.setWidth(tileWidth);
            actor.setHeight(tileHeight);
            actor.render(processing);
        }
    }

    public void setRenderTiles(boolean renderTiles)
    { this.renderTiles = renderTiles; }

    public void setRenderActors(boolean renderActors)
    { this.renderActors = renderActors; }
}
