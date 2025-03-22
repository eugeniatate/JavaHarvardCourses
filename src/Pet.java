/**
 * This class represents an abstract representation of a pet and provides 
 * a constructor and appropriate getter and setter for pet's attributes
 * 
 * @author Eugenia Tate
 * @version Last Modified 03/04/2025
 */
public abstract class Pet {

    private String petName;
    private int petBirthYear; 

    /**
     * This is a contructor
     * 
     * @param name  Pet's name as a string
     * @param year   Pet's birth year as an integer 
     */
    protected Pet (String name, int year) {
        this.petName = name; 
        this.petBirthYear = year; 
    }
    
    /**
     * This is a getter method that retrieves Pet's name
     * 
     * @return   Pet's name (string)
     */
    protected String getName () {
        return this.petName;
    }

     /**
     * This is a method for Pet's ability to make sounds/speak
     * it calls an appopriate subclass' speak() method
     * 
     * @return   string representing Pet's sounds
     */
    protected String speak() {
        return this.speak();
    }
}
