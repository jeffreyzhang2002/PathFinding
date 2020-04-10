package gui.displays;

import gui.GUI;
import javafx.util.Pair;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ColorButtonDisplay extends JPanel
{
    private GUI gui;
    private ArrayList<JButton> colorButtons;
    private ArrayList<Pair<String,Color>> colorButtonNames;
    private JPanel customColor;
    private Color pickedColor = Color.white;
    private JLabel currentColor = new JLabel(" Color: White");

    public ColorButtonDisplay(GUI gui)
    {
        this.gui = gui;
        initColorButtonNames();
        super.setLayout(new GridLayout(10,1));
        colorButtons = new ArrayList<>();

        currentColor.setForeground(pickedColor);

        ColorButtonActionListener listener = new ColorButtonActionListener();

        for(Pair<String,Color> p : colorButtonNames)
        {
            JButton button = new JButton(p.getKey());
            button.addActionListener(listener);
            button.setBackground(p.getValue());
            colorButtons.add(button);
            super.add(button);
        }

        customColor = new JPanel();

        JTextField prompt = new JTextField("RGB:");
        JTextField Rfield = new JTextField("0");
        JTextField Gfield = new JTextField("0");
        JTextField Bfield = new JTextField("0");

        prompt.setEditable(false);

        customColor.setLayout(new FlowLayout());
        customColor.add(prompt);
        customColor.add(Rfield);
        customColor.add(Gfield);
        customColor.add(Bfield);

        super.add(customColor);

        gui.statusBar.addLabel(currentColor);
    }

    public Color getPickedColor()
    { return pickedColor; }

    private void initColorButtonNames()
    {
        colorButtonNames = new ArrayList<>();
        colorButtonNames.add(new Pair<String, Color>("Red", Color.red));
        colorButtonNames.add(new Pair<String, Color>("Yellow", Color.yellow));
        colorButtonNames.add(new Pair<String, Color>("Orange", Color.orange));
        colorButtonNames.add(new Pair<String, Color>("Green", Color.green));
        colorButtonNames.add(new Pair<String, Color>("Blue", Color.blue));
        colorButtonNames.add(new Pair<String, Color>("Purple", new Color(148, 0, 211)));
        colorButtonNames.add(new Pair<String, Color>("White", Color.white));
        colorButtonNames.add(new Pair<String, Color>("Black", Color.black));
    }


    private class ColorButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int buttonNumber = colorButtons.indexOf(e.getSource());
            Pair<String,Color> c = colorButtonNames.get(buttonNumber);
            pickedColor = c.getValue();
            currentColor.setText(" Color: " + c.getKey());
            currentColor.setForeground(pickedColor);
        }
    }
}
