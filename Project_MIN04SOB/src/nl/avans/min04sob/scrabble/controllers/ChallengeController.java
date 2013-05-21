package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ChallengeModel;
import nl.avans.min04sob.scrabble.views.ChallengeView;

public class ChallengeController extends CoreController{
	private ChallengeView cv = new ChallengeView();
	private ChallengeModel  cm = new ChallengeModel();
	
	public ChallengeController ()
	{
	    cv.addActionListenerAccept (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.acceptCallenge();
				cv.javaFrame().dispose();
			}
		});
		cv.addActionListenerDecline (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.declineCallenge();
				cv.javaFrame().dispose();
			}
		});
		cv.addActionListenerOke (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.createChallenge("help   hoe moet dit @@@@@@@@@",  cv.getUsername());
				cv.javaFrame().dispose();
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

	 
}
