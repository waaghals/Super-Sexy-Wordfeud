package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ChallengeModel;
import nl.avans.min04sob.scrabble.views.ChallengeView;

public class ChallengeController extends CoreController{
	private ChallengeView cv = new ChallengeView();
	private ChallengeModel cm = new ChallengeModel();
	public ChallengeController ()
	{
		// open to challenge
		// open uitdager
	    cv.addActionListenerAccept (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.acceptCallenge(cv.getspelID());
				cv.javaFrame().dispose();
			}
		});
		cv.addActionListenerDecline (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.declineCallenge(cv.getspelID());
				cv.javaFrame().dispose();
			}
		});
		cv.addActionListenerOke (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.createChallenge("help   hoe moet dit @@@@@@@@@",  cv.getUsername(), 6);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
