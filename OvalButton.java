import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class OvalButton extends JButton {
    StateManager stateManager;

    //constructor
    public OvalButton(StateManager stateManager) {
        super("Oval");
        addActionListener(new OvalListener());
        this.stateManager = stateManager;
    }

    class OvalListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            stateManager.setState(new OvalState(stateManager));
        }
    }
}