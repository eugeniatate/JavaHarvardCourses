import javax.swing.*;
import java.awt.*;

/**
 * This program represents a Java Swing drawing of the Olympic rings.
 * 
* @author   Eugenia Tate
* @version  Last Modified 04/16/2025
 */
public class Olympics {
    public static void main(String[] args) {
        // instantiate OlympicRingsFrame and make it visible
        OlympicRingsFrame olympicRings = new OlympicRingsFrame();
        olympicRings.setVisible(true);
    }
}
// MAIN WINDOW
class OlympicRingsFrame extends JFrame {

    // CONSTRUCTOR
    public OlympicRingsFrame() {
        this.setTitle("Olympic Rings");
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(new RingsPanel());
    }
}

// RINGS PANEL to hold drawing
class RingsPanel extends JPanel {
    // overriding paintComponent to implement necesary drawing logic
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //setting thickness for rings 
        g2.setStroke(new BasicStroke(10)); 
        // drawing all 5 rings in different colors at different start coordinates 
        drawRing(g2, 50, 50, Color.BLUE);   
        drawRing(g2, 160, 50, Color.BLACK); 
        drawRing(g2, 270, 50, Color.RED);   
        drawRing(g2, 105, 105, Color.YELLOW); 
        drawRing(g2, 215, 105, Color.GREEN);  
    }

    // helper method that sets the color and draws the rings using specified x&y coordinates and color
    private void drawRing(Graphics2D g2, int x, int y, Color color) {
        g2.setColor(color);
        g2.drawOval(x, y, 100, 100);
    }
}

