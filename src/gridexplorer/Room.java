/*
 * The MIT License
 *
 * Copyright 2015 Christopher Wells.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package gridexplorer;

/**
 * Handles rooms used by the game.
 *
 * @author Christopher Wells
 */
public class Room {
	private int [][] grid;	// A grid of the objects that are within the room
	private int rows;	// The total number of rows that the room has
	private int columns;	// The total number of columns that the room has
	
	/**
	 * The constructor method to create the room. Creates the grid based on the
	 * given inputs for the number of rows and columns for the room.
	 * 
	 * @param c The number of columns that the room has
	 * @param r The number of rows that the room has
	 */
	public Room(int c, int r) {
		grid = new int[rows][columns];
	}
	
	/**
	 * The method used to get the number of rows that the room has.
	 * 
	 * @return The number of rows the room has
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * The method used to get the number of columns that the room has.
	 * 
	 * @return The number of columns the room has
	 */
	public int getColumns() {
		return columns;
	}
}
