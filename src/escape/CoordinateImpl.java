package escape;


import escape.builder.LocationInitializer;
import escape.required.Coordinate;

public class CoordinateImpl implements Coordinate {

    private final int x;
    private final int y;


    public CoordinateImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        CoordinateImpl coordinate = (CoordinateImpl) o;
        return x == coordinate.x && y == coordinate.y;
    }

    @Override
    public int getRow() {
        return x;
    }

    @Override
    public int getColumn() {
        return y;
    }
}









