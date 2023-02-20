package escape;

import escape.builder.EscapeGameInitializer;
import escape.builder.PieceTypeDescriptor;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.GameStatus;

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


    private int distance(Coordinate from, Coordinate to) {
        for (PieceTypeDescriptor pieceTypeDescriptor : gameInitializer.getPieceTypes()) {
            if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.LINEAR)) {
                if(from.getRow() == to.getRow()){
                    return Math.abs(from.getColumn() - to.getColumn());
                }
                if(from.getColumn() == to.getColumn()){
                    return Math.abs(from.getRow() - to.getRow());
                }
                if(from.getRow()-to.getRow() == from.getColumn()-to.getColumn()){
                    return Math.abs(from.getRow() - to.getRow());
                }
            } else if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.ORTHOGONAL)) {
                return Math.abs(from.getRow() - to.getRow()) + Math.abs(from.getColumn() - to.getColumn());
            } else if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.OMNI)) {
                int distX = Math.abs(from.getRow() - to.getRow());
                int distY = Math.abs(from.getColumn() - to.getColumn());

                int min = Math.min(distX, distY);
                int max = Math.max(distX, distY);

                int diag = min;
                int straight = max - min;

                return diag + straight;

            }
        }
        int x = Math.abs(from.getRow() - to.getRow());
        int y = Math.abs(from.getColumn() - to.getColumn());
        return x + y;
    }


    @Override
    public boolean isValidMove() {
        for (PieceTypeDescriptor pieceTypeDescriptor : gameInitializer.getPieceTypes()) {
            if (piece.getName().equals(pieceTypeDescriptor.getPieceName())) {
                if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.LINEAR)) {

                    if(pieceTypeDescriptor.getAttribute(EscapePiece.PieceAttributeID.DISTANCE).getValue() < distance(from, to)){
                        return false;
                    }else{
                        return from.getRow() == to.getRow() || from.getColumn() == to.getColumn() || Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn());
                    }
                } else if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.ORTHOGONAL)) {
                    if(pieceTypeDescriptor.getAttribute(EscapePiece.PieceAttributeID.DISTANCE).getValue() < distance(from, to)) {
                        return false;
                    }else{
                        return !from.equals(to);
                    }
                } else if (pieceTypeDescriptor.getMovementPattern().equals(EscapePiece.MovementPattern.OMNI)) {
                    if(pieceTypeDescriptor.getAttribute(EscapePiece.PieceAttributeID.DISTANCE).getValue() < distance(from, to)) {
                        return false;
                    }else{
                        return !from.equals(to);
                    }
                } else if (from.getRow() == to.getRow() && from.getColumn() == to.getColumn()) {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isMoreInformation () {
        return false;
    }

    @Override
    public MoveResult getMoveResult () {
        return null;
    }

    @Override
    public Coordinate finalLocation () {
        return null;
    }

}

