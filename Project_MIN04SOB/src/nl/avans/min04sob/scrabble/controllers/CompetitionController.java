package nl.avans.min04sob.scrabble.controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.ChallengeModel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;
import nl.avans.min04sob.scrabble.views.CompetitionScoreView;
import nl.avans.min04sob.scrabble.views.CompetitionView;
import nl.avans.min04sob.scrabble.views.CreateCompetitionView;

public class CompetitionController extends CoreController {

	private CompetitionModel competitionModel;
	private CompetitionView competitionView;
	private CoreWindow window;
	private CoreWindow window1;
	private AccountModel accountModel;
	private ChallengeModel challengeModel;
	private CompetitionScoreView competitionScoreView;
	private int index; //competitie id meegeven
	private CreateCompetitionView createCompetitionView;

	public CompetitionController(AccountModel user)
	{

		accountModel = user;
		competitionModel = new CompetitionModel();
		competitionView = new CompetitionView();
		challengeModel = new ChallengeModel(accountModel);
		competitionScoreView = new CompetitionScoreView();
		
		addView(competitionView);
		addModel(competitionModel);
		
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub
	}

	public void getAllCompetitions(){
		competitionView.fillCompetitions(competitionModel.getAllCompetitions());
	}
	
	public void getAllCompetitionsScore(){
		competitionScoreView.fillCompetitions(competitionModel.getAllCompetitions());
	}
	
	public void getAvailable(String username){
		competitionView.fillCompetitions(accountModel.getAvailableCompetitions(username));
	}
	
	public void getCompetitions(String username){
		competitionView.fillCompetitions(accountModel.getCompetitions(username));
	}
	
	public int getCompID(){ //competition ID meegeven
		return index;
	}
	public void getParticipants(int competition_id){
		competitionView.fillPlayerList(competitionModel.getUsersFromCompetition(competition_id));
	}
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}
	
	public void openCompetitionScores(){
		window1 = new CoreWindow();
		window1.add(competitionScoreView);
		
		window1.setPreferredSize(new Dimension(1400,320));
		window1.setResizable(false);
		window1.pack();
		
		competitionScoreView.addBackListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		competitionScoreView.addCompetitionListListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==1){
					int id = competitionScoreView.getSelectedCompetition().getCompId();
					Object[] rowData = new Object[6];
					int index = 0;
					//naam, aantal spellen, totaal punten, gemiddelde punten, win/lose, bayesian-
					for(AccountModel account: competitionModel.getUsersFromCompetition(id)){
						rowData[0] = account;
						rowData[1] = competitionModel.totalPlayedGames(id, account.getUsername());
						rowData[2] = competitionModel.totalPoints(id, account.getUsername());
						rowData[3] = competitionModel.averagePoints(id, account.getUsername());
						rowData[4] = competitionModel.amountWon(id, account.getUsername()) +" / " + competitionModel.amountLost(id, account.getUsername());
						rowData[5] = "bayesian-gemiddelde";
						competitionScoreView.addRow(rowData);
						index++;
					}
				}
			}
		});
		
		getAllCompetitionsScore();
		
	}
	
	
	
	public void openCompetitionView(){
		window = new CoreWindow();
		window.add(competitionView);
		
		window.setPreferredSize(new Dimension(400,320));
		window.setResizable(false);
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		//uitdagen
		competitionView.addActionButtonListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				challengeModel.controle(competitionView.getSelectedPlayer());
			}
		});
		
		competitionView.addCompetitionListListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==1){
					int id = competitionView.getSelectedCompetition().getCompId();
					getParticipants(id);
				}
			}
		});
		
		competitionView.setText("Ingeschreven competities", "Spelers in competitie", "Speler uitdagen", true);
		
		getCompetitions(accountModel.toString());
	}
	
	//wordt niet gebruikt
	public void openDeleteCompetitionView() {
		window = new CoreWindow();
		window.add(competitionView);
		
		window.setPreferredSize(new Dimension(400,320));
		window.setResizable(false);
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		competitionView.addCompetitionListListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==1){
					int id = competitionView.getSelectedCompetition().getCompId();
					getParticipants(id);
				}
			}
		});
		
		competitionView.setText("Competities", "Spelers in competitie", "Verwijder Competitie",true);
		
		getAllCompetitions();	
	}
	
	//wordt niet gebruikt
	public void openDeleteFromCompetitionView() {
		window = new CoreWindow();
		window.add(competitionView);
		
		window.setPreferredSize(new Dimension(400,320));
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		competitionView.addCompetitionListListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==1){
					int id = competitionView.getSelectedCompetition().getCompId();
					getParticipants(id);
				}
			}
		});
		
		competitionView.setText("Ingeschreven Competities", "Spelers in competitie", "Verwijderen uit Competitie",true);
		
		getAvailable(accountModel.toString());	
	}
	
	public void openJoinCompetitionView() {
		window = new CoreWindow();
		window.add(competitionView);
		
		window.setPreferredSize(new Dimension(400,320));
		window.setResizable(false);
		window.pack();
		
		competitionView.addBackListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		//joinCompetition
		competitionView.addActionButtonListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(competitionView.getSelectedCompetition() != null){
					int id = competitionView.getSelectedCompetition().getCompId();
					getParticipants(id);
					//competitionView.removeIndex(competitionView.getIndex());
				}
				else{
					System.out.println("selecteer een competitie");
				}
			}
			
		}); 
		
		competitionView.addCompetitionListListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==1){
					int id = competitionView.getSelectedCompetition().getCompId();
					getParticipants(id);
				}
			}
		});
		
		competitionView.setText("Beschikbare competities", "Spelers in competitie", "Competitie deelnemen", true);
		
		getAvailable(accountModel.toString());
		
	}
	
	public void openCreateCompetitionView(){
		window = new CoreWindow();
		window.add(createCompetitionView);
		
		window.setPreferredSize(new Dimension(400,320));
		window.setResizable(false);
		window.pack();
		
				
	}

}
