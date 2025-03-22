/**
 * This class represents an encoder and a decoder for the given Zip or Bar Code
 * 
 * @author Eugenia Tate
 * @version Last Modified 03/04/2025
 */
public class BarCode {
    private String myZipCode;
    private String myBarCode;
    //array of Strings to represent bar code segments using 1s and 0s as per provided table 
    //this array will make it easier to identify the right digit it represents
    private String [] barCodes = {"11000", "00011","00101", "00110", "01001", "01010", "01100", "10001", "10010", "10100"};
    final int ZIP_LENGTH = 5;
    final int BARCODE_LENGTH = 32;
    final int BARCODE_NUM_OF_SEGMENTS = 6;
    final int SEGMENT_INTERVAL = 5;

    /**
     * This is a contructor for Bar or Zip Code
     * 
     * @param zipOrBarCode  string representing bar or zip code 
     */
    public BarCode (String zipOrBarCode) {
        //if the length of zipOrBarCode is 5 we then can try to encode it as a Zip
        if (zipOrBarCode.length() == ZIP_LENGTH)  {
            this.myZipCode = zipOrBarCode;
            this.myBarCode = encode(myZipCode);
        }
        //if the length of zipOrBarCode is 32 we then can try to decode it as a BarCode
        else if (zipOrBarCode.length() == BARCODE_LENGTH) {
            this.myBarCode = zipOrBarCode;
            this.myZipCode = decode(myBarCode);
        }
        //if the length of zipOrBarCode  is neither 5 nor 32, we know it is an illegal string
        else { 
            throw new IllegalArgumentException("Illegal String that is not a zip code or a bar code"); 
        }
    }

    /**
     * This is a getter method for Zip Code
     * 
     * @return  string representing zip
     */
    public String getZipCode () {
        return this.myZipCode;
    }

    /**
     * This is a getter method for Bar Code
     * 
     * @return  string representing bar code
     */
    public String getBarCode () {
        return this.myBarCode;
    }

    /**
     * This is a method that takes a Zip code string and encodes it into a string of 
     * a combination of | and : to represent the zip code for postal services purposes
     * 
     * @return  string representing encoded zip in format using | and :
     */
    private String encode (String myZipCode) {
        char [] zipCodeEncoded = new char [32];
        StringBuilder encoded = new StringBuilder();
        String encodedFinal = "";
        //retrieving a check digit for the given zip
        int checkNum = getCheckDigit(myZipCode);

        // if given Zip code is valid, continue crreating a StringBuilder object by appending 
        //appropriate characters
        if (isValidZipCode(myZipCode)) {
            //we know that encoded Zip always starts with 1
            encoded.append("1");
            
            //for each digit in the Zip code, retrieve and append a bar code segment representing the digit
            for (int s = 0; s < myZipCode.length(); s++) {
                int digit = Character.getNumericValue(myZipCode.charAt(s));
                encoded.append(digitToCode(digit));
            }
            //append bar code segment representing check digit
            encoded.append(digitToCode(checkNum));
            //we know that encoded Zip always endss with 1
            encoded.append("1");
            //convert encoded string of 1s and 0s into a bar code of |s and :s
            //we know each 1 represents | and each 0 is :
            for (int j = 0; j < encoded.length(); j ++) {
                if (encoded.charAt(j) == '1') {
                    zipCodeEncoded[j] = '|';
                }
                else { 
                    zipCodeEncoded[j] =':'; 
                }
            }
            //convert the final array of chars into a string ready for output
            for (int i = 0; i < zipCodeEncoded.length; i++) {
                encodedFinal += zipCodeEncoded[i];
            }
        }
        //if the zip code is not valid, throw and exception 
        else {
            throw new IllegalArgumentException("Illegal String that is not a zip code or a bar code"); 
        }
       
        return encodedFinal;
    }

