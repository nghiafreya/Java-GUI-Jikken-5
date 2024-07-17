import java.awt.Color;

public class OvalState extends State {
    StateManager stateManager;
    MyOval currentOval;
    int old_x, old_y;


    //constructor 
    public OvalState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    //Set what will happen after using mouse in each state

    public void mouseDown (int x, int y) {
        /*課題2-1 */
        //stateManager.addDrawing(new MyOval(x, y));
        /************ */

        /*課題2-2 */
        //  old_x = x;
        //  old_y = y;

        //  currentOval = new MyOval(x, y, 0, 0);
        //  /*dash */
        //  currentOval.setDashed(stateManager.getDashed()); 
        //  stateManager.addDrawing(currentOval);
        /************ */


        /*課題2-3 */
        old_x = x;
        old_y = y;

        currentOval = new MyOval(x, y, 0, 0, Color.black, Color.white);
        /*dash */
        currentOval.setDashed(stateManager.getDashed()); 
        /*shadow*/
        currentOval.setShadow(stateManager.getShadow()); 
        stateManager.addDrawing(currentOval);
        /************ */
    }   
    
    
    public void mouseDrag(int x, int y) {
        int width = Math.abs(x - old_x);
        int height = Math.abs(y - old_y);
        int newX = Math.min(x, old_x);
        int newY = Math.min(y, old_y);
        
        currentOval.setLocation(newX, newY);
        currentOval.setSize(width, height);
        stateManager.updateDrawing();
    }

    
    public void mouseUp(int x, int y) {
        //if the rectangle is too small, delete it
        if (Math.abs(x - old_x) < 5 || Math.abs(y - old_y) < 5) {
            stateManager.removeDrawing(currentOval);   
        //else: draw it
        } else {
            stateManager.updateDrawing();
        }   
    }
    
}
