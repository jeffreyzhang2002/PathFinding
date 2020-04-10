package gui.statusBar;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.util.ArrayList;

public class StatusBar extends JPanel
{
    private ArrayList<JLabel> statusLabelList;

    public StatusBar()
    {
        statusLabelList = new ArrayList<>();
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void addLabel(JLabel label)
    {
        modifyLabel(label);
        statusLabelList.add(label);
    }

    public void addLabel(int pos, JLabel label)
    {
        modifyLabel(label);
        statusLabelList.add(pos,label);
    }

    public JLabel getLabel(int pos)
    { return statusLabelList.get(pos); }

    public JLabel removeLabel(int pos)
    { return statusLabelList.remove(pos); }

    private void modifyLabel(JLabel label)
    {
        label.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void pushNewLabels()
    {
        super.removeAll();
        for(JLabel label : statusLabelList)
            super.add(label);
    }
}
