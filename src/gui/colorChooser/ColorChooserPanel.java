package gui.colorChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Class for creating a panel of Buttons for color choosing functionality
 */
class ColorChooserPanel extends JPanel {

    private ArrayList<JButton> addedColorButtons;
    private AddedColorChooserHandler addedColorHandler;

    private ArrayList<JButton> defaultColorButtons;
    private DefaultColorChooserHandler defaultColorHandler;

    private Color chosenColor;

    private ColorNameGroup[] defaultColors = new ColorNameGroup[]
            {new ColorNameGroup("RED", Color.RED), new ColorNameGroup("GREEN", Color.GREEN),
             new ColorNameGroup("BLUE" , Color.BLUE), new ColorNameGroup("YELLOW", Color.YELLOW),
             new ColorNameGroup("ORANGE", Color.ORANGE), new ColorNameGroup("WHITE", Color.WHITE),
             new ColorNameGroup("GRAY", Color.GRAY), new ColorNameGroup("BLACK", Color.BLACK)};

    /**
     * How many Custom Colors that will be shown
     */
    public int maxShownCustomColors = 10;

    /**
     * Creates a ColorChooserPanel. WHen this is ran, ColorChoosePanel will use a FlowLayout
     */
    public ColorChooserPanel()
    {
        super.setLayout(new FlowLayout());
        super.setBackground(Color.white);

        defaultColorHandler = new DefaultColorChooserHandler();
        addedColorHandler = new AddedColorChooserHandler();

        initDefaultColors();
        addedColorButtons = new ArrayList<>(0);

        chosenColor = Color.white;

        pushNewButtons();
    }

    /**
     * Init ColorChooserPanel default colors. This method will add an default color handler that allows for button click
     * detection
     */
    private void initDefaultColors()
    {
        defaultColorButtons = new ArrayList<>();
        for (ColorNameGroup c : defaultColors)
        {
            JButton currentButton = new JButton(c.toString());
            currentButton.addActionListener(defaultColorHandler);
            currentButton.setBackground(c.color);
            defaultColorButtons.add(currentButton);
        }
    }

    /**
     * This method allows users to add a custom color to this Panel
     * @param c The new custom Color
     * @param colorName The name you give the Color. Ex Pink, Purple ...
     */
    public void addColor(Color c, String colorName)
    { addColor(new ColorNameGroup(colorName, c)); }

    /**
     * This method allows users to add a custom color to this Panel without giving it a name
     * @param c The new custom Color. The name of this Color will be in the form R,G,B
     */
    public void addColor(Color c)
    { addColor(new ColorNameGroup(c)); }

    /**
     * Adds a custom color
     * @param colorName color group
     */
    private void addColor(ColorNameGroup colorName)
    {
        JButton button = new JButton(colorName.toString());
        button.addActionListener(addedColorHandler);
        button.setBackground(colorName.color);
        addedColorButtons.add(0, button);

        while (addedColorButtons.size() > maxShownCustomColors)
            addedColorButtons.remove(addedColorButtons.size() - 1);
    }

    /**
     * Renders all the new Buttons that are added. This method needs to be ran for the new Buttons to be ran
     */
    public void pushNewButtons()
    {
        super.removeAll();

        for(JButton button: defaultColorButtons)
        { super.add(button); }

        for(JButton button: addedColorButtons)
        { super.add(button); }

        super.updateUI();
    }

    /**
     * Returns the current color that is chosen. If no color is picked this will return null.
     * @return
     */
    public Color getChosenColor()
    { return chosenColor; }

    /**
     * Private class that allows for color naming
     */
    private class ColorNameGroup
    {
        public String colorName;
        public Color color;

        public ColorNameGroup(String colorName, Color color)
        {
            this.colorName = colorName;
            this.color = color;
        }

        /**
         * Creates a ColorGroup using a given color with a name in the format RGB
         * @param color
         */
        public ColorNameGroup(Color color)
        {
            this.colorName = String.format("R: %d G: %d B: %d", color.getRed(), color.getGreen(), color.getBlue());
            this.color = color;
        }

        public String toString()
        { return colorName; }
    }

    /**
     * Class that detects when a default color is chosen
     */
    private class DefaultColorChooserHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int index = defaultColorButtons.indexOf(e.getSource());
            if(index != -1)
                chosenColor = defaultColorButtons.get(index).getBackground();

        }

    }

    /**
     * Class that detects when a custom color is chosen
     */
    private class AddedColorChooserHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int index = addedColorButtons.indexOf(e.getSource());
            if(index != -1)
                chosenColor = addedColorButtons.get(index).getBackground();

        }
    }
}
