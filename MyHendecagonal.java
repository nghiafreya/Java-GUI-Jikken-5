import java.awt.*;

public class MyHendecagonal extends MyDrawing {
    
    /*課題2-3 */
    public MyHendecagonal(int xpt, int ypt, int width, int height, Color lineColor, Color fillColor) {
        super();
        setLocation(xpt, ypt);
        setSize(width, height);
        setLineColor(lineColor);
        setFillColor(fillColor);
    }

    @Override
    public void draw (Graphics g) {
        int x = getX();
        int y = getY();
        int w = getW();
        int h = getH();

        if (w < 0) {
            x += w;
            w *= -1;
        }
        if (h < 0) {
            y += h;
            h *= -1;
        }

        Graphics2D g2 = (Graphics2D) g;

        /*Create Shadow */
        if (getShadow()) {  
            int shadowOffset = 10;
            Polygon shadow_hende = createHende(x + shadowOffset, y + shadowOffset, w, h);
            g2.setColor(Color.black);
            g2.fillPolygon(shadow_hende);
            g2.setColor(Color.black);
            g2.drawPolygon(shadow_hende);
        } 

        /*Create Dash lines */
        if (getDashed()) {
            g2.setStroke(new MyDashStroke(getLineWidth()));
        } else {
            g2.setStroke (new BasicStroke (getLineWidth()));
        } 

        /*Draw Hendecagonal */
        //Polygon hende = createHende(x, y, 40, 40); // 課題2-1 
        Polygon hende = createHende(x, y, w, h);
        g2.setColor (getFillColor());
        g2.fillPolygon (hende);
        g2.setColor (getLineColor());
        g2.drawPolygon(hende);

        //draw the "select rectangle"
        if (isSelected) {
            super.draw(g);
        }  
    }

    private Polygon createHende(int x, int y, int w, int h) {
        // hendecagonal is drawed in a rectangle
        int n = 11; 
        Polygon polygon = new Polygon();
        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI / n * i;
            int px = (int) (x + w / 2 + (w / 2) * Math.cos(angle));
            int py = (int) (y + h / 2 + (h / 2) * Math.sin(angle));
            polygon.addPoint(px, py);
        }
        return polygon;
    }
}
     


