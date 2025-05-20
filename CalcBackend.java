
// Implement your calculator math logic in this class.

// You MUST include these comments in CalcBackend.java, modifed with your actual char
// values, ...

//@@         Operation        char passed to feedChar()
//@@         ---------        -------------------------
//@@         Clear            'C',
//@@         Square Root      '\u221A',
//@@         Multiplication   '*',
//@@         Division         '/',
//@@         Addition         '+',
//@@         Subtraction      '-';

// ... in order for me to know which characters your feedChar() method associates with
// different operations. That information is necessary for me to test your CalcBackend
// class.

// You MUST initialize the calculator's state in the zero-arg constructor. You MUST NOT
// change feedChar's and getDisplayVal's signature or functionality. You MUST NOT have
// ANY other non-private members of this class.

// I will test your CalcBackend class via an expanded version of CalcBackendTest.java,
// which will create an instance of CalcBackend with the zero-arg constructor, pass
// simulated button clicks via feedChar, and retrieve resulting display Strings with
// getDisplayVal.

// Note that when I test your CalcBackend class, I am ONLY using your feedChar and
// getDisplayVal methods. That means that my tests COMPLETELY bypass your Calculator
// class. That's because  your Calculator class is not supposed to have ANY involvement
// with your calculator's computations. Your Calculator class should ONLY layout the
// calculator's JFrame, attach listeners, feed button clicks to CalcBackend via feedChar
// and then update the calculator's display via getDisplayVal.

public class CalcBackend {

    // different states the calculator can be in
    private enum State {
        READY_FOR_FIRST,
        BUILDING_INPUT_LEFT,
        BUILDING_INPUT_RIGHT,
        READY_FOR_SECOND,
        SHOWING_RESULT
    }

    // Variables defining calculator's internal state
    double displayVal; // Always contains double value matching GUI's display
    private String currentUserInput;
    private double resultOfCalc;
    private char lastOperatorClicked;
    private State state;

    // Zero-arg constructor initializes calculator's state
    public CalcBackend() {
        this.currentUserInput = "0";
        this.resultOfCalc = 0;
        this.lastOperatorClicked = '=';
        this.state = State.READY_FOR_FIRST;
    }

    // feedChar is called by GUI to tell CalcBackend that a particular button was
    // clicked
    public void feedChar(char c) {
        // Everytime feedChar is called, it must update the double value representing
        // what should currently be displayed in response to the clicked button. So the
        // CalcBackend "business logic" originates in feedChar.

        String category = getCategory(c);

        switch (state) {
            case READY_FOR_FIRST:
            case SHOWING_RESULT:
                handleReadyOrResultState(c, category);
                break;
            case BUILDING_INPUT_LEFT:
            case BUILDING_INPUT_RIGHT:
                handleBuildingState(c, category);
                break;
            case READY_FOR_SECOND:
                handleOperatorState(c, category);
                break;
        }
        // if user input can't be parsed to a double, display zero
        try {
            displayVal = Double.parseDouble(currentUserInput);
        } catch (NumberFormatException e) {
            displayVal = 0;
        }
    }

    /**
     * This method handles the state transitions when the calculator is either
     * in READY_FOR_FIRST or SHOWING_RESULT
     * 
     * @param c        char representing button clicked
     * @param category String representing category of a button pressed
     */
    private void handleReadyOrResultState(char c, String category) {
        // if user presses a digit, the calculator begins building the first operand
        if (category.equals("DIGIT")) {
            this.currentUserInput = "" + c;
            this.state = State.BUILDING_INPUT_LEFT;
        }
        // if decimal is pressed, build the fractional part of the operand
        else if (category.equals("DECIMAL")) {
            this.currentUserInput = "0.";
            this.state = State.BUILDING_INPUT_RIGHT;
        }
        // if unary operator such as Sq Root is pressed, calculation is applied
        // immediately.
        else if (category.equals("UNARY")) {
            applyUnaryOperator(c);
            this.state = State.SHOWING_RESULT;
        }
        // if C is pressed - reset
        else if (category.equals("CLEAR")) {
            reset();
        }
    }

