package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.views.ResignPanel;

public class ResignController extends CoreController {

	private ResignPanel resignPanel;
	private JFrame frame;
	private GameModel gameModel;
	
	public ResignController() {
		
		frame = new JFrame();
		
		resignPanel = new ResignPanel();
		
		frame.add(resignPanel);
		
		addView(resignPanel);
		addModel(gameModel);
		
		
		resignPanel.addResignActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doResign();
			}
		});
		
		resignPanel.addNoResignActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelResign();
			}
		});
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	@Override
	public void initialize() {
		
		
	}

	@Override
	public void addListeners() {
		
		
	}
	
	private void doResign() {
		gameModel.Resign();
		frame.dispose();
		frame = null;
	}
	
	private void cancelResign() {
		frame.dispose();
		frame = null;
	}

}
