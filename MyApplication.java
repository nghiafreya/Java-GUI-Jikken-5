import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.util.Vector;
public class MyApplication extends JFrame{
    StateManager stateManager;
    MyCanvas canvas;

    private JMenuBar menuBar;
    private JMenu FillColorMenu, LineColorMenu, LineWidthMenu;
    private JMenuItem redFill, blueFill, greenFill;
    private JMenuItem redLine, blueLine, greenLine, blackLine;
    private JMenuItem lineWidth1, lineWidth5, lineWidth10;

    private JMenu FileMenu;
    private JMenuItem openItem, saveItem;

    private JMenu MoveUpDownMenu;
    private JMenuItem upMostItem, upItem, downItem, downMostItem;

    Mediator med;
    
    //constructor
    public MyApplication(){
        super("My Paint Application");

        canvas = new MyCanvas();
        canvas.setBackground(Color.WHITE);

        //create a panel named jp 
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());

        //create a canvas
        stateManager = new StateManager(canvas);

        med = canvas.getMediator();

        
        //create 2 buttons
        RectButton rectButton = new RectButton(stateManager);
        jp.add(rectButton);
        OvalButton ovalButton = new OvalButton(stateManager);
        jp.add(ovalButton);
        IsosButton isosButton = new IsosButton(stateManager);
        jp.add(isosButton);
        HendeButton hendeButton = new HendeButton(stateManager);
        jp.add(hendeButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);

        //create a panel named function 
        JPanel function = new JPanel();
        function.setLayout(new FlowLayout());

        // Create buttons for dash, shadow 
        DashButton dashButton = new DashButton(stateManager);
        function.add(dashButton);
        ShadowButton shadowButton = new ShadowButton(stateManager);
        function.add(shadowButton);

        // Create buttons for select
        SelectButton selectButton = new SelectButton(stateManager);
        function.add(selectButton);


        DeleteButton deleteButton = new DeleteButton(stateManager);
        function.add(deleteButton);


        getContentPane().add(function, BorderLayout.SOUTH);
        getContentPane().add(canvas, BorderLayout.CENTER);
        /**************************** */
        //create a panel named COPYPASTE
        JPanel COPYPASTE = new JPanel();
        COPYPASTE.setLayout(new FlowLayout());


        // Add Cut, Copy, Paste buttons
        JButton cutButton = new JButton("Cut");
        cutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                med.cut();
            }
        });
        COPYPASTE.add(cutButton);

        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                med.copy();
            }
        });
        COPYPASTE.add(copyButton);

        JButton pasteButton = new JButton("Paste");
        pasteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                med.paste(0, 0);
            }
        });
        COPYPASTE.add(pasteButton);

        getContentPane().add(COPYPASTE, BorderLayout.WEST);
        getContentPane().add(canvas, BorderLayout.CENTER);
