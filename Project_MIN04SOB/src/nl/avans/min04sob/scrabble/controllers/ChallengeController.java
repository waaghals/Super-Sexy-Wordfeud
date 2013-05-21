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
		addView(cv);
		addModel(cm);
		
		cv.addActionListenerAccept (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dpeiets
			}
		});
		cv.addActionListenerDecline (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dpeiets
			}
		});
		cv.addActionListenerOke (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dpeiets
			}
		});
		cv.addActionListenerOke (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dpeiets
			}
		});
		cv.addActionListenerOkee (new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dpeiets
			}
		});
 }

	@Override
	public void initialize() 
	{
		 
		
	}

	@Override
	public void addListeners() 
	{
		// TODO Auto-generated method stub
		
	}
}
