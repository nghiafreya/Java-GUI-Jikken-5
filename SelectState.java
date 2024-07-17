import java.awt.Choice;
import java.awt.Color;

public class SelectState extends State {
    StateManager stateManager;
    int Chosen_point_x;
    int Chosen_point_y;
    MyRectangle selectionRect;

    //constructor 
    public SelectState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    //Set what will happen after using mouse in each state
    public void mouseDown(int x, int y) {

        boolean objectClicked = false;
        for (MyDrawing d : stateManager.getMediator().getDrawings()) {
            if (d.contains(x, y)) {
                objectClicked = true;
                break;
            }
        }

        if (objectClicked == false) { //clicked on an empty position 
            //creat height = 0, weight = 0 selectionRect
            selectionRect = new MyRectangle(x, y, 0, 0,  Color.BLACK, new Color(0, 0, 0, 0));
            selectionRect.setDashed(true);
            stateManager.addDrawing(selectionRect);
            Chosen_point_x = x;
            Chosen_point_y = y;           
        } else { //clicked on a shape
            stateManager.setSelected(x, y); 
            stateManager.updateDrawing();
            Chosen_point_x = x;
            Chosen_point_y = y;
        }        
    }
    
    public void mouseDrag(int x, int y) {
        //create the selection rectangle
        if (selectionRect != null) { //expanding selectionRect
            int width = Math.abs(x - Chosen_point_x);
            int height = Math.abs(y - Chosen_point_y);
            int newX = Math.min(x, Chosen_point_x);
            int newY = Math.min(y, Chosen_point_y);
            
            selectionRect.setLocation(newX, newY);
            selectionRect.setSize(width, height);
            stateManager.updateDrawing();

            // Select all drawings within the selection rectangle
            for (MyDrawing d : stateManager.getMediator().getDrawings()) {
                if (selectionRect.contains(d.getX(), d.getY(), d.getW(), d.getH())) {
                    stateManager.getMediator().addSelectedDrawing(d);
                } else {
                    stateManager.getMediator().removeSelectedDrawing(d);
                }
            }          
        } else { //click on a shape's area
            //counting the gap between the point we first clicked and the point after dragging
            int dx = x - Chosen_point_x;
            int dy = y - Chosen_point_y;

            //Move the shape (the case when only 1 shape is selected)
            if (stateManager.getSelectedDrawings() != null &&stateManager.getSelectedDrawings().size() == 1) { //if there is a shape that is selected
                stateManager.move(dx, dy);
                //The point after dragging now became the starting point (treated as "the point we just clicked")
                Chosen_point_x = x;
                Chosen_point_y = y;
                stateManager.updateDrawing();
            //Move the shape (the case when only multiple shapes are selected)
            } else if(stateManager.getSelectedDrawings() != null && stateManager.getSelectedDrawings().size() > 1) {
                for (MyDrawing m : stateManager.getSelectedDrawings()) {
                    m.move(dx, dy);
                    //The point after dragging now became the starting point (treated as "the point we just clicked")
                }
                stateManager.updateDrawing();
                Chosen_point_x = x;
                Chosen_point_y = y;
            }


        }
    }



    public void mouseUp(int x, int y) {
        if (selectionRect != null) {
            stateManager.getMediator().removeDrawing(selectionRect);
            selectionRect = null;
            stateManager.updateDrawing();
        }
    }
}
