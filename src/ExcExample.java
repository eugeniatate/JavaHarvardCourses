/**
 * This program repsents put back together previously scrambled pieces of code.
 * The program should print either thaws or throws dependinf on command line args. 
 * 
 * @author Eugenia Tate
 * @version Last Modified 03/22/2025
 */
public class ExcExample {
    public static void main (String [] args) {
        String test = args[0];

        System.out.print("t");
        System.out.print("h");
        try {
            doRisky(test);
        }
        catch (MyException e) {
            e.getMessage();
        }
        finally {
            System.out.print("w");
            System.out.print("s");
        }
    }
    public static void doRisky (String arg) throws MyException {
        
        if ("Warren".equals(arg)) {
            System.out.print("a");
        }
        else {
            throw new MyException();
        }
    }
}

class MyException extends Exception {
    MyException(){
        System.out.print("r");
        System.out.print("o");
    }
}