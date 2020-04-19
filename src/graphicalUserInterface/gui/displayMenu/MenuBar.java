package graphicalUserInterface.gui.displayMenu;

import javax.swing.*;

public class MenuBar extends JMenuBar
{
    static final JMenu fileMenu = new JMenu("File");
        static final JMenuItem saveMenu = new JMenuItem("Save");
        static final JMenuItem saveAsMenu = new JMenuItem("Save As");
        static final JMenuItem openMenu = new JMenuItem("Open");
    static final JMenu editMenu = new JMenu("Edit");
        static final JMenu gridMenu = new JMenu("Grid");
            static final JMenuItem gridSizeMenu = new JMenuItem("Size");
            static final JMenuItem gridBackgroundMenu = new JMenuItem("Background");
        static final JMenuItem actorMenu = new JMenuItem("Actor");
    static final JMenu runMenu = new JMenu("Run");
        static final JMenuItem simulationMenu = new JMenuItem("Simulation");
        static final JMenuItem stepMenu = new JMenuItem("Step");

    public MenuBar()
    {
        super.add(fileMenu);
            fileMenu.add(saveMenu);
            fileMenu.add(saveAsMenu);
            fileMenu.add(openMenu);
        super.add(editMenu);
            editMenu.add(gridMenu);
                gridMenu.add(gridSizeMenu);
                gridMenu.add(gridBackgroundMenu);
            editMenu.add(actorMenu);
        super.add(runMenu);
            runMenu.add(simulationMenu);
            runMenu.add(stepMenu);

       initializeActionListeners();
    }

    private void initializeActionListeners()
    {
        MenuBarFileActionListener fileActionListener = new MenuBarFileActionListener();
        saveMenu.addActionListener(fileActionListener);
        saveAsMenu.addActionListener(fileActionListener);
        openMenu.addActionListener(fileActionListener);

        MenuBarEditActionListener editActionListener = new MenuBarEditActionListener();
        gridSizeMenu.addActionListener(editActionListener);
        gridBackgroundMenu.addActionListener(editActionListener);
        actorMenu.addActionListener(editActionListener);

        MenuBarRunActionListener runActionListener = new MenuBarRunActionListener();
        simulationMenu.addActionListener(runActionListener);
        stepMenu.addActionListener(runActionListener);
    }
}
