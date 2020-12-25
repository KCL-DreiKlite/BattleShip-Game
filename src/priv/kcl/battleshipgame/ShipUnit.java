package priv.kcl.battleshipgame;


/**
 * 
 */
public class ShipUnit {

    /**
     * The direction hasn't be defined yet.
     */
    public static final int UNDEFINED_DIRECTION = 0;
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
     * then {@code shipDimension}'s value is
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
     * If the direction is HORIZONTAL, then the head of this ship unit is the LEFT. <p>
     * If the direction is VERTICAL, then the head is TOP.
     */
    protected Coordinate location;

    /**
     * 
     */
    protected boolean availablePart[];
    
    protected int shells;

    protected int torpedos;


    /**
     * Let the ship know it's got hit.
     * 
     * @param hitPart the part where ship got hit. The value corresponded to the part
     * by counting from the left or the top by the ship's direction. Start from 0
     * @param from who did that?!
     * @param weaponType hit by what kind of weapon. It could be
     * {@code ATTACKED_BY_SHELLS}, {@code ATTACKED_BY_TORPEDOS}, or
     * {@code ATTACKED_BY_AIRCRAFTS}
     * @return {@code true} if and only if the specific part still available and is
     * exist. Otherwise return {@code false}
     */
    protected boolean shipGotHit(int hitPart, ShipUnit from, int weaponType) {
        if (!alive)
            return false;

        if (hitPart < 0 || hitPart >= size)
            return false;
        else if (!availablePart[hitPart] || !alive)
            return false;
        
        availablePart[hitPart] = false;

        if (size >= 3 && hitPart != 0 && hitPart != size-1 && weaponType == ATTACKED_BY_TORPEDOS)
            shipSunk();
        
        return true;
    }
    /**
     * Let the ship know it's got hit.
     * 
     * @param hitCoordinate the coordinate where ship got hit
     * @param from who did that?!
     * @param weaponType hit by what kind of weapon. It could be
     * {@code ATTACKED_BY_SHELLS}, {@code ATTACKED_BY_TORPEDOS}, or
     * {@code ATTACKED_BY_AIRCRAFTS}
     * @return {@code true} if and only if the specific part still available and is
     * exist. Otherwise return {@code false}
     */
    protected boolean shipGotHit(Coordinate hitCoordinate, ShipUnit from, int weaponType) {
        if (!alive)
            return false;

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
     * Call this method when ship was sunk.
     */
    protected void shipSunk() {
        alive = false;

        for (int part = 0; part < availablePart.length; part++)
            availablePart[part] = false;
    }

    /**
     * Transform specific part as the coordinate form in battlefield.
     * @param targetPart the part need to be transform
     * @return the <strong>Coordinate</strong> by given ship part. Retrun {@code null}
     * if the target part doesn't exist or the direction is {@code UNDEFINED_DIRECTION}.
     */
    protected Coordinate transformToCoordinate(int targetPart) {
        if (direction == 0 || targetPart < 0 || size <= targetPart)
            return null;
        else if (direction == HORIZONTAL_DIRECTION)
            return new Coordinate(location.getX()+targetPart, location.getY());
        else
            return new Coordinate(location.getX(), location.getY()+targetPart);
    }

    /**
     * Transform coordinate in battlefield to part index.
     * @param targetCoordinate the coordinate need to be transform
     * @return the specific part index by given coordinate. Return <strong>-1</strong> if the
     * given coordinate is not included by the ship or the direction is 0.
     */
    protected int transformToPart(Coordinate targetCoordinate) {
        if (direction == 0)
            return -1;
        else if (direction == HORIZONTAL_DIRECTION) {
            if (targetCoordinate.getY() != location.getY())
                return -1;
            else if (targetCoordinate.getX() < location.getX() || location.getX()+size < targetCoordinate.getX())
                return -1;
            return targetCoordinate.getX() - location.getX();
        }
        else {
            if (targetCoordinate.getX() != location.getX())
                return -1;
            else if (targetCoordinate.getY() < location.getY() || location.getY()+size < targetCoordinate.getY())
                return -1;
            return targetCoordinate.getY() - location.getY();
        }
    }

    // Initialize.
    /**
     * @deprecated
     */
    ShipUnit() {
        size = 0;
        direction = 0;
        name = "";
        type = "";
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
    
    public boolean isAvailable(int part) {
        if (part < 0 || size <= part)
            return false;
        return availablePart[part];
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
    
    /**
     * Create a new ship unit.
     * 
     * @param name the name of this ship unit.
     * @param type the type name of this ship unit. (Ex. Cruiser, Destroyer, Carrier, etc.)
     * @param size the size of this ship unit.
     */
    ShipUnit(String name, String type, int size) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.direction = 0;
        
        availablePart = new boolean[size];
        for (int eachPart = 0; eachPart < size; eachPart++)
            availablePart[eachPart] = true;
        
    }
}

