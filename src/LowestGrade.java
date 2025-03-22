/**
 * This class represents a non-recursive program that, given a list of scores of
 * a student, removes the lowest
 * and returns the same list without that lowest score in it. The program then
 * also prints the scores
 * in format [xx, yy, zz...].
 * 
 * @author Eugenia Tate
 * @version Last Modified 02/16/2025
 */
public class LowestGrade {

    public static void main(String[] args) {
        int[] a = removeLowest(23, 90, 47, 55, 88);
        int[] b = removeLowest(85);
        int[] c = removeLowest();
        int[] d = removeLowest(59, 92, 93, 47, 88, 47);

        System.out.printf(" \"a\" should not retain score 23 and should contain 90, 47, 55, 88--> %s \n",
                arrayPrint(a));
        System.out.printf(
                " \"b\" should not rid of any scores since there is only one and should contain 85 only --> %s \n",
                arrayPrint(b));
        System.out.printf(" \"c\" should remain empty --> %s \n", arrayPrint(c));
        System.out.printf(" \"d\" should remove duplicate 47 and should contain 59, 92, 93, 88, 47--> %s \n",
                arrayPrint(d));
    }

    /**
     * This method removes the lowest score in the given array of scoes representted
     * by variable length param
     * 
     * @param scores variable length array of scores of type integer
     * @return integer array of scores
     */
    public static int[] removeLowest(int... scores) {
        // if there is no scores, then an empty array is returned
        if (scores.length == 0) {
            return new int[0];
        }
        // if there is only one score then a new array with a single integer (score) is
        // returned
        else if (scores.length == 1) {
            int[] newScores = new int[1];
            newScores[0] = scores[0];
            return newScores;
        }
        // scores contains more than 1 score
        else {
            int minIndex = 0;
            // finding at which index is the lowest score in scores
            for (int i = 1; i < scores.length; i++) {
                if (scores[i] < scores[minIndex]) {
                    minIndex = i;
                }
            }
            // copying over scores from scores into a new array skipping the lowest score
            int[] newScores = new int[scores.length - 1];
            int newIndex = 0;
            for (int j = 0; j < scores.length; j++) {
                if (j != minIndex) {
                    newScores[newIndex] = scores[j];
                    newIndex++;
                }
            }
            return newScores;
        }
    }

    /**
     * This method prints an array of integers without relying on Arrays.toString()
     * using a popular fencepost algorithm
     * 
     * @param a integer array
     * @return strign representation of an int array
     */
    public static String arrayPrint(int[] a) {
        String result = "";
        result += "[";

        if (a.length == 1)
            result += a[0];
        else {
            for (int i = 0; i < a.length; i++) {
                result += a[i];
                if (i < a.length - 1) {
                    result += ",";
                }
            }
        }
        result += "]";

        return result;
    }
}