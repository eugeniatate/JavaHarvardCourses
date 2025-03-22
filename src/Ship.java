/**
 * This file contains hierarchy of classes representing various vessels:
 * Ship is a super class
 * Cargo and Cruise Ships are its subclasses 
 * Tanker is a sub class of a Cargo Ship
 * Each class has it own constructor, accessor and setter methods, unique functionality methods, 
 * as well as toString() method that outputs String representation of each type of vessel's attributes
 * to the user running the program from main()
 * 
 * Note: I will refrain from leaving excessive comments on each class and its methods as the main comment above describes
 * repetitive nature of every class and its methods
 * 
 * @author Eugenia Tate
 * @version Last Modified 03/04/2025
 */
enum  KindOfEngine {STEAM_ENGINE, STEAM_TURBINE,
    GAS_TURBINE, DIESEL, ELECTRIC, WIND, HUMAN_FORCE};

public abstract class Ship {

    private String name; 
    private int year;
    KindOfEngine engine;

    public Ship(String n, int y, KindOfEngine engineType) {
        this.name = n;
        this.year = y;
        this.engine = engineType;
    }

    public String getName() {
        return this.name; 
    }

    public int getYear() {
        return this.year;
    }

    public String toString (){
        StringBuilder sb = new StringBuilder(); 
        sb.append("Ship name: " + this.getName()+ "\t");
        sb.append("Year: " + this.getYear() + "\t");
        sb.append("Engine Type: " + this.engine + "\t");
        return sb.toString();
    }
}

class CruiseShip extends Ship {
    private int maxPax;
    private boolean isNovivirusInfecting;

    public CruiseShip(String name, int year, KindOfEngine engine, int paxNum, boolean novivirus) {
        super(name, year, engine);
        this.maxPax = paxNum; 
        this.isNovivirusInfecting = novivirus;
    }

    public int getMaxPax() {
        return this.maxPax;
    }

    public boolean getNovivirusInfecting() {
        return this.isNovivirusInfecting;
    }
    
    public void setMaxPax (int num) {
        this.maxPax = num; 
    }

    public void setNovivirusInfecting (boolean virusInfecting) {
        this.isNovivirusInfecting = virusInfecting;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder(); 
        sb.append("CruiseShip name: " + this.getName()+ "\t");
        sb.append("Maximum # Pax: " + this.getMaxPax()+ "\t");
        return sb.toString();
    }
}

class CargoShip extends Ship {
    private double cargoCapacity; 

    protected CargoShip (String name, int year, KindOfEngine engine, double capacity) {
        super(name, year, engine);
        this.cargoCapacity = capacity;
    }

    public double getCargoCapacity () {
        return this.cargoCapacity;
    }

    public void setCapacity (double capacity) {
        this.cargoCapacity = capacity;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder(); 
        sb.append("CargoShip name: " + this.getName()+ "\t");
        sb.append("Cargo Capacity (in tons): " + this.getCargoCapacity()+ "\t");
        return sb.toString();
    }
}

class TankerVessel extends CargoShip {

    private boolean oilBearing;

    protected TankerVessel (String name, int year, KindOfEngine engine, double capacity, boolean oil) {
        super(name, year, engine, capacity);
        this.oilBearing = oil;
    }

    public boolean isTransportingOil () {
        return this.oilBearing;
    }

    public void setOilTransporting (boolean oilCarrying) {
        this.oilBearing = oilCarrying;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder(); 
        sb.append("Tanker name: " + this.getName()+ "\t");
        sb.append("Is Oil Tanker: " + this.isTransportingOil()+ "\t");
        sb.append("Capacity: " + this.getCargoCapacity()+ "\t");
        return sb.toString();
    }
}