package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.GameStatus;
import escape.required.LocationType;


public class EscapeGameManagerImpl<C extends Coordinate> implements EscapeGameManager<CoordinateImpl> {
    private EscapeGameInitializer gameInitializer;
    int boardX;
    int boardY;
    Coordinate.CoordinateType coordinateType;
    private String[] players;
    private LocationInitializer[] locationInitializers;

    /**
     * Constructor for EscapeGameManagerImpl
     *
     * @param gameInitializer gameInitializer
     */
    public EscapeGameManagerImpl(EscapeGameInitializer gameInitializer) {
        this.gameInitializer = gameInitializer;
        this.boardX = gameInitializer.getxMax();
        this.boardY = gameInitializer.getyMax();
        this.coordinateType = gameInitializer.getCoordinateType();
        this.locationInitializers = gameInitializer.getLocationInitializers();
    }

    /**
     * creates a new coordinate object
     *
     * @param x the x component
     * @param y the y component
     * @return coordinate
     */
    @Override
    public CoordinateImpl makeCoordinate(int x, int y) {
        return new CoordinateImpl(x, y);
    }

    /**
     * gets the piece at a given coordinate
     *
     * @param coordinate coordinate
     * @return piece at the coordinate
     */
    @Override
    public EscapePieceImpl getPieceAt(CoordinateImpl coordinate) {

        for (LocationInitializer locationInitializer : locationInitializers) {
            if (locationInitializer.x == coordinate.getRow() && locationInitializer.y == coordinate.getColumn()) {
                if (locationInitializer.locationType != LocationType.EXIT) {
                    return new EscapePieceImpl(locationInitializer.pieceName, locationInitializer.player);

                }
            }
        }
        return null;
    }

    /**
     * creates a new game status object
     *
     * @param from starting location
     * @param to   ending location
     * @return game status
     */
    @Override
    public GameStatus move(CoordinateImpl from, CoordinateImpl to) {
        GameStatus gameStatus = new GameStatusImpl(getPieceAt(from), gameInitializer, from, to);
        if (gameInitializer.getNumPlayerTurns() == 0) {
            gameInitializer.firstPlayer();
        }


        return gameStatus;
    }


}
