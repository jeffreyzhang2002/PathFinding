package gui;

import grid.Field;
import math.RGB;
import math.geometry.coordinates.DiscreteCoordinate;

import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

public class DrawField extends JPanel
{
    private static final long serialVersionUID = 1L;

    private int fieldWidth, consoleWidth, tileWidth;
    private Field field;
    private Point origin;

    private boolean renderTiles = true;
    private boolean renderActors = true;

    public DrawField(Field field, Point origin, int fieldWidth, int consoleWidth)
    {
        super.setSize(new Dimension(fieldWidth + consoleWidth, fieldWidth));
        if(field.getRows() != field.getCols())
            throw new IllegalArgumentException("must be square field");
        this.field = field;
        this.origin = origin;
        this.fieldWidth = fieldWidth;
        this.tileWidth = fieldWidth / field.getRows();
        this.consoleWidth = consoleWidth;
    }

    public void paint(Graphics g)
    {
        background(g);
        actors(g);



    }

    public void actors(Graphics g)
    {
        for (int i = 0; i < field.getRows(); i++)
            for (int j = 0; j < field.getCols(); j++)
            {
                g.setColor(Color.black);

                g.drawRect((origin.x + i * tileWidth),
                        (origin.y + j * tileWidth),  tileWidth, tileWidth);

                RGB color = field.getTileColorTracker().get(new DiscreteCoordinate(i, j));
                if (color == null)
                    g.setColor(Color.white);
                else
                    g.setColor(new Color(color.getR(), color.getG(), color.getB()));

                g.fillRect((origin.x + i * tileWidth + 1),
                        (origin.y + j * tileWidth + 1),  tileWidth - 1, tileWidth -1 );
            }
    }

    public void background(Graphics g)
    {
        g.setColor(Color.black);
        g.drawRect(origin.x, origin.y, fieldWidth, fieldWidth);

        g.setColor(Color.white);
        g.fillRect(origin.x, origin.y, fieldWidth, fieldWidth);

        g.setColor(Color.black);
        g.drawRect(fieldWidth + origin.x, origin.y, consoleWidth, fieldWidth);
        g.fillRect(fieldWidth + origin.x, origin.y, consoleWidth, fieldWidth);
    }
}
