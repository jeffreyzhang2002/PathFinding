package actor;

import grid.BoundedGrid;
import grid.Field;
import grid.Grid;
import math.Coordinate;
import math.DiscreteCoordinate;
import processing.core.PApplet;


import java.nio.file.Path;
import java.util.HashSet;

public abstract class Actor {
    private Field field;
    private DiscreteCoordinate position;

    public Actor(Field grid, DiscreteCoordinate position) {
        this.field = grid;
        this.position = position;
    }

    public final void setPosition(DiscreteCoordinate position)
    {
        field.remove(position);
        this.position = position;
        placeSelfInGrid();
    }

    public final DiscreteCoordinate getPosition() {
        return position;
    }

    public final Field getField()
    {
        return field;
    }

    public final void placeSelfInGrid()
    {
        field.put(this);
    }

    public final void removeSelfFromGrid()
    {
        field.remove(position);
    }

    public DiscreteCoordinate step() {
        DiscreteCoordinate next = chooseNextCoordinate(getNextCoordinates());
        Actor leftBehind = droppedActor();
        if (field.isValid(next)) {
            field.remove(position);
            field.set(next, this);
            position = next;
        }

        if(leftBehind != null)
            field.put(leftBehind);

        return this.position;
    }

    public abstract HashSet<DiscreteCoordinate> getNextCoordinates();

    public abstract DiscreteCoordinate chooseNextCoordinate(HashSet<DiscreteCoordinate> coordinateList);

    public Actor droppedActor()
    {
        return null;
    }

    public final void render(PApplet processing, Coordinate position, double width, double height) {
      renderSettings(processing);
      draw(processing,position,width,height);
    }

    public final void nativeRender(PApplet processing, float width, float height)
    {
        renderSettings(processing);
        draw(processing,position.toCoordinate(),width,height);
    }

    public void renderSettings(PApplet processing) {
        processing.ellipseMode(processing.CORNER);
        processing.rectMode(processing.CORNER);
    }

    public void draw(PApplet processing, Coordinate position, double width, double height)
    {
        processing.fill(0);
        processing.rect((float)position.getX(),(float)position.getY(), (float) width, (float) height);
    }
}
