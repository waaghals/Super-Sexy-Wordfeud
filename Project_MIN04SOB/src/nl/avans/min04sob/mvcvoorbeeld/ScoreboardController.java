package nl.avans.min04sob.mvcvoorbeeld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import nl.avans.min04sob.scrabble.core.CoreConstraint;
import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;

public class ScoreboardController extends CoreController {

	ScoreboardTableModel sbModel;
	ScoreboardPanel sbPanel;

	Random r;

	public ScoreboardController(CoreWindow window) {
		sbModel = new ScoreboardTableModel();
		sbPanel = new ScoreboardPanel();
		sbPanel.setScoreboardTableModel(sbModel);

		sbPanel.addGeneratorButtonActionListner(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addRandomScore();
			}
		});

		addModel(sbModel);
		addView(sbPanel);

		window.add(sbPanel, new CoreConstraint(10, 10, 0, 10));

		r = new Random();
		Timer timer = new Timer(5000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addRandomScore();

			}
		});
		timer.start();

	}

	private void addRandomScore() {
		String[] names = { "Patrick", "Joep", "Thomas", "Aaron", "Alexander" };
		String randomName = names[r.nextInt(names.length)];
		int randomScore = r.nextInt(10000);
		sbModel.addScore(randomName, randomScore);
	}

	@Override
	public void initialize() {
		// TODO Automatisch gegenereerde methodestub
		
	}

	@Override
	public void addListeners() {
		// TODO Automatisch gegenereerde methodestub
		
	}

}
