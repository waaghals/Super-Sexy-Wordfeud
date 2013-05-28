package nl.avans.min04sob.scrabble.controllers;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;
import nl.avans.min04sob.scrabble.views.CompetitionView;

public class CompetitionController extends CoreController {

	private CompetitionModel cm;
	private CompetitionView cv;
	private CoreWindow window;
	private AccountModel am;
	public CompetitionController(AccountModel user)
	{
		am = user;
		cm = new CompetitionModel(800);
		cv = new CompetitionView();
		window = new CoreWindow();
		
		window.add(cv);
		
		addView(cv);
		addModel(cm);
		
		window.pack();
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
		cv.fillCompitions(am.getCompitions(username));
	}
	
	public void getParticipants(int competition_id){
		cv.fillPlayerList(cm.getUsersFromCompetition(competition_id));
	}

}
