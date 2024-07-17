import javax.swing.*;
import java.awt.event.*;

public class SelectButton extends JButton {
    StateManager stateManager;

    // Constructor
    public SelectButton(StateManager stateManager) {
        super("Toggle Select"); 
        this.stateManager = stateManager;
        addActionListener(new SelectListener()); //add an action listener for the button
    }

    class SelectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { //if anything happens to the buttona
            // execute the code below
            stateManager.setState(new SelectState(stateManager));
        }
    }
}
