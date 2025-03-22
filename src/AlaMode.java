import java.util.Arrays;

/**
 * This class represents a program that non-recursively evaluates an array of
 * integers
 * and returns the integer that has most frequent appearance.
 * Assumptions :
 * 1) Array has at least 1 integer
 * 2) There can be no "ties"
 * 
 * @author Eugenia Tate
 * @version Last Modified 02/16/2025
 */
public class AlaMode {

    public static void main(String[] args) {
        int[] a = { 0, 11, 12, 13, 15, 16, 12, 4, 3, 11, 12, 13, 11, 0, 12 };
        int[] b = { 1, 1 };
        int[] c = { 11, 11, 5, 6, 7, 3, 2, 3, 11, 4 };
        System.out.print(Arrays.toString(a));
        System.out.printf(" most frequent number that appears 4 times is (12) -->  %d \n", mode(a));
        System.out.print(Arrays.toString(b));
        System.out.printf(" most frequent number that appears 2 times is (1) -->  %d \n", mode(b));
        System.out.print(Arrays.toString(c));
        System.out.printf(" most frequent number that appears 3 times is (11) -->  %d \n", mode(c));
    }

    /**
     * This method non-recursively evaluates an array of integers a
     * and returns the integer that has most frequent appearance.
     * 
     * @param arrayOfInts array of integers
     * @return integer value of the most frequently encountered number
     */
    public static int mode(int[] arrayOfInts) {
        int mostFrequent = 0;
        // find the largest number in the array param to later create an array of size
        // max +1
        int max = max(arrayOfInts);
        // create a new array of length that is one more than the largest number in
        // array
        int[] temp = new int[max + 1];

        if (arrayOfInts.length == 1) {
            mostFrequent = arrayOfInts[0];
        }

        else {
            // tally up how mnay times each number appears in array param and increment
            // the value of temp array value at index representing the number
            count(arrayOfInts, temp);
            // calculating the most frequently seen number
            mostFrequent = 0;
            for (int j = 1; j < temp.length; j++) {
                if (temp[j] > temp[mostFrequent]) {
                    mostFrequent = j;
                }
            }
        }
        return mostFrequent;
    }

    /**
     * This method represents a classic way of finding the largest number in the
     * array of ints
     * 
     * @param someArray int array
     * @return decimal value of the largest number
     */
    private static int max(int[] someArray) {
        int max = 0;
        for (int i = 0; i < someArray.length; i++) {
            if (someArray[i] > max) {
                max = someArray[i];
            }
        }
        return max;
    }

    /**
     * This method tallies up how many times each number appears in originalArray
     * and increments
     * the index representing the number by the number of the number of appearances
     * 
     * @param originalArray array of integers (original)
     * @param counter       array of integers (counter array, only holds counta of
     *                      appearances)
     */
    private static void count(int[] originalArray, int[] counter) {
        for (int i = 0; i < originalArray.length; i++) {
            if (originalArray[i] > 0) {
                int position = originalArray[i];
                counter[position]++;
            } else if (originalArray[i] == 0) {
                counter[0]++;
            } else {
                continue;
            }
        }
    }
}