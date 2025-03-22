/**
 * This class represents a recursive solution to sum of the first n reciprocals:
 * sum of (1 +1/2 + 1/3 + 1/4 + ...1/n)
 * 
 * @author Eugenia Tate
 * @version Last Modified 02/16/2025
 */
public class RecursiveSum {

    public static void main(String[] args) {
        System.out.println("Sum of 1 reciprocals = " + sumTo(1));
        System.out.println("Sum of 0 reciprocals = " + sumTo(0));
        System.out.println("Sum of 2 reciprocals = " + sumTo(2));
        System.out.println("Sum of 3 reciprocals = " + sumTo(3));
        System.out.println("Sum of 5 reciprocals = " + sumTo(5));
        System.out.println("Sum of negative value reciprocals returns InvalidArgumentException: ");
        sumTo(-1);
    }

    /**
     * This method recursively calculates the sum of the first n reciprocals
     * 
     * @param n int value of the number of reciprocals
     * @return floating value of the sum of n reciprocals
     */
    public static double sumTo(int n) {
        double sum = 0.0;
        if (n == 0)
            return sum;

        if (n < 0) {
            throw new IllegalArgumentException();
        }

        if (n == 1) {
            sum = 1 / 1;
        } else {
            sum += sumTo(n - 1) + (1 / (double) n);
        }
        return sum;
    }
}