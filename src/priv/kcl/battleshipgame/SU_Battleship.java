package priv.kcl.battleshipgame;

public final class SU_Battleship extends ShipUnit {

    public static final int BATTLESHIP_SIZE = 4;

    SU_Battleship(String name, int direction) {
        super(name, "Battleship", BATTLESHIP_SIZE, direction);

        
    }
    SU_Battleship(String name) {
        super(name, "Battleship", BATTLESHIP_SIZE);
    }
}
