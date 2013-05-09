

import nl.avans.min04sob.mvcvoorbeeld.BoardDemoController;
import nl.avans.min04sob.mvcvoorbeeld.ChatController;
import nl.avans.min04sob.mvcvoorbeeld.MainWindow;
import nl.avans.min04sob.mvcvoorbeeld.ScoreboardController;


public class VoorbeeldMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MainWindow gameScreen = new MainWindow();
		new BoardDemoController(gameScreen);
		new ScoreboardController(gameScreen);
		new ChatController(gameScreen);
		gameScreen.pack();
	}
}