/*********************** */


        canvas.addMouseListener(new MouseAdapter(){
            //call the process of current mouseDown
            public void mousePressed(MouseEvent e) {
                stateManager.mouseDown(e.getX(), e.getY());
            }            
        });

        canvas.addMouseListener(new MouseAdapter(){
            //call the process of current mouseDown
            public void mouseReleased(MouseEvent r) {
                stateManager.mouseUp(r.getX(), r.getY());
            }         
        });
        

        
        canvas.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent d) {
                stateManager.mouseDrag(d.getX(), d.getY());
            }
        });

        //課題3-1
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Fill Color
        FillColorMenu = new JMenu("Fill Color");
        redFill = new JMenuItem("Red");
        blueFill = new JMenuItem("Blue");
        greenFill = new JMenuItem("Green");
        FillColorMenu.add(redFill);
        FillColorMenu.add(blueFill);
        FillColorMenu.add(greenFill);
        redFill.addActionListener(new FillColorListener(Color.RED));
        blueFill.addActionListener(new FillColorListener(Color.BLUE));
        greenFill.addActionListener(new FillColorListener(Color.GREEN));

        //Line Color
        LineColorMenu = new JMenu("Line Color");
        redLine = new JMenuItem("Red");
        blueLine = new JMenuItem("Blue");
        greenLine = new JMenuItem("Green");
        blackLine = new JMenuItem("Black");
        LineColorMenu.add(redLine);
        LineColorMenu.add(blueLine);
        LineColorMenu.add(greenLine);
        LineColorMenu.add(blackLine);
        redLine.addActionListener(new LineColorListener(Color.RED));
        blueLine.addActionListener(new LineColorListener(Color.BLUE));
        greenLine.addActionListener(new LineColorListener(Color.GREEN));
        blackLine.addActionListener(new LineColorListener(Color.BLACK));

        //課題3-2 Line Width
        //Line Width
        LineWidthMenu = new JMenu("Line Width");
        lineWidth1 = new JMenuItem("Line Width: 1");
        lineWidth5 = new JMenuItem("Line Width: 5");
        lineWidth10 = new JMenuItem("Line Width: 10");
        LineWidthMenu.add(lineWidth1);
        LineWidthMenu.add(lineWidth5);
        LineWidthMenu.add(lineWidth10);
        lineWidth1.addActionListener(new LineWidthListener(1));
        lineWidth5.addActionListener(new LineWidthListener(5));
        lineWidth10.addActionListener(new LineWidthListener(10));

        menuBar.add(FillColorMenu);
        menuBar.add(LineColorMenu);
        menuBar.add(LineWidthMenu);

        /************************** */
        //課題4-2, 4-3: Open file, Save file

        //Open and Save
        FileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        FileMenu.add(openItem);
        FileMenu.add(saveItem);
        //課題4-3
        openItem.addActionListener(new FileOpenListener());
        saveItem.addActionListener(new FileSaveListener());   

        menuBar.add(FileMenu);

        /************************** */
        //課題5: Move up, move down
        

        MoveUpDownMenu = new JMenu("Move To");
        upMostItem = new JMenuItem("Move To Upmost");
        upItem = new JMenuItem("Move Up");
        downItem = new JMenuItem("Move Down");
        downMostItem = new JMenuItem("Move To Downmost");
        MoveUpDownMenu.add(upItem);
        MoveUpDownMenu.add(downItem);
        MoveUpDownMenu.add(upMostItem);
        MoveUpDownMenu.add(downMostItem);
 
        downItem.addActionListener(new MoveDownListener());
        upItem.addActionListener(new MoveUpListener()); 
        downMostItem.addActionListener(new MoveDownMostListener());
        upMostItem. addActionListener(new MoveUpMostListener()); 

        menuBar.add(MoveUpDownMenu);

    }

    
    /************** */
    //menuBar's ActionListener   

    class FillColorListener implements ActionListener {
        Color fillColor;

        //constructor
        FillColorListener(Color fillColor) {
            this.fillColor = fillColor;
        }
        public void actionPerformed(ActionEvent e) {
                med.setFillColor(fillColor);
        }    
    }

    class LineColorListener implements ActionListener {
        Color lineColor;

        //constructor
        LineColorListener(Color lineColor) {
            this.lineColor = lineColor;
        }
        public void actionPerformed(ActionEvent e) {
                med.setLineColor(lineColor);
        }    
    } 

    class LineWidthListener implements ActionListener {
        int lineWidth;

        //constructor
        LineWidthListener(int lineWidth) {
            this.lineWidth = lineWidth;
        }
        public void actionPerformed(ActionEvent e) {
                med.setLineWidth(lineWidth);
        }    
    } 

    /************** */
    //Open, Save Listener

    //課題4-3
    class FileOpenListener implements ActionListener {

        //constructor
        FileOpenListener() {
            //nothing
        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(null); // ファイルロード用のダイアログを開く
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                med.file_import(file.getAbsolutePath());
                med.clearSelectedDrawings();
                med.repaint();
            }
        }  
    }
        
    class FileSaveListener implements ActionListener {
        //constructor
        FileSaveListener() {
            //nothing
        }
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(null); // ファイルセーブ用のダイアログを開く

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                med.file_export(file.getAbsolutePath());
                med.repaint();
            }
            
        }  

    }

    /***************** */
// Move Up, Move Down Listener
class MoveDownListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (!med.getSelectedDrawings().isEmpty() && med.getSelectedDrawings().size() == 1) { // When only 1 shape is selected
            MyDrawing drawing = med.getSelectedDrawings().get(0); // get that shape
            int index = med.getDrawings().indexOf(drawing); // get that shape's index
            if (index > 0) {
                med.getDrawings().remove(drawing);
                med.getDrawings().add(index - 1, drawing); // Move the object down one level
                med.repaint(); 
            }
        } else { // When no shape or multiple shapes are selected 
            // do nothing
        } 
    } 
}

class MoveUpListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (!med.getSelectedDrawings().isEmpty() && med.getSelectedDrawings().size() == 1) { // When only 1 shape is selected
            MyDrawing drawing = med.getSelectedDrawings().get(0); // get that shape
            int index = med.getDrawings().indexOf(drawing); // get that shape's index
            if (index < med.getDrawings().size() - 1) {
                med.getDrawings().remove(drawing);
                med.getDrawings().add(index + 1, drawing); // Move the object up one level
                med.repaint(); 
            }
        } else { // When no shape or multiple shapes are selected 
            // do nothing
        } 
    }     
}

class MoveUpMostListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (!med.getSelectedDrawings().isEmpty() && med.getSelectedDrawings().size() == 1) { // When only 1 shape is selected
            MyDrawing drawing = med.getSelectedDrawings().get(0); // get that shape
            int index = med.getDrawings().indexOf(drawing); // get that shape's index
            if (index < med.getDrawings().size() - 1) {
                med.getDrawings().remove(drawing);
                med.getDrawings().add(med.getDrawings().size(), drawing); // Move the object to the upmost level
                med.repaint(); 
            }
        } else { // When no shape or multiple shapes are selected 
            // do nothing
        } 
    }     
}

class MoveDownMostListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if (!med.getSelectedDrawings().isEmpty() && med.getSelectedDrawings().size() == 1) { // When only 1 shape is selected
            MyDrawing drawing = med.getSelectedDrawings().get(0); // get that shape
            int index = med.getDrawings().indexOf(drawing); // get that shape's index
            if (index > 0) {
                med.getDrawings().remove(drawing);
                med.getDrawings().add(0, drawing); // Move the object to the downmost level
                med.repaint(); 
            }
        } else { // When no shape or multiple shapes are selected 
            // do nothing
        } 
    }     
}
    /***************** */

    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    public static void main(String[] args) {
        MyApplication app = new MyApplication();
        app.pack();
        app.setVisible(true);
    }


}   
