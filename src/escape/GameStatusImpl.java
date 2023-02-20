package escape;

import escape.builder.EscapeGameInitializer;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.GameStatus;

public class GameStatusImpl implements GameStatus {


    EscapePiece piece;
    EscapeGameInitializer gameInitializer;
    Coordinate from;
    Coordinate to;


    @Override
    public boolean isValidMove() {

        return false;
    }

    @Override
    public boolean isMoreInformation() {
        return false;
    }

    @Override
    public MoveResult getMoveResult() {
        return null;
    }

    @Override
    public Coordinate finalLocation() {
        return null;
    }
}
