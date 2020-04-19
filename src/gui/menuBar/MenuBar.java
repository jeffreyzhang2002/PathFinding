package gui.menuBar;

import javafx.scene.layout.Background;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar
{
    JMenu menu1;
        JMenuItem menu1A, menu1B;
    JMenu menu2;
        JMenu menu2A;
            JMenuItem menu2A1, menu2A2, menu2A3;
        JMenuItem menu2B;
    JMenu menu3;
        JMenuItem menu3A, menu3B;


    public MenuBar()
    {
        /////////////////////////////////////////////////
        menu1 = new JMenu("File");
            menu1A = new JMenuItem("Open");
            menu1B = new JMenuItem("Save");

        super.add(menu1);
            menu1.add(menu1A);
            menu1.add(menu1B);

        /////////////////////////////////////////////////
        menu2 = new JMenu("Edit");
            menu2A = new JMenu("Grid");
                menu2A1 = new JMenuItem("BackGround");
                menu2A2 = new JMenuItem("Dimensions");
                menu2A3 = new JMenuItem("Background Image");
            menu2B = new JMenuItem("Actors");

        super.add(menu2);
            menu2.add(menu2A);
                menu2A.add(menu2A1);
                menu2A.add(menu2A2);
                menu2A.add(menu2A3);
            menu2.add(menu2B);

        /////////////////////////////////////////////////
        menu3 = new JMenu("Run");
            menu3A = new JMenuItem( "Run Sim");
            menu3B = new JMenuItem("Gen path");

        super.add(menu3);
            menu3 = new JMenu("Run");
                menu3.add(menu3A);
                menu3.add(menu3B);
    }
}
