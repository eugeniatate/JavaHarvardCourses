import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This program represents a Java application using Swing graphics that draws the face of a
*  clock, showing the time that the user enters in two JTextField areas (one
*   for the hours, one for the minutes).
* Reference used for trigonometry used in logic: https://forums.oracle.com/ords/apexds/post/how-to-create-a-clock-using-swing-0522 
* @author   Eugenia Tate
* @version  Last Modified 04/15/2025
 */

public class Clock {
    public static void main(String[] args) {
        // Instantiate MyClock and add all the components to the main window frame
        JFrame frame = new JFrame("My Awesome Clock");
        MyClock clockPanel = new MyClock();

        JTextField hourField = new JTextField(2);
        JTextField minuteField = new JTextField(2);
        JButton setTimeButton = new JButton("Set Time");

        JPanel inputTimePanel = new JPanel();
        inputTimePanel.add(new JLabel("Hour:"));
        inputTimePanel.add(hourField);
        inputTimePanel.add(new JLabel("Minute:"));
        inputTimePanel.add(minuteField);
        inputTimePanel.add(setTimeButton);

        // ACTION LISTENER
        // Reads user input when button is clicked and validates it’s a number and within proper ranges.
        setTimeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    int hour = Integer.parseInt(hourField.getText());
                    int minute = Integer.parseInt(minuteField.getText());
            
                    if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid hour (0-23) and minute (0-59).");
                        return;
                    }
                    // if user input is valid - calls a helper setTime() method
                    clockPanel.setTime(hour, minute);
                } 
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter numbers only.");
                }
            }
        });
            
        frame.setLayout(new BorderLayout());
        frame.add(clockPanel, BorderLayout.CENTER);
        frame.add(inputTimePanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);
    }
}

class MyClock extends JPanel {

    private int hour = 0;
    private int minute = 0;

    public MyClock() {
        setPreferredSize(new Dimension(400, 400));
    }

    // converts hour to 12 hour format and redraws the clock 
    public void setTime(int hour, int minute) {
        this.hour = hour % 12;
        this.minute = minute % 60;
        // repaints the clock every time Set Time is clicked
        repaint();
    }

    protected void paintComponent(Graphics g) {
        drawClock((Graphics2D) g);
    }

    private void drawClock(Graphics2D g2d) {
        int width = this.getWidth();
        int height = this.getHeight();
        // calculates the radius while ensuring the clock does not touch the edges of the window by giving it some padding 
        int radius = Math.min(width, height) / 2 - 20;
        // center coordinates for the clock
        int centerX = width / 2;
        int centerY = height / 2;
    
        // draws clock circle
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
    
        // calculate angles using trigonometry 
        // clock is 360 degrees which means hour hand moves 30 degrees every hour (360 / 12) + a little more based on minutes 
        // since 12 oclock is at the top we substract 90 to ensure proper hand positioning 
        double hourAngle = Math.toRadians((hour % 12 + minute / 60.0) * 30 - 90);
        // minute hand moves 6 degrees per minute (360 degrees / 60 min)
        double minuteAngle = Math.toRadians(minute * 6 - 90);
    
        // draw hands
        drawHand(g2d, centerX, centerY, hourAngle, radius * 0.5, 6);   
        drawHand(g2d, centerX, centerY, minuteAngle, radius * 0.75, 3); 

        // Draw tick marks by looping over 12 hrs
        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30 - 90);
            int outerX = centerX + (int)(Math.cos(angle) * radius);
            int outerY = centerY + (int)(Math.sin(angle) * radius);
            int innerX = centerX + (int)(Math.cos(angle) * (radius - 10));
            int innerY = centerY + (int)(Math.sin(angle) * (radius - 10));
            g2d.drawLine(innerX, innerY, outerX, outerY);
        }
    }
    
    private void drawHand(Graphics2D g2d, int x, int y, double angle, double length, int thickness) {
        // calculates the end point of the hand 
        int endX = x + (int) (Math.cos(angle) * length);
        int endY = y + (int) (Math.sin(angle) * length);
        // draws a line from the center to that point using the specified thickness
        g2d.setStroke(new BasicStroke(thickness));
        g2d.drawLine(x, y, endX, endY);
    }
}
