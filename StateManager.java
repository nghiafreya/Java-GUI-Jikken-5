import java.util.Vector;
public class StateManager {
    private State state;
    private MyCanvas canvas;
    private boolean isDashed;
    private boolean isShadow;
    private Mediator mediator;


    public StateManager(MyCanvas canvas){
        this.canvas = canvas;
        //task 3-0
        this.mediator = canvas.getMediator();
    }

    public void setState(State state) {
        this.state = state; //state is a object from State class so it has 3 methos which is mouseUp, mouseDown, mouseDrag
        //also state is either RectState, OvalState, TriangleState,... Rmb that mouseUp, mouseDown, mouse... are not states
    }

        /*Dash */
    public void setDashed(boolean isDashed){
        this.isDashed = isDashed;
    }

    public boolean getDashed(){
        return isDashed;
    }
    
    /*Shadow */
    public void setShadow(boolean isShadow){
        this.isShadow = isShadow;
    }

    public boolean getShadow(){
        return isShadow;
    }

    //課題3-1

    // Select
    public void setSelected(int x, int y) {
        mediator.setSelected(x, y);
    }

    
    public Vector<MyDrawing> getSelectedDrawings() {
        return mediator.getSelectedDrawings();
    }
    
    //Move
    public void move(int x, int y) {
        mediator.move(x, y);
    }
/********** */

    
    public void mouseUp(int x, int y) {
        state.mouseUp(x, y); //call the mouseUp method of the object state from State class
    }

    public void mouseDown(int x, int y){
        state.mouseDown(x, y);
    }

    public void mouseDrag(int x, int y){
        state.mouseDrag(x, y);
    }

    public void addDrawing(MyDrawing drawing){
        mediator.addDrawing(drawing);
        mediator.repaint(); 
    }

    public void removeDrawing(MyDrawing drawing) {
        mediator.removeDrawing(drawing);
        mediator.repaint();
    }

    public void updateDrawing() {
        mediator.repaint();
    }

    //Task 4-0
    public Mediator getMediator() {
        return mediator;
    }
}
