package nl.avans.min04sob.scrabble.testcase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.testcase.ScoreboardModel.Boardline;

public class ScoreboardController extends CoreController {
	
	ScoreboardModel sbModel;
	ScoreboardView sbView;
	Random r;

	public ScoreboardController() {
		sbModel = new ScoreboardModel();
		sbView = new ScoreboardView();
		
		sbView.setTitle("test");
		sbView.setVisible(true);
		sbView.getGeneratorButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addRandomScore();
			}
		});
		sbView.pack();
		
		addModel(sbModel);
		addView(sbView);
		
		r = new Random();
	}
	
	private void addRandomScore(){
		String[] names = {"Patrick", "Joep", "Thomas", "Aaron", "Alexander"};
		String randomName = names[r.nextInt(names.length)];
		int randomScore = r.nextInt(100);
		sbModel.addScore(sbModel.new Boardline(randomName, randomScore));
	}
	

}
