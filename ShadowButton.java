import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class ShadowButton extends JButton {
    StateManager stateManager;

    // Constructor
    public ShadowButton(StateManager stateManager) {
        super("Toggle Shadow"); 
        this.stateManager = stateManager;
        addActionListener(new ShadowListener()); //add an action listener for the button
    }

    class ShadowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) { //if anything happens to the button
            // execute the code below
            //普通の場合、選択した図形がない
            if (stateManager.getSelectedDrawings().isEmpty()) {
                stateManager.setShadow(!stateManager.getShadow());
            } else { //選択した図形がある
                Vector<MyDrawing> selectedShadowDrawing = stateManager.getSelectedDrawings();
                stateManager.setShadow(!stateManager.getShadow());

                for (MyDrawing drawing : selectedShadowDrawing) {
                    if (stateManager.getShadow()) { //影がある
                        //draw shadow:
                        drawing.setShadow(true);
                        stateManager.updateDrawing();
                    } else { //影がない
                        //delete shadow:
                        drawing.setShadow(false);
                        stateManager.updateDrawing();
                    }
                }
            }
        }
    }
}
