package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.ChatModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.views.BoardPanel;
import nl.avans.min04sob.scrabble.views.ChangePassPanel;
import nl.avans.min04sob.scrabble.views.ChatPanel;
import nl.avans.min04sob.scrabble.views.GamesPanel;
import nl.avans.min04sob.scrabble.views.MenuView;
import nl.avans.min04sob.scrabble.views.UserInfoPanel;

public class MainController extends CoreController {

	private ChangePassPanel changePassPanel;
	private CoreWindow frame;
	private MenuView menu;
	private AccountModel account;
	private GamesPanel gamesPanel;
	private BoardPanel currGamePanel;
	private ChatPanel chatPanel;
	private ChatModel chatModel;

	public MainController() {
		initialize();
		addListeners();

		addView(menu);
		addView(gamesPanel);
		addModel(account);
		addView(chatPanel);
		addModel(chatModel);

		// Add the old messages first.
		for (String message : chatModel.getMessages()) {
			chatPanel.addToChatField(message);
		}

		frame.setJMenuBar(menu);
		frame.addRightPanel(gamesPanel);
		frame.addCenterPanel(currGamePanel);
		frame.addLeftPanel(chatPanel);
		frame.pack();

	}

	@Override
	public void initialize() {
		frame = new CoreWindow("Wordfeud", JFrame.EXIT_ON_CLOSE);
		changePassPanel = new ChangePassPanel();
		menu = new MenuView();
		account = new AccountModel();

		gamesPanel = new GamesPanel();

		currGamePanel = new BoardPanel();
		chatPanel = new ChatPanel();
		chatModel = new ChatModel(511, account.getUsername());
	}

	@Override
	public void addListeners() {

		menu.addChangePassItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});

		menu.addLoginItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController loginC = new LoginController(account);
				loginC.addView(menu);
			}
		});

		menu.addLogoutItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.logout();
			}
		});

		gamesPanel.addGameListListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				GameModel selectedGame = gamesPanel.getSelectedValue();
				openGame(selectedGame.getGameId());
			}
		});
		
		chatPanel.addListenerChatField(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					sendChat();
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

		});
		chatPanel.addListenerChatSendButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendChat();
			}
		});
	}

	protected void openGame(int gameId) {
		// TODO Open chat for gameId and open gameBoard for gameId

	}

	private void changePass() {
		frame.addCenterPanel(changePassPanel);
	}
	
	

	public void sendChat() {
		String message = chatPanel.getChatFieldSendText();

		if (!message.equals("") && !message.equals(" ")) {
			chatModel.send(message);
			chatModel.update();

			// Empty the chat message box
			chatPanel.setChatFieldSendText("");
		}
	}

}
