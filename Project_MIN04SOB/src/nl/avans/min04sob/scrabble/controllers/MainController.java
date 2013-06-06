package nl.avans.min04sob.scrabble.controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import nl.avans.min04sob.scrabble.core.mvc.CoreController;
import nl.avans.min04sob.scrabble.core.mvc.CoreWindow;
import nl.avans.min04sob.scrabble.misc.InvalidMoveException;
import nl.avans.min04sob.scrabble.misc.ScrabbleTableCellRenderer;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.ChallengeModel;
import nl.avans.min04sob.scrabble.models.ChatModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.StashModel;
import nl.avans.min04sob.scrabble.models.Tile;
import nl.avans.min04sob.scrabble.views.BoardPanel;
import nl.avans.min04sob.scrabble.views.ChatPanel;
import nl.avans.min04sob.scrabble.views.MenuView;
import nl.avans.min04sob.scrabble.views.SelectSwapView;

public class MainController extends CoreController {

	private CoreWindow frame;
	private MenuView menu;
	private AccountModel account;

	private AccountController accountcontroller;
	private BoardPanel currGamePanel;
	private ChatPanel chatPanel;
	private ChatModel chatModel;
	private BoardModel boardModel;

	private GameModel currentGame;

	private CoreWindow swapWindow;
	private SelectSwapView swapView;

	private StashModel stashModel;

	public MainController() {

		initialize();
		addListeners();
		addView(menu);
		addView(chatPanel);
		addView(frame);
		addModel(boardModel);

		addModel(account);
		addModel(new ChallengeModel(account));

		// Add the old messages first.
		// for (String message : chatModel.getMessages()) {
		// chatPanel.addToChatField(message);
		// }

		frame.setJMenuBar(menu);
		frame.setPreferredSize(new Dimension(1000, 680));
		frame.pack();
		frame.setLocationRelativeTo(null);
		startUp();
	}

