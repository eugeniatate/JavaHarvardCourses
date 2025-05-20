import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This program reads a file of numbers, removes duplicates and prints only uniques number into output file. 
 *
 * @author Eugenia Tate
 * @version Last Modified 03/22/2025
 */

public class NoDuplicates {

    public static void main (String [] args) {
        // arraylist to store unique numbers without duplicates
        ArrayList <Integer> intList = new ArrayList<>(); 
        Scanner console = new Scanner(System.in);
        System.out.println("What is the input file name?");
        File inputFile = new File(console.next());
        System.out.println("What is the output file name?");
        File outputFile = new File(console.next());
       
        try {
            Scanner input = new Scanner(inputFile);
            PrintStream output = new PrintStream(outputFile);
                
            int num = input.nextInt();
            System.out.println("ORIGINAL FILE " + inputFile.getName() + " contains the following values");
            System.out.println(num);
            intList.add(num);
            output.println(num);
                
            while (input.hasNextInt()) {
                num = input.nextInt();
                System.out.println(num);
                // if num is not in the list yet, add it in 
                if (!intList.contains(num)) {
                    intList.add(num);
                    output.println(num);
                }
                // if num is already in the list - skip and move to next int
                continue;
            }
                
            System.out.println("OUTPUT FILE: " + outputFile.getName() + " contains the following values");
    
            for (int i: intList) {
                System.out.println(i);
            }
            output.close();
            input.close();
        }
        // program requirements do not specidy re-prompting user for another file in case of exceptions
        // assumed that re-prompting was not needed 
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.getStackTrace();
        }
        catch (InputMismatchException ime) {
            System.out.println("File NOT containing numbers.");
            ime.getStackTrace();
        }
        console.close();
    }
}
