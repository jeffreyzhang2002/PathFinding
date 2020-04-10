package gui.colorChooser;

import javax.swing.*;
import java.awt.*;

public class ColorChooserMain
{
    public static void main(String[] args)
    {
        ColorChooser main = new ColorChooser();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);

        while(main.isActive()) {
            main.updateAll();
            //main.pack();
        }
    }
}
