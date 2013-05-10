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
	private JFrame frame;
	private final int xSizeChat, ySizeChat, checkMessagesTimer, playerId,
			gameId;

	public ChatController(int playerId, int gameId) {
		xSizeChat = 150;
		ySizeChat = 250;
		checkMessagesTimer = 10;

		this.playerId = playerId;
		this.gameId = gameId;

		chatPanel = new ChatPanel(xSizeChat, ySizeChat);
		chatModel = new ChatModel(gameId, playerId);

		addView(chatPanel);
		chatPanel.getChatField().setText(
				chatPanel.getChatField().getText()
						+ chatModel.allmessagesforstart());
		this.startcheckingmessages();

		chatPanel.addListenerChatField(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					send();

				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		chatPanel.addListenerChatSendButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				send();

			}

		});
	}

	public void send() {
		if (!chatPanel.getChatFieldSend().getText().equals("")
				&& !chatPanel.getChatFieldSend().getText().equals(" ")) {

			chatPanel.getChatField().setText(
					chatPanel.getChatField().getText() + "you: "
							+ chatPanel.getChatFieldSend().getText() + "\n");
			chatModel.send(chatPanel.getChatFieldSend().getText());
			chatPanel.getChatFieldSend().setText("");
		}
	}

	public void startcheckingmessages() {
		ScheduledExecutorService exec = Executors
				.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				chatPanel.getChatField().setText(
						chatPanel.getChatField().getText()
								+ chatModel.newmessages());
			}
		}, 0, this.checkMessagesTimer, TimeUnit.SECONDS);
	}

	public ChatPanel getchatpanel() {
		return chatPanel;
	}
}
