package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ChallengeModel;
import nl.avans.min04sob.scrabble.views.ChallengeView;

public class ChallengeController extends CoreController  {
	private ChallengeView challengeview;
	private ChallengeModel challengemodel ;
 
	public ChallengeController (final String name)
	{
		challengeview = new ChallengeView();
		challengemodel= new ChallengeModel(name);
		addView(challengeview);
		addModel(challengemodel);
		challengeview.addActionListenerAccept (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					challengemodel.respondChallenge(challengeview.getspelID(), name,true);
					challengeview.javaFrame().dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				challengeview.javaFrame().dispose();
			}
		});
		challengeview.addActionListenerDecline (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					challengemodel.respondChallenge(challengeview.getspelID(), name,false);
					challengeview.javaFrame().dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				challengeview.javaFrame().dispose();
			}
		});
		challengeview.addActionListenerOke (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					challengemodel.controle(name,  challengeview.getUsername(), challengeview.getspelID());
					challengeview.javaFrame().dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				challengeview.javaFrame().dispose();
			}
		});
	}
 
	public void propertyChange(String evt)  {
	String x =  evt;
	 switch(x)
		 {
		 case "1": challengers();break;
		 case "2": challengeview.response("challenge denied");break;
		 case "3": challengeview.response("challenge accepted");break;
		 case "4": challengeview.response("something went wrong");break;
		 default: break;
		 }
	}
	 
	public void challengers()
	{
		challengeview.viewArraylistRemove();
	 	int index=0;
		while(index<challengemodel.challengeArray().size())
		{
			challengeview.viewArrayListadd(challengemodel.challengeArray().get(index));
		}
		challengeview.showChallenge();
	}
	
	public void toChallenge()
	{
		challengeview.toChallenge();
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
