package gui;

import actor.Barrier;
import grid.Field;
import gui.colorChooser.ColorChooser;
import gui.displays.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame
{
    public MainDisplay mainContent;
    public FieldDisplay fieldContent;
    public ConsoleDisplay consoleContent;
    public ButtonDisplay buttonContent;
    public ColorButtonDisplay colorContent;
    public MenuDisplay menuBar;
    public StatusDisplay statusBar;
    public JLabel mousePosLabel;

    private Field field = new Field(10, 10);

    public GUI() {
        super.setLayout(new BorderLayout());
        super.addMouseMotionListener(new MainMouseHandler());

        menuBar = new MenuDisplay(this);
        statusBar = new StatusDisplay(this);

        mousePosLabel = new JLabel("Mouse: ");
        statusBar.addLabel(mousePosLabel);

        fieldContent = new FieldDisplay(field, this);
        consoleContent = new ConsoleDisplay(this);
        mainContent = new MainDisplay(new Dimension(1000, 700), this, fieldContent, consoleContent);
        buttonContent = new ButtonDisplay(this);



        super.getContentPane().add(mainContent, BorderLayout.CENTER);
        super.getContentPane().add(menuBar, BorderLayout.NORTH);
        super.getContentPane().add(buttonContent, BorderLayout.EAST);
        super.getContentPane().add(statusBar, BorderLayout.SOUTH);
    }

    private class MainMouseHandler implements MouseMotionListener
    {
        public void mouseMoved(MouseEvent e)
        { mousePosLabel.setText(String.format("Mouse: ( %d, %d )", e.getX(), e.getY())); }

        public void mouseDragged(MouseEvent e)
        { }
    }
}
