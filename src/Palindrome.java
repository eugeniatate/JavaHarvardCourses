import java.util.Scanner;

/**
 * This class represents a program that recursivley checks if a supplied by user
 * input sentence is a palindrome — a String that is equal to itself when you
 * reverse its characters.
 *
 * @author Eugenia Tate
 * @version Last Modified 02/16/2025
 */
public class Palindrome {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please input a sentence or a word to check if it is a palindrome: ");
        String s = keyboard.nextLine();
        System.out.printf("%s is a Palindrome: %b\n", s, isPalindrome(s));
        keyboard.close();
    }

    /**
     * This method recursivley checks if a sentence is a palindrome — a String that
     * is equal to itself when you reverse its characters-
     * and outputs the boolean result of the check.
     * 
     * @param s string value of a sentence to evaluate
     * @return boolean value of the evalution result
     */
    public static boolean isPalindrome(String s) {
        boolean palindrome;
        s = s.toLowerCase().trim(); // trimming off witespace and changing to lower case
        int stringLength = s.length();
        // BASE case: we know if a string is one or fewer chars it is a palindrome
        if (stringLength <= 1) {
            palindrome = true;
        }
        // RECURSIVE CASE: if a string contains more than 1 character
        else {
            int start = 0;
            int end = stringLength - 1;
            char start_char = s.charAt(start);
            // chacking to see if the first char of the string is a letter or a digit and
            // assigning boolean values
            boolean isLetterOrDigit = Character.isLetterOrDigit(start_char);
            // boolean isDigit = Character.isDigit(start_char);
            // loop over every char in a string and set a start char variable ONLY to an
            // actual first letter or digit,
            // skipping over anything that is not letter or digit
            while (!isLetterOrDigit) {
                if (start >= end) {
                    break;
                } else {
                    start += 1;
                    start_char = s.charAt(start);
                    isLetterOrDigit = Character.isLetterOrDigit(start_char);
                }
            }
            start_char = s.charAt(start);

            char end_char = s.charAt(end);
            // chacking to see if the last char of the string is a letter or a digit and
            // assigning boolean values
            boolean isEndCharLetterOrDigit = Character.isLetterOrDigit(end_char);
            // loop over every char in a string and set an end char variable ONLY to an
            // actual last letter or digit,
            // skipping over anything that is not letter or digit
            while (!isEndCharLetterOrDigit) {
                if (end <= start) {
                    break;
                } else {
                    end -= 1;
                    end_char = s.charAt(end);
                    isEndCharLetterOrDigit = Character.isLetterOrDigit(end_char);
                }
            }

            // comparing if start and end chars are equal
            // if equal, it is a palindrome and start index is bumped up
            // we then call a recursive () on a substring to determine if rest is a
            // palindrome
            if (start_char == end_char) {
                palindrome = true;
                start += 1;
                if (end > start) {
                    palindrome = isPalindrome(s.substring(start, end));
                }
                // we reached the middle of a string and it is a palindrome
                else {
                    palindrome = true;
                }
            } else {
                palindrome = false;
            }
        }
        return palindrome;
    }
}
