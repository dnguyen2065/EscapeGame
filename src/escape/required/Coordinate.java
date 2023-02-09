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

package escape.required;

/**
 * This interface defines the requirements for coordinates.
 *
 * MODIFIABLE: NO
 * MOVEABLE: NO
 * REQUIRED: YES
 * 
 * You may extend this interface for your internal use.
 *
 * IMPORTANT: Override the equals(Coordinate other) method in your implementation so that two
 * Coordinates with the same row and column are equal.
 */
public interface Coordinate
{
    /**
     * This enumeration defines the valid types of coordinates that may be
     * available for this course offering. While you may not use these in
     * your EscapeGameManager implementation, it is used in the
     */
	static enum CoordinateType {
		// Standard hex coordinates
		// The distance from (0,0)->(-1, 2) is 2; (-1, 2)->(2, -2) is 4.
		HEX,

		// Standard squares where distance is measure as shortest combination of
		// orthogonal and diagonal moves. Examples: (1,1)->(2,2) is distance 1,
		// (1,2)->(3,5) is distance 3
		SQUARE
	};

	/**
	 * @return the column component of the Coordinate
	 */
	default public int getRow() {
		throw new EscapeException("Not implemented");
	}

	/**
	 * @return the row component of the Coordinate
	 */
	default public int getColumn() {
		throw new EscapeException("Not implemented");
	}
}
