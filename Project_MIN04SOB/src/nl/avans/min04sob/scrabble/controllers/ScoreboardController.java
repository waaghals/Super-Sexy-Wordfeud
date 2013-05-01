package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.Timer;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ScoreboardTableModel;
import nl.avans.min04sob.scrabble.views.ScoreboardTable;
import nl.avans.min04sob.scrabble.views.ScoreboardWindow;

public class ScoreboardController extends CoreController {
	
	ScoreboardTableModel sbModel;
	ScoreboardWindow sbWindow;
	ScoreboardTable sbTable;
	Random r;

	public ScoreboardController() {
		sbModel = new ScoreboardTableModel();
		sbWindow = new ScoreboardWindow();
		sbTable = new ScoreboardTable(sbModel);
		sbWindow.add(new JScrollPane(sbTable));
		
		sbWindow.setTitle("Scoreboard");
		sbWindow.setVisible(true);
		sbWindow.getGeneratorButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addRandomScore();
			}
		});
		
		sbWindow.pack();
		addModel(sbModel);
		addView(sbWindow);
		addView(sbTable);
		
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
		sbModel.addScore(randomName, randomScore);
	}
	

}
