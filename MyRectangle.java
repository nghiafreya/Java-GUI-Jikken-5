    import java.awt.*;
    public class MyRectangle extends MyDrawing {
        
        /*課題2-3 */
        public MyRectangle (int xpt, int ypt, int width, int height, Color lineColor, Color fillColor) {
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
                g2.setColor(Color.black);
                g2.fillRect(x + shadowOffset, y + shadowOffset, w, h);
                g2.setColor (Color.black);
                g2.drawRect (x + shadowOffset, y + shadowOffset, w, h);
            } 

            /*Create Dash lines */
            if (getDashed()) {
                g2.setStroke(new MyDashStroke(getLineWidth()));
            } else {
                g2.setStroke (new BasicStroke (getLineWidth()));
            }   
            
            
            /*Create real Rectangle */
            g2.setColor (getFillColor());
            g2.fillRect (x, y, w, h); 
            g2.setColor (getLineColor());
            g2.drawRect (x, y, w, h);

            //draw the "select rectangle"
            if (isSelected) {
                super.draw(g);
            }            
        }
    }
