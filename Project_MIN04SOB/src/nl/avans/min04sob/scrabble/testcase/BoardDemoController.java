package nl.avans.min04sob.scrabble.testcase;

import nl.avans.min04sob.scrabble.core.CoreController;

public class BoardDemoController extends CoreController {

	public BoardDemoController() {
		BoardPanel playBoard = new BoardPanel();
		MainWindow gameScreen = new MainWindow();
		gameScreen.add(playBoard);
		addView(playBoard);
		addView(gameScreen);
	}
}
