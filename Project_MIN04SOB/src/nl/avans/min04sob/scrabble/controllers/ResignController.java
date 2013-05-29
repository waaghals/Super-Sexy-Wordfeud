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
	private String labelName;
	
	public ResignController(GameModel game) {
		gameModel = game;
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
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
		setLabelName();
		resignPanel.setResignLabelName(getLabelName());
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void setLabelName() {
		labelName = "Weet je zeker dat je de huidige game wilt opgeven?";
	}
	
	public String getLabelName() {
		return labelName;
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
