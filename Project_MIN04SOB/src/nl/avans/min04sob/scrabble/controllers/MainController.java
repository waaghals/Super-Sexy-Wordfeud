package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

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
	private InviteController invController;

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
		addView(frame);

		// Add the old messages first.
		// for (String message : chatModel.getMessages()) {
		// chatPanel.addToChatField(message);
		// }

		frame.setJMenuBar(menu);

		frame.pack();
	}

	@Override
	public void initialize() {
		observer = false;
		frame = new CoreWindow("Wordfeud", JFrame.EXIT_ON_CLOSE);
		// changePassPanel = new ChangePassPanel();
		menu = new MenuView();

		// competitioncontroller = new CompetitionController();
		account = new AccountModel();


		currGamePanel = new BoardPanel();

		boardModel = new BoardModel();
		currGamePanel.setRenderer(new ScrabbleTableCellRenderer(boardModel));
		currGamePanel.setModel(boardModel);

		boardModel.setValueAt(new Tile("B", 15, false), 8, 8);
		chatPanel = new ChatPanel();
		chatModel = null;

	}

	@Override
	public void addListeners() {

		menu.viewChallengeItemActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//crtl.challengers();
				new ChallengeController2();

			}
		});
		menu.adddoChallengeItemActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//crtl.toChallenge();
				new CompetitionController(account).openCompetitionView();
			}
		});
		menu.addChangePassItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//changePass();
				AccountController accountController = new AccountController(account);
				accountController.setChangePassPanel();
			}
		});

		menu.seeCompetitionsItem(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CompetitionController(account).openCompetitionView();
			}
		});

		menu.joinCompetitionItem(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new CompetitionController(account).openJoinCompetitionView();

				//invController = new InviteController();
				//invController.setButtonsJoin();
			}
		});

		menu.viewWords(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AcceptDeclineController();
			}
		});

		menu.deleteCompetitionItem(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invController = new InviteController();
				invController.setButtonsRemove();
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

	private void addButtonListeners() {
		currGamePanel.addResignActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resigncontroller = new ResignController(currentGame);
			}
		});

		currGamePanel.addNextActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentGame.getCurrentobserveturn() < currentGame
						.getNumberOfTotalTurns() + 1) {
					currentGame.setCurrentobserveturn(currentGame
							.getCurrentobserveturn() + 1);

					currGamePanel.update();

					currentGame.updateboardfromdatabasetoturn(currentGame
							.getCurrentobserveturn());

					currentGame.getBoardModel().update();

					updatelabels(currentGame.getCurrentobserveturn());
				}
			}
		});
		currGamePanel.addPreviousActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentGame.getCurrentobserveturn() > 0) {
					currentGame.setCurrentobserveturn(currentGame
							.getCurrentobserveturn() - 1);
					currentGame.getBoardModel().setBoardToDefault();
					currGamePanel.update();
					for (int x = 0; currentGame.getCurrentobserveturn() > x
							|| currentGame.getCurrentobserveturn() == x; x++) {
						currentGame.updateboardfromdatabasetoturn(x);

					}
					updatelabels(currentGame.getCurrentobserveturn());

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
		chatPanel.setEnabled(true);
		chatPanel.getChatFieldSend().setEnabled(true);
		frame.remove(currGamePanel);

		ArrayList<GameModel> games;
		StashModel stash = new StashModel();
		Tile[] letters = stash.getPlayerTiles(account, selectedGame);

		currGamePanel.setPlayerTiles(letters);

		games = account.getObserverAbleGames();

		System.out.println("test");
		boolean yourTurn = selectedGame.yourturn();

		currGamePanel = new BoardPanel();
		boardModel = selectedGame.getBoardModel();
		currGamePanel.setRenderer(new ScrabbleTableCellRenderer(boardModel));
		currGamePanel.setModel(boardModel);
		AccountModel accountTurn;

		updatelabels(selectedGame.getCurrentobserveturn());

		// currGamePanel.setLabelScore(selectedGame.getCurrentValueForThisTurn());
		addModel(boardModel);
		selectedGame.setPlayerLetterFromDatabase();
		selectedGame.getBoardFromDatabase();
		selectedGame.update();

		addButtonListeners();

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

	private void updatelabels(int toTurn) {
		if (currentGame.isIamchallenger()) {
			currGamePanel.setLabelsNamesScores(currentGame.getChallenger()
					.getUsername(), currentGame.score(toTurn).split(",")[0],
					currentGame.getOpponent().getUsername(),
					currentGame.score(toTurn).split(",")[1]);

		} else {
			currGamePanel.setLabelsNamesScores(currentGame.getOpponent()
					.getUsername(), currentGame.score(toTurn).split(",")[0],
					currentGame.getChallenger().getUsername(), currentGame
							.score(toTurn).split(",")[1]);

		}
		setTurnLabel();
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
		switch (evt.getPropertyName()) {
		case Event.LOGIN:
			crtl = new ChallengeController(account.getUsername());
			frame.getContentPane().add(currGamePanel,
					"cell 4 0 6 6,growx,aligny top");

			frame.getContentPane().add(chatPanel,
					"cell 0 0 4 6,alignx left,aligny top");
			frame.repaint();

			break;
		case Event.LOGOUT:
			frame.getContentPane().remove(currGamePanel);
			frame.getContentPane().remove(chatPanel);
			frame.repaint();
			break;

		}
	}

	public void setTurnLabel() {
		if (currentGame.isObserver()) {
			if (currentGame.whosturn()) {
				currGamePanel.setLabelPlayerTurn(" van "
						+ currentGame.getChallenger().getUsername());
			} else {
				currGamePanel.setLabelPlayerTurn(" van "
						+ currentGame.getOpponent().getUsername());
			}
		}
	}
}
