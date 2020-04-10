package gui.displays;

import gui.GUI;
import jdk.nashorn.internal.ir.Labels;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.util.ArrayList;

public class StatusDisplay extends JPanel
{
    private GUI gui;
    public ArrayList<JLabel> labels;

    public StatusDisplay(GUI gui) {
        this.gui = gui;
        labels = new ArrayList<>();
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        for (JLabel l : labels) {
            l.setHorizontalAlignment(SwingConstants.LEFT);
            super.add(l);
        }
    }

    public ArrayList<JLabel> getAllLabels()
    { return labels; }

    public void addLabel(JLabel label)
    {
        labels.add(label);
        setupLabel(label);
        super.add(label);
    }

    public void addLabel(int pos, JLabel label)
    {
        labels.add(pos,label);
        setupLabel(label);
        super.add(label);
    }

    private void setupLabel(JLabel l)
    { l.setHorizontalAlignment(SwingConstants.LEFT); }

    public void update()
    {
        super.removeAll();
        for(JLabel label: labels)
        {
            super.add(label);
        }
    }

    public JLabel getLabel(int pos)
    { return labels.get(pos); }

}
