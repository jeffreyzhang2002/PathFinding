package gui.displays;

import gui.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonDisplay extends JPanel
{

    private GUI gui;
    private ArrayList<JButton> buttons;
    private String[] buttonNames = new String[]{"Run Sim", "Edit Field", "Save Field"};

    public ButtonDisplay(GUI gui)
    {
        this.gui = gui;
        super.setLayout(new GridLayout(25,1));
        buttons = new ArrayList<>();

        ButtonActionListener listener = new ButtonActionListener();

        for(String s: buttonNames)
        {
            JButton button = new JButton(s);
            button.addActionListener(listener);
            buttons.add(button);
            super.add(button);
        }
    }

    private class ButtonActionListener implements ActionListener
    {
        private boolean button1State = false;

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int buttonNumber = buttons.indexOf(e.getSource());

            System.out.println(buttonNumber);

            switch (buttonNumber)
            {
                case 0:
                    break;
                case 1:
                    button1State = !button1State;
                    //gui.colorContent.setVisible(button1State);
                case 2:

            }
        }
    }
}
