package gui.displays;

import gui.GUI;

import javax.swing.*;
import java.awt.*;

public class ConsoleDisplay extends JPanel
{
    private GUI gui;
    public ConsoleDisplay(GUI gui)
    {
        this.gui = gui;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        //g.setColor(Color.black);
        //g.fillRect(0, 0, 700, 700);

    }
}