     /**
     * This is a method that takes a Bar code string and decodes it into a string of 
     * digits from (0 to 9) that represents the zip code 
     * 
     * @return  string representing decoded bar code
     */
    private String decode (String barCodeToDecode) {
        StringBuilder sb = new StringBuilder();
        //we know each bar code starts with 1 and it is irrelevant to calculations, so the start will be at 1 for later calc
        int start  = 1; 
        int end = 6;
        String decodedZip = "";
        
        // if given Bar code is valid, continue appending to StringBuilder object  
        //appropriate numbers (1 or 0) to convert properly
        if (isValidBarCode(barCodeToDecode)) {
            for (int k = 0; k < myBarCode.length(); k ++) {
                if (myBarCode.charAt(k) == '|') {
                    sb.append('1');
                }
                else {
                    sb.append('0');
                }
            }
           
            //we know there are always 6 segments to a bar code if it is valid.
            //we loop 6 times to convert substrings representing 5 digit bar code pieces 
            //into a digit for a zip and append each digit to decodedZip variable
            for (int i = 0; i < BARCODE_NUM_OF_SEGMENTS; i ++) {
                decodedZip += codeToDigit(sb.substring(start, end));
                start += SEGMENT_INTERVAL;
                end += SEGMENT_INTERVAL;
            }
            
            //to ensue the decoded Zip is valid we need to validate if the sum of all digits in the zip can be evenly divided y 10
            if (isValidBarCodeHelper(decodedZip)) {
                //if the zip is valid, the last check digit gets trimmed and only 5 digit zip is stored in a variable 
                decodedZip = decodedZip.substring(0,ZIP_LENGTH);
            }
            //throw exception if zip code sum of all digit is not divided by 10 evenly
            else {
                throw new IllegalArgumentException ("Illegal bar code");
            }
        }
        //throws exception if bar code is not valid 
        else {
            throw new IllegalArgumentException("Illegal String that is not a zip code or a bar code"); 
        }

       return decodedZip;
    }

    /**
     * This method checks if zipCode is valid: every character is a digit
     * 
     * @return  boolean representing validity of the zip
     */
    private boolean isValidZipCode (String zipCode){
        boolean valid = true;
        
        for (int k = 0; k < zipCode.length(); k++) { 
            if (Character.isDigit(zipCode.charAt(k))) {
                valid = true;
            }
            else { valid = false;}
        }
        return valid;
    }

    /**
     * This helper method checks if bar code's decoded zip is valid: sum of all digits is divisible by 10 
     * without remainder
     * 
     * @return  boolean representing validity of the zip
     */
    private boolean isValidBarCodeHelper (String decoded) {
        int sum = 0;
        for (int i = 0; i < decoded.length(); i ++) {
            sum += Character.getNumericValue(decoded.charAt(i));
        }
        if (sum % 10 == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method checks if bar code is valid: starts and ends with | and every character is either | or :
     * 
     * @return  boolean representing validity of the bar code
     */
    private boolean isValidBarCode (String barCode){
        boolean valid = true; 
        if (barCode.charAt(0) == '|' && barCode.charAt(31) == '|' ) {
            for (int k = 1; k < barCode.length()-1; k++) {
                if (barCode.charAt(k) == '|' || barCode.charAt(k) == ':') { 
                    valid = true;
                }
            }
        }
        else { 
            valid = false;
        }
        return valid;
    }

    /**
     * This method returns the five-character bar code segment that represent the digit   
     * 
     * @param   integer representing a digit from 0 to 9
     * @return  string - five-character bar code segment
     */
    private String digitToCode (int digit){
        return barCodes[digit];
    }

    /**
     * This method returns digit 0-9 based on corresponding bar segment passed in. 
     * 
     * @param   string - five-character bar code segment
     * @return  integer associated with corresponding bar code segment
     */
    private int codeToDigit (String barCodeSegment) {
        int digit = 0;
        for (int i = 0; i < barCodes.length; i++) {
            if ( barCodes[i].equals(barCodeSegment)) {
                digit = i;
            }
        }
        return digit;
    }

    /**
     * This method returns the integer 0-9 that is necessary for the sum of the
     * digits to equal the next multiple of 10
     * 
     * @param   string - five-character zip code 
     * @return  integer representing chck digit
     */
    private int getCheckDigit(String myZip) {
        int sum = 0;
        for (int i = 0; i < myZip.length(); i ++) {
            sum += Character.getNumericValue(myZip.charAt(i));
        }
        int checkDigit;
        int division = sum/10;
        if (division == 0) {
            checkDigit = 10 - sum;
        }
        else if (division == 1) {
            checkDigit = 20 - sum;
        }
        else if (division == 2) {
            checkDigit = 30 - sum;
        }
        else if (division == 3) {
            checkDigit = 40 - sum;
        }
        else { 
            checkDigit = 50 - sum;
        }
        return checkDigit;
    }
}
