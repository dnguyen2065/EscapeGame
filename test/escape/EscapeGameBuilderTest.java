package escape;

import escape.CoordinateImpl;
import escape.EscapeGameManager;
import escape.builder.EscapeGameBuilder;
import escape.builder.EscapeGameInitializer;
import escape.builder.LocationInitializer;
import escape.builder.PieceTypeDescriptor;
import escape.required.Coordinate;
import escape.required.EscapePiece;
import escape.required.GameStatus;
import escape.required.LocationType;
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
        CoordinateImpl coordinate = new CoordinateImpl(4, 7);
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        EscapePieceImpl escapePiece2 = new EscapePieceImpl(EscapePiece.PieceName.HORSE, "Mark");
        assertTrue(egm.getPieceAt(coordinate).equals(escapePiece));
        assertFalse(egm.getPieceAt(coordinate).equals(escapePiece2));
    }

    @Test
    public void testIsValidMove() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.FROG, "Gary");
        CoordinateImpl from = new CoordinateImpl(4, 7);
        CoordinateImpl to = new CoordinateImpl(3, 7);
        egm.move(from, to);
        GameStatusImpl gameStatus = new GameStatusImpl(escapePiece, egi, from, to);

        assertTrue(gameStatus.isValidMove());

    }

    @Test
    public void testIsValidMove2() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameInitializer egi = egb.getGameInitializer();

        EscapeGameManager egm = egb.makeGameManager();
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.BIRD, "Chris");
        CoordinateImpl from = new CoordinateImpl(4, 6);
        CoordinateImpl to = new CoordinateImpl(2, 5);
        egm.move(from, to);
        GameStatusImpl gameStatus = new GameStatusImpl(escapePiece, egi, from, to);
        assertFalse(gameStatus.isValidMove());
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
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 7), egm.makeCoordinate(4, 4));
        gs.isValidMove();
        assertEquals("Chris", egi.currPlayer);
        gs = egm.move(egm.makeCoordinate(4, 6), egm.makeCoordinate(2, 6));
        gs.isValidMove();
        assertEquals("Gary", egi.currPlayer);
    }

    @Test
    public void testInfBoard() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/infconfig.egc");
        EscapeGameManager<Coordinate> egm = egb.makeGameManager();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 4), egm.makeCoordinate(4, 14));
        assertTrue(gs.isValidMove());
//        gs = egm.move(egm.makeCoordinate(4, 6), egm.makeCoordinate(4, 507));
//        assertFalse(gs.isValidMove()); this test would not run because bfs took too long
    }

    @Test
    public void testDrawGame() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 7), egm.makeCoordinate(3, 7));//gary
        gs.isValidMove();
        gs = egm.move(egm.makeCoordinate(4, 6), egm.makeCoordinate(5, 6));//chris
        gs.isValidMove();
        gs = egm.move(egm.makeCoordinate(3, 7), egm.makeCoordinate(1, 7));//gary
        gs.isValidMove();
        gs = egm.move(egm.makeCoordinate(5, 6), egm.makeCoordinate(6, 7));//chris
        gs.isValidMove();
        assertEquals(GameStatus.MoveResult.DRAW, gs.getMoveResult());
    }

    @Test
    public void testLoseGame() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 7), egm.makeCoordinate(2, 7));//gary
        System.out.println(gs.isValidMove());
        gs = egm.move(egm.makeCoordinate(4, 6), egm.makeCoordinate(6, 7));//chris
        System.out.println(gs.isValidMove());
        gs = egm.move(egm.makeCoordinate(2, 7), egm.makeCoordinate(1, 7));//gary
        System.out.println(gs.isValidMove());
        gs = egm.move(egm.makeCoordinate(6, 7), egm.makeCoordinate(8, 8));//chris
        System.out.println(gs.isValidMove());
        assertEquals(GameStatus.MoveResult.LOSE, gs.getMoveResult());
    }

    @Test
    public void testWinGame() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 7), egm.makeCoordinate(6, 7));//gary
        System.out.println(gs.isValidMove());
        gs = egm.move(egm.makeCoordinate(4, 5), egm.makeCoordinate(4, 3));//chris
        System.out.println(gs.isValidMove());
        gs = egm.move(egm.makeCoordinate(6, 7), egm.makeCoordinate(8, 8));//gary
        System.out.println(gs.isValidMove());
        gs = egm.move(egm.makeCoordinate(4, 3), egm.makeCoordinate(4, 1));//chris
        System.out.println(gs.isValidMove());
        assertEquals(GameStatus.MoveResult.WIN, gs.getMoveResult());
    }

    @Test
    public void testGameNotOver() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 7), egm.makeCoordinate(5, 7));//gary
        gs.isValidMove();
        gs = egm.move(egm.makeCoordinate(4, 6), egm.makeCoordinate(4, 3));//chris
        gs.isValidMove();
        gs = egm.move(egm.makeCoordinate(5, 7), egm.makeCoordinate(8, 8));//gary
        gs.isValidMove();

        assertEquals(GameStatus.MoveResult.NONE, gs.getMoveResult());
    }

    @Test
    public void testGameStatus() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameInitializer egi = egb.getGameInitializer();
        GameStatus gs = new GameStatusImpl(new EscapePieceImpl(null, null), egi, new CoordinateImpl(4, 6), new CoordinateImpl(4, 3));
        assertNull(gs.finalLocation());
        assertFalse(gs.isMoreInformation());
    }

    @Test
    public void testEscapePiece() throws Exception {
        EscapePieceImpl escapePiece = new EscapePieceImpl(EscapePiece.PieceName.BIRD, "Chris");
        EscapePieceImpl escapePiece2 = new EscapePieceImpl(EscapePiece.PieceName.BIRD, "Gary");

        assertEquals(EscapePiece.PieceName.BIRD, escapePiece.getName());
        assertEquals("Chris", escapePiece.getPlayer());
        assertFalse(escapePiece.equals(escapePiece2));
    }

    @Test
    public void moveCheck() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 7), egm.makeCoordinate(4, 7));//gary
        assertFalse(gs.isValidMove());
        gs = egm.move(egm.makeCoordinate(4, 5), egm.makeCoordinate(4, 1));//chris
        assertFalse(gs.isValidMove());


    }

    @Test
    public void testGetTurnLimit() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        Score score = new Score("frank", egi);
        assertEquals(2, score.getTurnLimit());
    }

    @Test
    public void testOrthoTooFar() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/orthoconfig.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 7), egm.makeCoordinate(4, 1));//gary
        gs.isValidMove();
        assertFalse(gs.isValidMove());
    }

    @Test
    public void testOmniTooFar() throws Exception {
        EscapeGameBuilder egb = new EscapeGameBuilder("configurations/config.egc");
        EscapeGameManager egm = egb.makeGameManager();
        EscapeGameInitializer egi = egb.getGameInitializer();
        GameStatus gs;
        gs = egm.move(egm.makeCoordinate(4, 7), egm.makeCoordinate(1, 6));//gary
        assertFalse(gs.isValidMove());
    }


}