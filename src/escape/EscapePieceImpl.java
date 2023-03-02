package escape;

import escape.builder.EscapeGameInitializer;
import escape.required.EscapePiece;

import java.util.Objects;

public class EscapePieceImpl implements EscapePiece {
    private final EscapePiece.PieceName name;
    private final String player;

    public EscapePieceImpl(EscapePiece.PieceName name, String player) {
        this.name = name;
        this.player = player;
    }



    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public EscapePiece.PieceName getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EscapePieceImpl escapePiece)) {
            return false;
        }
        return Objects.equals(name, escapePiece.name) && Objects.equals(player, escapePiece.player);
    }

}
