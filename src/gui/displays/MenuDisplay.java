package gui.displays;

import gui.GUI;

import javax.swing.*;

public class MenuDisplay extends JMenuBar
{
    private GUI gui;

    public MenuDisplay(GUI gui)
    {
        this.gui = gui;

        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        JMenu menu3 = new JMenu("Run");

        super.add(menu1);
        super.add(menu2);
        super.add(menu3);

        JMenuItem menu1A = new JMenuItem("Open");
        JMenuItem menu1B = new JMenuItem("Save");

        menu1.add(menu1A);
        menu1.add(menu1B);

        JMenuItem menu2A = new JMenuItem("Edit Grid");
        JMenuItem menu2B = new JMenuItem("Edit Resolution");

        menu2.add(menu2A);
        menu2.add(menu2B);

        JMenuItem menu3A = new JMenuItem( "Run Sim");
        JMenuItem menu3B = new JMenuItem("Gen path");

        menu3.add(menu3A);
        menu3.add(menu3B);
    }
}
