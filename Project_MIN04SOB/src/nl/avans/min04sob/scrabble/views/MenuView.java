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
import nl.avans.min04sob.scrabble.models.Role;

public class MenuView extends JMenuBar implements CoreView {

	private JMenu accountMenu;
	private JMenu challengeMenu;
	private JMenu moderaterMenu;
	private JMenu competitionMenu;

	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	private JMenuItem changePassItem;
	private JMenuItem registerItem;

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
		createMenus();
		createAccountMenu();
		createChallengeMenu();
		createModeratorMenu();
		createCompetitionMenu();

		setLoggedOutState();
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

		moderaterMenu = new JMenu("Modereer");
		moderaterMenu.setMnemonic('M');
		moderaterMenu.setEnabled(false);

		add(accountMenu);
		add(challengeMenu);
		add(competitionMenu);
		add(moderaterMenu);

	}

	private void createChallengeMenu() {
		doChallengeItem = new JMenuItem("Spelers uitdagen");
		viewChallengeItem = new JMenuItem("Bekijk uitdagingen ( "
				+ numChallenge + " )");

		challengeMenu.add(doChallengeItem);
		challengeMenu.add(viewChallengeItem);
	}

	private void createAccountMenu() {
		registerItem = new JMenuItem("Registreren");
		registerItem.setMnemonic('R');
		
		loginItem = new JMenuItem("Inloggen");
		loginItem.setMnemonic('I');
		
		logoutItem = new JMenuItem("Logout");
		logoutItem.setMnemonic('O');
		logoutItem.setEnabled(true);

		changePassItem = new JMenuItem("Wachtwoord veranderen");
		changePassItem.setMnemonic('W');
	}

	private void createCompetitionMenu() {
		// create the menuItems
		seeCompetitionsItem = new JMenuItem("Competitie's bekijken");
		joinCompetitionItem = new JMenuItem("Deelnemen aan competitie's");
		deleteCompetitionItem = new JMenuItem("verwijder competitie");
		// add the menu and the menuItems

		competitionMenu.add(seeCompetitionsItem);
		competitionMenu.add(joinCompetitionItem);
		competitionMenu.add(deleteCompetitionItem);
	}

	private void setLoggedOutState() {
		accountMenu.removeAll();
		accountMenu.add(loginItem);
		accountMenu.add(registerItem);
	}

	private void setLoggedInState() {
		accountMenu.removeAll();
		accountMenu.add(changePassItem);
		accountMenu.add(logoutItem);
	}

	private void createModeratorMenu() {
		viewWords = new JMenuItem("Woorden beheren");
		viewWords.setMnemonic('W');
		moderaterMenu.add(viewWords);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case Event.LOGIN:
			AccountModel user = (AccountModel) evt.getNewValue();
			setLoggedInState();

			challengeMenu.setEnabled(true);
			competitionMenu.setEnabled(true);
			if (user.isRole(Role.MODERATOR)) {
				moderaterMenu.setEnabled(true);
			}
			setChallengeCount(user.getChallengeCount());

			break;
		case Event.LOGOUT:
			setLoggedOutState();
			challengeMenu.setEnabled(false);
			competitionMenu.setEnabled(false);
			moderaterMenu.setEnabled(false);
			break;

		case "numChallengeUpdate":
			numChallenge = (int) evt.getNewValue();
			break;

		default:
			break;
		}
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
	
	public void addRegisterListener(ActionListener listener){
		registerItem.addActionListener(listener);
	}
	
	public void setChallengeCount(int count){
		//TODO make this work
		numChallenge = count;
		viewChallengeItem =  new JMenuItem("Bekijk uitdagingen ( "
				+ numChallenge + " )");
		challengeMenu.revalidate();
		
	}

}
