package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nl.avans.min04sob.mvcvoorbeeld.BoardPanel;
import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.core.CoreWindow;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.views.ChangePassPanel;
import nl.avans.min04sob.scrabble.views.GamesPanel;
import nl.avans.min04sob.scrabble.views.MenuView;
import nl.avans.min04sob.scrabble.views.UserInfoPanel;

public class MainController extends CoreController {

	private UserInfoPanel userInfoPanel;
	private ChangePassPanel changePassPanel;
	private CoreWindow frame;
	private MenuView menu;
	private AccountModel account;
	private GamesPanel gamesPanel;
	private BoardPanel currGamePanel;

	public MainController() {
		
		userInfoPanel.setUsername(account.getUsername());
	
		addView(menu);
		addView(gamesPanel);
		addModel(account);
		
		frame.setJMenuBar(menu);
		frame.addTopPanel(userInfoPanel);
		frame.addRightPanel(gamesPanel);
		frame.addCenterPanel(currGamePanel);
	}
	
	@Override
	public void initialize() {
		frame = new CoreWindow("Wordfeud" ,JFrame.EXIT_ON_CLOSE);
		userInfoPanel = new UserInfoPanel();
		changePassPanel = new ChangePassPanel();
		menu = new MenuView();
		account = new AccountModel();
		
		gamesPanel = new GamesPanel();
		gamesPanel.addGames(account.getOpenGames());
		
		currGamePanel = new BoardPanel();
	}

	@Override
	public void addListeners() {
		userInfoPanel.addActionListenerLogout(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				account.logout();
			}
		});

		userInfoPanel.addActionListenerChangePass(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});

		userInfoPanel.addActionListenerAdmin(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		menu.addChangePassItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});
		
		menu.addLoginItemActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginController loginC = new LoginController();
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
				// TODO Automatisch gegenereerde methodestub
				gamesPanel.getSelectedValue();
			}
		});
	}

	private void changePass() {
		frame.addCenterPanel(changePassPanel);
	}

	
}
