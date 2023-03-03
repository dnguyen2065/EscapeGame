package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.builder.PieceTypeDescriptor;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.LocationType;

import java.util.Objects;

public class MoveCheck {


    EscapePiece piece;
    EscapeGameInitializer gameInitializer;
    Coordinate from;
    Coordinate to;
    String playerTurn;


    public MoveCheck(EscapePiece piece, EscapeGameInitializer gameInitializer, Coordinate from, Coordinate to) {
        this.piece = piece;
        this.gameInitializer = gameInitializer;
        this.from = from;
        this.to = to;
        this.playerTurn = gameInitializer.currPlayer;
    }

    public static int distance(CoordinateImpl from, CoordinateImpl to, PieceTypeDescriptor pieceTypeDescriptor) {
        EscapePiece.MovementPattern pattern = whatPattern(pieceTypeDescriptor);
        assert pattern != null;
        if (pattern.equals(EscapePiece.MovementPattern.LINEAR)) {
            //return linDist(from, to);
        } else if (pattern.equals(EscapePiece.MovementPattern.ORTHOGONAL)) {
            return orthoDist(from, to);
        } else if (pattern.equals(EscapePiece.MovementPattern.OMNI)) {
            return omniDist(from, to);
        }
        return -1;
    }

    private static EscapePiece.MovementPattern whatPattern(PieceTypeDescriptor pieceTypeDescriptor) {
        if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.LINEAR)) {
            return EscapePiece.MovementPattern.LINEAR;
        } else if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.ORTHOGONAL)) {
            return EscapePiece.MovementPattern.ORTHOGONAL;
        } else if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.OMNI)) {
            return EscapePiece.MovementPattern.OMNI;
        }
        return null;
    }
// For coverage
//    private static int linDist(CoordinateImpl from, CoordinateImpl to) {
//        if (from.getRow() == to.getRow()) {
//            return Math.abs(from.getColumn() - to.getColumn());
//        }
//        if (from.getColumn() == to.getColumn()) {
//            return Math.abs(from.getRow() - to.getRow());
//        }
//        if (from.getRow() - to.getRow() == from.getColumn() - to.getColumn()) {
//            return Math.abs(from.getRow() - to.getRow());
//        }
//        return -1;
//    }

    private static int orthoDist(CoordinateImpl from, CoordinateImpl to) {
        return Math.abs(from.getRow() - to.getRow()) + Math.abs(from.getColumn() - to.getColumn());
    }

    private static int omniDist(CoordinateImpl from, CoordinateImpl to) {
        int distX = Math.abs(from.getRow() - to.getRow());
        int distY = Math.abs(from.getColumn() - to.getColumn());

        int min = Math.min(distX, distY);
        int max = Math.max(distX, distY);

        int straight = max - min;

        return min + straight;
    }

    public boolean falseConditions() {
        EscapeGameManager<CoordinateImpl> escapeGameManager = new EscapeGameManagerImpl<>(gameInitializer);
        if (escapeGameManager.getPieceAt((CoordinateImpl) from) == null) {
            return false;
        } else if ((escapeGameManager.getPieceAt((CoordinateImpl) to) != null && !Objects.equals(escapeGameManager.getPieceAt((CoordinateImpl) from).getPlayer(), escapeGameManager.getPieceAt((CoordinateImpl) to).getPlayer()))) {
            return false;
        } else if (escapeGameManager.getPieceAt((CoordinateImpl) to) != null && Objects.equals(escapeGameManager.getPieceAt((CoordinateImpl) from), escapeGameManager.getPieceAt((CoordinateImpl) to))) {
            return false;
        } else if (to.getColumn() <= 0 || to.getRow() <= 0) {
            return false;
        } else if ((gameInitializer.getxMax() < to.getRow() || gameInitializer.getyMax() < to.getColumn()) && (gameInitializer.getxMax() != 0 || gameInitializer.getyMax() != 0)) {
            return false;
        } else if (!Objects.equals(gameInitializer.currPlayer, escapeGameManager.getPieceAt((CoordinateImpl) from).getPlayer())) {
            return false;
        } else if (from.getRow() == to.getRow() && from.getColumn() == to.getColumn()) {
            return false;
        } else {
            return true;
        }


    }

    public boolean isValidMove() {
        EscapeGameManagerImpl<CoordinateImpl> escapeGameManager = new EscapeGameManagerImpl<>(gameInitializer);
        for (PieceTypeDescriptor pieceTypeDescriptor : gameInitializer.getPieceTypes()) {
            if (pieceTypeDescriptor.getPieceName().equals(escapeGameManager.getPieceAt((CoordinateImpl) from).getName())) {
                if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.LINEAR)) {

                    if (pieceTypeDescriptor.getAttribute(EscapePiece.PieceAttributeID.DISTANCE).getValue() < distance((CoordinateImpl) from, (CoordinateImpl) to, pieceTypeDescriptor)) {
                        return false;
                    } else {
                        return makeMove();
                    }
                } else if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.ORTHOGONAL)) {
                    if (pieceTypeDescriptor.getAttribute(EscapePiece.PieceAttributeID.DISTANCE).getValue() < distance((CoordinateImpl) from, (CoordinateImpl) to, pieceTypeDescriptor)) {
                        return false;
                    } else {
                        return makeMove();

                    }
                } else if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.OMNI)) {
                    if (pieceTypeDescriptor.getAttribute(EscapePiece.PieceAttributeID.DISTANCE).getValue() < distance((CoordinateImpl) from, (CoordinateImpl) to, pieceTypeDescriptor)) {
                        return false;
                    } else {
                        return makeMove();

                    }
                }
            }
        }

        return false;
    }


    private boolean makeMove() {
        LocationInitializer[] LI = gameInitializer.getLocationInitializers();
        Score score = new Score(playerTurn, gameInitializer);
        score.accumScore((CoordinateImpl) from, (CoordinateImpl) to);
        for (LocationInitializer locationInitializer : LI) {
            if (locationInitializer.x == from.getRow() && locationInitializer.y == from.getColumn()) {
                gameInitializer.playerTurn();
                locationInitializer.x = to.getRow();
                locationInitializer.y = to.getColumn();
                return true;
            }
        }
        return false;
    }

}
