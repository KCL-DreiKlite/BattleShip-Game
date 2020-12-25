package priv.kcl.battleshipgame;


public final class SU_Cruiser extends ShipUnit {
    
    public static final int CRUISER_SIZE = 3;

    SU_Cruiser(String name, int direction) {
        super(name, Battlefield.TYPE_CRUISER, 3, direction);

    }
    SU_Cruiser(String name) {
        super(name, Battlefield.TYPE_CRUISER, 3);
    }
}
