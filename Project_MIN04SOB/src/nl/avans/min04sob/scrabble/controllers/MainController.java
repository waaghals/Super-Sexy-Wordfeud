package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.GameModel;
import nl.avans.min04sob.scrabble.views.BoardPanel;
import nl.avans.min04sob.scrabble.views.ChangePassPanel;
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

	public MainController() {
		initialize();
		addListeners();
	
		addView(menu);
		addView(gamesPanel);
		addModel(account);
		
		frame.setJMenuBar(menu);
		frame.addRightPanel(gamesPanel);
		frame.addCenterPanel(currGamePanel);
		frame.pack();
		
		
	}
	
	@Override
	public void initialize() {
		frame = new CoreWindow("Wordfeud" ,JFrame.EXIT_ON_CLOSE);
		changePassPanel = new ChangePassPanel();
		menu = new MenuView();
		account = new AccountModel();
		
		gamesPanel = new GamesPanel();
		
		currGamePanel = new BoardPanel();
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
	}

	protected void openGame(int gameId) {
		// TODO Open chat for gameId and open gameBoard for gameId
		
	}

	private void changePass() {
		frame.addCenterPanel(changePassPanel);
	}

	
}