    /**
     * This method handles the state transitions when the calculator is building
     * the first or second operand (BUILDING_INPUT_LEFT, BUILDING_INPUT_RIGHT).
     * 
     * @param c        char representing button clicked
     * @param category String representing category of a button pressed
     */
    private void handleBuildingState(char c, String category) {
        // handles digits incl. zero
        if (category.equals("DIGIT")) {
            if (currentUserInput.equals("0"))
                this.currentUserInput = "";
            this.currentUserInput += c;
        }
        // handles decimal avoiding duplicate decimal points
        else if (category.equals("DECIMAL") && !this.currentUserInput.contains(".")) {
            this.currentUserInput += ".";
            this.state = State.BUILDING_INPUT_RIGHT;
        }
        // handles binary operations by calling compute()
        else if (category.equals("BINARY")) {
            compute(Double.parseDouble(currentUserInput));
            this.lastOperatorClicked = c;
            this.currentUserInput = Double.toString(resultOfCalc);
            this.state = State.READY_FOR_SECOND;
        }
        // handles unary operations by calling applyUnaryOperator()
        else if (category.equals("UNARY")) {
            applyUnaryOperator(c);
            this.state = State.SHOWING_RESULT;
        }
        // handles = sign by calling compute()
        else if (category.equals("EQUALS")) {
            compute(Double.parseDouble(currentUserInput));
            this.currentUserInput = Double.toString(resultOfCalc);
            this.state = State.SHOWING_RESULT;
        }
        // handles Clear use case bu calling reset()
        else if (category.equals("CLEAR")) {
            reset();
        }
    }

    /**
     * This method is called when the calculator is in the READY_FOR_SECOND state -
     * user pressed an operator and is now entering the second operand.
     * It handles digit, decimal, unary, and clear inputs.
     * 
     * @param c        char representing button clicked
     * @param category String representing category of a button pressed
     */
    private void handleOperatorState(char c, String category) {
        if (category.equals("DIGIT")) {
            this.currentUserInput = "" + c;
            this.state = State.BUILDING_INPUT_LEFT;
        } else if (category.equals("DECIMAL")) {
            this.currentUserInput = "0.";
            this.state = State.BUILDING_INPUT_RIGHT;
        } else if (category.equals("UNARY")) {
            applyUnaryOperator(c);
            this.state = State.SHOWING_RESULT;
        } else if (category.equals("CLEAR")) {
            reset();
        }
    }

    /**
     * This method is a helper method to handle unary operator such as square root.
     * Computes square root of the current input
     * 
     * @param c char representing button clicked
     */
    private void applyUnaryOperator(char c) {
        double val = Double.parseDouble(currentUserInput);
        if (c == '√') {
            val = Math.sqrt(val);
        }
        this.currentUserInput = Double.toString(val);
        this.resultOfCalc = val;
    }

    // what the display should return based on logic
    public String getDisplayVal() {
        String displayString = String.valueOf(displayVal);
        // removes trailing zeroes
        if (displayString.endsWith(".0")) {
            displayString = displayString.substring(0, displayString.length() - 2);
        }
        // shortens output result to 10 chars
        if (displayString.length() > 10) {
            displayString = displayString.substring(0, 10);
        }
        return displayString;
    }

    /**
     * This method categorizes each character based on its type
     * (digit, decimal, operator, etc.).
     * 
     * @param c char representing button clicked
     * @return String category of a char for button clicked
     */
    private String getCategory(char c) {
        if (Character.isDigit(c))
            return "DIGIT";
        if (c == '.')
            return "DECIMAL";
        if ("+-*/".indexOf(c) >= 0)
            return "BINARY";
        if (c == '√')
            return "UNARY";
        if (c == '=')
            return "EQUALS";
        if (c == 'C')
            return "CLEAR";
        return "UNKNOWN";
    }

    // helper method, performs math operations
    private void compute(double num) {
        switch (lastOperatorClicked) {
            case '+':
                this.resultOfCalc += num;
                break;
            case '-':
                this.resultOfCalc -= num;
                break;
            case '*':
                this.resultOfCalc *= num;
                break;
            case '/':
                this.resultOfCalc /= num;
                break;
            case '=':
                this.resultOfCalc = num;
                break;
        }
    }

    // erases the calculator content reverting it to the original state
    private void reset() {
        this.displayVal = 0;
        this.resultOfCalc = 0;
        this.lastOperatorClicked = '=';
        this.currentUserInput = "0";
        this.state = State.READY_FOR_FIRST;
    }
}
