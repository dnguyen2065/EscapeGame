package escape;

import escape.CoordinateImpl;
import escape.EscapeGameManager;
import escape.builder.EscapeGameBuilder;
import escape.builder.EscapeGameInitializer;
import escape.builder.PieceTypeDescriptor;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.GameStatus;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class EscapeGameBuilderTest {

    @Test
    public void test() {
        EscapeGameManager egm = null;
        try {
            egm = new EscapeGameBuilder("configurations/config.egc").makeGameManager();
        } catch (Exception e) {
            fail("Exception from builder: " + e.getMessage());
        }
        assertNotNull(egm);
    }

    @Test
    public void testCoordinateEquals() {
        CoordinateImpl coordinate = new CoordinateImpl(1, 1);
        CoordinateImpl coordinate2 = new CoordinateImpl(1, 1);
        assertTrue(coordinate.equals(coordinate2));

    }

    @Test
    public void testCoordinateEquals2() {
        CoordinateImpl coordinate = new CoordinateImpl(1, 1);
        CoordinateImpl coordinate2 = new CoordinateImpl(2, 2);
        assertFalse(coordinate.equals(coordinate2));

    }

    @Test
    public void testMakeCoordinate() {
        EscapeGameInitializer gameInitializer = new EscapeGameInitializer();
        EscapeGameManagerImpl<Coordinate> egm = new EscapeGameManagerImpl<>(gameInitializer);
        CoordinateImpl testCoord = new CoordinateImpl(1, 1);
        CoordinateImpl coord = egm.makeCoordinate(1, 1);
        assertEquals(testCoord, coord);
    }

    @Test
    public void testMakeCoordinate2() {
        EscapeGameInitializer gameInitializer = new EscapeGameInitializer();
        EscapeGameManagerImpl<Coordinate> egm = new EscapeGameManagerImpl<>(gameInitializer);
        CoordinateImpl testCoord = new CoordinateImpl(1, 1);
        CoordinateImpl coord = egm.makeCoordinate(2, 1);
        assertNotEquals(testCoord, coord);
    }

    @Test
    public void testGetRow() {
        CoordinateImpl coordinate = new CoordinateImpl(1, 1);
        assertEquals(1, coordinate.getRow());
        assertNotEquals(2, coordinate.getRow());
    }

    @Test
    public void testGetColumn() {
        CoordinateImpl coordinate = new CoordinateImpl(1, 1);
        assertEquals(1, coordinate.getColumn());
        assertNotEquals(2, coordinate.getColumn());
    }

    //RELEASE 2 ----------------------------------------------------

    @Test
    public void testGetCoordinateType() {
        EscapeGameInitializer gameInitializer = new EscapeGameInitializer();
        gameInitializer.setCoordinateType(Coordinate.CoordinateType.SQUARE);
        assertEquals(Coordinate.CoordinateType.SQUARE, gameInitializer.getCoordinateType());
    }

    @Test
    public void testGetPlayer() {
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        assertEquals("Gary", escapePiece.getPlayer());
        assertNotEquals("Bob", escapePiece.getPlayer());

    }

    @Test
    public void testGetName() {
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        assertEquals(EscapePiece.PieceName.FROG, escapePiece.getName());
        assertNotEquals(EscapePiece.PieceName.HORSE, escapePiece.getName());
    }

    @Test
    public void testEscapePieceEquals() {
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        EscapePieceImpl escapePiece2 = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        assertTrue(escapePiece.equals(escapePiece2));
    }

    @Test
    public void testEscapePieceEquals2() {
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        EscapePieceImpl escapePiece2 = new EscapePieceImpl(EscapePiece.PieceName.HORSE, "Gary");
        assertFalse(escapePiece.equals(escapePiece2));
    }

    @Test
    public void testGetPieceAt() throws Exception {
        EscapeGameManager egm = new EscapeGameBuilder("configurations/config.egc").makeGameManager();
        CoordinateImpl coordinate = new CoordinateImpl(4, 4);
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        EscapePieceImpl escapePiece2 = new EscapePieceImpl(EscapePiece.PieceName.HORSE, "Mark");
        assertTrue(egm.getPieceAt(coordinate).equals(escapePiece));
        assertFalse(egm.getPieceAt(coordinate).equals(escapePiece2));
    }

    @Test
    public void testIsValidMove() throws Exception {
        EscapeGameBuilder egm = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameInitializer egi = egm.getGameInitializer();
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        CoordinateImpl from = new CoordinateImpl(4, 4);
        CoordinateImpl to = new CoordinateImpl(2, 2);
        GameStatusImpl gameStatus = new GameStatusImpl(escapePiece, egi, from, to);
        assertTrue(gameStatus.isValidMove());

    }

    @Test
    public void testIsValidMove2() throws Exception {
        EscapeGameBuilder egm = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameInitializer egi = egm.getGameInitializer();
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.BIRD, "Chris");
        CoordinateImpl from = new CoordinateImpl(4, 6);
        CoordinateImpl to = new CoordinateImpl(2, 5);
        GameStatusImpl gameStatus = new GameStatusImpl(escapePiece, egi, from, to);
        assertFalse(gameStatus.isValidMove());
    }

    @Test
    public void testIsValidMove3() throws Exception {
        EscapeGameBuilder egm = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameInitializer egi = egm.getGameInitializer();
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.HORSE, "Chris");
        CoordinateImpl from = new CoordinateImpl(4, 5);
        CoordinateImpl to = new CoordinateImpl(7, 7);
        GameStatusImpl gameStatus = new GameStatusImpl(escapePiece, egi, from, to);
        assertTrue(gameStatus.isValidMove());
    }

    @Test
    public void testIsValidMove4() throws Exception {
        EscapeGameBuilder egm = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameInitializer egi = egm.getGameInitializer();
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        CoordinateImpl from = new CoordinateImpl(4, 4);
        CoordinateImpl to = new CoordinateImpl(4, 4);
        GameStatusImpl gameStatus = new GameStatusImpl(escapePiece, egi, from, to);
        assertFalse(gameStatus.isValidMove());
    }
    //FINAL TESTS ----------------------------------------------------

    @Test
    public void testFirstPlayerUp() throws Exception {
        EscapeGameBuilder egm = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameInitializer egi = egm.getGameInitializer();
        String firstPlayer = egi.firstPlayer();
        assertEquals("Gary", firstPlayer);
    }


    @Test
    public void testGetPlayerTurn() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        egi.firstPlayer();
        assertEquals("Gary", egi.currPlayer);
        egm.move(egm.makeCoordinate(4, 4), egm.makeCoordinate(2, 2));
        assertEquals("Chris", egi.currPlayer);
        egm.move(egm.makeCoordinate(4, 6), egm.makeCoordinate(2, 6));
        assertEquals("Gary", egi.currPlayer);
    }

    @Test
    public void testInfBoard() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/infconfig.egc");
        EscapeGameManager<Coordinate> egm = egb.makeGameManager();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 4), egm.makeCoordinate(4, 500));
        assertTrue(gs.isValidMove());
        gs = egm.move(egm.makeCoordinate(4, 500), egm.makeCoordinate(4, 1001));
        assertFalse(gs.isValidMove());
    }

    @Test
    public void testWinGame() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 4), egm.makeCoordinate(2, 2));
        gs.isValidMove();
        gs = egm.move(egm.makeCoordinate(4, 6), egm.makeCoordinate(2, 6));
        gs.isValidMove();
        gs = egm.move(egm.makeCoordinate(2, 2), egm.makeCoordinate(1, 1));
        gs.isValidMove();
        gs = egm.move(egm.makeCoordinate(2, 6), egm.makeCoordinate(1, 6));
        gs.isValidMove();
        assertEquals(gs.getMoveResult(), GameStatus.MoveResult.WIN);
    }


}