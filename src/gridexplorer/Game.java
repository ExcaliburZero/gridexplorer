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

import java.util.Scanner;

/**
 * The Game class represents an instance of the game.
 *
 * @author Christopher Wells
 */
public class Game {

	private Scanner kb = new Scanner(System.in);
	private String moveDirection;
	private String[] roomList;
	private int roomIndex;
	private Room curRoom;
	private boolean playing;

	/**
	 * The method used to construct a Game object. Creates the first room that
	 * the game will have.
	 *
	 * @param rooms A string array of the rooms used in the game in the order
	 * they will be used in
	 */
	public Game(String[] rooms) {
		roomList = rooms;
		roomIndex = 0;
		curRoom = new Room(roomList[roomIndex], this);
		playing = true;
	}

	/**
	 * The method that allows the user to move the player in the game.
	 */
	public void promptMove() {
		System.out.print("Enter a move direction (wasd or q to quit): ");
		moveDirection = kb.nextLine();
		switch (moveDirection) {
			case "w":
				curRoom.movePlayer("up", 1);
				break;
			case "a":
				curRoom.movePlayer("left", 1);
				break;
			case "s":
				curRoom.movePlayer("down", 1);
				break;
			case "d":
				curRoom.movePlayer("right", 1);
				break;
			case "q":
				playing = false;
				break;
			default:
				System.out.println("Invalid command.");
				break;
		}
	}

	/**
	 * The method used to check if the game is still playing or not.
	 *
	 * @return Whether the game is playing or not
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * The method used to display the current room of the game.
	 */
	public void displayRoom() {
		curRoom.display();
		int[] playerPos = curRoom.getPlayerPos();
		System.out.println("Pos: " + curRoom.formatPosition(playerPos));
	}

	/**
	 * The method used to move the game to the next room.
	 */
	public void nextRoom() {
		roomIndex += 1;

		// Check to make sure that there is a next room
		if (roomIndex < roomList.length) {
			curRoom = new Room(roomList[roomIndex], this);
		} else {
			// If there is no next room, then the player has won the game
			winGame();
		}
	}

	/**
	 * The method used end the game, and inform the player that they have
	 * successfully competed the game.
	 */
	private void winGame() {
		System.out.println("Congratulations! You won!");
		playing = false;
	}
}
