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
 * Runs the game.
 *
 * @author Christopher Wells
 */
public class Main {

	/**
	 * The method that is run when the program starts.
	 * 
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		// Create a test room
		Room testRoom = new Room(10, 10, 5, 5);
		
		// Display the size of the test room
		System.out.println("Rows: " + testRoom.getRows());
		System.out.println("Columns: " + testRoom.getColumns());
		
		// Display the test room
		testRoom.display();
		
		// Move the player and then display the test room
		//testRoom.movePlayer("left", 2);
		//testRoom.display();
		
		// Try to move the player off of the grid
		testRoom.movePlayer("down", 4);
		testRoom.display();
	}

}
