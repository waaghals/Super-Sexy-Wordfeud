package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ChallengeModel;
import nl.avans.min04sob.scrabble.views.ChallengeView;

public class ChallengeController implements Observer{
	private ChallengeView cv = new ChallengeView();
	private ChallengeModel cm = new ChallengeModel();
	public ChallengeController ()
	{
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
					cm.controle("iets",  cv.getUsername(), cv.getspelID());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cv.javaFrame().dispose();
			}
		});
	}
	 
	@Override
	public void update(Observable arg0, Object arg1) {
	String x = (String) arg1;
	 switch(x)
		 {
		 case "1": cv.showChallenge(challengers());break;
		 case "2": cv.response("challenge denied");break;
		 case "3": cv.response("challenge accepted");break;
		 case "4": cv.response("something went wrong");break;
		 default: break;
		 }
	}
	
	public String challengers()
	{
		String iets = "";
		int index=0;
		while(index<cm.Uitnodigingnaam().size())
		{
			iets = iets + cm.Uitnodigingnaam()+ ": "+cm.Uitnodigingnummer();
			index++;
		}
		return iets;
	}
	
	public void openchallenges()
	{
		cv.showChallenge(challengers());
	}
	
	public void toChallenge()
	{
		toChallenge();
	}
	
	 
}
