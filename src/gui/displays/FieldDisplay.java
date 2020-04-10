package gui.displays;

import actor.Actor;
import grid.Field;
import gui.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;

public class FieldDisplay extends JPanel
{
    private GUI gui;
    private Field field;
    private int tileWidth;
    private JLabel closestGridSpot;

    public FieldDisplay(Field field, GUI gui)
    {
        this.gui = gui;
        if(field.getRows() != field.getCols())
            throw new IllegalArgumentException("must be square field");
        this.field = field;
        updateTiles();

        FieldMouseHandler handler = new FieldMouseHandler();

        super.addMouseMotionListener(handler);
        super.addMouseListener(handler);

        closestGridSpot = new JLabel(" ");
        gui.statusBar.addLabel(closestGridSpot);
    }

    public void paint(Graphics g)
    {
        updateTiles();
        super.paint(g);
        paintTiles(g);
        paintActors(g);
    }

    public void paintTiles(Graphics g) {
        for (int i = 0; i < field.getRows(); i++) {
            for (int j = 0; j < field.getCols(); j++) {
                g.setColor(Color.black);

                g.drawRect((i * tileWidth),
                        (j * tileWidth), tileWidth, tileWidth);

                Color color = field.getTileColorTracker().get(new Point(i, j));
                if (color == null)
                    g.setColor(Color.white);
                else
                    g.setColor(color);

                g.fillRect((i * tileWidth + 1),
                        (j * tileWidth + 1), tileWidth - 1, tileWidth - 1);

            }
        }
    }

    public void paintActors(Graphics g)
    {
        HashSet<Actor> actorList = field.getAllObjects();
        for(Actor a: actorList)
            a.paint(g, new Point(a.getPosition().x * tileWidth + 1, a.getPosition().y * tileWidth + 1), tileWidth);
    }

    private void updateTiles()
    { tileWidth = Math.min(super.getWidth(),super.getHeight()) / field.getRows(); }

    public Point closeTo(Point pos)
    {
        Point p = new Point(pos.x/tileWidth, pos.y/tileWidth);
        if(p.x >= field.getRows() || p.y >= field.getCols())
            p = null;
        return p;
    }

    public void update()
    {
        super.updateUI();
    }

    private class FieldMouseHandler implements MouseMotionListener, MouseListener
    {
        Point closestPoint = null;
        boolean mouseInside;
        public void mouseMoved(MouseEvent e)
        {
            gui.mousePosLabel.setText(String.format("Mouse: ( %d, %d ) ", e.getX(), e.getY()));

            if(mouseInside) {
                closestPoint = closeTo(e.getPoint());
                if (closestPoint != null) {
                    closestGridSpot.setText(String.format("Coordinate: ( %d, %d ) ", closestPoint.x, closestPoint.y));
                }
            }
            else
                closestGridSpot.setText("Coordinate: null ");
        }

        public void mouseDragged(MouseEvent e)
        { }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(closestPoint != null && gui.colorContent.isVisible())
            {
                field.setTileColor(closestPoint, gui.colorContent.getPickedColor());
                update();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            mouseInside = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            mouseInside = true;
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }
}
