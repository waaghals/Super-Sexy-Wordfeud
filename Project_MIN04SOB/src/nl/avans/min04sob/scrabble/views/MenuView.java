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
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.GameModel;

public class MenuView extends JMenuBar implements CoreView {

	private JMenu gameMenu;
	private JMenu accountMenu;
	private JMenu challengeMenu;
	private JMenu moderaterMenu;

	private JMenuItem loginItem;
	private JMenuItem logoutItem;
	private JMenuItem changePassItem;

	private JMenuItem doChallengeItem;
	private JMenuItem viewChallengeItem;

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
		createGameMenu();
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

	private void createGameMenu() { // ArrayList<GameModel>
		gameMenu = new JMenu("Huidige spellen");
		gameMenu.removeAll();
	}

	private void createModeratorMenu() {
		moderaterMenu = new JMenu("Modereer");
		viewWords = new JMenuItem("Woorden beheren");
		moderaterMenu.add(viewWords);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "login":
			AccountModel user = (AccountModel) evt.getNewValue();
			setAfterLoginMenu(user.getUsername());

			add(challengeMenu);
			if (user.isModerator()) {
				add(moderaterMenu);
			}

			for (GameModel game : user.getOpenGames()) {
				JMenuItem item = new JMenuItem();
				gameMenu.add(new JMenuItem(game.toString()));

			}

			if (user.getOpenGames().size() > 0) {
				add(gameMenu);
				addGameMenuActionListener(1, new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent arg0) {
						System.out.println(arg0.getSource());
					}
				});
			}

			break;
		case "logout":
			setAfterLogoutMenu();
			remove(challengeMenu);
			remove(moderaterMenu);
			remove(gameMenu);
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

	public void addGameMenuActionListener(int index, ChangeListener listener) {
		if (gameMenu.getItemCount() < index) {
			gameMenu.getItem(index).addChangeListener(listener);
			// gameMenu.getItem(index).addActionListener(listener);
			
			GameModel[] selectedObject = (GameModel[]) gameMenu.getModel().getSelectedObjects();
			
		}
	}

}
