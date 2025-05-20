import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program represents exam analysis tool that takes in allowed exam answer options for each question, 
 * processes a file with multiple students' exam answers, and analyzes each student's results as well as questions 
 * and how they were answered by each student. 
 *
 * @author Eugenia Tate
 * @version Last Modified 03/22/2025
 */
public class ExamAnalysis {

    protected static final Character [] allowedAnswers = {'A','B', 'C', 'D', 'E'};
    public static void main (String [] args) {

         // adding 5 allowed answers to an arraylist for easier processing later on
         ArrayList <Character> a = new ArrayList<>();
         for (int j = 0; j < allowedAnswers.length; j++){
            a.add(allowedAnswers[j]);
        }
        Scanner console = new Scanner(System.in);

        // storing correct answers to the exam in a StringBuilder 
        StringBuilder correctAnswers = getAnswers(console, a);
    
        // storing an exam results file in a file variable 
        File inputFile = getResultsFile(console);
        ArrayList <StringBuilder> studentAnswers = new ArrayList<>();
        try {
            Scanner input = new Scanner(inputFile);
            int studentCounter = 0;
            // processing each line of the results file until it reaches no more available lines to read
            while (input.hasNextLine() ) {
                StringBuilder answers = new StringBuilder(input.nextLine());
                // checks if a results file line is valid and of correct length (matching the number of correct answers provided by the user )
                if (isAnswerValid(answers, a) && correctAnswers.length() == answers.length()) {
                    // if results line is valid and of correct length, increase number of students, print their responses and store each 
                    // in the arraylist for further processing
                    studentCounter++;
                    System.out.print("Student #" +studentCounter + "\'s responses: ");
                    System.out.println(answers);
                    studentAnswers.add(answers); 
                }
                // if a results line is invalid or of wrong length, skip it and not add it to the arraylist
                else {
                    continue;
                }
            }
            System.out.println("\nWe have reached \"end of file\"!"); 
            System.out.println("\nThank you for the data on " + studentCounter + " students. Here's the analysis:\n");
            input.close();
        }
        catch (FileNotFoundException e )  {
            e.getStackTrace();
        }
        analyzeData(studentAnswers, correctAnswers);
        questionAnalysis(correctAnswers, studentAnswers);
        console.close();
    }

    /**
     * This method reads user input to retrieve correct responses to the exam. It checks to see if user types an 
     * invalid result option that does not match provided allowed options. If user types something invalid - the program
     * prompts them for a new value. 
     * 
     * @param   console scanner to accept user input
     * @param   a       arraylist representing allowed resuts options 
     * @return  stringbuilder object representing answers to the exam
     */
    public static StringBuilder getAnswers (Scanner console, ArrayList <Character> a) {
        StringBuilder sb = new StringBuilder();
        boolean valid = false;
        
        while(!valid) {
            System.out.println("Please type the correct answers to the exam questions, one after the other: ");
            sb = new StringBuilder(console.nextLine());
            for (int i = 0; i < sb.length()-1; i ++){
                // if user input is not within the allowed range of response options - prompt for new value 
                if (!a.contains(sb.charAt(i))) {
                    valid = false;
                    System.out.println("You entered the invalid answers key. Please try again");
                    break;
                }
                else {
                    valid = true;
                    continue;
                }
            }
        }
        return sb;
    }

     /**
     * This method reads user input to retrieve a file with students' responses to the exam. It checks to see if user types an 
     * invalid file that does not open and can't be read, and also to ensure that it is a file and not a directory. 
     * If user types something invalid - the program prompts them for a new value for the file to read. 
     * 
     * @param   console scanner to accept user input
     * @return  inputFile -  the file wuth students' results         
     */
    public static File getResultsFile (Scanner console) {
        boolean validFile = false;
        System.out.println("Please enter a file name with students' answers to the exam: ");
        File inputFile = new File(console.next());
        while (!validFile){
            if (inputFile.isFile() && inputFile.canRead()){
                validFile = true; 
            } 
            else {
                validFile = false;
                System.out.println("Please try to enter correct file name this time:");
                inputFile = new File(console.next());
            }
        }
        return inputFile;
    }

    /**
     * This method analyzes students' answers to the exam questions by comparing them to the correct answers provided by the user. 
     * It then sorts and prints each student's response into correct, incorrect and blank response columns. 
     * 
     * @param   studentAnswers  arrayList of StringBuilder objects representing students' reponses to the exam
     * @param   correctAnswers  StringBuilder object representing correct responses to exam questions (answer key)        
     */
    public static void analyzeData (ArrayList <StringBuilder> studentAnswers, StringBuilder correctAnswers) {
        String studentNum = "Student #";
        String correct = "Correct";
        String incorrect = "Incorrect";
        String blank = "Blank";
        System.out.printf("%-17s%-17s%-17s%-17s\n", studentNum, correct, incorrect, blank);

        // calls prettifying supplemetory method to print underlying squiggles under column titles 
        String []tableHeaders = {studentNum, correct,incorrect,blank};
        printSquiggles(tableHeaders);
        System.out.println();

        // counts the number of correct, incorrect and blank responses of each student
        for (StringBuilder sb: studentAnswers) {
            int correct_num =0, incorrect_num =0, blank_num = 0;
            for (int i = 0; i < sb.length(); i++){
                // if student's response for question at i is equal to the answer in the key for the same question - 
                // adds to correct counter 
                if (sb.charAt(i) == correctAnswers.charAt(i)) {
                    correct_num++;
                }
                else if (sb.charAt(i) == ' ') {
                    blank_num ++; 
                }
                else {
                    incorrect_num ++;
                }
            }
            System.out.printf("%4d%17d%17d%17d\n\n", studentAnswers.indexOf(sb)+1, correct_num, incorrect_num, blank_num);
        }
    }

