package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.ModeratorModel;
import nl.avans.min04sob.scrabble.views.AcceptDeclineView;

public class AcceptDeclineController extends CoreController {

	private AcceptDeclineView adView;
	private ModeratorModel modModel;
	public AcceptDeclineController() {
		modModel = new ModeratorModel();
		this.addModel(modModel);
		adView = new AcceptDeclineView();

		this.addView(adView);
		fillJList();
		addListeners();
	}

	public void fillJList() {
		String[] words = modModel.getRequestedWordList();
		adView.fillJList(words);

	}

	public void acceptWord() {
		String word = adView.getSelectedWord();
		modModel.acceptWord(word);

	}

	public void deniedWord() {
		String word = adView.getSelectedWord();
		modModel.deniedWord(word);
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addListeners() {
		adView.addAcceptActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptWord();
			}
		});

		adView.addDeniedActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deniedWord();
			}
		});

		adView.addBackActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adView.removeVenster();
			}
		});
	}

}
