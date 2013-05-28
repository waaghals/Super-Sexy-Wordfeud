package nl.avans.min04sob.scrabble.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.ScrabbleTableCellRenderer;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.ChatModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.StashModel;
import nl.avans.min04sob.scrabble.models.Tile;
import nl.avans.min04sob.scrabble.views.BoardPanel;
import nl.avans.min04sob.scrabble.views.ChatPanel;
import nl.avans.min04sob.scrabble.views.GamesComboBox;
import nl.avans.min04sob.scrabble.views.MenuView;

public class MainController extends CoreController {

	private CoreWindow frame;
	private MenuView menu;
	private AccountModel account;
	private ChallengeController crtl;
	private AccountController accountcontroller;
	private BoardPanel currGamePanel;
	private ChatPanel chatPanel;
	private ChatModel chatModel;
	private BoardModel boardModel;
	private GameModel currentGame;

	private Boolean observer;
	private CompetitionController competitioncontroller;
	private ResignController resigncontroller;

	public MainController() {

		initialize();
		addListeners();

		addView(menu);
		addModel(boardModel);
		addModel(account);
		addView(chatPanel);

		// Add the old messages first.
		// for (String message : chatModel.getMessages()) {
		// chatPanel.addToChatField(message);
		// }

		frame.setJMenuBar(menu);
		

		frame.getContentPane().add(currGamePanel,
				"cell 4 0 6 6,growx,aligny top");

		frame.getContentPane().add(chatPanel,
				"cell 0 0 4 6,alignx left,aligny top");

		frame.pack();
	}

	@Override
	public void initialize() {
		observer = false;
		frame = new CoreWindow("Wordfeud", JFrame.EXIT_ON_CLOSE);
		// changePassPanel = new ChangePassPanel();
		menu = new MenuView();

		//competitioncontroller = new CompetitionController();
		account = new AccountModel();

	

		crtl = new ChallengeController(account.getUsername());

		currGamePanel = new BoardPanel();

		boardModel = new BoardModel();
		currGamePanel.setRenderer(new ScrabbleTableCellRenderer(boardModel));
		currGamePanel.setModel(boardModel);

		boardModel.setValueAt(new Tile("B", 15, false), 8, 8);
		chatPanel = new ChatPanel();
		chatModel = null;
		addButtonListener();
	}

	@Override
	public void addListeners() {

		menu.viewChallengeItemActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 crtl.challengers();
				// TODO stops program from running

			}
		});
		menu.adddoChallengeItemActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crtl.toChallenge();

			}
		});
		menu.addChangePassItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});

		menu.seeCompetitionsItem(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CompetitionController(account);
			}
		});
		
		

		menu.joinCompetitionItem(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		menu.viewWords(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AcceptDeclineController();
			}
		});

		menu.deleteCompetitionItem(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		addLoginListener();



		menu.addLogoutItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.logout();
				// addLoginListener();
			}
		});

		menu.addRegisterListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AccountController login = new AccountController(account);
				login.loginToRegister();
			}
		});

		menu.addOpenGamesListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JMenuItem source = (JMenuItem) e.getSource();
				GameModel clickedGame = (GameModel) source
						.getClientProperty("game");
				openGame(clickedGame);
			}
		});

		menu.addViewGamesListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JMenuItem source = (JMenuItem) e.getSource();
				GameModel clickedGame = (GameModel) source
						.getClientProperty("game");

				// TODO open game as observer
				openGame(clickedGame);
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

	private void addButtonListener() {
		currGamePanel.addResignActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resigncontroller = new ResignController(currentGame);
				System.out.println("Testresign");
			}
		});
		System.out.println("teseeeeeeeeeeeeeeeeeeee1eeeeeeeeeeeet");
		currGamePanel.addNextActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentGame.setCurrentobserveturn(currentGame
						.getCurrentobserveturn() + 1);
				
				currGamePanel.update();

				
					currentGame.updateboardfromdatabasetoturn(currentGame
							.getCurrentobserveturn());

				
					currentGame.getBoardModel().update();
			}

		});
		currGamePanel.addPreviousActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				currentGame.setCurrentobserveturn(currentGame
						.getCurrentobserveturn() - 1);
				currentGame.getBoardModel().setBoardToDefault();
				currGamePanel.update();
				for (int x = 0; currentGame.getCurrentobserveturn() > x
						|| currentGame.getCurrentobserveturn() == x; x++) {
					currentGame.updateboardfromdatabasetoturn(x);

				}
				
			}
			


		});
	}

	private void addLoginListener() {
		menu.addLoginItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountcontroller = new AccountController(account);
				accountcontroller.addView(menu);
			}
		});
	}

	protected void openGame(GameModel selectedGame) {
		removeModel(chatModel);
		setCurrentGame(selectedGame);
		chatModel = new ChatModel(selectedGame, account);
		addModel(chatModel);
		removeModel(boardModel);
	
		frame.remove(currGamePanel);

		ArrayList<GameModel> games;
		StashModel stash = new StashModel();
		Tile[] letters = stash.getPlayerTiles(account, selectedGame);

		currGamePanel.setPlayerTiles(letters);

		games = account.getObserverAbleGames();

		System.out.println("test");
		boolean yourTurn = selectedGame.yourturn();
		currGamePanel.setYourTurn(yourTurn);

		// score.setText(games.get(x).score());
		currGamePanel = new BoardPanel();
		boardModel = selectedGame.getBoardModel();
		currGamePanel.setRenderer(new ScrabbleTableCellRenderer(boardModel));
		currGamePanel.setModel(boardModel);

		addModel(boardModel);
		selectedGame.setPlayerLetterFromDatabase();
		selectedGame.getBoardFromDatabase();
		selectedGame.update();
		addButtonListener();
		frame.getContentPane().add(currGamePanel, "cell 4 0 6 7,grow");
		frame.revalidate();
		frame.repaint();

		chatPanel.empty();
		ArrayList<String> messages = chatModel.getMessages();
		for (String message : messages) {
			chatPanel.addToChatField(message);
		}
	}

	private void setCurrentGame(GameModel selectedGame) {
		currentGame = selectedGame;
	}

	private void changePass() {
		frame.remove(chatPanel);
		frame.remove(currGamePanel);
		frame.getContentPane().add(accountcontroller.getchangepasspanel(),
				"cell 0 1 4 8,alignx left,aligny top");
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
	public void propertyChange(PropertyChangeEvent evt) {
		switch(evt.getPropertyName()) {
		case Event.LOGIN:
			frame.getContentPane().add(currGamePanel, "cell 4 0 6 6,growx,aligny top");

			frame.getContentPane().add(chatPanel,
					"cell 0 0 4 6,alignx left,aligny top");
			addButtonListener();
			frame.repaint();
			break;
		case Event.LOGOUT:
			frame.getContentPane().remove(currGamePanel);
			frame.getContentPane().remove(chatPanel);
			frame.repaint();
			break;

		}
	}
}
