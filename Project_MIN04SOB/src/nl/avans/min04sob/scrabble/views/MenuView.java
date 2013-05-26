package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.avans.min04sob.scrabble.core.CoreView;
import nl.avans.min04sob.scrabble.core.Event;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.GameModel;

public class MenuView extends JMenuBar implements CoreView {

	private JMenu accountMenu;
	private JMenu challengeMenu;
	private JMenu moderaterMenu;
	private JMenu competitionMenu;

	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	private JMenuItem changePassItem;

	private JMenuItem doChallengeItem;
	private JMenuItem viewChallengeItem;
	
	private JMenuItem seeCompetitionsItem;
	private JMenuItem joinCompetitionItem;
	private JMenuItem deleteCompetitionItem;

	private JMenuItem viewWords;

	private JLabel accountName;
	private JLabel accountText;

	private int numChallenge;
	private String username;

	public MenuView() {
		numChallenge = 0;
		createAccountMenu();
		createChallengeMenu();
		createModeratorMenu();
		createCompetitionMenu();
	}

	private void createChallengeMenu() {
		challengeMenu = new JMenu("Uitdagingen");
		challengeMenu.setMnemonic('U');
		doChallengeItem = new JMenuItem("Spelers uitdagen");
		viewChallengeItem = new JMenuItem("Bekijk uitdagingen ( "
				+ numChallenge + " )");

		challengeMenu.add(doChallengeItem);
		challengeMenu.add(viewChallengeItem);
	}

	private void createAccountMenu() {
		accountMenu = new JMenu("Account");
		accountMenu.setMnemonic('A');

		setAfterLogoutMenu();

		// Add it to the JMenuBar
		add(accountMenu);
	}
	
	private void createCompetitionMenu() {
		// create the menu
		competitionMenu = new JMenu("Competitie");
		competitionMenu.setMnemonic('C');
		// create the menuItems
		seeCompetitionsItem = new JMenuItem("Competitie's bekijken");
		joinCompetitionItem = new JMenuItem("Deelnemen aan competitie's");
		deleteCompetitionItem = new JMenuItem("verwijder competitie"); 
		// add the menu and the menuItems
		add(competitionMenu);
		competitionMenu.add(seeCompetitionsItem);
		competitionMenu.add(joinCompetitionItem);
		competitionMenu.add(deleteCompetitionItem);
	}

	private void setAfterLogoutMenu() {
		accountMenu.removeAll();

		loginItem = new JMenuItem("Inloggen");
		loginItem.setMnemonic('I');
		loginItem.setEnabled(true);

		changePassItem = new JMenuItem("Wachtwoord veranderen");
		changePassItem.setMnemonic('W');
		changePassItem.setEnabled(true);

		logoutItem = new JMenuItem("Logout");
		logoutItem.setMnemonic('X');
		logoutItem.setEnabled(true);

		accountMenu.add(loginItem);
	}

	private void setAfterLoginMenu(String username) {
		accountMenu.removeAll();
		this.username = username;
		accountText = new JLabel("Ingelogd als:");
		accountName = new JLabel(username);

		accountMenu.add(changePassItem);
		accountMenu.add(logoutItem);
		accountMenu.addSeparator();
		accountMenu.add(accountText);
		accountMenu.add(accountName);
	}

	private void createModeratorMenu() {
		moderaterMenu = new JMenu("Modereer");
		moderaterMenu.setMnemonic('M');
		viewWords = new JMenuItem("Woorden beheren");
		moderaterMenu.add(viewWords);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case Event.LOGIN:
			AccountModel user = (AccountModel) evt.getNewValue();
			setAfterLoginMenu(user.getUsername());

			add(challengeMenu);
			add(competitionMenu);
			if (user.isRol("moderater")) {
				add(moderaterMenu);
			}

			break;
		case Event.LOGOUT:
			setAfterLogoutMenu();
			remove(challengeMenu);
			remove(moderaterMenu);
			break;

		case "numChallengeUpdate":
			numChallenge = (int) evt.getNewValue();
			break;

		default:
			break;
		}
		revalidate();
	}

	public void addLoginItemActionListener(ActionListener listener) {
		loginItem.addActionListener(listener);
	}

	public void addLogoutItemActionListener(ActionListener listener) {
		logoutItem.addActionListener(listener);
	}

	public void addChangePassItemActionListener(ActionListener listener) {
		changePassItem.addActionListener(listener);
	}
	public void adddoChallengeItemActionListener(ActionListener listener) {
		doChallengeItem.addActionListener(listener);
	}
	public void viewChallengeItemActionListener(ActionListener listener) {
		viewChallengeItem.addActionListener(listener);
	}
		
	public void seeCompetitionsItem(ActionListener listener) {
		seeCompetitionsItem.addActionListener(listener);
	}
	public void joinCompetitionItem(ActionListener listener) {
		joinCompetitionItem.addActionListener(listener);
	}
	public void viewWords(ActionListener listener) {
		viewWords.addActionListener(listener);
	}
	public void deleteCompetitionItem(ActionListener listener) {
		deleteCompetitionItem.addActionListener(listener);
	}
	
}
