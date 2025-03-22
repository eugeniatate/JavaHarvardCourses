public class ShipTest {
    
    public static void main (String [] args) {
        Ship cruiseShipA = new CruiseShip("CruiseShipA", 1999, KindOfEngine.DIESEL, 353, true);
        Ship cruiseShipB = new CruiseShip("CruiseShipB", 1988, KindOfEngine.ELECTRIC, 564, false);
        Ship cargoShipA = new CargoShip("CargoShipA", 1956, KindOfEngine.WIND, 1000.98);
        Ship cargoShipB = new CargoShip("CargoShipB", 1945, KindOfEngine.GAS_TURBINE, 56873.65);
        Ship tankerA = new TankerVessel("TankerA", 1977, KindOfEngine.STEAM_ENGINE, 456.00, false);
        Ship tankerB = new TankerVessel("TankerB", 1979, KindOfEngine.STEAM_TURBINE, 876.05, true);
        Ship [] ships = {cruiseShipA, cruiseShipB, cargoShipA, cargoShipB, tankerA, tankerB};

        String descriptionOfAShip; 
        for (int i = 0; i < ships.length; i++) {
            descriptionOfAShip = ships[i].toString();
            System.out.println(descriptionOfAShip + "\n");
        }
    }
}
