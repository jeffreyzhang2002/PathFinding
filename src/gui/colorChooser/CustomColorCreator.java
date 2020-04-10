package gui.colorChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a panel that allows for creation of custom colors using RGB values
 */
class CustomColorCreator extends JPanel
{
    private JTextField promptLabel;
    private JTextField rLabel, gLabel, bLabel;
    private JButton enterButton;

    private Color currentCustomColor;

    /**
     * Creates a Custom Color Creator. By default this class will use a FlowLayout
     */
    public CustomColorCreator()
    {
        super.setLayout(new FlowLayout());
        super.setBackground(Color.WHITE);

        promptLabel = new JTextField("RGB: ");
        promptLabel.setEditable(false);

        rLabel = new JTextField("255");
        gLabel = new JTextField("255");
        bLabel = new JTextField("255");

        enterButton = new JButton("Enter");

        CustomCreationHandler handler = new CustomCreationHandler();
        rLabel.addActionListener(handler);
        gLabel.addActionListener(handler);
        bLabel.addActionListener(handler);
        enterButton.addActionListener(handler);

        currentCustomColor = new Color(255,255,255);
        enterButton.setBackground(currentCustomColor);

        super.add(promptLabel);
        super.add(rLabel);
        super.add(gLabel);
        super.add(bLabel);
        super.add(enterButton);
    }

    /**
     * Gets the current Custom Colors By default this will return the Color White
     * @return the current Custom Color.
     */
    public Color getCurrentCustomColor()
    { return currentCustomColor; }

    /**
     * Private class that allows for Detection of Custom Color creation
     */
    private class CustomCreationHandler implements ActionListener
    {
        private int r = 255, g = 255, b = 255;

        public void actionPerformed(ActionEvent e)
        {
            try
            {
                if (e.getSource().equals(rLabel))
                    r = Integer.parseInt(e.getActionCommand());
                else if (e.getSource().equals(gLabel))
                    g = Integer.parseInt(e.getActionCommand());
                else if (e.getSource().equals(bLabel))
                    b = Integer.parseInt(e.getActionCommand());
                else if (e.getSource().equals(enterButton))
                {
                    r = Integer.parseInt(rLabel.getText());
                    g = Integer.parseInt(gLabel.getText());
                    b = Integer.parseInt(bLabel.getText());
                    currentCustomColor = new Color(r,g,b);
                }
                enterButton.setEnabled(true);
                enterButton.setBackground(new Color(r, g, b));
            } catch (Exception E) {
                enterButton.setEnabled(false);
                enterButton.setBackground(Color.DARK_GRAY);
            }
        }
    }
}
