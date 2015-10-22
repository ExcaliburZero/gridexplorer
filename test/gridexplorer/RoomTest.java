/*
 * The MIT License
 *
 * Copyright 2015 Christopher Wells <cwellsny@nycap.rr.com>.
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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christopher Wells <cwellsny@nycap.rr.com>
 */
public class RoomTest {

	private String[] testRooms = {"room1"};
	private Game testGame;
	private int testRows = 4;
	private int testColumns = 5;
	private int testSpawnRow = 3;
	private int testSpawnColumn = 2;
	private Room testRoom;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		// Setup output streams
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() {
		// Cleanup output streams
		System.setOut(null);
		System.setErr(null);
	}

	/**
	 * A test which checks to make sure that the getColumns method of the Room
	 * class returns the correct value for a basic room.
	 */
	@Test
	public void testGetColumns() {
		String testInfo = "A test for the getColumns method of Room class";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		int columns = testRoom.getColumns();
		assertEquals(testInfo, testColumns, columns);
	}

	/**
	 * A test which checks to make sure that the getRows method of the Room
	 * class returns the correct value for a basic room.
	 */
	@Test
	public void testGetRows() {
		String testInfo = "A test for the getRows method of Room class";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		int rows = testRoom.getRows();
		assertEquals(testInfo, testRows, rows);
	}

	/**
	 * A test which checks the getSpawnPos method of the Room class.
	 */
	@Test
	public void testGetSpawnPos() {
		String testInfo = "A test for the getSpawnPos method of the Room class";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		int[] spawnPos = {testSpawnRow, testSpawnColumn};
		int[] returnedSpawnPos = testRoom.getSpawnPos();
		assertArrayEquals(testInfo, spawnPos, returnedSpawnPos);
	}

	/**
	 * A test which moves the player and checks the getPlayerPos method of the
	 * Room class.
	 */
	@Test
	public void testGetPlayerPos() {
		String testInfo = "A test for the getPlayerPos method of the Room class";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		int[] expectedPlayerPos = {testSpawnRow - 1, testSpawnColumn - 1};
		testRoom.movePlayer("up", 1);
		testRoom.movePlayer("left", 1);
		int[] returnedPlayerPos = testRoom.getPlayerPos();
		assertArrayEquals(testInfo, expectedPlayerPos, returnedPlayerPos);
	}

	/**
	 * A test which attempts to display a basic room and checks that the output
	 * is correct for the given info about the room.
	 */
	@Test
	public void testDisplay() {
		String testInfo = "A test for the creation and display of a basic room";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		testRoom.display();
		assertEquals(testInfo, "     \n     \n     \n  @  \n", outContent.toString());
	}

	/**
	 * A test which checks the two integer argument form of the formatPosition
	 * method of the Room class.
	 */
	@Test
	public void testFormatPositionInts() {
		String testInfo = "A test for the formatPosition method of Room class";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		String returnedString = testRoom.formatPosition(1, 2);
		assertEquals(testInfo, "(1, 2)", returnedString);
	}

	/**
	 * A test which checks the integer array argument form of the formatPosition
	 * method of the Room class.
	 */
	@Test
	public void testFormatPositionIntArray() {
		String testInfo = "A test for the formatPosition method of Room class";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		int[] testPoints = {1, 2};
		String returnedString = testRoom.formatPosition(testPoints);
		assertEquals(testInfo, "(1, 2)", returnedString);
	}

	/**
	 * A test which checks the hasPos method of the Room class. It checks the
	 * output of the method against some certain known points.
	 */
	@Test
	public void testHasPos() {
		String testInfo = "A test for the hasPos method of Room class";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		int[][] testPoints = {
			{0, 0}, // True
			{-1, 0}, // False
			{0, -1}, // False
			{testRows, testColumns}, // False
			{testRows - 1, testColumns - 1}, // True
			{testRows, testColumns - 1}, // False
			{testRows - 1, testColumns}, // False
			{testRows - 1, 0}, // True
			{0, testColumns - 1}, // True
			{testRows, 0}, // False
			{0, testColumns}, // False
		};
		boolean[] expectedResults = {
			true, // (0, 0)
			false, // (-1, 0)
			false, // (0, -1)
			false, // (testRows, testColumns)
			true, // (testRows - 1, testColumns - 1)
			false, // (testRows, testColumns - 1)
			false, // (testRows - 1, testColumns)
			true, // (testRows - 1, 0)
			true, // (0, testColumns - 1)
			false, // (testRows, 0)
			false, // (0, testColumns)
		};
		for (int i = 0; i < testPoints.length; i++) {
			boolean returnedOutcome = testRoom.hasPos(testPoints[i][0], testPoints[i][1]);
			assertTrue(testInfo, expectedResults[i] == returnedOutcome);
		}
	}

	/**
	 * A test which checks the objectAt method of the Room class using several
	 * points with know objects at them.
	 */
	@Test
	public void testObjectAt() {
		String testInfo = "A test for the objectAt method of Room class";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		int[][] testPositions = {
			{0, 0}, // Empty
			{testSpawnRow, testSpawnColumn}, // Player position
			{-1, -1}, // Non-existant position
		};
		int[] expectedResults = {
			0, // (0, 0)
			2, // (testSpawnRow, testSpawnColumn)
			999, // (-1, -1)
		};
		for (int i = 0; i < testPositions.length; i++) {
			int returnedIdentifier = testRoom.objectAt(testPositions[i][0], testPositions[i][1]);
			assertEquals(testInfo, expectedResults[i], returnedIdentifier);
		}
	}

	/**
	 * A test which checks the spawnPlayer method of the Room class. It works by
	 * moving the player from the spawn position, spawning the player, and then
	 * checking to make sure that the player is back at the spawn point.
	 */
	@Test
	public void testSpawnPlayer() {
		String testInfo = "A test for the spawnPlayer method of the Room class";
		testGame = new Game(testRooms);
		testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
		int[] expectedPlayerPos = {testSpawnRow, testSpawnColumn};
		testRoom.movePlayer("up", 1);
		testRoom.movePlayer("left", 1);
		testRoom.spawnPlayer();
		int[] returnedPlayerPos = testRoom.getPlayerPos();
		assertArrayEquals(testInfo, expectedPlayerPos, returnedPlayerPos);
	}
}
