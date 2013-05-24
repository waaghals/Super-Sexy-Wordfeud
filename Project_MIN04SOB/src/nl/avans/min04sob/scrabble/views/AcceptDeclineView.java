package nl.avans.min04sob.scrabble.views;

import javax.swing.JList;

import nl.avans.min04sob.scrabble.models.GameModel;

public class AcceptDeclineView {

	private JList wordList;
	private GameModel gameModel = new GameModel();
	
	public AcceptDeclineView()
	{
		wordList = new JList(gameModel.getRequestedWords());
	}
}
