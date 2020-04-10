package gui.colorChooser;

import javax.swing.*;
import java.awt.*;

/**
 * Creates a ColorChooser. This class includes both a ColorChooserPanel and a CustomColorCreator;
 */
public class ColorChooser extends JFrame
{
    public ColorChooserPanel colorChooserPanel;
    public CustomColorCreator customColorCreator;

    private Color lastCustomColor;
    private JPanel chosenColorDisplay;

    public ColorChooser()
    {
        super("Color Creator");
        super.setSize(500,200);
        super.setBackground(Color.white);

        colorChooserPanel = new ColorChooserPanel();
        customColorCreator = new CustomColorCreator();

        lastCustomColor = customColorCreator.getCurrentCustomColor();
        chosenColorDisplay = new JPanel();
        chosenColorDisplay.setBackground(colorChooserPanel.getChosenColor());

        super.getContentPane().add(BorderLayout.CENTER, colorChooserPanel);
        super.getContentPane().add(BorderLayout.NORTH, customColorCreator);
        super.getContentPane().add(BorderLayout.SOUTH, chosenColorDisplay);
    }

    public void updateAll()
    {
        chosenColorDisplay.setBackground(colorChooserPanel.getChosenColor());
        chosenColorDisplay.updateUI();

        if(!customColorCreator.getCurrentCustomColor().equals(lastCustomColor))
        {
            lastCustomColor = customColorCreator.getCurrentCustomColor();;
            colorChooserPanel.addColor(lastCustomColor);
            colorChooserPanel.pushNewButtons();
        }
    }

    public Color getChosenColor()
    { return colorChooserPanel.getChosenColor(); }
}
