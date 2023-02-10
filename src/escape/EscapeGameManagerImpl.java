package escape;

import escape.builder.EscapeGameInitializer;
import escape.required.Coordinate;


public class EscapeGameManagerImpl<C extends Coordinate> implements EscapeGameManager<CoordinateImpl>
{
    private EscapeGameInitializer gameInitializer;
    private final int boardX;
    private final int boardY;
    private final Coordinate.CoordinateType coordinateType;

    private String player1;
    private String player2;

    public EscapeGameManagerImpl(EscapeGameInitializer gameInitializer) {
        this.boardX = gameInitializer.getxMax();
        this.boardY = gameInitializer.getyMax();
        this.coordinateType = gameInitializer.getCoordinateType();
    }



    @Override
    public CoordinateImpl makeCoordinate(int x, int y) {
        return new CoordinateImpl(x, y);
    }


}
