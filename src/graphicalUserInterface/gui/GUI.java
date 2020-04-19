package graphicalUserInterface.gui;

import graphicalUserInterface.gui.displayMenu.MenuBar;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame
{
    graphicalUserInterface.gui.displayMenu.MenuBar menuBar;

    public GUI()
    {
        menuBar = new MenuBar();
        super.getContentPane().add(BorderLayout.NORTH, menuBar);
    }
}
