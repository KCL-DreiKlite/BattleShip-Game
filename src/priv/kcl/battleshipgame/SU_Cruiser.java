package priv.kcl.battleshipgame;


public final class SU_Cruiser extends ShipUnit {
    
    public static final int CRUISER_SIZE = 3;

    SU_Cruiser(String name, int direction) {
        super(name, "Cruiser", 3, direction);

    }
    SU_Cruiser(String name) {
        super(name, "Cruiser", 3);
    }
}
