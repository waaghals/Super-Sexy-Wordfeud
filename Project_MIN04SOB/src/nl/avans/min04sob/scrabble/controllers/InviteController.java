package nl.avans.min04sob.scrabble.controllers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.InviteModel;
import nl.avans.min04sob.scrabble.views.JoinCompetitionView;

public class InviteController extends CoreController {

	private JoinCompetitionView joinView;
	private JFrame window;
	private InviteModel inviteModel;

	public InviteController() {
		initialize();
	}

	@Override
	public void initialize() {
		window = new JFrame();

		inviteModel = new InviteModel();
		addModel(inviteModel);

		joinView = new JoinCompetitionView();
		addView(joinView);

		window.add(joinView);

		// actiondinges

		joinView.addActionListenerActieButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actieButton();
			}
		});

		joinView.addActionListenerAnnuleerButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annuleerButton();
			}
		});
	}

	public void actieButton() {

	}

	public void annuleerButton() {
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void addListeners() {
		// TODO Auto-generated method stub

	}

	public void setButtonsChallenge() {
		joinView.setText("Ingeschreven competities", "Uitdagen");
	}
	
	public void setButtonsJoin() {
		joinView.setText("Beschikbare competities", "Deelnemen");
	}
}
