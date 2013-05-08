package nl.avans.min04sob.scrabble;

import nl.avans.min04sob.mvcvoorbeeld.BoardDemoController;
import nl.avans.min04sob.mvcvoorbeeld.ChatControllerPATRICKVOORBEELD;
import nl.avans.min04sob.mvcvoorbeeld.MainWindow;
import nl.avans.min04sob.mvcvoorbeeld.ScoreboardController;

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
