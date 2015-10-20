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
	private Room testRoom = new Room(4, 5, 3, 2, testGame);

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
	}

	@After
	public void tearDown() {
	}
	
	/**
	 * A test which checks to make sure that the getColumns method of the Room
	 * class returns the correct value for a basic room.
	 */
	@Test
	public void testGetColumns() {
		int columns = testRoom.getColumns();
		assertEquals("A test for the getColumns method of Room class", 5, columns);
	}
	
	/**
	 * A test which checks to make sure that the getRows method of the Room
	 * class returns the correct value for a basic room.
	 */
	@Test
	public void testGetRows() {
		int rows = testRoom.getRows();
		assertEquals("A test for the getRows method of Room class", 4, rows);
	}
}
