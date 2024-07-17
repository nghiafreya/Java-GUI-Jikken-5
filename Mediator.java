import java.util.Enumeration;
import java.util.Vector;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 


public class Mediator{
    Vector<MyDrawing> drawings;
    MyCanvas canvas;
    Vector<MyDrawing> selectedDrawings;  // 選択されたオブジェクトのリスト
    Vector<MyDrawing> buffer; // コピー・カット用バッファ
    StateManager stateManager;

    //constructor
    public Mediator(MyCanvas canvas) {
        this.canvas = canvas;
        drawings = new Vector<MyDrawing>();
        selectedDrawings = new Vector<MyDrawing>();
        buffer = new Vector<MyDrawing>();  // Initialize the buffer
        this.stateManager = stateManager;
    }

    public Enumeration<MyDrawing> drawingsElements() {
        return drawings.elements();
    }

    public void addDrawing (MyDrawing d) {
        drawings.add(d);
    }

    public void removeDrawing(MyDrawing d) {
        drawings.remove(d);
    }

    public void repaint() {
        canvas.repaint();
    }

    //Task 4-0

    public void clearSelectedDrawings() {
        for (MyDrawing d : selectedDrawings) {
            d.setSelected(false);
        }
        selectedDrawings.clear();
    }

    public void setSelectedDrawings(MyDrawing d) {
        clearSelectedDrawings(); // Clear previous selections before adding new ones
        if (!selectedDrawings.contains(d)) {
            selectedDrawings.add(d);
            d.setSelected(true);
        }
    }
    
    public Vector<MyDrawing> getSelectedDrawings() {
        return selectedDrawings;
    }

    public void setSelected(int x, int y) {        
        for (int i = drawings.size() - 1; i >= 0; i--) {
            MyDrawing d = drawings.get(i);
            if (d.contains(x, y)) {
                if (!d.getSelected()) { //if d has not been selected yet
                    if (!selectedDrawings.isEmpty()) {
                        clearSelectedDrawings(); // Clear previous selections when starting a new selection
                    }
                    setSelectedDrawings(d);
                } else if (d.getSelected()) { //if d has already been selected
                    break;
                }
                break;
            } else { //when the selected point (x, y) doesn't belong to any shapes
                //do nothing
            }
        }
    }

    /*******************/
    //課題3-1
    public void move(int dx, int dy) {
        if (selectedDrawings != null) {
            for (MyDrawing d : selectedDrawings) {
                d.move(dx, dy);
            }
        }
    }

   
    public void setLineColor(Color lineColor) {
        if (selectedDrawings != null) {
            for (MyDrawing d : selectedDrawings) {
                d.setLineColor(lineColor);
            }
            repaint();
        }
    }

    public void setFillColor(Color fillColor) {
        if (selectedDrawings != null) {
            for (MyDrawing d : selectedDrawings) {
                d.setFillColor(fillColor);
            }
            repaint();
        }
    }

    public void setLineWidth(int lineWidth) {
        if (selectedDrawings != null) {
            for (MyDrawing d : selectedDrawings) {
                d.setLineWidth(lineWidth);
            }
            repaint();
        }
    }
/***************/
    //Cut Copy Paste
    public void clearBuffer()
    {
        buffer.clear();
    }

    public void copy() {
        // バッファをクリアする
        clearBuffer();
        if (!selectedDrawings.isEmpty()) {
            for (MyDrawing d : selectedDrawings) {
                buffer.add(d.clone());
            }
        } else {
            //do nothing if the buffer is empty
        }
    }

    public void cut() {
        // バッファをクリアする
        clearBuffer();
        if (!selectedDrawings.isEmpty()) {
            for (MyDrawing d : selectedDrawings) {
                buffer.add(d.clone());
                removeDrawing(d);
            }
            repaint();
        } else {
            //do nothing if the buffer is empty
        }

    }

    public void paste(int x, int y)
    { 
        if (!buffer.isEmpty()) {
            for (MyDrawing d : buffer) {
                MyDrawing clone = d.clone();
                int newX = d.getX();
                int newY = d.getY();
                clone.setLocation(newX, newY);
                addDrawing(clone);
                repaint();
                d.move(10, 10);
            }
        }

    }
        //Task 4-0
        // Add getDrawings method to retrieve all drawings
        public Vector<MyDrawing> getDrawings() {
            return drawings;
        }
    
        // Add addSelectedDrawing method to add a drawing to the selected list
        public void addSelectedDrawing(MyDrawing d) {
            if (!selectedDrawings.contains(d)) {
                selectedDrawings.add(d);
                d.setSelected(true);
            }
        }
    
        // Add removeSelectedDrawing method to remove a drawing from the selected list
        public void removeSelectedDrawing(MyDrawing d) {
            if (selectedDrawings.contains(d)) {
                selectedDrawings.remove(d);
                d.setSelected(false);
            }
        }

        // File input / output
        public void file_import (String file_name) {
            // File入力
            try {
                FileInputStream fin = new FileInputStream(file_name);
                ObjectInputStream in = new ObjectInputStream(fin);

                drawings = (Vector)in.readObject();
                for (MyDrawing d : drawings) {
                    d.setSelected(false);
                }
                fin.close();
            } catch (Exception ex) {
            }
        }
        

        public void file_export (String file_name) {
            // File出力
            try {
                FileOutputStream fout = new FileOutputStream(file_name);
                ObjectOutputStream out = new ObjectOutputStream(fout);

                out.writeObject(drawings);
                out.flush();

                fout.close();
            } catch (Exception ex) {
            }
        }

            

}