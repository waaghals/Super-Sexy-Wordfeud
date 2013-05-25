package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ModeratorModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.views.AcceptDeclineView;

public class AcceptDeclineController extends CoreController{

	private GameModel gameModel = new GameModel();
	private JList wordList;
	private String[] words;
	private AcceptDeclineView adView;
	private ModeratorModel modModel;
	
	private ModeratorModel adModel = new ModeratorModel();
	
	
	public AcceptDeclineController()
	{
		modModel = new ModeratorModel();
		this.addModel(modModel);
		adView = new AcceptDeclineView();
		wordList = new JList();
		getTheRequestedWords();
		wordList.setListData(words);
		this.addView(adView);
		fillJList();
	}

	public void getTheRequestedWords()
	{
		words = gameModel.getRequestedWords();
	}
	
	public void fillJList(){
		adView.fillJList(wordList);
	}
	
	public void checkStatus(){		//naam kan miss beter, nog bedenken.
		if(adView.getStatus()){
			modModel.changeStatus(1);
		}
		else{
			modModel.changeStatus(0);
		}
	}
	
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListeners() {
		/*addActionListenerBack(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		*/
	}
	
	
}
