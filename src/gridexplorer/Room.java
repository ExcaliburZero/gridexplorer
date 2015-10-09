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

	private int[][] grid;	// A grid of the objects that are within the room
	private int rows;	// The total number of rows that the room has
	private int columns;	// The total number of columns that the room has
	private int spawnRow;	// The row of the player spawn point
	private int spawnColumn;	// The column of the player spawn point
	private int playerRow;	// The current row of the player
	private int playerColumn;	// The current column of the player

	private boolean spawned;	// Whether or not the player has been spawned yet

	/**
	 * The constructor method to create the room. Creates the grid based on the
	 * given inputs for the number of rows and columns for the room.
	 *
	 * @param r The number of rows that the room has
	 * @param c The number of columns that the room has
	 * @param sr The row of the player spawn point
	 * @param sc The column of the player spawn point
	 */
	public Room(int r, int c, int sr, int sc) {
		rows = r;
		columns = c;
		spawnRow = sr;
		spawnColumn = sc;
		grid = new int[r][c];
		spawned = false;
		spawnPlayer();
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

	/**
	 * The method used to determine whether a specific position is valid in the
	 * room or not.
	 *
	 * @param r The row of the position
	 * @param c The column of the position
	 * @return Whether or not the position is valid
	 */
	public boolean hasPos(int r, int c) {
		if (r < 0 || r >= rows) {	// Check if the row is valid
			return false;
		} else if (c < 0 || c >= columns) {	// Check if the column is valid
			return false;
		} else {	// If both are valid, then the position is valid
			return true;
		}
	}

	/**
	 * The method used to get the integer identifier of the object at a given
	 * point.
	 *
	 * @param r The row of the object
	 * @param c The column of the object
	 * @return The integer identifier of the object at the specified point
	 */
	public int objectAt(int r, int c) {
		if (hasPos(r, c)) {
			return grid[r][c];
		} else {
			return 999;
		}
	}

	/**
	 * The method used to convert and integer identifier into its corresponding
	 * character.
	 *
	 * @param indentifier The integer identifier to be converted to a character
	 * @return The character specified by the integer identifier
	 */
	private char identifyInt(int indentifier) {
		switch (indentifier) {
			case 0:	// Blank spaces
				return ' ';
			case 1:	// Walls
				return '#';
			case 2:	// Player
				return '@';
		}
		return '?';
	}

	/**
	 * The method used to display the contents of the grid.
	 */
	public void display() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				// Print out each entry of the grid
				System.out.print(identifyInt(objectAt(i, j)));
			}
			// Handle the rows of the grid
			System.out.println("");
		}
	}

	/**
	 * The method used to add an object to a specified point on the grid.
	 *
	 * @param r The row of the position
	 * @param c The column of the position
	 * @param id The integer identifier of the object to be added
	 */
	private void addObject(int r, int c, int id) {
		if (hasPos(r, c)) {
			grid[r][c] = id;
		}
	}

	/**
	 * The method used to remove an object from a specified point on the grid.
	 *
	 * @param r The row of the position
	 * @param c The column of the position
	 */
	private void removeObject(int r, int c) {
		if (hasPos(r, c)) {
			grid[r][c] = 0;
		}
	}

	/**
	 * The method use to spawn or re-spawn the player at the spawn point.
	 */
	public void spawnPlayer() {
		// If the player is already in the room then remove it
		if (spawned) {
			removeObject(playerRow, playerColumn);
		}

		// Reset the player position
		playerRow = spawnRow;
		playerColumn = spawnColumn;

		// Add the player at the spwan position
		addObject(spawnRow, spawnColumn, 2);
	}

	/**
	 * The method used to move an object from one position to another.
	 *
	 * @param or The old row of the object
	 * @param oc The old column of the object
	 * @param nr The new row of the object
	 * @param nc The new column of the object
	 * @param id The integer identifier for the object
	 */
	private void moveObject(int or, int oc, int nr, int nc, int id) {
		// Check to make sure that the object being moved is actually at the old
		// position
		if (objectAt(or, oc) == id) {
			// Remove the object from its old position and add it to its new position
			removeObject(or, oc);
			addObject(nr, nc, id);
		}
	}

	/**
	 * The method used to move the player.
	 *
	 * @param d The direction to move the player in
	 * @param a The amount of steps to move the player
	 */
	public void movePlayer(String d, int a) {
		// Variables to store the possible new row and column fo the player
		int newRow = playerRow;
		int newColumn = playerColumn;

		// Find the new position of the player based on the direction arguement
		if (d.equals("up")) {
			newRow -= a;
		} else if (d.equals("down")) {
			newRow += a;
		} else if (d.equals("left")) {
			newColumn -= a;
		} else if (d.equals("right")) {
			newColumn += a;
		}

		// Test to make sure that the new position is empty and that the new
		// poistion is valid
		if (objectAt(newRow, newColumn) == 0 && hasPos(newRow, newColumn)) {
			moveObject(playerRow, playerColumn, newRow, newColumn, 2);
		}
	}
}
