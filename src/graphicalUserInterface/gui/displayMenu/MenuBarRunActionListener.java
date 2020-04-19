package graphicalUserInterface.gui.displayMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarRunActionListener implements ActionListener
{
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(MenuBar.simulationMenu)) {
            System.out.println("simulation menu clicked");
        } else if(e.getSource().equals(MenuBar.stepMenu)) {
            System.out.println("step menu clicked");
        }
    }
}
