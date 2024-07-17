import java.awt.*; 
import java.io.*; 

public class MyDrawing implements Cloneable, Serializable {
    int x, y, w, h;
    Color lineColor, fillColor;
    int lineWidth;
    boolean isSelected;
    Shape region;
    final int SIZE = 7;

    public MyDrawing() { //constructor 
        x = y = 0;
        w = h = 0;
        lineColor = Color.black;
        fillColor =Color.white;
        lineWidth = 1;
        setRegion();
    }

    @Override
    
    public MyDrawing clone() {
        try {
            MyDrawing cloned = (MyDrawing) super.clone();
            cloned.lineColor = new Color(this.lineColor.getRGB());
            cloned.fillColor = new Color(this.fillColor.getRGB());
            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void draw(Graphics g) {
        if(isSelected) {
            g.setColor(Color.BLACK);
            g.fillRect(x+w/2 - SIZE/2, y - SIZE/2, SIZE, SIZE);
            g.fillRect(x - SIZE/2, y+h/2 - SIZE/2, SIZE, SIZE);
            g.fillRect(x+w/2 - SIZE/2, y+h - SIZE/2, SIZE, SIZE);
            g.fillRect(x+w - SIZE/2, y+h/2 - SIZE/2, SIZE, SIZE);
            g.fillRect(x - SIZE/2, y - SIZE/2, SIZE, SIZE);
            g.fillRect(x+w - SIZE/2, y - SIZE/2, SIZE, SIZE);
            g.fillRect(x - SIZE/2, y+h - SIZE/2, SIZE, SIZE);
            g.fillRect(x+w - SIZE/2, y+h - SIZE/2, SIZE, SIZE); 

        }
    }

    /*task 3-1*/
    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    //task 3-1
    public boolean contains(int x, int y) {
        //MyDrawingを継承する子クラス内でそれぞれ定義する
        //包含判定図形が矩形ならば、例えば、
        return region.contains(x, y);
    }

    // (Task 4.0) Method to check if a rectangle intersects with the drawing
    public boolean contains(int x, int y, int width, int height) {
        Rectangle selectionRectangle = new Rectangle(x, y, width, height);
        Rectangle drawingRectangle = new Rectangle(this.x, this.y, this.w, this.h);
        return selectionRectangle.intersects(drawingRectangle);
    }
    
    public void setRegion() {
        //MyDrawingを継承する子クラス内でそれぞれ定義する
        //包含判定図形が矩形ならば、例えば、
        region = new Rectangle(x, y, w, h);
    }
/******** */

    
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        setRegion();
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        setRegion();
    }

    public void setSize(int w, int h) {
        this.w = w;
        this.h = h;
        setRegion();
    }

    public int getX() { 
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
        setRegion();
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        setRegion();
    }

    public Color getLineColor() {
        return lineColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setLineWidth (int lineWidth) {
        this.lineWidth = lineWidth;
    } 

    public int getLineWidth () {
        return lineWidth;
    }

    // /*Task2-10 破線を使って図形を描く*/
    private boolean isDashed = false;

    public void setDashed(boolean b) {
        isDashed = b;
    }

    public boolean getDashed(){
        return isDashed;
    }

    /*課題2-3*/
    private boolean isShadow = false;

    public void setShadow(boolean s) {
        isShadow = s;
    }

    public boolean getShadow(){
        return isShadow;
    }

}
