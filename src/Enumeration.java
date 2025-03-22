/**
 * This class represents a program that computes the number of days in each
 * month
 * regardless whether it is a leap year or not.
 * 
 * @author Eugenia Tate
 * @version Last Modified 02/16/2025
 */
class Enumeration {

    enum Months {
        JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC
    };

    /**
     * This method will receive a Month variable and an integer representing year
     * and
     * will output how many days are in each month of a given year.
     * 
     * @param m    month value
     * @param year year value
     * @return integer value of the number of days
     */
    public static int daysInMonth(Months m, int year) {

        switch (m) {
            case JAN, MAR, MAY, JUL, AUG, OCT, DEC:
                return 31;

            case FEB:
                if (year % 4 == 0 || year % 400 == 0) {
                    return 29;
                } else {
                    return 28;
                }

            default:
                return 30;
        }
    }

    public static void main(String[] args) {
        for (Months m : Months.values()) {
            System.out.println(m + " 2023 has " + daysInMonth(m, 2023) + " days!");
        }
    }
}
