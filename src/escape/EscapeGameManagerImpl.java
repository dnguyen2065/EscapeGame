package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.GameStatus;


public class EscapeGameManagerImpl<C extends Coordinate> implements EscapeGameManager<CoordinateImpl> {
    private EscapeGameInitializer gameInitializer;
    int boardX;
    int boardY;
    Coordinate.CoordinateType coordinateType;
    private String[] players;
    private LocationInitializer[] locationInitializers;

    public EscapeGameManagerImpl(EscapeGameInitializer gameInitializer) {
        this.gameInitializer = gameInitializer;
        this.boardX = gameInitializer.getxMax();
        this.boardY = gameInitializer.getyMax();
        this.coordinateType = gameInitializer.getCoordinateType();
        this.locationInitializers = gameInitializer.getLocationInitializers();
    }

    @Override
    public CoordinateImpl makeCoordinate(int x, int y) {
        return new CoordinateImpl(x, y);
    }

    @Override
    public EscapePieceImpl getPieceAt(CoordinateImpl coordinate) {

        for (LocationInitializer locationInitializer : locationInitializers) {
            if (locationInitializer.x == coordinate.getRow() && locationInitializer.y == coordinate.getColumn()) {
                return new EscapePieceImpl(locationInitializer.pieceName, locationInitializer.player);
            }
        }
        return null;
    }

    @Override
    public GameStatus move(CoordinateImpl from, CoordinateImpl to) {
        GameStatus gameStatus = new GameStatusImpl(getPieceAt(from), gameInitializer, from, to);
        if (gameInitializer.getNumPlayerTurns() == 0) {
            System.out.println("First player is " + gameInitializer.firstPlayer());
            gameInitializer.firstPlayer();
        }


        return gameStatus;
    }

    private void makeMove(CoordinateImpl from, CoordinateImpl to) {
        for (LocationInitializer locationInitializer : locationInitializers) {
            if (locationInitializer.x == from.getRow() && locationInitializer.y == from.getColumn()) {
                locationInitializer.x = to.getRow();
                locationInitializer.y = to.getColumn();
            }
        }
    }

}
