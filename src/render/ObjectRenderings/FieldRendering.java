package render.ObjectRenderings;

import actor.Actor;
import grid.Field;
import math.RGB;
import math.geometry.coordinates.DiscreteCoordinate;
import math.geometry.coordinates.Point;
import processing.core.PApplet;
import render.Renderable;

/**
 * Renders the Field
 */
public class FieldRendering extends Renderable
{
    private Field field;
    private float tileWidth, tileHeight;
    private boolean renderTiles = true;
    private boolean renderActors = true;

    /**
     * Allows the Field to be rendered
     * @param field the Field that will be rendered
     * @param origin the Origin it will be. The Origin should be the Top Left Corner for a Field
     * @param width the Width that it will be displayed
     * @param height the Height that it will be displayed
     */
    public FieldRendering(Field field, Point<Float> origin, float width, float height)
    {
        super(origin, width, height);
        this.field = field;
        tileWidth = width / field.getRows();
        tileHeight = height / field.getCols();
    }

    /**
     * Settings for rendering a Field
     * @param processing the rendering Engine
     */
    public void renderSettings(PApplet processing)
    { processing.rectMode(PApplet.CORNER); }

    /**
     * Every that is draw when render Field is called
     * @param processing
     */
    public void renderDraw(PApplet processing)
    {
        renderTiles(processing);
        if(renderActors)
            renderActors(processing);
    }

    /**
     * Render the Background for the Field
     * @param processing
     */
    public void renderBackground(PApplet processing)
    {
        processing.strokeWeight(1);
        processing.stroke(0);
        processing.fill(255);
        processing.rect(super.getOrigin().getX(), super.getOrigin().getY(), super.getWidth(), super.getHeight());
    }

    /**
     * Render the Tiles for the field
     * @param processing
     */
    private void renderTiles(PApplet processing)
    {
        for (int i = 0; i < field.getRows(); i++)
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
                    if(color == null)
                        continue;
                    else
                        processing.stroke(color.getR(), color.getG(), color.getB());

                processing.rect((super.getOrigin().getX() + i * tileWidth),
                        (super.getOrigin().getY() + j * tileHeight),  tileWidth, tileHeight);
            }
    }

    /**
     * Renders the Actors on the field
     * @param processing
     */
    public void renderActors(PApplet processing)
    {
        for(Actor actor: field.getAllObjects())
        {
            actor.setOrigin(new Point<>((super.getOrigin().getX() + actor.getPosition().getX() * tileWidth), (super.getOrigin().getY() + actor.getPosition().getY() * tileHeight)));
            actor.setWidth(tileWidth);
            actor.setHeight(tileHeight);
            actor.render(processing);
        }
    }

    /**
     * Determines if the Tiles are rendered or not
     * @param renderTiles
     */
    public void setRenderTiles(boolean renderTiles)
    { this.renderTiles = renderTiles; }

    /**
     * Determines if the actors are rendered or not
     * @param renderActors
     */
    public void setRenderActors(boolean renderActors)
    { this.renderActors = renderActors; }
}
