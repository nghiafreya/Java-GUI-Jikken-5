import java.awt.Color;

public class IsosState extends State {
    StateManager stateManager;
    MyIsosceles currentIsos;
    int old_x, old_y;


    //constructor 
    public IsosState(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    //Set what will happen after using mouse in each state

    public void mouseDown (int x, int y) {
        /*課題2-1 */
        //stateManager.addDrawing(new MyIsosceles(x, y));
        /************ */

        /*課題2-2 */
        // old_x = x;
        // old_y = y;

        // currentIsos= new MyIsosceles(x, y, 0, 0);
        // /*dash */
        // currentIsos.setDashed(stateManager.getDashed()); 
        // stateManager.addDrawing(currentIsos);

        /*課題2-3 */
        old_x = x;
        old_y = y;

        currentIsos = new MyIsosceles(x, y, 0, 0, Color.black, Color.white);
        /*dash */
        currentIsos.setDashed(stateManager.getDashed()); 
        /*shadow*/
        currentIsos.setShadow(stateManager.getShadow());
        stateManager.addDrawing(currentIsos);
    }   
    
    
    public void mouseDrag(int x, int y) {
        int width = Math.abs(x - old_x);
        int height = Math.abs(y - old_y);
        int newX = Math.min(x, old_x);
        int newY = Math.min(y, old_y);
        
        currentIsos.setLocation(newX, newY);
        currentIsos.setSize(width, height);
        stateManager.updateDrawing();
    }

    
    public void mouseUp(int x, int y) {
        //if the isosceles is too small, delete it
        if (Math.abs(x - old_x) < 5 || Math.abs(y - old_y) < 5) {
            stateManager.removeDrawing(currentIsos);   
        //else: draw it
        } else {
            stateManager.updateDrawing();
        }   
    } 
}