     /**
     * This method analyzes each question and how students responded to each overall.
     * 
     * @param   studentAnswers  arrayList of StringBuilder objects representing students' reponses to the exam
     * @param   correctAnswers  StringBuilder object representing correct responses to exam questions (answer key)        
     */
    public static void questionAnalysis (StringBuilder correctAnswers, ArrayList <StringBuilder> studentAnswers) {
        
        // for each question from the exam, prints formatted answer choices with the correct answer marked by asterisk (*)
        for (int i = 0; i < correctAnswers.length(); i++) {
            int countA =0 , countB =0, countC = 0, countD = 0, countE = 0,countBlanks = 0;
            int numOfStudents = studentAnswers.size();
            double percA, percB, percC, percD, percE, percBlanks =0.0;

            System.out.println("Question #"+ (i+1) +":\n");
            if (correctAnswers.charAt(i) == 'A') {
                System.out.printf("%4s%12s%12s%12s%12s%12s\n\n", "A*", "B", "C", "D", "E", "Blank");
            }
            else if (correctAnswers.charAt(i) == 'B') {
                System.out.printf("%4s%12s%12s%12s%12s%12s\n\n", "A", "B*", "C", "D", "E", "Blank");
            }
            else if (correctAnswers.charAt(i) == 'C') {
                System.out.printf("%4s%12s%12s%12s%12s%12s\n\n", "A", "B", "C*", "D", "E", "Blank");
            }
            else if (correctAnswers.charAt(i) == 'D') {
                System.out.printf("%4s%12s%12s%12s%12s%12s\n\n", "A", "B", "C", "D*", "E", "Blank");
            }
            else  {
                System.out.printf("%4s%12s%12s%12s%12s%12s\n\n", "A", "B", "C", "D", "E*", "Blank");
            }
            
            // call supplementary method sumAnswers() to calculate number of times a certain response is seen for each question
            // calculate percentages of each kind of response in proportion to # of students who took the exam
            countA = sumAnswers(studentAnswers, i, 'A'); 
            percA = (double)countA/numOfStudents * 100;
            countB = sumAnswers(studentAnswers, i, 'B'); 
            percB = (double) countB/numOfStudents * 100;
            countC = sumAnswers(studentAnswers, i, 'C'); 
            percC = (double) countC / numOfStudents * 100;
            countD = sumAnswers(studentAnswers, i, 'D'); 
            percD = (double) countD / numOfStudents * 100;
            countE = sumAnswers(studentAnswers, i, 'E'); 
            percE = (double) countE / numOfStudents * 100;
            countBlanks = sumAnswers(studentAnswers, i, ' '); 
            percBlanks = (double) countBlanks / numOfStudents * 100;
            // prints the above counts and percentages in a formatted way 
            System.out.printf("%4d%12d%12d%12d%12d%10d\n\n", countA, countB, countC, countD, countE, countBlanks);
            System.out.printf("%4.1f%%%12.1f%%%11.1f%%%10.1f%%%10.1f%%%10.1f%%\n\n\n\n", percA, percB, percC, percD, percE, percBlanks);
        }
    }

     /**
     * This method reads user input to retrieve a file with students' responses to the exam. It checks to see if user types an 
     * invalid file that does not open and can't be read, and also to ensure that it is a file and not a directory. 
     * If user types something invalid - the program prompts them for a new value for the file to read. 
     * 
     * @param   studentAnswers  arrayList of StringBuilder objects representing students' reponses to the exam
     * @param   i   - counter from the loop from calling method that represents # of question
     * @param   c - character that corresponds to a student response that we need to count occurrences of 
     * @return  sum integer representing the number of occurrences for given response       
     */
    private static int sumAnswers (ArrayList <StringBuilder> studentAnswers, int i, char c ) {
        int sum = 0;
        for (StringBuilder s: studentAnswers) {
            if (s.charAt(i) == c){
                sum++;
            }
            else {
                continue;
            }
        }
        return sum;
    }

     /**
     * This method checks if student's responses to the exam questions are valid (A, B, C, D, E, or blank)
     * 
     * @param   s   StringBuilder object that represents student's responses 
     * @param   a   ArrayList representing only valid exam response options 
     * 
     * @return  valid boolean that represents whether the answer choice is valid for the exam      
     */
    private static boolean isAnswerValid (StringBuilder s, ArrayList <Character> a){
        boolean valid = false; 
        for (int i = 0; i < s.length(); i++) {
            if (a.contains(s.charAt(i)) || s.charAt(i) == ' ') {
                valid = true;
            }
            else { 
                valid = false;
                break;
            }
        }
        return valid; 
    }

     /**
     * This method prints nicely formatted squiggles to underline column titles
     * 
     * @param   tableHeaders    array of column headers to be used to print out the right amount of squiggles  
     */
    private static void printSquiggles (String []tableHeaders){
        // creating an arraylist to hold squiggles - each of different lengths depending on column header length
        ArrayList <String> squigglesList = new ArrayList<>();
        for (int i = 0; i <tableHeaders.length; i++) {
            String squiggly = "";
            for (int j=0; j < tableHeaders[i].length(); j++) {
                squiggly+="~";
            }
            squigglesList.add(squiggly);
        }
        //for each squiggle string in the list print it in the formatted way
        for (String s: squigglesList) {
            System.out.printf("%-17s", s);
        }
    }
}
