package gui;

import javax.swing.*;
import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        GraphicalUserInterface gui = new GraphicalUserInterface();
        gui.setSize(new Dimension(700,700));
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
