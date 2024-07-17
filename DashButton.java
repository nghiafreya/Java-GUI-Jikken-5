import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class DashButton extends JButton {
    StateManager stateManager;

    // Constructor
    public DashButton(StateManager stateManager) {
        super("Toggle Dash"); 
        this.stateManager = stateManager;
        addActionListener(new DashListener()); //add an action listener for the button
    }

    class DashListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { //if anything happens to the button
            // execute the code below
            //普通の場合、選択した図形がない
            if (stateManager.getSelectedDrawings().isEmpty()) {
                stateManager.setDashed(!stateManager.getDashed());
            } else { //選択した図形がある
                Vector<MyDrawing> selectedDashedDrawing = stateManager.getSelectedDrawings();
                stateManager.setDashed(!stateManager.getDashed());

                for (MyDrawing drawing : selectedDashedDrawing) {
                    if (stateManager.getDashed()) { //破線である
                        //switch to dashed lines:
                        drawing.setDashed(true);
                        stateManager.updateDrawing();
                    } else { //破線ではない
                        //switch to normal lines:
                        drawing.setDashed(false);
                        stateManager.updateDrawing();
                    }
                }
            }
        }
    }
}
