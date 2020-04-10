package gui.displays;

import gui.GUI;

import javax.swing.*;
import java.awt.*;

public class MainDisplay extends JPanel
{
    private GUI gui;
    FieldDisplay fieldContent;
    ConsoleDisplay consoleContent;

    public MainDisplay(Dimension dim, GUI gui, FieldDisplay fieldContent, ConsoleDisplay consoleContent)
    {
        this.gui = gui;
        this.fieldContent = fieldContent;
        this.consoleContent = consoleContent;

        super.setSize(dim);
        super.setLayout(new GridBagLayout());
        super.setBackground(Color.lightGray);

        int width;
        if(super.getWidth() == super.getHeight())
            width = (int) (super.getWidth() * 0.75);
        else
            width = Math.min(super.getWidth(), super.getHeight());

        fieldContent.setSize(new Dimension(width, width));
        fieldContent.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.ipadx = fieldContent.getWidth();
        c.ipady = fieldContent.getHeight();

        consoleContent.setSize(new Dimension(super.getWidth() - width,width));
        consoleContent.setBackground(Color.black);

        GridBagConstraints d = new GridBagConstraints();
        d.anchor = GridBagConstraints.FIRST_LINE_END;
        d.gridx = 1;
        d.gridy = 0;
        d.gridwidth = 1;
        d.gridheight = 1;
        d.ipadx = consoleContent.getWidth();
        d.ipady = consoleContent.getHeight();

        super.add(fieldContent, c);
        super.add(consoleContent, d);
    }
}
