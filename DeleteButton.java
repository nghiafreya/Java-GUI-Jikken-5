import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.event.*;

public class DeleteButton extends JButton {
    StateManager stateManager;


    // Constructor
    public DeleteButton(StateManager stateManager) {
        super("Toggle Delete"); 
        this.stateManager = stateManager;
        addActionListener(new DeleteListener()); //add an action listener for the button
    }

    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { //if anything happens to the button
            if (!stateManager.getSelectedDrawings().isEmpty()) {
                for (MyDrawing deleteDrawing : stateManager.getSelectedDrawings()) {
                    stateManager.removeDrawing(deleteDrawing);
                    stateManager.updateDrawing();
                }
            } else { //if no shape is selected then dont remove anything
                //do nothing
            }
        }
    }
}
