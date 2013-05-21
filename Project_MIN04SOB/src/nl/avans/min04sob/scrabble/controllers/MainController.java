package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.core.ScrabbleTableCellRenderer;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.ChatModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.Tile;
import nl.avans.min04sob.scrabble.views.BoardPanelView;
import nl.avans.min04sob.scrabble.views.ChangePassPanel;
import nl.avans.min04sob.scrabble.views.ChatPanel;
import nl.avans.min04sob.scrabble.views.GamesComboBox;
import nl.avans.min04sob.scrabble.views.MenuView;

public class MainController extends CoreController {

	private ChangePassPanel changePassPanel;
	private CoreWindow frame;
	private MenuView menu;
	private AccountModel account;
	private GamesComboBox gamesPanel;
	private BoardPanelView currGamePanel;
	private ChatPanel chatPanel;
	private ChatModel chatModel;
	private BoardModel boardModel;

	public MainController() {
		initialize();
		addListeners();

		addView(menu);
		addView(gamesPanel);
		addModel(boardModel);
		addModel(account);
		addView(chatPanel);

		// Add the old messages first.
		//for (String message : chatModel.getMessages()) {
			//chatPanel.addToChatField(message);
		//}

		frame.setJMenuBar(menu);
	
		frame.getContentPane().add(gamesPanel, "cell 0 0 2 1,alignx left,aligny top");
		frame.getContentPane().add(currGamePanel, "cell 4 0 6 7,grow");
		frame.getContentPane().add(chatPanel, "cell 0 1 4 8,alignx left,aligny top");
		frame.pack();

	}

	@Override
	public void initialize() {
		frame = new CoreWindow("Wordfeud", JFrame.EXIT_ON_CLOSE);
		//changePassPanel = new ChangePassPanel();
		menu = new MenuView();
		account = new AccountModel();

		gamesPanel = new GamesComboBox();

		currGamePanel = new BoardPanelView();
		boardModel = new BoardModel();
		currGamePanel.setRenderer(new ScrabbleTableCellRenderer(boardModel));
		currGamePanel.setModel(boardModel);
		
		boardModel.setValueAt(new Tile("B",false), 8, 8);
		chatPanel = new ChatPanel();
		chatModel = null;
	}

	@Override
	public void addListeners() {

		menu.addChangePassItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});

		addLoginListener();

		menu.addLogoutItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.logout();
				addLoginListener();
			}
		});
		
		gamesPanel.addGameListListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameModel selectedGame = gamesPanel.getSelectedGame();
				openGame(selectedGame);
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
	
	private void addLoginListener(){
		menu.addLoginItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController loginC = new LoginController(account);
				loginC.addView(menu);
			}
		});
	}

	protected void openGame(GameModel selectedGame) {
		removeModel(chatModel);

		chatModel = new ChatModel(selectedGame, account);
		addModel(chatModel);
		
		chatPanel.empty();
		for (String message : chatModel.getMessages()) {
			chatPanel.addToChatField(message);
		}
	}

	private void changePass() {
		frame.remove(chatPanel);
		frame.remove(currGamePanel);
		frame.add(new ChangePassPanel(), "cell 0 1 4 8,alignx left,aligny top");
		frame.repaint();
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
