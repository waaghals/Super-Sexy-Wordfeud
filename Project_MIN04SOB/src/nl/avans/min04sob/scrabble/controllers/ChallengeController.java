package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.sql.SQLException;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.ChallengeModel;
import nl.avans.min04sob.scrabble.views.ChallengeView;

public class ChallengeController extends CoreController  {
	private ChallengeView challengeview;
	private ChallengeModel challengemodel ;
 
	public ChallengeController (final String name)
	{
		challengeview = new ChallengeView();
		challengemodel= new ChallengeModel(new AccountModel(name));
		challengemodel.update();
		addView(challengeview);
		addModel(challengemodel);
		challengeview.addActionListenerAccept (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					challengemodel.respondChallenge(name,true);//??
					 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				challengeview.javaFrame().dispose();
			}
		});
		challengeview.addActionListenerDecline (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					challengemodel.respondChallenge(challengeview.getSelectedChallenge(),false);
				 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				challengeview.javaFrame().dispose();
			}
		});
		challengeview.addActionListenerOke2(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				challengeview.javaFrame().dispose();
			}
			
		});
		challengeview.addActionListenerOke (new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					challengemodel.controle(challengeview.getUsername());
				 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
 
	@Override
	public void addListeners() {
		// TODO Auto-generated method stub
		
	}
	 
	public void challengers()
	{ 
		challengeview.viewArraylistRemove();
	 	int index=0;
		while(index<challengemodel.challengeArray().size())
		{
			challengeview.viewArrayListadd(challengemodel.challengeArray().get(index));
			index++;
		} 
		challengeview.showChallenge();
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt)  {
	 
	System.out.println(evt.getPropertyName());
	 switch(evt.getPropertyName())
		 {
		 case "1": challengers();break;
		 case "2": challengeview.response("challenge denied");break;
		 case "3": challengeview.response("challenge accepted");break;
		 case "4": challengeview.response("something went wrong");break;
		 default: break;
		 }
	}

	public void toChallenge()
	{
		challengeview.toChallenge();
	} 
}