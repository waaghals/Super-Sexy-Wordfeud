package nl.avans.min04sob.scrabble.controllers;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.CompetitionModel;
import nl.avans.min04sob.scrabble.views.CompetitionView;

public class CompetitionController extends CoreController {

	private CompetitionModel cm;
	private CompetitionView cv;
	public CompetitionController()
	{
		addView(cv);
		addModel(cm);
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
		cv.fillCompitions(cm.getCompitions(username));
	}
	
	public void getParticipants(int competition_id){
		cv.fillPlayerList(cm.getUsersFromCompetition(competition_id));
	}

}
