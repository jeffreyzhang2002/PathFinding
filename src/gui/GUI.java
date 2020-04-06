package gui;

import grid.Field;

import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class GUI
{
    public static JFrame mainFrame = new JFrame("GUI");
    public static JMenuBar menuBar = new JMenuBar();
    public static JPanel buttonBar = new JPanel();
    public static JPanel statusBar = new JPanel();

    public static final int fieldDimension = 650;
    public static final int consoleWidth = 300;
    public static final int StatusBarHeight = 20;
    public static final int buttonBarWidth = 100;


    public static void main(String[] args)
    {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        initMenuBar();
        initButtonBar();
        initStatusBar();


        mainFrame.setSize(fieldDimension + consoleWidth + buttonBarWidth,
                menuBar.getHeight() + fieldDimension + statusBar.getHeight() + mainFrame.getInsets().top + 50);

        JDisplay field = new JDisplay(new Field(10,10),new Point(0,0),fieldDimension, consoleWidth);

        mainFrame.getContentPane().add(BorderLayout.NORTH, menuBar);
        mainFrame.getContentPane().add(BorderLayout.CENTER, field);
        mainFrame.getContentPane().add(BorderLayout.EAST, buttonBar);
        mainFrame.getContentPane().add(BorderLayout.SOUTH, statusBar);

        System.out.println(mainFrame.getWidth() + " height: " + mainFrame.getHeight());

        mainFrame.setVisible(true);
    }

    public static void initMenuBar()
    {
        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Edit");
        JMenu menu3 = new JMenu("Run");

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);

        JMenuItem menu1A = new JMenuItem("Open");
        JMenuItem menu1B = new JMenuItem("Save");

        menu1.add(menu1A);
        menu1.add(menu1B);
    }

    public static void initStatusBar()
    {
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusBar.setSize(fieldDimension + consoleWidth + buttonBarWidth, StatusBarHeight);
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));

        JLabel statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);

        statusBar.add(statusLabel);
    }

    public static void initButtonBar()
    {
        buttonBar.setSize(buttonBarWidth,fieldDimension);
        buttonBar.setLayout(new FlowLayout());
        buttonBar.add(new JButton("press me"));
    }
}
