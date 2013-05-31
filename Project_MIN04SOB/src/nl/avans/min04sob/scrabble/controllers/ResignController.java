package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.views.ResignPanel;

public class ResignController extends CoreController {

	private ResignPanel resignPanel;
	private JFrame frame;
	private GameModel gameModel;
	private String labelName;
	
	public ResignController(GameModel game) {
		initialize();
		addListeners();
		
		gameModel = game;
		
		frame.setAlwaysOnTop(true);
		frame.add(resignPanel);
		
		addView(resignPanel);
		addModel(gameModel);
		
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
		frame = new JFrame();
		setLabelName();
		resignPanel = new ResignPanel();
		resignPanel.setResignLabelName(getLabelName());
	}

	@Override
	public void addListeners() {
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
	}
	
	private void doResign() {
		gameModel.resign();
		frame.dispose();
		frame = null;
	}
	
	private void cancelResign() {
		frame.dispose();
		frame = null;
	}

}
