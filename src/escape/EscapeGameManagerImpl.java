package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.required.Coordinate;
import escape.required.GameStatus;
import escape.required.LocationType;


public class EscapeGameManagerImpl<C extends Coordinate> implements EscapeGameManager<CoordinateImpl>
{
    private EscapeGameInitializer gameInitializer;
    private final int boardX;
    private final int boardY;
    private final Coordinate.CoordinateType coordinateType;


    private String[] players;

    public EscapeGameManagerImpl(EscapeGameInitializer gameInitializer) {
        this.gameInitializer = gameInitializer;
        this.boardX = gameInitializer.getxMax();
        this.boardY = gameInitializer.getyMax();
        this.coordinateType = gameInitializer.getCoordinateType();
    }
    @Override
    public CoordinateImpl makeCoordinate(int x, int y) {
        return new CoordinateImpl(x, y);
    }

    @Override
    public EscapePieceImpl getPieceAt(CoordinateImpl coordinate) {
        LocationInitializer[] LI =  gameInitializer.getLocationInitializers();

        for (LocationInitializer locationInitializer : LI) {
            if (locationInitializer.x == coordinate.getRow() && locationInitializer.y == coordinate.getColumn()){
                return new EscapePieceImpl(locationInitializer.pieceName, locationInitializer.player);
            }

        }

        return null;
    }
    @Override
    public GameStatus move (CoordinateImpl from, CoordinateImpl to) {



        return null;
    }


}
