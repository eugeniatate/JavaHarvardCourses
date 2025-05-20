import javax.swing.JOptionPane;

/**
 * This simple SWING program prompts user for their age and outputs message saying if they are old or not.
 * @author Eugenia Tate
 * @version Last Modified 04/15/2025
 */
public class Age {
    public static void main (String [] args) {
        String userInput;
        int age = 0;
        boolean valid = false;
        // re-prompt user for a valid age input in case user tries to enter something invalid 
        while (!valid){
            try {
                userInput = JOptionPane.showInputDialog("What is your age?");
                age = Integer.parseInt(userInput);
                valid = true;
                if (age > 40) {
                    JOptionPane.showMessageDialog(null, "you are too old!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "you are still young!");
                }
            }
            catch (NumberFormatException nfe) {
                JOptionPane.showInputDialog("Re-enter your age using numbers");
                valid = false;
            }
        }  
    }
}
