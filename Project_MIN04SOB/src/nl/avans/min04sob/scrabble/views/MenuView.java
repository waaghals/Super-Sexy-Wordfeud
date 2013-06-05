package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.core.Role;
import nl.avans.min04sob.scrabble.core.mvc.CoreView;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.GameModel;

@SuppressWarnings("serial")
public class MenuView extends JMenuBar implements CoreView {

	private JMenu accountMenu;
	private JMenu challengeMenu;
	private JMenu toolboxMenu;
	private JMenu competitionMenu;
	private JMenu gameMenu;
	private JMenu gameMenuView;
	private JMenu gameMenuOpen;

	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	private JMenuItem changePassItem;
	private JMenuItem registerItem;
 

	private JMenuItem doChallengeItem;
	private JMenuItem viewChallengeItem;

	private JMenuItem seeCompetitionsItem;
	private JMenuItem joinCompetitionItem;
	private JMenuItem deleteFromCompetitionItem;
	private JMenuItem deleteCompetitionItem;

	private JMenuItem viewWords;
	private JMenuItem AccountaanmakenItem;
	private int numChallenge;

	private ActionListener openGameListener;
	private ActionListener viewGameListener;
	private JMenuItem createCompetitionItem;
	private JMenuItem viewPlayers;


	public MenuView() {
		numChallenge = 0;
		createMenus();
		createAccountMenu();
		createChallengeMenu();
		createModeratorMenu();
		createCompetitionMenu();

		setLoggedOutState();
	}

	public void addChangePassItemActionListener(ActionListener listener) {
		changePassItem.addActionListener(listener);
	}

	public void adddoChallengeItemActionListener(ActionListener listener) {
		doChallengeItem.addActionListener(listener);
	}
	
	private void addGamesToMenu(JMenu menu, ArrayList<GameModel> games) {
		menu.removeAll();
		ArrayList<JMenuItem> items = new ArrayList<JMenuItem>();
		JMenuItem item;
		for (GameModel game : games) {
			item = new JMenuItem(game.toString());
			item.putClientProperty("game", game);
			if(!items.contains(item)) {
				items.add(item);
			}
		}
		for(JMenuItem i : items) {
			menu.add(i);
		}
	}


	public void addLoginItemActionListener(ActionListener listener) {
		loginItem.addActionListener(listener);
	}

	public void addLogoutItemActionListener(ActionListener listener) {
		logoutItem.addActionListener(listener);
	}

	private void addMenuItemListeners(JMenu menu, ActionListener listener) {
		for (int i = 0; i < menu.getItemCount(); i++) {
			JMenuItem menuItem = menu.getItem(i);
			menuItem.addActionListener(listener);
		}
	}

	public void addOpenGamesListener(ActionListener listener) {
		openGameListener = listener;
		addMenuItemListeners(gameMenuOpen, openGameListener);
	}

	public void addRegisterListener(ActionListener listener) {
		registerItem.addActionListener(listener);
	}

	public void addViewGamesListener(ActionListener listener) {
		viewGameListener = listener;
		addMenuItemListeners(gameMenuView, viewGameListener);
	}

