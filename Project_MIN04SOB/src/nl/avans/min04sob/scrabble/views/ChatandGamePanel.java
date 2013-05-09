package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;

import javax.swing.JPanel;

import nl.avans.min04sob.scrabble.controllers.ChatController;

public class ChatandGamePanel extends JPanel{

	public ChatandGamePanel(int playerid){
		setPreferredSize(new Dimension(210, 300));
		ChatController chat = new ChatController(playerid);
		add(chat.getchatpanel());
	}
}
