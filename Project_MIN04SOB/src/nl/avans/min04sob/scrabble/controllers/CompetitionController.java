package nl.avans.min04sob.scrabble.controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;
import nl.avans.min04sob.scrabble.views.CompetitionView;
import nl.avans.min04sob.scrabble.views.JoinCompetitionView;

public class CompetitionController extends CoreController {

	private CompetitionModel competitionModel;
	private CompetitionView competitionView;
	private JoinCompetitionView joinCompetitionView;
	private CoreWindow window;
	private AccountModel accountModel;
	public CompetitionController(AccountModel user)
	{
		accountModel = user;
		competitionModel = new CompetitionModel(1);
		competitionView = new CompetitionView();
		joinCompetitionView = new JoinCompetitionView();
		window = new CoreWindow();
		
		window.add(competitionView);
		
		addView(competitionView);
		addModel(competitionModel);
		addView(joinCompetitionView);
		
		//getCompetitions(am.toString());
		
		window.setPreferredSize(new Dimension(400,375));
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub
		
	}
	
	public void getCompetitions(String username){
		competitionView.fillCompitions(accountModel.getCompitions(username));
	}
	
	public void getParticipants(int competition_id){
		competitionView.fillPlayerList(competitionModel.getUsersFromCompetition(competition_id));
	}

}
