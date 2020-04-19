package gui;

import gui.colorChooser.ColorChooser;
import gui.statusBar.StatusBar;

import javax.swing.*;
import java.awt.*;

public class GraphicalUserInterface extends JFrame
{
    private StatusBar statusBar;
    private ColorChooser colorChooser;

    public GraphicalUserInterface()
    {
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        statusBar = new StatusBar();
        super.getContentPane().add(BorderLayout.SOUTH, statusBar);

        initColorChooser();
    }

    public void initColorChooser()
    {
        colorChooser = new ColorChooser();
        colorChooser.setVisible(true);
        colorChooser.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void constantUpdate()
    {
        while(super.isVisible())
        {
            if(colorChooser.isVisible())
                colorChooser.updateAll();


        }
    }
}
