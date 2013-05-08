package nl.avans.min04sob.scrabble;

import nl.avans.min04sob.scrabble.controllers.BoardDemoController;
import nl.avans.min04sob.scrabble.controllers.ChatControllerPATRICKVOORBEELD;
import nl.avans.min04sob.scrabble.controllers.ScoreboardController;
import nl.avans.min04sob.scrabble.views.MainWindow;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainWindow gameScreen = new MainWindow();
		new BoardDemoController(gameScreen);
		new ScoreboardController(gameScreen);
		new ChatControllerPATRICKVOORBEELD(gameScreen);
		gameScreen.pack();
	}
}
