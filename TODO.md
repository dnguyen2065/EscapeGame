Name: Dylan Nguyen

TDD TODO/Task list

**Build Tests**
    
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
    public void testCoordinateEquals(){
        CoordinateImpl coordinate = new CoordinateImpl(1, 1);
        CoordinateImpl coordinate2 = new CoordinateImpl(1, 1);
        assertTrue(coordinate.equals(coordinate2));

    }

    @Test
    public void testCoordinateEquals2(){
        CoordinateImpl coordinate = new CoordinateImpl(1, 1);
        CoordinateImpl coordinate2 = new CoordinateImpl(2, 2);
        assertFalse(coordinate.equals(coordinate2));

    }

    @Test
    public void testMakeCoordinate(){
        EscapeGameInitializer gameInitializer = new EscapeGameInitializer();
        EscapeGameManagerImpl<Coordinate> egm = new EscapeGameManagerImpl<>(gameInitializer);
        CoordinateImpl testCoord = new CoordinateImpl(1, 1);
        CoordinateImpl coord = egm.makeCoordinate(1, 1);
        assertEquals(testCoord, coord);
    }

    @Test
    public void testMakeCoordinate2(){
        EscapeGameInitializer gameInitializer = new EscapeGameInitializer();
        EscapeGameManagerImpl<Coordinate> egm = new EscapeGameManagerImpl<>(gameInitializer);
        CoordinateImpl testCoord = new CoordinateImpl(1, 1);
        CoordinateImpl coord = egm.makeCoordinate(2, 1);
        assertNotEquals(testCoord, coord);
    }

    @Test
    public void testGetRow(){
        CoordinateImpl coordinate = new CoordinateImpl(1, 1);
        assertEquals(1, coordinate.getRow());
        assertNotEquals(2, coordinate.getRow());
    }

    @Test
    public void testGetColumn(){
        CoordinateImpl coordinate = new CoordinateImpl(1, 1);
        assertEquals(1, coordinate.getColumn());
        assertNotEquals(2, coordinate.getColumn());
    }

These are for implementing the EscapeGameBuilder's `makeGameManager()` method.

| **#** | Test                                                            | Comments                        |
|:-----:|:----------------------------------------------------------------|:--------------------------------|
|   1   | Build game manager 2 X 2, 2 players, and coordinate type SQUARE | create game object              |
|   2   | Build game with one CLEAR [default]  location                   | create game object              |
|   3   | Create new CoordinateImpl Class                                 | create game object              |
|   4   | Give it x and y coords                                          | create game object              |
|   5   | Create Test for equals method                                   | create game object              |
|   6   | Override equals method                                          | create game object              |
|   7   | Create test for making a Coordinate                             | create game object              |
|   8   | Override makeCoordinate method                                  | create game object              |
|   9   | Test getRow method                                              | create game object              |
|   10  | Test getColumn method                                           | create game object              |
|   11  | Override both methods                                           | create game object              |
|   12  | Test makeGameManager                                            | create game object              |
|   13  | populate makeGameManager                                        | create game object              |

NOTE: no major refactoring was needed between tests.
