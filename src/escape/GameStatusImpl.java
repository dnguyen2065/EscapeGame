package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.GameStatus;
import escape.required.LocationType;

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
        if (!moveCheck.falseConditions()) {
            return false;
        }
        return moveCheck.isValidMove();
    }

    @Override
    public boolean isMoreInformation() {
        return false;
    }

    @Override
    public MoveResult getMoveResult() {
        System.out.println(gameInitializer.currPlayer + ": " + gameInitializer.originalPlayerScore);
        System.out.println(gameInitializer.prevPlayer + ": " + gameInitializer.opponentPlayerScore);
        Score score = new Score(gameInitializer.prevPlayer, gameInitializer);

        int numTurns = gameInitializer.getNumPlayerTurns();
        if (numTurns >= score.getTurnLimit() * 2) {
            if (gameInitializer.originalPlayerScore == gameInitializer.opponentPlayerScore) {
                return MoveResult.DRAW;
            } else if (gameInitializer.opponentPlayerScore < gameInitializer.originalPlayerScore) {
                return MoveResult.WIN;
            } else {
                return MoveResult.LOSE;
            }
        }
        return MoveResult.NONE;
    }

    @Override
    public Coordinate finalLocation() {
        return null;
    }

}