	private void addButtonListeners() {
		currGamePanel.addRefreshActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		currGamePanel.addResignActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResignController(currentGame);
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
		// swappen
		currGamePanel.addSwapActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// view maken om de letters te selecteren
				// TODO moest de methode verander waardoor jullie ding niet goed
				// meer werkte je moet getValueAt gebruiken nu.
				Tile[] letters = stashModel
						.getPlayerTiles(account, currentGame);

				selectSwap(letters);
			}
		});

		currGamePanel.addPlayActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*BoardModel newBoard = BoardModel.newInstance(boardModel);
				currentGame.getBoardFromDatabase();
				BoardModel oldBoard = BoardModel.newInstance(boardModel);
				currentGame.checkValidMove(oldBoard, newBoard);*/
				currentGame.playWord(boardModel);
			}
		});
	}

	@Override
	public void addListeners() {

		menu.viewChallengeItemActionListener(new ActionListener() { // uitdagingen
																	// bekijken

			@Override
			public void actionPerformed(ActionEvent e) {
				// crtl.challengers();
				new ChallengeController(account);
				// new ChallengeController(account.getUsername());

			}
		});
		menu.adddoChallengeItemActionListener(new ActionListener() { // uitdagen

			@Override
			public void actionPerformed(ActionEvent e) {
				new CompetitionController(account).openChallengeView();
				// crtl.toChallenge();

			}
		});
		menu.addChangePassItemActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// changePass();
				AccountController accountController = new AccountController(
						account);
				accountController.setChangePassPanel();
			}
		});
		menu.Accountaanmaken(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountController accountController = new AccountController(
						account);
				accountController.loginToRegister();
			}
		});
		menu.seeCompetitionsItem(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CompetitionController(account).openCompetitionScores();
			}
		});

		menu.joinCompetitionItem(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new CompetitionController(account).openJoinCompetitionView();

				// invController = new InviteController();
				// invController.setButtonsJoin();
			}
		});
		menu.viewPlayers(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				accountcontroller = new AccountController(account);
				accountcontroller.adminChangePass();

			}
		});

		menu.viewWords(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AcceptDeclineController();
			}
		});

		menu.deleteFromCompetitionItem(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CompetitionController(account)
						.openDeleteFromCompetitionView();
			}
		});

		menu.deleteCompetitionItem(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new CompetitionController(account).openDeleteCompetitionView();

			}
		});

		menu.createCompetitionItem(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new CompetitionController(account).openCreateCompetitionView();
			}
		});

		addLoginListener();

		menu.addLogoutItemActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				account.logout();
				closePanels();
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
				currGamePanel.playerView();
				chatPanel.playerView();
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
				currGamePanel.observerView();
				chatPanel.observerView();
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

	private void refresh() {
		boardModel.removeMutatable();
		openGame(currentGame);
	}

	private void addLoginListener() {
		menu.addLoginItemActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accountcontroller = new AccountController(account);
				accountcontroller.addView(menu);
				accountcontroller.addView(chatPanel);
			}
		});
	}

	private void startUp() {
		accountcontroller = new AccountController(account);
		accountcontroller.addView(menu);
		accountcontroller.addView(chatPanel);

	}

	@Override
	public void initialize() {
		frame = new CoreWindow("Wordfeud", JFrame.EXIT_ON_CLOSE);
		// changePassPanel = new ChangePassPanel();
		menu = new MenuView();

		// competitioncontroller = new CompetitionController();
		account = new AccountModel();

		currGamePanel = new BoardPanel();

		boardModel = new BoardModel();
		currGamePanel.setRenderer(new ScrabbleTableCellRenderer(boardModel));
		chatPanel = new ChatPanel();
	}

	public void closePanels() {
		frame.remove(currGamePanel);
		frame.remove(chatPanel);
		frame.repaint();
	}

	protected void openGame(GameModel selectedGame) {
		addModel(selectedGame);
		removeModel(chatModel);
		setCurrentGame(selectedGame);
		chatModel = new ChatModel(selectedGame, account);
		addModel(chatModel);
		removeModel(boardModel);

		chatPanel.getChatFieldSend().setEnabled(true);

		closePanels();

		currGamePanel = selectedGame.getBoardPanel();
		boardModel = selectedGame.getBoardModel();
		addModel(boardModel);
		addView(currGamePanel);

		currGamePanel.setRenderer(new ScrabbleTableCellRenderer(boardModel));
		currGamePanel.setModel(boardModel);

		updatelabels(selectedGame.getCurrentobserveturn());

		selectedGame.setplayertilesfromdatabase(selectedGame
				.getCurrentobserveturn());

		selectedGame.getBoardFromDatabase();
		selectedGame.update();

		addButtonListeners();

		openPanels();

		chatPanel.empty();
		ArrayList<String> messages = chatModel.getMessages();
		for (String message : messages) {
			chatPanel.addToChatField(message);
		}
		chatModel.update();
	}

	public void openPanels() {
		frame.add(currGamePanel, "cell 4 0 6 6,growx,aligny top");

		frame.add(chatPanel, "cell 0 0 4 6,alignx left,aligny top");
		frame.revalidate();
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

	private void setCurrentGame(GameModel selectedGame) {
		currentGame = selectedGame;
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
		} else {
			if (currentGame.yourturn()) {
				currGamePanel.setLabelPlayerTurn("aan jouw ("
						+ account.getUsername() + ")");
			} else {
				currGamePanel.setLabelPlayerTurn(currentGame.getOpponent()
						.getUsername());
			}
		}
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

	// selectSwap
	public void selectSwap(Tile[] letters) {
		swapWindow = new CoreWindow();
		swapView = new SelectSwapView(letters);
		swapWindow.add(swapView);
		swapWindow.setResizable(false);
		swapWindow.setTitle("letters wisselen");
		swapWindow.pack();

		swapView.addButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int size = 0;

				List<Tile> selectedTiles = swapView.getSelectedTiles();
				for (Tile tile : selectedTiles) {

					// elke tile uit hand verwijderen en aan de pot toevoegen
					// zelfde hoeveelheid uit te pot halen en aan hand toevoegen
					// rij toevoegen aan beurt
				}

			}
		});
	}

}
