import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HendeButton extends JButton {
    StateManager stateManager;

    //constructor
    public HendeButton(StateManager stateManager) {
        super("Hendecagonal"); //create button name
        addActionListener(new HendeListener());
        this.stateManager = stateManager;
    }

    class HendeListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            //set the state to HendeState after pressing Hende Button (after an event happened to Hende Button)
            stateManager.setState(new HendeState(stateManager));
        }
    }    
}
