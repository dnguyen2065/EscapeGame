package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.GameStatus;


public class EscapeGameManagerImpl<C extends Coordinate> implements EscapeGameManager<CoordinateImpl>
{
    private EscapeGameInitializer gameInitializer;


    private String[] players;

    public EscapeGameManagerImpl(EscapeGameInitializer gameInitializer) {
        this.gameInitializer = gameInitializer;
        int boardX = gameInitializer.getxMax();
        int boardY = gameInitializer.getyMax();
        Coordinate.CoordinateType coordinateType = gameInitializer.getCoordinateType();
    }
    @Override
    public CoordinateImpl makeCoordinate(int x, int y) {
        return new CoordinateImpl(x, y);
    }

    @Override
    public EscapePiece getPieceAt(CoordinateImpl coordinate) {
        LocationInitializer[] LI = gameInitializer.getLocationInitializers();

        for (LocationInitializer locationInitializer : LI) {
            if (locationInitializer.x == coordinate.getRow() && locationInitializer.y == coordinate.getColumn()) {
                return new EscapePieceImpl(locationInitializer.pieceName, locationInitializer.player);
            }
        }
        return null;
    }
    @Override
    public GameStatus move (CoordinateImpl from, CoordinateImpl to) {
        GameStatusImpl gameStatus = new GameStatusImpl(getPieceAt(from), gameInitializer, from, to);
        if(gameInitializer.getNumPlayerTurns() == 0){
            gameInitializer.firstPlayer();
        }
        if (gameStatus.isValidMove()) {
            System.out.println("Valid Move");
            makeMove(from, to);
            gameInitializer.playerTurn();
        }
        return gameStatus;
    }
    private  void makeMove(CoordinateImpl from, CoordinateImpl to) {
        LocationInitializer[] LI = gameInitializer.getLocationInitializers();
        for (LocationInitializer locationInitializer : LI) {
            if (locationInitializer.x == from.getRow() && locationInitializer.y == from.getColumn()) {
                locationInitializer.x = to.getRow();
                locationInitializer.y = to.getColumn();

            }
        }

    }

}
