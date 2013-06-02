package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.InviteModel;
import nl.avans.min04sob.scrabble.views.CompetitionScoreView;

public class InviteController extends CoreController {

	private CompetitionScoreView joinView;
	private JFrame window;
	private InviteModel inviteModel;

	public InviteController() {
		initialize();
	}

	public void actieButton() {
		//code voor uitdagen van een speler
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub

	}

	public void annuleerButton() {
		window.dispose();
	}

	@Override
	public void initialize() {
		window = new JFrame();

		inviteModel = new InviteModel();
		addModel(inviteModel);

		joinView = new CompetitionScoreView();
		addView(joinView);
				
		window.add(joinView);
		window.pack();
		window.setVisible(true);
		
		setButtonsJoin();

		// actiondinges

		joinView.addActionListenerActieButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actieButton();
			}
		});

		joinView.addActionListenerAnnuleerButton(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				annuleerButton();
			}
		});
	}

	public void setButtonsChallenge() {
		joinView.setText("Ingeschreven competities", "Uitdagen");
	}
	
	public void setButtonsJoin() {
		joinView.setText("Beschikbare competities", "Deelnemen");
	}
	
	public void setButtonsRemove() {
		joinView.setText("Aangemaakte competities", "Verwijderen");
	}
}
