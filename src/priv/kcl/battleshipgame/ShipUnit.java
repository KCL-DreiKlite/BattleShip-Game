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


    public static final int ATTACKED_BY_SHELLS = 0;
    public static final int ATTACKED_BY_TORPEDOS = 1;
    public static final int ATTACKED_BY_AIRCRAFTS = 2;
    
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
    
    /**
     * The location of this ship unit in battlefield.<p>
     * If the direction is HORIZONTAL, then the head of this ship unit is the TOP. <p>
     * If the direction is VERTICAL, then the head is LEFT.
     */
    protected Coordinate location;

    /**
     * 
     */
    protected boolean availablePart[];

    protected int torpedos;

    protected int shells;

    /**
     * Let the ship know it's got hit.
     * 
     * @param hitPart the part where ship got hit. The value corresponded to the part
     * by counting from the left or the top by the ship's direction. Start from 0
     * @param from who did that?!
     * @param weaponType hit by what kind of weapon. It could be
     * <code>ATTACKED_BY_SHELLS</code>, <code>ATTACKED_BY_TORPEDOS</code>, or
     * <code>ATTACKED_BY_AIRCRAFTS</code>
     * @return <code>true</code> if and only if the specific part still available and
     * is exist. Otherwise return <code>false</code>
     */
    protected boolean shipGotHit(int hitPart, ShipUnit from, int weaponType) {
        if (hitPart < 0 || hitPart >= size)
            return false;
        else if (!availablePart[hitPart] || !alive)
            return false;
        
        availablePart[hitPart] = false;
        
        return true;
    }
    /**
     * Let the ship know it's got hit.
     * 
     * @param hitCoordinate the coordinate where ship got hit
     * @param from who did that?!
     * @param weaponType hit by what kind of weapon. It could be
     * <code>ATTACKED_BY_SHELLS</code>, <code>ATTACKED_BY_TORPEDOS</code>, or
     * <code>ATTACKED_BY_AIRCRAFTS</code>
     * @return <code>true</code> if and only if the ship's part from given coordinate
     * is exist and still available. Otherwise return <code>false</code>
     */
    protected boolean shipGotHit(Coordinate hitCoordinate, ShipUnit from, int weaponType) {
        if (direction == HORIZONTAL_DIRECTION) {
            if (hitCoordinate.getY() != location.getY())
                return false;
            else if (hitCoordinate.getX() < location.getX() || location.getX()+size < hitCoordinate.getX())
                return false;
            return shipGotHit(hitCoordinate.getX()-location.getX(), from, weaponType);
        }
        else {
            if (hitCoordinate.getX() != location.getX())
                return false;
            else if (hitCoordinate.getY() < location.getY() || location.getY()+size < hitCoordinate.getY())
                return false;
            return shipGotHit(hitCoordinate.getY()-location.getY(), from, weaponType);
        }
    }

    // protected boolean firing(ShipUnit target, int part) {

    // }
    // protected boolean firing(ShipUnit target, Coordinate coordinate) {
        
    // }

    /**
     * Call this method when ship is sunk.
     */
    protected void shipSunk() {
        alive = false;

        for (int part = 0; part < availablePart.length; part++)
            availablePart[part] = false;
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
    /**
     * Get the full name of the ship.
     * @return a string contains TYPE and NAME
     */
    public String getFullName() {return type + "-" + name; }
    public int getSize() {return size; }
    public int getDirection() {return direction; }
    public boolean isAlive() {return alive; }
}

