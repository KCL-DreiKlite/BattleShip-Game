package priv.kcl.battleshipgame;

import java.util.ArrayList;

/**
 * Define a new battlefield to let ships deploy on it. The whole game system will
 * assembled in this class (exclude GUI).
 * <p>
 * There're two sizes defined in this class: <strong>16x16</strong> and
 * <strong>20x20</strong>. Each size has it's maximum ships deployment, firing
 * range for each kind of ships, and maximum ammunition (shells, torpedos and
 * aircrafts). Here's the detail: (You can see more information in 'Project idea'
 * folder.)
 * <p>
 * The following table is based on the battlefield heading left. Each index
 * of rows and columns are start from 0 (i.e. the range field are 0-15 or 0-19).
 * <pre>
 * |          -           |     16x16     |     20x20
 * ----------------------|---------------|---------------
 * Maximum Deployment    | CV : 1        | CV : 2
 *                       | BB : 2        | BB : 3
 *                       | CL : 3        | CL : 4
 *                       | DD : 3        | DD : 3
 * ----------------------|---------------|---------------
 * Deploy Range (column) | CV : 11-15    | CV :
 *                       | BB : Anywhere | BB : Anywhere
 *                       | CL : 0-8      | CL :
 *                       | DD : 0-3      | DD :
 * ----------------------|---------------|---------------
 * Firing Range (column) | CV : N/A      | CV : N/A
 *                       | BB : Anywhere | BB : Anywhere
 *                       | CL : 0-10     | CL :
 *                       | DD : 0-3      | DD :
 * ----------------------|---------------|---------------
 * Torpedos Launch Limit | CV : N/A      | CV : N/A
 *                       | BB : N/A      | BB : N/A
 *                       | CL : 1        | CL :
 *                       | DD : 1        | DD :
 * 
 * </pre>
 * 
 */
public class Battlefield {

    /** 16x16 size definition */
    public static final int SIZE_16x16 = 16;
    /** 20x20 size definition */
    public static final int SIZE_20x20 = 20;
    
    /** The type name of Destroyer */
    public static final String TYPE_DESTROYER = "DD";
    /** The type name of Cruiser */
    public static final String TYPE_CRUISER = "CL";
    /** The type name of Battleship */
    public static final String TYPE_BATTLESHIP = "BB";
    /** The type name of Aircraft Carrier */
    public static final String TYPE_AIRCRAFT_CARRIER = "CV";

    protected ArrayList<ShipUnit> shipUnitsList = new ArrayList<ShipUnit>();

    

    /**
     * Create a new battlefield with default size (16x16).
     */
    Battlefield() {
        
    }
    
}
