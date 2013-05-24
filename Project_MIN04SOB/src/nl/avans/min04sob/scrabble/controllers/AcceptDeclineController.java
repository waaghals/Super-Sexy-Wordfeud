package nl.avans.min04sob.scrabble.controllers;

import javax.swing.JList;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.AcceptDeclineModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.views.AcceptDeclineView;

public class AcceptDeclineController extends CoreController{

	private GameModel gameModel = new GameModel();
	private JList wordList;
	private String[] words;
	private AcceptDeclineView adView;
	
	private AcceptDeclineModel adModel = new AcceptDeclineModel();
	
	
	public AcceptDeclineController()
	{
		adView = new AcceptDeclineView();
		wordList = new JList();
		getTheRequestedWords();
		wordList.setListData(words);
		this.addView(adView);
		adView.fillJList(wordList);
	}

	public void getTheRequestedWords()
	{
		words = gameModel.getRequestedWords();
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub
		
	}
}
