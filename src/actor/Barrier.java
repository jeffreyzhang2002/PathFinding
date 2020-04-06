package actor;

import grid.Field;
import processing.core.PApplet;
import java.awt.*;

/**
 * Creates a Barrier Actor. A Barrier is unable to move and remains at the starting position the entire time
 */
public class Barrier extends Actor
{
    public Barrier(Field grid, Point position)
    { super(grid, position); }

    /**
     * This method returns the current position because a Barrier is unable to move
     * @return the current position
     */
    public Point step()
    { return super.getPosition(); }
}
