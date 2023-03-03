package escape;


import escape.builder.LocationInitializer;
import escape.required.Coordinate;

public class CoordinateImpl implements Coordinate {

    private final int x;
    private final int y;

    /**
     * Constructor for CoordinateImpl
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public CoordinateImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * checks if two coordinates are equal
     *
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        CoordinateImpl coordinate = (CoordinateImpl) o;
        return x == coordinate.x && y == coordinate.y;
    }

    /**
     * gets the row of the coordinate
     *
     * @return row of the coordinate
     */
    @Override
    public int getRow() {
        return x;
    }

    /**
     * gets the column of the coordinate
     *
     * @return column of the coordinate
     */
    @Override
    public int getColumn() {
        return y;
    }
}









