package escape.mastertest.master.release2;

import escape.*;
import escape.builder.*;
import escape.mastertest.master.util.BaseTestMaster;
import escape.required.*;
//import master.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.stream.*;

import static escape.required.EscapePiece.PieceName.*;
import static escape.required.GameStatus.MoveResult.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

public class R2MasterSquareTests extends BaseTestMaster {
    private EscapeGameManager<Coordinate> manager = null;

    private static boolean firstTest = true;

    @AfterAll
    static void testBreakdown() {
        firstTest = false;
    }

    @BeforeEach
    void setup() throws Exception {
        manager = new EscapeGameBuilder("master_configurations/R2SQUARE1.egc").makeGameManager();
    }

    public R2MasterSquareTests() {
        super();
        if (firstTest) {
            testReporter.startNewTestGroup("Escape Release 2 tests", 40);
        }
        firstTest = false;
    }

    /********************** Check boards ***********************/
    /**
     * This test, which only is used for two of the pieces on the board
     * is worth so many points because I had to modify the other tests
     * to take into account how many students did not implement the
     * EscapePiece interface.
     */
    @ParameterizedTest
    @MethodSource("configurationSupplier")
    void checkPiecesFromConfiguration(int row, int column, EscapePiece.PieceName name, String player) {
        startTest("Initial board check", 5);
        Coordinate c = manager.makeCoordinate(row, column);
        EscapePiece p = manager.getPieceAt(c);
        assertEquals(name, p.getName());
        assertEquals(player, p.getPlayer());
        markTestPassed();
    }

    static Stream<Arguments> configurationSupplier() {
        return Stream.of(
                arguments(5, 5, HORSE, "A"),
                arguments(4, 10, FROG, "A"),
                arguments(7, 14, DOG, "A"),
                arguments(10, 10, BIRD, "B")
        );
    }

    /********************** Valid first moves ***********************/
    @ParameterizedTest
    @MethodSource("validFirstMoveSupplier")
    void validFirstMove(int fromRow, int fromColumn, int toRow, int toColumn) {
        startTest("validFirstMove", 2);
        Coordinate from = manager.makeCoordinate(fromRow, fromColumn);
        Coordinate to = manager.makeCoordinate(toRow, toColumn);
        EscapePiece pf = manager.getPieceAt(from);
        EscapePiece.PieceName name = pf.getName();
        String player = pf.getPlayer();
        GameStatus status = manager.move(from, to);
        assertNotNull(status);
        assertTrue(status.isValidMove());
        assertEquals(NONE, status.getMoveResult());
        EscapePiece pt = manager.getPieceAt(to);
        System.out.println();
        assertEquals(name, pt.getName());
        assertEquals(player, pt.getPlayer());
        assertNull(manager.getPieceAt(from));
        markTestPassed();
    }

    static Stream<Arguments> validFirstMoveSupplier() {
        return Stream.of(
                // OMNI - distance 1, in line
                arguments(5, 5, 6, 5),
                arguments(5, 5, 6, 6),
                arguments(5, 5, 5, 6),
                arguments(5, 5, 4, 6),
                arguments(5, 5, 4, 5),
                arguments(5, 5, 4, 4),
                arguments(5, 5, 5, 4),
                arguments(5, 5, 6, 4),
                // OMNI non-linear
                arguments(5, 5, 10, 6),
                // ORTHOGONAL
                arguments(4, 10, 6, 11),
                arguments(4, 10, 3, 12),
                // LINEAR
                arguments(7, 14, 12, 14),
                arguments(7, 14, 3, 10),
                arguments(7, 14, 12, 14)
        );
    }

    /********************** Invalid first moves ***********************/
    @ParameterizedTest
    @MethodSource("invalidFirstMoveSupplier")
    void invalidFirstMove(String testName, int fromRow, int fromColumn, int toRow, int toColumn) {
        startTest("invalidFirstMove", 1);
        Coordinate from = manager.makeCoordinate(fromRow, fromColumn);
        Coordinate to = manager.makeCoordinate(toRow, toColumn);
        GameStatus status = manager.move(from, to);
        assertFalse(status.isValidMove());
        markTestPassed();
    }

    static Stream<Arguments> invalidFirstMoveSupplier() {
        return Stream.of(
                arguments("Absolute distance too far", 5, 5, 13, 5),
                arguments("Wrong player", 17, 4, 16, 4),
                arguments("No piece on source", 15, 1, 15, 2),
                arguments("Oppoosing piece on destination", 17, 5, 17, 4),
                arguments("Moving player piece on destination", 17, 5, 18, 5),
                arguments("Orthogonal distance too far", 4, 10, 7, 11),
                arguments("Linear distance too far", 7, 14, 7, 8),
                arguments("Move zero locations", 5, 5, 5, 5),
                arguments("Move off board in positive direction", 9, 16, 9, 19),
                arguments("Move off board in a negative direction", 5, 5, 0, 5)
        );
    }
}
