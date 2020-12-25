package priv.kcl.battleshipgame;

public class Coordinate {
    private int X;
    private int Y;


    public void setCoordinate(int x, int y) {
        X = x;
        Y = y;
    }
    public void setCoordinate(Coordinate coordinate) {
        X = coordinate.X;
        Y = coordinate.Y;
    }
    
    public void add(Coordinate b) {
        X += b.X;
        Y += b.Y;
    }
    public void subtract(Coordinate b) {
        X -= b.X;
        Y -= b.Y;
    }
    
    public void setX(int x) {X = x; }
    public void setY(int y) {Y = y; }
    
    public Coordinate clone() {
        return new Coordinate(X, Y);
    }
    public int getX() {return X; }
    public int getY() {return Y; }

    public String toString() {
        return "[X=" + X + ",Y=" + Y + "]";
    }

    Coordinate() {
        X = 0;
        Y = 0;
    }
    Coordinate(int x, int y) {
        setCoordinate(x, y);
    }
    Coordinate(Coordinate coordinate) {
        setCoordinate(coordinate);
    }
}
