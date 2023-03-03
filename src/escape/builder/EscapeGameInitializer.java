/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Copyright ©2016-2023 Gary F. Pollice
 *******************************************************************************/

package escape.builder;

import escape.required.Coordinate.*;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * An example of reading a game configuration file and storing the information in this
 * data object. Using this object, you can get the information needed to create your
 * game.
 * <p>
 * MODIFIABLE: YES
 * MOVEABLE: NO
 * REQUIRED: NO
 *
 * @see EscapeGameBuilder#makeGameManager()
 */
@XmlRootElement
public class EscapeGameInitializer {
    private CoordinateType coordinateType;

    // Board items
    private int xMax, yMax;
    private LocationInitializer[] locationInitializers;

    // Piece items
    private PieceTypeDescriptor[] pieceTypes;

    // Rule items
    private RuleDescriptor[] rules;
    private int numPlayerTurns;
    public String currPlayer;
    public int originalPlayerScore;
    public String prevPlayer;
    public int opponentPlayerScore;

    public EscapeGameInitializer() {
        // Needed for JAXB
    }

    /**
     * @return the coordinateType
     */
    public CoordinateType getCoordinateType() {
        return coordinateType;
    }

    /**
     * @param coordinateType the coordinateType to set
     */
    public void setCoordinateType(CoordinateType coordinateType) {
        this.coordinateType = coordinateType;
    }

    /**
     * @return the xMax
     */
    public int getxMax() {
        return xMax;
    }

    /**
     * @param xMax the xMax to set
     */
    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    /**
     * @return the yMax
     */
    public int getyMax() {
        return yMax;
    }

    /**
     * @param yMax the yMax to set
     */
    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    /**
     * @return the locationInitializers
     */
    public LocationInitializer[] getLocationInitializers() {
        return locationInitializers;
    }

    /**
     * @param locationInitializers the locationInitializers to set
     */
    public void setLocationInitializers(LocationInitializer... locationInitializers) {
        this.locationInitializers = locationInitializers;
    }

    /**
     * @return the types
     */
    public PieceTypeDescriptor[] getPieceTypes() {
        return pieceTypes;
    }

    /**
     * @param types the types to set
     */
    public void setPieceTypes(PieceTypeDescriptor... types) {
        this.pieceTypes = types;
    }

    /**
     * @return the rules
     */
    public RuleDescriptor[] getRules() {
        return rules;
    }

    /**
     * @param rules the rules to set
     */
    public void setRules(RuleDescriptor[] rules) {
        this.rules = rules;
    }


    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EscapeGameInitializer [xMax=" + xMax + ", yMax=" + yMax
                + ", coordinateType=" + coordinateType + ", locationInitializers="
                + Arrays.toString(locationInitializers) + ", types="
                + Arrays.toString(pieceTypes) + "]";
    }

    /**
     * @return the numPlayerTurns
     */
    public int getNumPlayerTurns() {
        return numPlayerTurns;
    }

    public String originalPlayer = null;
    public String opponentPlayer = null;

    /**
     * This method is used to determine which player's turn it is. and to keep track of the number of turns
     */
    public void playerTurn() {

        originalPlayer = locationInitializers[0].player;
        for (LocationInitializer li : locationInitializers) {
            if (!li.player.equals(originalPlayer)) {
                opponentPlayer = li.player;
                break;
            }

        }
        if (currPlayer.equals(originalPlayer)) {
            numPlayerTurns++;
            currPlayer = opponentPlayer;
            prevPlayer = originalPlayer;
        } else {
            numPlayerTurns++;
            currPlayer = originalPlayer;
            prevPlayer = opponentPlayer;
        }

    }

    public String firstPlayer() {
        currPlayer = locationInitializers[0].player;
        return locationInitializers[0].player;
    }

}
