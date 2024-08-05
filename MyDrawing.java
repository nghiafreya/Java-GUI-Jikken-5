import java.awt.*; 
import java.io.*; 
import java.awt.geom.*;
import java.awt.image.BufferedImage; 

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

    public void applyGrayscale(Graphics g) {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        draw(g2); // Draw the shape onto the BufferedImage
        g2.dispose();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int gray = (int) (color.getRed() * 0.3 + color.getGreen() * 0.59 + color.getBlue() * 0.11);
                Color grayscaleColor = new Color(gray, gray, gray);
                image.setRGB(x, y, grayscaleColor.getRGB());
            }
        }

        g.drawImage(image, this.x, this.y, null);
        
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
