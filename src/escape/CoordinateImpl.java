package escape;


import escape.builder.LocationInitializer;
import escape.required.Coordinate;

public class CoordinateImpl implements Coordinate {

    private int x;
    private int y;


    public CoordinateImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public CoordinateImpl(LocationInitializer li) {
        this.x = li.x;
        this.y = li.y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CoordinateImpl coordinate)) {
            return false;
        }
        return x == coordinate.x && y == coordinate.y;
    }
}









