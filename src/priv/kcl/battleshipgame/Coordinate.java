package priv.kcl.battleshipgame;

public class Coordinate {
    private int X;
    private int Y;

    Coordinate() {
        X = 0;
        Y = 0;
    }

    Coordinate(int x, int y) {
        setCoordinate(x, y);
    }

    public void setCoordinate(int x, int y) {
        X = x;
        Y = y;
    }
    public void setCoordinate(Coordinate coordinate) {
        X = coordinate.getX();
        Y = coordinate.getY();
    }

    public void setX(int x) {X = x; }
    public void setY(int y) {Y = y; }

    public Coordinate getCoordinate() {
        return new Coordinate(X, Y);
    }
    public int getX() {return X; }
    public int getY() {return Y; }
}
