package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ChallengeModel;
import nl.avans.min04sob.scrabble.views.ChallengeView2;


public class ChallengeController2 extends CoreController {

	private ChallengeView2 challengeView2;
	private ChallengeModel challengeModel;
	private JFrame frame;
	
	public ChallengeController2() {
		initialize();
		addListeners();
		
		frame.setAlwaysOnTop(true);
		frame.add(challengeView2);
		
		addView(challengeView2);
		addModel(challengeModel);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	@Override
	public void initialize() {
		frame = new JFrame();
		challengeView2 = new ChallengeView2();
		challengeModel = new ChallengeModel();
	}

	@Override
	public void addListeners() {
		challengeView2.addActionListenerAccept(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				acceptChallenge();
			}
		});
		challengeView2.addActionListenerDecline(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				declineChallenge();
			}
		});
		challengeView2.addActionListenerBack(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		
	}
	
	private void acceptChallenge() {
		//TODO doesnt have a good model yet
		//challengeModel.something();
	}
	
	private void declineChallenge() {
		//TODO RT acceptchallenge()
	}
	
	private void goBack() {
		frame.dispose();
		frame = null;
	}

}
