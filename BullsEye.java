import javax.swing.*;
import java.awt.*;
/**
 * This program draws a black and white bulls eye. 
 * @author Eugenia Tate
 * @version Last Modified 04/15/2025
 */
public class BullsEye {
    public static void main(String[] args) {
        // Construct the window
        BullsEyeDrawing bullsEye = new BullsEyeDrawing("Bull's Eye");
        // Make it visible
        bullsEye.setVisible(true);
    }
}

// MAIN WINDOW
class BullsEyeDrawing extends JFrame {
   
    BullsEyePanel bullsEyePanel = new BullsEyePanel();

    public BullsEyeDrawing (String title) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null); 
        this.add(bullsEyePanel);
    }
}

// DRAWING AREA
class BullsEyePanel extends JPanel {
    // overrides paintComponent of Graphics object 
    protected void paintComponent(Graphics g) {
        drawBullsEye(g, this.getWidth() / 2, this.getHeight() / 2, 150, 5);
    }
    
    /**
     *  helper method that draws alternating black and white circles that shrink in size 
     * @param   g           Graphics object 
     * @param   centerx     the X coordinate of top-left corner of the bounding box for the circle
     * @param   centerY     the Y coordinate of top-left corner of the bounding box for the circle
     * @param   maxRadius   integer representing radius of the outer ring (biggest ring)
     * @param   numRings    integer representing number of rings 
     * 
     * */ 
    private void drawBullsEye(Graphics g, int centerX, int centerY, int maxRadius, int numRings) {
        // sets distance between each ring
        int distance = maxRadius / numRings;
        // loop through each ring and calculate the position and size for each ring
        for (int i = 0; i < numRings; i++) {
            int radius = maxRadius - (i * distance);
            int x = centerX - radius;
            int y = centerY - radius;
            int diameter = radius * 2;
            // alternating colors
            if (i % 2 == 0) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.WHITE);
            }
            // filling circle at given coordinates with given height and width 
            g.fillOval(x, y, diameter, diameter);
        }
    }
}

