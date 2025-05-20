import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Swing application representing a currency converter between euros and U.S. dollars, and vice versa. 
 * For this exercise, a conversion rate of 1 euro = 1.13 U.S. dollars was used.
 * @author Eugenia Tate
 * @version Last Modified 04/15/2025
 */
public class Currency {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 120);
        frame.setLocationRelativeTo(null); 
        frame.add(new CurrencyConverterPanel());
        frame.setVisible(true);
    }
}

class CurrencyConverterPanel extends JPanel {
    private static final double CONVERSION_RATE_EUR_USD = 1.13;
    private JTextField euroField = new JTextField(10);
    private JTextField usdField = new JTextField(10);
    private JButton toUsdButton = new JButton(">");
    private JButton toEurButton = new JButton("<");

    // CONSTRUCTOR
    public CurrencyConverterPanel() {
        layoutComponents();
        addActionListeners();
    }

    // LAYOUT COMPONENTS
    public void layoutComponents(){
        setLayout(new FlowLayout());
        this.add(new JLabel("Euros:"));
        this.add(euroField);
        this.add(toUsdButton);
        this.add(toEurButton);
        this.add(new JLabel("USD:"));
        this.add(usdField);
    }

    // ADD LISTENERS
    public void addActionListeners(){
        toUsdButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                convertToUsd();
            }
        });
        toEurButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent ae) {
                convertToEur();
            }
        });
    }

    // BUSINESS LOGIC
    private void convertToUsd() {
        try {
            double euros = Double.parseDouble(euroField.getText());
            double dollars = euros * CONVERSION_RATE_EUR_USD;
            //setting formatted text for the field dollars
            usdField.setText(String.format("%.2f", dollars));
        } catch (NumberFormatException nfe) {
            this.showError("Please enter a valid number in the Euro field.");
        }
    }

    private void convertToEur() {
        try {
            double dollars = Double.parseDouble(usdField.getText());
            double euros = dollars / CONVERSION_RATE_EUR_USD;
            //setting formatted text for the field Euros
            euroField.setText(String.format("%.2f", euros));
        } catch (NumberFormatException nfe) {
            this.showError("Please enter a valid number in the USD field.");
        }
    }
    /**
     * Outputs an error message to the user using Message Dialog
     * @param message   String for error message to display to the user
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}

