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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Room class represents a room within the game.
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
	private Game curGame;	// The current instance of the game that is being played

	/**
	 * The constructor method to create the room. Creates the grid based on the
	 * given inputs for the number of rows and columns for the room.
	 *
	 * @param r The number of rows that the room has
	 * @param c The number of columns that the room has
	 * @param sr The row of the player spawn point
	 * @param sc The column of the player spawn point
	 * @param g The current instance of the game that is being played
	 */
	public Room(int r, int c, int sr, int sc, Game g) {
		rows = r;
		columns = c;
		spawnRow = sr;
		spawnColumn = sc;
		curGame = g;
		grid = new int[r][c];
		spawned = false;
		spawnPlayer();
	}

	/**
	 * The constructor method to create the room based on an input file. It
	 * takes in the name of the room file without its location and file type, as
	 * all room files are placed in the "resources/rooms/" directory and are of
	 * the ".txt" file type.
	 *
	 * @param roomName The name of the room file
	 * @param g The current instance of the game that is being played
	 */
	public Room(String roomName, Game g) {
		try {
			File roomFile = new File("resources/rooms/" + roomName + ".txt");
			Scanner findSize = new Scanner(roomFile);
			Scanner rm = new Scanner(roomFile);
			String line;
			curGame = g;
			columns = 0;

			// Find out how many rows and columns the input file has
			line = findSize.nextLine();
			rows = line.length();
			columns++;
			while (findSize.hasNextLine()) {
				line = findSize.nextLine();
				columns++;
			}

			// Create the grid and fill it with the file contents
			grid = new int[rows][columns];
			int i = 0;
			while (rm.hasNextLine()) {
				line = rm.nextLine();
				for (int j = 0; j < line.length(); j++) {
					// Find player spawn position
					if (line.charAt(j) == '@') {
						spawnRow = i;
						spawnColumn = j;
					} else {
						grid[i][j] = identifyChar(line.charAt(j));
					}
				}
				i++;
			}

			// Check to make sure that there is a spawn position
			if (spawnRow == -1) {
				System.out.println("No spawn position in the room file.");
			} else {
				spawnPlayer();
			}
		} catch (Exception FileNotFoundException) {
			System.out.println("Invalid room file '" + roomName + "'");
		}
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
	 * The method used to convert an integer identifier into its corresponding
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
			case 3:	// Portal
				return '*';
			default:
				return '?';
		}
	}

	/**
	 * The method used to convert a character into its corresponding integer
	 * identifier.
	 *
	 * @param character The character to be converted into an integer identifier
	 * @return The integer identifier specified by the character
	 */
	private int identifyChar(char character) {
		switch (character) {
			case ' ':
				return 0;
			case '#':
				return 1;
			case '@':
				return 2;
			case '*':
				return 3;
			default:
				return 999;
		}
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
		} else {
			System.out.println("Invalid position for addObject.");
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
		} else {
			System.out.println("Invalid position for removeObject.");
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
		} else {
			System.out.println("Incorrect position for moveObject.");
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
		switch (d) {
			case "up":
				newRow -= a;
				break;
			case "down":
				newRow += a;
				break;
			case "left":
				newColumn -= a;
				break;
			case "right":
				newColumn += a;
				break;
			default:
				System.out.println("Invalid direction for movePlayer.");
				break;
		}

		// Test to make sure that the new position is empty and that the new
		// poistion is valid
		if (hasPos(newRow, newColumn)) {
			switch (objectAt(newRow, newColumn)) {
				case 0:
					moveObject(playerRow, playerColumn, newRow, newColumn, 2);
					playerRow = newRow;
					playerColumn = newColumn;
					break;
				case 3:
					curGame.nextRoom();
					break;
				default:
					System.out.println("New position " + formatPosition(newRow, newColumn) + " is not empty: " + identifyInt(objectAt(newRow, newColumn)));
					break;
			}
		} else {
			System.out.println("New position " + formatPosition(newRow, newColumn) + " does not exist.");
		}
	}

	/**
	 * The method used to get the position of the player.
	 *
	 * @return The position of the player as an integer array
	 */
	public int[] getPlayerPos() {
		int[] playerPos = new int[2];
		playerPos[0] = playerRow;
		playerPos[1] = playerColumn;
		return playerPos;
	}

	/**
	 * The method used to get the spawn position of the room.
	 *
	 * @return The spawn position as an integer array
	 */
	public int[] getSpawnPos() {
		int[] spawnPos = new int[2];
		spawnPos[0] = spawnRow;
		spawnPos[1] = spawnColumn;
		return spawnPos;
	}

	/**
	 * The method used to format the coordinates of a position for printing.
	 * Takes in the coordinates as two integers.
	 *
	 * @param r The row of the position
	 * @param c The column of the position
	 * @return The position expressed as a set of coordinates
	 */
	public String formatPosition(int r, int c) {
		return "(" + r + ", " + c + ")";
	}

	/**
	 * The method used to format the coordinates of a position for printing.
	 * Takes in the coordinates as a two entry integer array.
	 *
	 * @param position The row and column of the position as a two entry integer
	 * array
	 * @return The position expressed as a set of coordinates
	 */
	public String formatPosition(int[] position) {
		return "(" + position[0] + ", " + position[1] + ")";
	}
}
