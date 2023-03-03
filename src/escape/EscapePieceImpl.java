package escape;

import escape.builder.EscapeGameInitializer;
import escape.required.EscapePiece;

import java.util.Objects;

public class EscapePieceImpl implements EscapePiece {
    private final EscapePiece.PieceName name;
    private final String player;

    /**
     * Constructor for EscapePieceImpl
     *
     * @param name   name of the piece
     * @param player player of the piece
     */
    public EscapePieceImpl(EscapePiece.PieceName name, String player) {
        this.name = name;
        this.player = player;
    }

    /**
     * gets the player of the piece
     *
     * @return player of the piece
     */
    @Override
    public String getPlayer() {
        return player;
    }

    /**
     * gets the name of the piece
     *
     * @return name of the piece
     */
    @Override
    public EscapePiece.PieceName getName() {
        return name;
    }

    /**
     * checks if two pieces are equal
     *
     * @param o object to be compared
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        EscapePieceImpl escapePiece = (EscapePieceImpl) o;
        return name == escapePiece.name && Objects.equals(player, escapePiece.player);
    }

}
