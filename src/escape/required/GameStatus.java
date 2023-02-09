/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Copyright ©2023 Gary F. Pollice
 *******************************************************************************/
package escape.required;

/**
 * This interface defines all of the information for a
 * client to understand the game state after a move
 *
 * MODIFIABLE: NO
 * MOVEABLE: NO
 * REQUIRED: YES
 *
 */
public interface GameStatus {

    /**
     * WIN:  Player that moved wins the game
     * LOSE: Player that moved loses the game
     * DRAW: The game is over and is a draw
     * NONE: The game is still in progress and neither player has won
     */
    public static enum MoveResult { WIN, LOSE, DRAW, NONE }

    /**
     * ATTACKER: The player that moved attacked another piece and won
     * DEFENDER: The defending piece won the battle
     * DRAW:     The battle was a draw
     * NONE:     There was no battle
     */
    public static enum CombatResult { ATTACKER, DEFENDER, DRAW, NONE }

    /**
     * @return true if the move was a valid move, fals if not (an exception may have
     * been thown in some cases)
     */
    boolean isValidMove();

    /**
     * @return true if more informaton was sent to the objservers
     */
    boolean isMoreInformation();

    /**
     * @return an indicator if the game ended and the state of win/loss
     */
    MoveResult getMoveResult();

    /**
     * @return the location of the moving piece after the move if it is different
     * the destination (to) specified in the move();
     */
    Coordinate finalLocation();
}
