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
	private Game testGame = new Game(testRooms);
	private int testRows = 4;
	private int testColumns = 5;
	private int testSpawnRow = 3;
	private int testSpawnColumn = 2;
	private Room testRoom = new Room(testRows, testColumns, testSpawnRow, testSpawnColumn, testGame);
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	public RoomTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

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
		int rows = testRoom.getRows();
		assertEquals(testInfo, testRows, rows);
	}

	/**
	 * A test which attempts to display a basic room and checks that the output
	 * is correct for the given info about the room.
	 */
	@Test
	public void testDisplay() {
		String testInfo = "A test for the creation and display of a basic room";
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
		int[] testPoints = {1, 2};
		String returnedString = testRoom.formatPosition(testPoints);
		assertEquals(testInfo, "(1, 2)", returnedString);
	}
}
