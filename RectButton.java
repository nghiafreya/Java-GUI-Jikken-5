import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class RectButton extends JButton {
    StateManager stateManager;

    //constructor
    public RectButton(StateManager stateManager) {
        super("Rectangle"); //create button name
        addActionListener(new RectListener());
        this.stateManager = stateManager;
    }

    class RectListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            //set the state to RectState after pressing Rect Button (after an event happened to Rect Button)
            stateManager.setState(new RectState(stateManager));
        }
    }
}

