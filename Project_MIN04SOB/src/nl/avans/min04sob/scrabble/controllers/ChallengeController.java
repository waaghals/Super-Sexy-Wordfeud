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

public class ChallengeController extends CoreController  {
	private ChallengeView cv = new ChallengeView();
	private ChallengeModel cm = new ChallengeModel();
	 
	public ChallengeController (final String naam)
	{
		addView(cv);
		addModel(cm);
	    cv.addActionListenerAccept (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.respondChallenge(cv.getspelID(), naam,true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cv.javaFrame().dispose();
			}
		});
		cv.addActionListenerDecline (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.respondChallenge(cv.getspelID(), naam,false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cv.javaFrame().dispose();
			}
		});
		cv.addActionListenerOke (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cm.controle(naam,  cv.getUsername(), cv.getspelID());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cv.javaFrame().dispose();
			}
		});
	}
	 
	 /*/
	public void   {
	String x =  ;
	 switch(x)
		 {
		 case "1": cv.showChallenge(challengers());break;
		 case "2": cv.response("challenge denied");break;
		 case "3": cv.response("challenge accepted");break;
		 case "4": cv.response("something went wrong");break;
		 default: break;
		 }
			}
	 /*/
	public String challengers()
	{
		String iets = "";
		int index=0;
		while(index<cm.gegevens().size())
		{
			iets = iets + cm.gegevens();
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

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub
		
	}
	
	 
}
