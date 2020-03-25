package actor;

import grid.Field;
import math.geometry.coordinates.DiscreteCoordinate;
import processing.core.PApplet;

/**
 * Creates a Barrier Actor. A Barrier is unable to move and remains at the starting position the entire time
 */
public class Barrier extends Actor
{
    public Barrier(Field grid, DiscreteCoordinate position)
    { super(grid, position); }

    public void renderSettings(PApplet processing)
    {
        processing.rectMode(PApplet.CORNER);
    }

    /**
     * Draws a Barrier Object
     * @param processing the Rendering Engine
     */
    public void renderDraw(PApplet processing)
    {
        processing.fill(0, 0, 0);
        processing.rect(super.getOrigin().getX().floatValue(), super.getOrigin().getY().floatValue(),
                super.getWidth(),super.getHeight());
    }

    /**
     * This method returns the current position because a Barrier is unable to move
     * @return the current position
     */
    public DiscreteCoordinate step()
    { return super.getPosition(); }
}
