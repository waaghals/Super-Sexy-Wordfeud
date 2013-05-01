package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JScrollPane;
import javax.swing.Timer;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ChatTableModel;
import nl.avans.min04sob.scrabble.views.ChatTable;
import nl.avans.min04sob.scrabble.views.ScoreboardWindow;

public class ChatController extends CoreController {
	
	ChatTableModel chatModel;
	ScoreboardWindow sbWindow;
	ChatTable chatTable;
	Random r;

	public ChatController() {
		chatModel = new ChatTableModel();
		sbWindow = new ScoreboardWindow();
		chatTable = new ChatTable(chatModel);
		sbWindow.add(new JScrollPane(chatTable));
		
		sbWindow.setTitle("Chat");
		sbWindow.setVisible(true);
		sbWindow.getGeneratorButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addRandomScore();
			}
		});
		
		sbWindow.pack();
		addModel(chatModel);
		addView(sbWindow);
		addView(chatTable);
		
		r = new Random();
		Timer timer = new Timer(500, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addRandomScore();
				
			}
		});
		timer.start();

	}
	
	private void addRandomScore(){
		String[] names = {"Patrick", "Joep", "Thomas", "Aaron", "Alexander"};
		String[] berichten = {"Hoi", "Halloo", "Sup", "Does it smell like updog?", "Hois"};
		String randomName = names[r.nextInt(names.length)];
		String randomMessage = berichten[r.nextInt(berichten.length)];
		chatModel.addMessage(randomName, randomMessage);
		
	}
	

}
