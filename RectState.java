import java.awt.Color;

public class RectState extends State {
    StateManager stateManager;
    MyRectangle currentRect;
    int old_x, old_y;


    //constructor 
    public RectState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    //Set what will happen after using mouse in each state

    public void mouseDown (int x, int y) {
        /*課題2-3 */
        old_x = x;
        old_y = y;

        currentRect = new MyRectangle(x, y, 0, 0, Color.black, Color.white);
        /*dash */
        currentRect.setDashed(stateManager.getDashed()); 
        /*shadow*/
        currentRect.setShadow(stateManager.getShadow());
        stateManager.addDrawing(currentRect);
    }   
    
    
    public void mouseDrag(int x, int y) {
        int width = Math.abs(x - old_x);
        int height = Math.abs(y - old_y);
        int newX = Math.min(x, old_x);
        int newY = Math.min(y, old_y);
        
        currentRect.setLocation(newX, newY);
        currentRect.setSize(width, height);
        stateManager.updateDrawing();
    }

    
    public void mouseUp(int x, int y) {
        //if the rectangle is too small, delete it
        if (Math.abs(x - old_x) < 5 || Math.abs(y - old_y) < 5) {
            stateManager.removeDrawing(currentRect);   
        //else: draw it
        } else {
            stateManager.updateDrawing();
        }   
    } 
}
