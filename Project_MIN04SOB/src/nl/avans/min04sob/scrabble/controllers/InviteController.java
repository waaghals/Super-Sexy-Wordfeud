package nl.avans.min04sob.scrabble.controllers;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.InviteModel;
import nl.avans.min04sob.scrabble.views.InviteView;

public class InviteController extends CoreController {

	private InviteView inviteView;
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

		inviteView = new InviteView();
		addView(inviteView);

		window.add(inviteView);
		setButtons();

		// actiondinges

		inviteView.addActionListenerActieButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actieButton();
			}
		});

		inviteView.addActionListenerAnnuleerButton(new ActionListener() {
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

	public static void main(String[] arg) {
		new InviteView();

	}

	public void setButtons() {
		inviteView.setButtons("Ingeschreven competities",
				"Spelers in de competitie", "Uitdagen");
	}
}
