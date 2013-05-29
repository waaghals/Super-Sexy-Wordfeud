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
	private CoreWindow window1;
	private AccountModel accountModel;

	public CompetitionController(AccountModel user)
	{

		accountModel = user;
		//cm = new CompetitionModel(800);
		competitionView = new CompetitionView();
		joinCompetitionView = new JoinCompetitionView();
		
		addView(competitionView);
		addModel(competitionModel);
		addView(joinCompetitionView);
		
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub
	}
	
	public void openCompetitionView(){
		window = new CoreWindow();
		window.add(competitionView);
		
		window.setPreferredSize(new Dimension(375,350));
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		getCompetitions(accountModel.toString());
	}
	
	public void openJoinCompetitionView() {
		window1 = new CoreWindow();
		window1.add(joinCompetitionView);
		
		window1.setPreferredSize(new Dimension(375,350));
		window1.pack();
		
		joinCompetitionView.addActionListenerAnnuleerButton(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				window1.dispose();			
			}
		});
		
		joinCompetitionView.addActionListenerActieButton(new ActionListener(){

			public void actionPerformed(ActionEvent e) {	
				competitionModel.join(joinCompetitionView.selectedCompetition().getCompId(),accountModel.toString());		
			}	
		});
		
	}
	
	public void getCompetitions(String username){
		competitionView.fillCompetitions(accountModel.getCompetitions(username));
	}
	
	public void getParticipants(int competition_id){
		competitionView.fillPlayerList(competitionModel.getUsersFromCompetition(competition_id));
	}

	

}
