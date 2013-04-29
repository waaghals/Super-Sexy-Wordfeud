package nl.avans.min04sob.scrabble.testcase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.testcase.ScoreboardModel.Boardline;

public class ScoreboardController extends CoreController {
	
	ScoreboardModel sbModel;
	ScoreboardWindow sbView;
	Random r;

	public ScoreboardController() {
		sbModel = new ScoreboardModel();
		sbView = new ScoreboardWindow();
		
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
		Timer timer = new Timer(5000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addRandomScore();
				
			}
		});
		timer.start();

	}
	
	private void addRandomScore(){
		String[] names = {"Patrick", "Joep", "Thomas", "Aaron", "Alexander"};
		String randomName = names[r.nextInt(names.length)];
		int randomScore = r.nextInt(10000);
		sbModel.addScore(sbModel.new Boardline(randomName, randomScore));
	}
	

}
