import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This program represents classic Caesar Cipher to encrypt and decrypt words by reading them from a file 
 * and outputing the result into a new file. A Caesar Cipher is one of the earliest forms of an alphabetic cipher for creating “secret
 * messages.” A Caesar Cipher works by substituting for each letter in the original
 * “plaintext” a letter obtained by shifting the alphabet by a constant number.
 *
 * @author Eugenia Tate
 * @version Last Modified 03/22/2025
 */
public class CaesarCipher {
    private static final char [] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main (String [] args) {
        // part of the program that handles user input 
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to Caesar Cipher!\n");
        System.out.println("Enter 1 to Encipher, 2 to Decipher (-1 to exit): ");
        int choice = console.nextInt();
        System.out.println("What shift should i use? ");
        int shift = console.nextInt();
        System.out.println("What is the input file name? ");
        File inputFile = new File(console.next());
        System.out.println("What is the output file name? ");
        File outputFile = new File(console.next());

        try { 
            Scanner input = new Scanner(inputFile);
            String result = ""; 
            PrintStream output = new PrintStream(outputFile);
            while (input.hasNextLine()) {
                String s = input.nextLine();
                if (choice == 1) {
                    result = caesarEncipher(s,shift);
                }
                else if (choice == 2) {
                    result = caesarDecipher(s, shift);
                }
                else {
                    System.exit(0);
                }
                output.println(result);
            }
            input.close();
            output.close();
            console.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("DONE!");
    }

    /**
     * This is a method that encodes a line of user's file input by using a shift also specified by the user
     * 
     * @param   string representing a line of test file provided by user
     * @param   shift  represents number of characters to use for encryption to replace each character by the one that is 
     *          shift places away
     * @return  string representing encrypted string 
     */
    public static String caesarEncipher (String input, int shift) {
        String cipherText = "";
        StringBuilder sb = new StringBuilder(input);
        ArrayList <Character> alphabet = new ArrayList<>(); 
        for (char c: ALPHABET) {
            alphabet.add(c);
        }
        // start encoding algorithm
        for (int i = 0; i < sb.length(); i++) {
            // if character is a letter and is uppercase - continue encoding algorithm 
            if (Character.isLetter(sb.charAt(i)) && Character.isUpperCase(sb.charAt(i))) {
                int shifted_position = alphabet.indexOf(sb.charAt(i)) + shift;
                shifted_position %= ALPHABET.length;
                cipherText += ALPHABET[shifted_position];
            }
            // if not an uppercase letter, skip and just leave it the way it is in a string 
            else {
                cipherText += sb.charAt(i);
            }
        }
        return cipherText;
    }

     /**
     * This is a method that decodes a line of user's specified file by using a shift number also specified by the user
     * 
     * @param   string representing a line of test file provided by user
     * @param   shift  represents number of characters to use for decryption to replace each character by the one that is 
     *          shift places away
     * @return  string representing decrypted string 
     */
    public static String caesarDecipher (String input, int shift) {
        String decipherText = "";
        // reuses encrypting method by using the shift number but turning it negative 
        shift *=-1;
        decipherText = caesarEncipher(input,shift);
        return decipherText;
    }
}
