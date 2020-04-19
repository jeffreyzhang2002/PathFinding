package graphicalUserInterface;

import graphicalUserInterface.gui.GUI;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
