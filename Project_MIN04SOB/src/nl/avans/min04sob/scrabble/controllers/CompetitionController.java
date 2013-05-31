package nl.avans.min04sob.scrabble.controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.ChallengeModel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;
import nl.avans.min04sob.scrabble.views.CompetitionView;
import nl.avans.min04sob.scrabble.views.JoinCompetitionView;

public class CompetitionController extends CoreController {

	private CompetitionModel competitionModel;
	private CompetitionView competitionView;
	private JoinCompetitionView joinCompetitionView;
	private CoreWindow window;
	private AccountModel accountModel;
	private ChallengeModel challengeModel;

	public CompetitionController(AccountModel user)
	{

		accountModel = user;
		//cm = new CompetitionModel(800);
		competitionView = new CompetitionView();
		joinCompetitionView = new JoinCompetitionView();
		challengeModel = new ChallengeModel(accountModel);
		
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
		
		window.setPreferredSize(new Dimension(400,320));
		window.setResizable(false);
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		competitionView.addActionButtonListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try {
					challengeModel.controle("schaap"/*competitionView.getSelectedPlayer()*/);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		competitionView.setText("Ingeschreven competities", "Spelers in competitie", "Speler uitdagen", true);
		
		getCompetitions(accountModel.toString());
	}
	
	public void openJoinCompetitionView() {
		window = new CoreWindow();
		window.add(competitionView);
		
		window.setPreferredSize(new Dimension(400,320));
		window.setResizable(false);
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		competitionView.setText("Beschikbare competities", "Spelers in competitie", "Competitie deelnemen", true);
		
		getAvailable(accountModel.toString());
		
	}
	
	public void openCompetitionScores(){
		window = new CoreWindow();
		window.add(competitionView);
		
		window.setPreferredSize(new Dimension(400,320));
		window.setResizable(false);
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		competitionView.setText("Competities", "Spelers in competitie", "",false);
		
		getAllCompetitions();
		
	}
	
	public void openDeleteCompetitionView() {
		window = new CoreWindow();
		window.add(competitionView);
		
		window.setPreferredSize(new Dimension(400,320));
		window.setResizable(false);
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		competitionView.setText("Competities", "Spelers in competitie", "Verwijder Competitie",true);
		
		getAllCompetitions();	
	}
	
	public void openDeleteFromCompetitionView() {
		window = new CoreWindow();
		window.add(competitionView);
		
		window.setPreferredSize(new Dimension(400,320));
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		competitionView.setText("Ingeschreven Competities", "Spelers in competitie", "Verwijderen uit Competitie",true);
		
		getAvailable(accountModel.toString());	
	}
	
	public void getCompetitions(String username){
		competitionView.fillCompetitions(accountModel.getCompetitions(username));
	}
	
	public void getParticipants(int competition_id){
		competitionView.fillPlayerList(competitionModel.getUsersFromCompetition(competition_id));
	}
	
	public void getAvailable(String username){
		competitionView.fillAvailableCompetitions(accountModel.getAvailableCompetitions(username));
	}
	
	public void getAllCompetitions(){
		competitionView.fillAllCompetitions(accountModel.getAllCompetitions());
	}

}
