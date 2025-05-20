import java.io.IOException;
import java.util.*;

/**
 * This program outputs all of its command-line arguments, one per line, in reverse order. 
 * 
 * @author Eugenia Tate
 * @version Last modified 03/22/2025
 */
public class Prob1 {
 
    public static void main (String[] args) throws IOException {
        ArrayList <String> list = new ArrayList<>();
        for (String s: args) {
            list.add(s);
        }
        for (int i = list.size()-1; i>=0; i--) {
            System.out.println(list.get(i));
        }
    }
}