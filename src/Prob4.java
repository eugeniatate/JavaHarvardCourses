import java.util.*;

/**
 * This program asks the user to input 2 integers and then divides them.
 * 
 * @author Eugenia Tate
 * @version Last modified 03/22/2025
 */

public class Prob4 {
    public static void main (String [] args) { 
        Scanner keyboard = new Scanner (System.in);
        int n1, n2, r;
        boolean valid = false; 
        while (!valid)  {
            try {
                System.out.print("Type an int: ");
                n1 = keyboard.nextInt();
                System.out.print("Now type another int: ");
                n2 = keyboard.nextInt();
                r = n1 / n2;
                System.out.println(r + "");
                valid = true;
            }
            catch (InputMismatchException ime) {
                System.out.println("Please try to use integers instead of strings.");
                valid = false;
                keyboard.next();
            }
            catch (ArithmeticException ae) {
                System.out.println("Can not divide by zero. Please try again.");
                valid = false;
            }
        } 
        keyboard.close();
    }
}
