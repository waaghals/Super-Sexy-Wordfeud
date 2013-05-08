package nl.avans.min04sob.scrabble.controllers;

import java.awt.Dimension;

import javax.swing.JScrollPane;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.views.BoardPanel;
import nl.avans.min04sob.scrabble.views.MainWindow;

public class BoardDemoController extends CoreController {

	public BoardDemoController() {
		BoardPanel playBoard = new BoardPanel();
		MainWindow gameScreen = new MainWindow();
		gameScreen.add(new JScrollPane(playBoard));
		gameScreen.pack();
		addView(playBoard);
		addView(gameScreen);
	}
}
