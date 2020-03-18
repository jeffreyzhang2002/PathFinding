package actor;

import grid.Field;
import math.Coordinate;
import math.DiscreteCoordinate;
import processing.core.PApplet;
import java.util.HashSet;

/**
 * This abstract class is a meant for for creating Actors that can be placed on top of a field.
 * The following methods must be overridden getNextCoordinates() and chooseNextCoordinates()
 * The following methods may be overridden renderSettings(), draw(), step() and droppedActor()
 */
public class Actor
{
    private Field field;
    private DiscreteCoordinate position;

    /**
     * Creates an abstract actor using a given field and the position it is current at.
     * The method will check if the given position is on the Grid
     * @param grid The Grid the actor will be placed inside of
     * @param position The position the actor will be at
     */
    public Actor(Field grid, DiscreteCoordinate position)
    {
        this.field = grid;
        if(!field.isValid(position))
            throw new IllegalArgumentException("given position is not on field");
        this.position = position;
    }

    /**
     * Sets the position the actor will be at
     * @param position the position the actor will be moved to
     */
    public final void setPosition(DiscreteCoordinate position)
    {
        if(!field.isValid(position))
            throw new IllegalArgumentException("given position is not on field");
        this.position = position;
    }

    /**
     * gets the Position the actor currently is
     * @return the Current position
     */
    public final DiscreteCoordinate getPosition()
    { return position; }

    /**
     * gets the field the actor is on
     * @return the Field the actor is on
     */
    public final Field getField()
    { return field; }

    /**
     * Render the current actor at the given position
     * @param processing the Processing engine
     * @param position the Position it will be rendered at
     * @param width the width the character render will be
     * @param height the Height the character render will be
     */
    public final void render(PApplet processing, Coordinate position, double width, double height)
    {
        renderSettings(processing);
        draw(processing,position,width,height);
    }

    /**
     * Render the current actor at the actual position
     * @param processing the Processing engine
     * @param width the width the character render will be
     * @param height the Height the character render will be
     */
    public final void nativeRender(PApplet processing, float width, float height)
    {
        renderSettings(processing);
        draw(processing,position.toCoordinate(),width,height);
    }

    /**
     * This method put the actor on the grid using the actors current position.
     */
    public final void placeSelfInGrid()
    { field.put(this); }

    /**
     * This method removes the current actor from the Grid
     */
    public final void removeSelfFromGrid()
    { field.remove(position); }

    /**
     * Moves the actor to the next designated position
     * @return
     */
    public DiscreteCoordinate step()
    {
        DiscreteCoordinate next = chooseNextCoordinate(getNextCoordinates());
        Actor leftBehind = droppedActor();

        if (field.isValid(next))
        {
            field.remove(position);
            field.set(next, this);
            position = next;
        }

        if(leftBehind != null)
            field.put(leftBehind);

        return this.position;
    }

    /**
     * gets a set of possible next coordinates the actor can move to. This method should be overridden.
     * Currently this return a Set containing the current position
     * @return a HashSet
     */
    public HashSet<DiscreteCoordinate> getNextCoordinates()
    {
        HashSet<DiscreteCoordinate> coordinateList = new HashSet<>();
        coordinateList.add(position);
        return coordinateList;
    }

    /**
     * chooses the next coordinate this actor will go to. This method should be overridden.
     * Currently this returns the current position
     * @param coordinateList a HashSet of possible coordinate this actor can go to
     * @return the next posistion
     */
    public DiscreteCoordinate chooseNextCoordinate(HashSet<DiscreteCoordinate> coordinateList)
    { return position; }

    /**
     * This method returns what will be left behind when an actor moves. This method can be overridden. Currently it
     * drops nothing.
     * @return
     */
    public Actor droppedActor()
    { return null; }

    /**
     * The rendering settings for the current actor. This method can be overridden
     * @param processing the Rendering Engine
     */
    public void renderSettings(PApplet processing)
    {
        processing.ellipseMode(processing.CORNER);
        processing.rectMode(processing.CORNER);
    }

    /**
     * What is drawn when render is called. This method should be overridden
     * @param processing the Rendering Engine
     * @param position the position it will drawn at
     * @param width the Width
     * @param height the Height
     */
    public void draw(PApplet processing, Coordinate position, double width, double height)
    {
        processing.fill(0);
        processing.rect((float)position.getX(),(float)position.getY(), (float) width, (float) height);
    }

    public String toString()
    { return position.toString(); }
}
