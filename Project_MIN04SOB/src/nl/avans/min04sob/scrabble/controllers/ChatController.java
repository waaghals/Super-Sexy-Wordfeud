package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.ChatModel;
import nl.avans.min04sob.scrabble.views.ChatPanel;

public class ChatController extends CoreController {
	private ChatPanel chatPanel;
	private ChatModel chatModel;
	private final int xSizeChat, ySizeChat, playerId, gameId;

	public ChatController(int playerId, int gameId) {
		xSizeChat = 150;
		ySizeChat = 250;

		this.playerId = playerId;
		this.gameId = gameId;

		chatPanel = new ChatPanel(xSizeChat, ySizeChat);
		chatModel = new ChatModel(gameId, playerId);

		addView(chatPanel);
		addModel(chatModel);

		// Add the old messages first.
		for (String message : chatModel.getMessages()) {
			chatPanel.addToChatField(message);
		}

		// Register the listeners
		setListeners();
	}

	public void send() {
		String message = chatPanel.getChatFieldSendText();

		if (!message.equals("") && !message.equals(" ")) {
			chatModel.send(message);
			chatModel.update();

			// Empty the chat message box
			chatPanel.setChatFieldSendText("");
		}
	}

	public ChatPanel getchatpanel() {
		return chatPanel;
	}

	public void setListeners() {
		chatPanel.addListenerChatField(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					send();
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});
		chatPanel.addListenerChatSendButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				send();
			}
		});
	}
}
