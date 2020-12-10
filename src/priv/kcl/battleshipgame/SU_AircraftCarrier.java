package priv.kcl.battleshipgame;

public final class SU_AircraftCarrier extends ShipUnit {
    
    public static final int AIRCRAFT_CARRIER_SIZE = 4;

    SU_AircraftCarrier(String name, int direction) {
        super(name, "Aircraft Carrier", AIRCRAFT_CARRIER_SIZE, direction);


    }
    SU_AircraftCarrier(String name) {
        super(name, "Aircraft Carrier", AIRCRAFT_CARRIER_SIZE);
        
    }
}
