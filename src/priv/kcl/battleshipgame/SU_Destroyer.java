package priv.kcl.battleshipgame;

public final class SU_Destroyer extends ShipUnit {
    
    public static final int DESTROYER_SIZE = 2;

    SU_Destroyer(String name, int direction) {
        super(name, "Destroyer", DESTROYER_SIZE, direction);

    }
    SU_Destroyer(String name) {
        super(name, "Destroyer", DESTROYER_SIZE);
        
    }
}