	private void createAccountMenu() {
		registerItem = new JMenuItem("Registreren");
		registerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK)); 

		loginItem = new JMenuItem("Inloggen");
		loginItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_MASK)); 

		logoutItem = new JMenuItem("Logout");
		logoutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK)); 

		changePassItem = new JMenuItem("Wachtwoord veranderen");
		changePassItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK)); ;
	}

	private void createChallengeMenu() {
		doChallengeItem = new JMenuItem("Spelers uitdagen");
		doChallengeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK)); 
		viewChallengeItem = new JMenuItem("Bekijk uitdagingen"/*( "
				+ numChallenge + " )"*/);
		viewChallengeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK)); 
		
		challengeMenu.add(doChallengeItem);
		challengeMenu.add(viewChallengeItem);
	}

	private void createCompetitionMenu() {
		// create the menuItems
		seeCompetitionsItem = new JMenuItem("Competities bekijken");
		seeCompetitionsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK)); 
		joinCompetitionItem = new JMenuItem("Deelnemen aan competities");
		joinCompetitionItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK)); 
		deleteFromCompetitionItem = new JMenuItem("Verwijderen uit competitie");
		deleteFromCompetitionItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK)); 
		deleteCompetitionItem = new JMenuItem("verwijder competitie");
		deleteCompetitionItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_MASK)); 
		createCompetitionItem = new JMenuItem("Competitie aanmaken");
		createCompetitionItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, KeyEvent.CTRL_MASK)); 
		// add the menu and the menuItems

		competitionMenu.add(seeCompetitionsItem);
		competitionMenu.add(joinCompetitionItem);
		competitionMenu.add(deleteFromCompetitionItem);
		competitionMenu.add(deleteCompetitionItem);
		competitionMenu.add(createCompetitionItem);
	}

	private void createMenus() {
		accountMenu = new JMenu("Account");
		accountMenu.setMnemonic('A');
		accountMenu.setEnabled(true);

		challengeMenu = new JMenu("Uitdagingen");
		challengeMenu.setMnemonic('U');
		challengeMenu.setEnabled(false);

		competitionMenu = new JMenu("Competitie");
		competitionMenu.setMnemonic('C');
		competitionMenu.setEnabled(false);

		toolboxMenu = new JMenu("Gereedschap");
		toolboxMenu.setMnemonic('G');
		toolboxMenu.setEnabled(false);

		gameMenu = new JMenu("Spellen");
		gameMenu.setMnemonic('S');
		gameMenu.setEnabled(false);

		gameMenuOpen = new JMenu("Bezig");
		gameMenuOpen.setMnemonic('B');
		gameMenuOpen.setEnabled(false);

		gameMenuView = new JMenu("Meekijken");
		gameMenuView.setMnemonic('M');
		gameMenuView.setEnabled(false);

		gameMenu.add(gameMenuOpen);
		gameMenu.add(gameMenuView);


		 
		
		add(accountMenu);
		add(challengeMenu);
		add(competitionMenu);
		add(toolboxMenu);
		add(gameMenu);
	}

	private void createModeratorMenu() {
		viewWords = new JMenuItem("Woorden beheren");
		viewWords.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK)); 
		AccountaanmakenItem = new JMenuItem("Account aanmaken");
		AccountaanmakenItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_MASK));
		
		viewPlayers = new JMenuItem("Gebruikers beheren");
		viewPlayers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK)); 
		
		toolboxMenu.add(AccountaanmakenItem );
		toolboxMenu.add(viewWords);
		toolboxMenu.add(viewPlayers);
	}

	public void deleteCompetitionItem(ActionListener listener) {
		deleteCompetitionItem.addActionListener(listener);
	}

	public void deleteFromCompetitionItem(ActionListener listener){
		deleteFromCompetitionItem.addActionListener(listener);
	}
	
	public void joinCompetitionItem(ActionListener listener) {
		joinCompetitionItem.addActionListener(listener);
	}
	
	public void createCompetitionItem(ActionListener listener) {
		 createCompetitionItem.addActionListener(listener);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case Event.LOGIN:
			AccountModel user = (AccountModel) evt.getNewValue();
			setLoggedInState();
			setVisibleMenus(user);

			setChallengeCount(user.getChallengeCount());
			
			addGamesToMenu(gameMenuOpen, user.getOpenGames());
			addGamesToMenu(gameMenuView, user.getObserverAbleGames());

			addMenuItemListeners(gameMenuOpen, openGameListener);
			addMenuItemListeners(gameMenuView, viewGameListener);

			break;
		case Event.LOGOUT:
			setLoggedOutState();

			break;
		case Event.NEWCHALLENGE:
			String[] challenges = (String[]) evt.getNewValue();
			setChallengeCount(challenges.length);
			break;

		default:
			break;
		}
		
	}

	private void setVisibleMenus(AccountModel user) {
		if (user.isRole(Role.MODERATOR) || user.isRole(Role.ADMINISTRATOR)) {
			toolboxMenu.setEnabled(true);
		}
		
		if(user.isRole(Role.MODERATOR)){
			viewWords.setEnabled(true);
		}
		
		if(user.isRole(Role.ADMINISTRATOR)){
			viewPlayers.setEnabled(true);
		}
		
		int numGames = user.getOpenGames().size();
		if (user.isRole(Role.OBSERVER) || (numGames > 0 && user.isRole(Role.PLAYER))) {
			gameMenu.setEnabled(true);
		}

		if (user.isRole(Role.OBSERVER)) {
			gameMenuView.setEnabled(true);
		}

		if(user.isRole(Role.PLAYER)){
			competitionMenu.setEnabled(true);
			joinCompetitionItem.setEnabled(true);
			challengeMenu.setEnabled(true);
			seeCompetitionsItem.setEnabled(true);
			deleteCompetitionItem.setEnabled(false);
			deleteFromCompetitionItem.setEnabled(false);
		}
		
		if(user.getOwnedCompetitions().length > 0){
			createCompetitionItem.setEnabled(false);
		}

		if (numGames > 0 && user.isRole(Role.PLAYER)) {
			gameMenuOpen.setEnabled(true);
		}
	}

	public void seeCompetitionsItem(ActionListener listener) {
		seeCompetitionsItem.addActionListener(listener);
	}

	public void setChallengeCount(int count) {
		// TODO make this work
		numChallenge = count;
		viewChallengeItem = new JMenuItem("Bekijk uitdagingen ( "
				+ numChallenge + " )");
		challengeMenu.revalidate();

	}

	private void setLoggedInState() {
		accountMenu.removeAll();
		accountMenu.add(changePassItem);
		accountMenu.add(logoutItem);
	}

	private void setLoggedOutState() {
		accountMenu.removeAll();
		accountMenu.add(loginItem);
		accountMenu.add(registerItem);
		challengeMenu.setEnabled(false);
		competitionMenu.setEnabled(false);
		toolboxMenu.setEnabled(false);
		viewWords.setEnabled(false);
		viewPlayers.setEnabled(false);
		gameMenu.setEnabled(false);
		
		gameMenuOpen.removeAll();
		gameMenuView.removeAll();
	}

	public void viewChallengeItemActionListener(ActionListener listener) {
		viewChallengeItem.addActionListener(listener);
	}
	
	public void viewWords(ActionListener listener) {
		viewWords.addActionListener(listener);
	}
	public void viewPlayers(ActionListener listener) {
		viewPlayers.addActionListener(listener);
	}
	public void Accountaanmaken(ActionListener listener) {
		AccountaanmakenItem.addActionListener(listener);
	}
}
