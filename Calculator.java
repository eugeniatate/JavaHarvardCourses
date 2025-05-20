import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This program creates a GUI Calculator application using Swing similar to any other calculator application. 
 * Backend logic is contained within CalcBackend.java while this file handles the UI only. 
 * 
 * EXTRA CREDIT OPTION #7: usage of GridBagLayout 
 * 
 * @author Eugenia Tate
 * @version Last Modified 04/15/2025
 */

public class Calculator {
    public static void main(String[] args) {
        // instantiate MyCalculator object and set it to visible
        MyCalculator myCalculator = new MyCalculator();
        myCalculator.setVisible(true);
    }
}

// MAIN CALCULATOR WINDOW
class MyCalculator extends JFrame {
    private final JTextField displayField;
    private final CalcBackend calcBackend;

    // CONSTRUCTOR FOR MYCALCULATOR (JFRAME)
    public MyCalculator() {
        calcBackend = new CalcBackend();
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        displayField = new JTextField("0");
        displayField.setFont(new Font("SansSerif", Font.BOLD, 30));
        displayField.setBackground(Color.YELLOW);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        // calls a method to add components 
        this.addComponents();
        this.setSize(350, 450);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }

    // ADDING LAYOUT COMPONENTS
    public void addComponents(){
        this.add(displayField, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbConstraints = new GridBagConstraints();
        gbConstraints.fill = GridBagConstraints.BOTH;
        gbConstraints.insets = new Insets(2, 2, 2, 2);
        // layout of calculator buttons
        String[][] calcButtons = {
            {"C", "√", "/", "*"},
            {"7", "8", "9", "-"},
            {"4", "5", "6", "+"},
            {"1", "2", "3", "="},
            {"0", "0", ".", "="}
        };
        // nested loop to go through the grid and create buttons
        for (int row = 0; row < calcButtons.length; row++) {
            for (int col = 0; col < calcButtons[row].length; col++) {
                String label = calcButtons[row][col];
                if (!label.isEmpty()) {
                    // skip duplicates of 0 and =
                    if ((label.equals("0") && col == 1) || (label.equals("=") && row == 4)) continue;

                    JButton btn = new JButton(label);
                    btn.setFont(new Font("SansSerif", Font.BOLD, 20));
                    // adding action listener for every button 
                    btn.addActionListener(new ActionListener() {
                        public void actionPerformed ( ActionEvent ae) {
                            calcBackend.feedChar(label.charAt(0));
                            displayField.setText(calcBackend.getDisplayVal());
                        }
                    });

                    gbConstraints.gridx = col;
                    gbConstraints.gridy = row;
                    // if button is 0 or = apply custom width/height
                    if (label.equals("0")) {
                        gbConstraints.gridwidth = 2; 
                    } 
                    else if (label.equals("=") && row == 3) {
                        gbConstraints.gridheight = 2; 
                    } 
                    // otherwise make all buttons same width and height
                    else {
                        gbConstraints.gridwidth = 1;
                        gbConstraints.gridheight = 1;
                    }
                    // adds button to the button panel of the calculator
                    buttonPanel.add(btn, new GridBagConstraints(
                        gbConstraints.gridx, gbConstraints.gridy, gbConstraints.gridwidth, gbConstraints.gridheight,
                        1.0, 1.0,
                        GridBagConstraints.CENTER,
                        GridBagConstraints.BOTH,
                        new Insets(2, 2, 2, 2),
                        0, 0
                    ));
                }
            }
        }
        //adds buttons panel to the frame
        add(buttonPanel, BorderLayout.CENTER);
    }
}
