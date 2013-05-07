package nl.avans.min04sob.scrabble.controllers;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.MainWindow;
import nl.avans.min04sob.scrabble.views.BoardPanel;

public class BoardDemoController extends CoreController {

	public BoardDemoController() {
		BoardPanel playBoard = new BoardPanel();
		MainWindow gameScreen = new MainWindow();
		gameScreen.add(playBoard);
		addView(playBoard);
		addView(gameScreen);
	}
}
