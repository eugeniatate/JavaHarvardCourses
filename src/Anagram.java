/**
 * This class represents a program that evaluates 2 strings to determine if 
 * they are anagrams - they contain the same letters and the same number of each letter.
 * 
 * @author      Eugenia Tate
 * 
 */
 
import java.util.Arrays;

public class Anagram {
    private static final char [] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static void main (String [] args) {
        String a = "gin and vermouth";
        String b = "!hung over, damn it!";
        System.out.printf("\'%s\' and \'%s\' are anagrams: %b\n", a, b, isAnagram(a, b));
        String c = "ginnnn and vermouth";
        String d = "hung overrrr, damn it";
        System.out.printf("\'%s\' and \'%s\' are anagrams: %b\n", c, d, isAnagram(c, d));
        String e = "spot";
        String f = "stop!";
        System.out.printf("\'%s\' and \'%s\' are anagrams: %b\n", e, f, isAnagram(e, f));
        String g = "spa";
        String i = "wasp";
        System.out.printf("\'%s\' and \'%s\' are anagrams: %b\n", g, i, isAnagram(g, i));
    }

    /**
     * This method performs all necessary evaluations by calling helper methods 
     * and returns boolean value of whether 2 strings are anagrams
     * 
     * @param s1        String 1
     * @param s2        String 2
     * @return          boolean value of string comparison in terms of Anagram 
     */
    public static boolean isAnagram(String s1, String s2) {
        //trimming off whitespace and putting into lowercase both strings
        // because strings can be case-insensitive
        s1 = s1.toLowerCase().trim(); 
        s2 = s2.toLowerCase().trim();
        //creating 2 alphabet-sized char arrays
        char [] string1 = new char [ALPHABET.length];
        char [] string2 = new char [ALPHABET.length];
        //count frequencies of each letter appearing in both strings
        count(string1, s1);
        count(string2, s2);
        //compare 2 char strings for equality: 
        //if the same indexes representing characters have the same counts - the original strings are anagrams
        return Arrays.equals(string1, string2);
    }

    /**
     * Helper method that locates and returns an index for the char param inside array of characters alphabet
     * 
     * @param alphabet
     * @param t     character value 
     * @return      integer value of index for character param t
     */
    public static int findIndex (char [] alphabet, char t) {

        int foundIndex = 0;
        for (int c = 0; c < alphabet.length; c ++) {
            if (alphabet[c] == t) {
                foundIndex = c;
            }
        }
        return foundIndex; 
    }

    /**
     * Helper method that counts occurrences of each char in a string and stores them in a char array
     * 
     * @param charArray     array of chars
     * @param someString    String 
     */
    private static void count (char[] charArray, String someString) {
        for (int i = 0; i < someString.length(); i ++ ) {
            if (Character.isLetter(someString.charAt(i))) {
                char target = someString.charAt(i);
                int position = findIndex(ALPHABET, target);
                charArray[position] ++; 
            }
            //skip non-letter characters in a string
            else {
                continue;
            }
        }
    }
}