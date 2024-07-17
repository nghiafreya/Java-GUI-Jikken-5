import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IsosButton extends JButton{
    StateManager stateManager;

    //constructor
    public IsosButton(StateManager stateManager) {
        super("Isosceles"); //create button name
        addActionListener(new IsosListener());
        this.stateManager = stateManager;
    }

    class IsosListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            //set the state to IsosState after pressing Isos Button (after an event happened to Isos Button)
            stateManager.setState(new IsosState(stateManager));
        }
    }
}
