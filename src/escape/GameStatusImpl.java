package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.builder.PieceTypeDescriptor;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.GameStatus;

import java.util.Objects;

import static escape.MoveCheck.distance;

public class GameStatusImpl implements GameStatus {


    EscapePiece piece;
    EscapeGameInitializer gameInitializer;
    Coordinate from;
    Coordinate to;

    public GameStatusImpl(EscapePiece piece, EscapeGameInitializer gameInitializer, Coordinate from, Coordinate to) {
        this.piece = piece;
        this.gameInitializer = gameInitializer;
        this.from = from;
        this.to = to;
    }



    @Override
    public boolean isValidMove() {
        MoveCheck moveCheck = new MoveCheck(piece, gameInitializer, from, to);
        if (!moveCheck.falseConditions()){
            return false;
        }
        return moveCheck.isValidMove();
    }

    @Override
    public boolean isMoreInformation () {
        return false;
    }

    @Override
    public MoveResult getMoveResult () {
        return MoveResult.NONE;
    }

    @Override
    public Coordinate finalLocation () {
        return null;
    }

}

