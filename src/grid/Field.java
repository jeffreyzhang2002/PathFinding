package grid;

import actor.Actor;
import math.Coordinate;
import math.DiscreteCoordinate;
import math.RGB;
import processing.core.PApplet;

import java.util.HashSet;

public class Field extends BoundedGrid<Actor>
{
    private BoundedGrid<RGB> tileDisplayerGrid;
    private PApplet processing;
    private DiscreteCoordinate origin;
    private double width, height, tileWidth, tileHeight;

    public Field(int rows, int cols)
    {
        super(rows, cols);
    }

    public void initRendering(PApplet processing, DiscreteCoordinate topLeftOrigin, double width, double height)
    {
        this.processing = processing;
        origin = topLeftOrigin;
        this.width = width;
        this.height = height;
        tileWidth = width/super.getRows();
        tileHeight = height/super.getCols();
        tileDisplayerGrid = new BoundedGrid<>(super.getRows(),super.getCols());
    }

    public void step()
    {
        for(Actor actor: super.getAllObjects())
            actor.step();
    }

    public Actor put(Actor actor)
    {
        return super.set(actor.getPosition(),actor);
    }

    public Actor set(DiscreteCoordinate coordinate, Actor actor)
    {
        actor.setPosition(coordinate);
        return super.set(coordinate,actor);
    }

    public boolean fixActorPositionMismatches(boolean gridCorrect)
    {
        boolean errors = false;
        for(int i=0; i<super.getRows(); i++)
            for(int j=0; j<super.getCols(); j++) {
                DiscreteCoordinate current = new DiscreteCoordinate(i, j);
                Actor actor = super.get(current);
                if (actor.getPosition() != current) {
                    errors = true;
                    if (gridCorrect)
                        actor.setPosition(current);
                    else {
                        super.remove(current);
                        super.set(actor.getPosition(), actor);
                    }
                }
            }
        return errors;
    }

    public void renderField() {
        processing.rectMode(processing.CORNER);
        for (int i = 0; i < super.getRows(); i++)
            for (int j = 0; j < super.getCols(); j++) {
                RGB color = tileDisplayerGrid.get(new DiscreteCoordinate(i,j));
                if (color == null)
                    processing.fill(255);
                else
                    processing.fill(color.getR(),color.getG(),color.getB());
                processing.rect((float) (origin.getX() + i * tileWidth), (float) (origin.getY() + j * tileHeight), (float) tileWidth, (float) tileHeight);
            }
    }

    public void renderActor()
    {
        for(Actor actor: super.getAllObjects())
            actor.render(processing, new Coordinate(origin.getX() + actor.getPosition().getX() * tileWidth ,
                    origin.getY() + actor.getPosition().getY() * tileHeight), tileWidth,tileHeight);
    }

    public void render()
    {
        renderField();
        renderActor();
    }

    public Grid<RGB> getTileDisplayerGrid()
    {
        return tileDisplayerGrid;
    }
}
