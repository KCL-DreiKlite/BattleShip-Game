package priv.kcl.battleshipgame;


/**
 * 
 */
public class ShipUnit {

    /**
     * The horizontal direction value.
     */
    public static final int HORIZONTAL_DIRECTION = 1;
    /**
     * The vertical direction value.
     */
    public static final int VERTICAL_DIRECTION = 2;
    
    protected boolean alive = true;

    protected final String name;
    protected final String type;

    /**
     * Store the field of the ship unit. For example, if there's a ship in
     * field placed like this:
     * <blockquote>
     * 0000
     * </blockquote>
     * then <code>shipDimension</code>'s value is
     * <pre>
     * shipDimension[0][0] = true
     * shipDimension[1][0] = true
     * shipDimension[2][0] = true
     * shipDimension[3][0] = true
     * </pre>
     */
    // protected boolean[][] shipDimension;

    /**
     * The size (or length) of this ship unit.
     */
    protected final int size;

    /**
     * The direction of this ship unit.
     * <blockquote>
     * 0 : Undefined or sunk (dead) or not-placed<p>
     * 1 : Horizontal<p>
     * 2 : Vertical<p>
     * </blockquote>
     * 
     * @see ShipUnit.location
     */
    protected int direction;

    protected boolean availablePart[];

    /**
     * The location of this ship unit in battlefield.<p>
     * If the direction is HORIZONTAL, then the head of this ship unit is the TOP. <p>
     * If the direction is VERTICAL, then the head is LEFT.
     */
    protected Coordinate location;

    void shipGotHit(Coordinate hitLocation) {

    }

    // Initialize.
    ShipUnit() {
        size = 0;
        direction = 0;
        name = "";
        type = "";
    }

    /**
     * Create a new ship unit.
     * 
     * @param name the name of this ship unit.
     * @param type the type name of this ship unit. (Ex. Cruiser, Destroyer, Carrier, etc.)
     * @param size the size of this ship unit.
     * @param direction placed direction of this ship unit.
     */
    ShipUnit(String name, String type, int size, int direction) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.direction = direction;

        availablePart = new boolean[size];
        for (int eachPart = 0; eachPart < size; eachPart++)
            availablePart[eachPart] = true;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getName() {return name; }
    public String getType() {return type; }
    public int getSize() {return size; }
    public int getDirection() {return direction; }
}

