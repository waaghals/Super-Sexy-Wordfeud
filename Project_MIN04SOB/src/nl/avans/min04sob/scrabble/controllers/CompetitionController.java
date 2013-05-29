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

	private CompetitionModel cm;
	private CompetitionView cv;
	private JoinCompetitionView jcv;
	private CoreWindow window;
	private CoreWindow window1;
	private AccountModel am;

	public CompetitionController(AccountModel user)
	{

		am = user;
		//cm = new CompetitionModel(800);
		cv = new CompetitionView();
		jcv = new JoinCompetitionView();
		
		addView(cv);
		addModel(cm);
		addView(jcv);
		
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
		window.add(cv);
		
		window.setPreferredSize(new Dimension(375,350));
		window.pack();
		
		cv.addBackListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				window.dispose();
			}	
		});
		
		getCompetitions(am.toString());
	}
	
	public void openJoinCompetitionView() {
		window1 = new CoreWindow();
		window1.add(jcv);
		
		window1.setPreferredSize(new Dimension(375,350));
		window1.pack();
		
		jcv.addActionListenerAnnuleerButton(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				window1.dispose();			
			}
		});
		
		jcv.addActionListenerActieButton(new ActionListener(){

			public void actionPerformed(ActionEvent e) {	
				cm.join(jcv.selectedCompetition().getCompId(),am.toString());		
			}	
		});
		
	}
	
	public void getCompetitions(String username){
		cv.fillCompitions(am.getCompitions(username));
	}
	
	public void getParticipants(int competition_id){
		cv.fillPlayerList(cm.getUsersFromCompetition(competition_id));
	}

	

}
