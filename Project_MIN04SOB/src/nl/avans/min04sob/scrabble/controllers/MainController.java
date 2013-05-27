package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.core.ScrabbleTableCellRenderer;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.BoardModel;
import nl.avans.min04sob.scrabble.models.ChatModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.models.Role;
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
	private GamesComboBox gamesPanel;
	private BoardPanel currGamePanel;
	private ChatPanel chatPanel;
	private ChatModel chatModel;
	private BoardModel boardModel;
	private JLabel turn;
	private JLabel score;
	private Boolean observer;
	private CompetitionController competitioncontroller; 


	public MainController() {
		initialize();
		addListeners();

		addView(menu);
		addView(gamesPanel);
		addModel(boardModel);
		addModel(account);
		addView(chatPanel);

		// Add the old messages first.
		// for (String message : chatModel.getMessages()) {
		// chatPanel.addToChatField(message);
		// }

		frame.setJMenuBar(menu);

		frame.getContentPane().add(gamesPanel,
				"cell 0 0 2 1,alignx left,aligny top");
		frame.getContentPane().add(currGamePanel, "cell 4 0 6 7,grow");

		frame.getContentPane().add(chatPanel,
				"cell 0 1 4 8,alignx left,aligny top");

		// TODO positienering moet beter maar snap niet veel van die cell 0 0 0
		// 0
		frame.getContentPane().add(turn,
				"cell 0 0 3 2,alignx right , aligny top");
		frame.getContentPane()
				.add(score, "cell 4 0 3 3,alignx left,aligny top");

		frame.pack();

	}

	@Override
	public void initialize() {
		observer = false;
		frame = new CoreWindow("Wordfeud", JFrame.EXIT_ON_CLOSE);
		// changePassPanel = new ChangePassPanel();
		menu = new MenuView();
		competitioncontroller = new CompetitionController();
		account = new AccountModel();
		turn = new JLabel();
		turn.setText("TEEEEST");

		score = new JLabel();
		score.setText("teeeeest");

		
		
		crtl=new ChallengeController(account.getUsername());

		gamesPanel = new GamesComboBox();


		

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
				//crtl.openchallenges();
				//TODO stops program from running

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

			}
		});

		menu.joinCompetitionItem(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		menu.viewWords(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
				addLoginListener();
			}
		});
		
		menu.addRegisterListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AccountController login = new AccountController(account);
				login.loginToRegister();
			}
		});

		gamesPanel.addGameListListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameModel selectedGame = gamesPanel.getSelectedGame();

				openGame(selectedGame);

			}
		});

		if(account.isRole(Role.OBSERVER)){
		gamesPanel.addObserverCheckBoxListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("teeeeeeeeeest0");
					if(gamesPanel.checkBoxIsSelected()){
						
						gamesPanel.addGames(account.getObserverAbleGames());
						observer = true;
						System.out.println("teeeeeeeeeest");
						
					}else{
						gamesPanel.addGames(account.getOpenGames());
						observer = false;
						System.out.println("teeeeeeeeeest1");
					}				
			}
		});
		
		}
		



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

		chatModel = new ChatModel(selectedGame, account);
		addModel(chatModel);
		removeModel(boardModel);

		frame.remove(currGamePanel);

		ArrayList<GameModel> games;
		StashModel stash = new StashModel();
		Tile[] letters = stash.getPlayerTiles(account, selectedGame);

		currGamePanel.setPlayerTiles(letters);
		if(observer){
			games = account.getObserverAbleGames();
		}else{
		
			games = account.getOpenGames();
		}
		for(int x= 0; games.size() > x;x++){
			
			if(games.get(x).getGameId() ==	selectedGame.getGameId()){

				System.out.println("test");
				if (games.get(x).yourturn()) {
					turn.setText("you turn");
				} else {
					turn.setText("openentturn");
				}

				// score.setText(games.get(x).score());
				currGamePanel = games.get(x).getBoardcontroller().getBpv();
				boardModel = games.get(x).getBoardcontroller().getBpm();
				currGamePanel.setRenderer(new ScrabbleTableCellRenderer(
						boardModel));
				currGamePanel.setModel(boardModel);
			
				addModel(boardModel);

				
				 games.get(x).getBoardFromDatabase();
					games.get(x).update();
			}
		}
			
			frame.getContentPane().add(currGamePanel, "cell 4 0 6 7,grow");
			frame.revalidate();
			frame.repaint();
		
			
		

		chatPanel.empty();
		ArrayList<String> messages = chatModel.getMessages();
		for (String message : messages) {
			chatPanel.addToChatField(message);
		}
	}

	private void changePass() {
		frame.remove(chatPanel);
		frame.remove(currGamePanel);
		frame.add(accountcontroller.getchangepasspanel(),
				"cell 0 1 4 8,alignx left,aligny top");
		frame.repaint();
	}

	public boolean yourturn() {

		return false;

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
