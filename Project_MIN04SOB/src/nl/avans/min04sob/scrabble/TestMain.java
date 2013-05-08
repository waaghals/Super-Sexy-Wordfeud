package nl.avans.min04sob.scrabble;

import nl.avans.min04sob.scrabble.controllers.BoardDemoController;
import nl.avans.min04sob.scrabble.controllers.ChatControllerPATRICKVOORBEELD;
import nl.avans.min04sob.scrabble.controllers.ScoreboardController;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new BoardDemoController();
		new ScoreboardController();
		new ChatControllerPATRICKVOORBEELD();
	}

}
