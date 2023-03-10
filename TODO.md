Name: Dylan Nguyen

TDD TODO/Task list

**Build Tests**

These are for implementing the EscapeGameBuilder's `makeGameManager()` method.

| **#** | Test                                                                   | Comments                                                                  |
|:-----:|:-----------------------------------------------------------------------|:--------------------------------------------------------------------------|
|   1   | Build game manager 2 X 2, 2 players, and coordinate type SQUARE        | create game object                                                        |
|   2   | Build game with one CLEAR [default]  location                          | create game object                                                        |
|   3   | Create new CoordinateImpl Class                                        | create game object                                                        |
|   4   | Give it x and y coords                                                 | create game object                                                        |
|   5   | Create Test for equals method                                          | create game object                                                        |
|   6   | Override equals method                                                 | create game object                                                        |
|   7   | Create test for making a Coordinate                                    | create game object                                                        |
|   8   | Override makeCoordinate method                                         | create game object                                                        |
|   9   | Test getRow method                                                     | create game object                                                        |
|  10   | Test getColumn method                                                  | create game object                                                        |
|  11   | Override both methods                                                  | create game object                                                        |
|  12   | Test makeGameManager                                                   | create game object                                                        |
|  13   | populate makeGameManager                                               | create game object                                                        |
|  14   | Test coordiante types (already implemented)                            | create game object                                                        |
|  15   | Test getting the player from piece                                     | create game object                                                        |
|  16   | Test getName from piece                                                | create game object                                                        |
|  17   | Create EscapePieceImpl                                                 | create game object                                                        |
|  18   | Override getPlayer method                                              | create game object                                                        |
|  19   | Override getName method                                                | create game object                                                        |
|  20   | Test Escape piece equals                                               | create game object                                                        |
|  21   | Override equals method                                                 | create game object                                                        |
|  22   | Test getPieceAt                                                        | create game object                                                        |
|  23   | Override getPieceAt method with equals                                 | create game object                                                        |
|  24   | create GameStatusImpl                                                  | create game object                                                        |
|  25   | Test isValidMove for many cases                                        | create game object                                                        |
|  26   | Override isValidMove method                                            | create game object                                                        |
|  27   | add helper method to get distance depending on movement pattern        | create game object                                                        |
|  28   | implement helper into isValidMove                                      | create game object                                                        |
|  29   | Test who the first player to make a move is                            |                                                                           |
|  30   | create firstPlayer method                                              |                                                                           |
|  31   | Test to see generally whos turn it is                                  |                                                                           |
|  32   | implement taking turns                                                 |                                                                           |
|  33   | Test to see if an infinite board is possible                           |                                                                           |
|  34   | implement infinite board                                               | because of my implementation of bfs it takes to long to go a far distance |
|  35   | Test a draw game                                                       |                                                                           |
|  36   | implement draw game when querying getMoveResult                        |                                                                           |
|  37   | Test to see if a player can lose                                       |                                                                           |
|  38   | implement losing a game when querying getMoveResult                    |                                                                           |
|  39   | Test to see if a player can win                                        |                                                                           |
|  40   | implement winning a game when querying getMoveResult                   |                                                                           |
|  41   | Test to see if the game is not over if all the turns havent been taken |                                                                           |
|  42   | implement game not over when querying getMoveResult                    |                                                                           |
|  43   | Test invalid movement requirements                                     |                                                                           |
|  44   | create method to check for invalid moves                               |                                                                           |

NOTE: no major refactoring was needed between tests.
