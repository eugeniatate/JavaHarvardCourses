/**
 * This class represents a program that recursively computes x to the power of n
 * in a more efficient way by diving an even exponent by 2.
 * 
 * @author Eugenia Tate
 * @version Last Modified 02/16/2025
 */
public class Power {

    public static void main(String[] args) {

    }

    /**
     * /*
     * This method recives a double representation of a number and an integer to
     * represent the exponent.
     * It recursively computes x to the power of n by taking advantage of a more
     * efficient algorithm that
     * divides an even exponent by 2 if it is an even number.
     * 
     * @param x a floating value of a number
     * @param n an integer value of the exponent
     * @return a floating value of the computation result
     */
    public static double power(double x, int n) {
        // The modified power() method will be called a total of 11 times to compute
        // power( foobar, 1024 ).
        if (n == 0)
            return 1.0;
        else if (n > 0 && n % 2 == 0) {
            double preliminaryResult;
            preliminaryResult = x * power(x, ((n - 1) / 2));
            return (preliminaryResult * preliminaryResult);
        } else if (n > 0 && n % 2 != 0)
            return x * power(x, n - 1);
        else
            return 1.0 / power(x, -n);
    }

}
