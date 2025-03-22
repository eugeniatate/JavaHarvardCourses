/**
 * This class contains a method that recursively outputs a
 * triangular pattern in reverse order (the narrowest angle down).
 *
 * @author Eugenia Tate
 * @version Last modified 02/16/2025
 */
class recTriangle {
    public static void main(String[] args) {

    }

    /**
     * This method will receive an integer to represent the height of a triangle
     * that recursively outputs a triangular pattern in reverse order (the narrowest
     * angle down).
     * 
     * @param s integer value received
     */
    public static void printTriangle(int s) {
        if (s < 1)
            return;

        for (int i = 0; i < s; i++) {
            System.out.print("[]");
        }
        System.out.println();
        printTriangle(s - 1);
    }
}