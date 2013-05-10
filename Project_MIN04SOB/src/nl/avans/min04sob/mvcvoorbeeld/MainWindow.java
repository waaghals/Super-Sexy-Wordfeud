package nl.avans.min04sob.mvcvoorbeeld;

import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import nl.avans.min04sob.scrabble.core.CoreLayout;
import nl.avans.min04sob.scrabble.core.CoreWindow;

public class MainWindow extends CoreWindow {

	private JMenu gameMenu;
	private JMenu accountMenu;
	private JMenu challengeMenu;
	private JMenu moderaterMenu;

	private void createAccountMenu() {
		// Create file menu
		accountMenu = new JMenu("Account");
		accountMenu.setMnemonic('A');

		// Create file menu items
		JMenuItem changePassItem = new JMenuItem("Wachtwoord veranderen");
		changePassItem.setMnemonic('C');
		changePassItem.setEnabled(true);
		
		JMenuItem logoutItem = new JMenuItem("Logout");
		logoutItem.setMnemonic('x');
		logoutItem.setEnabled(true);
		
		JLabel accountText = new JLabel("Ingelogd als:");
		JLabel accountName = new JLabel("Patrick");
		
		
		// Add to menu
		accountMenu.add(changePassItem);
		accountMenu.add(logoutItem);
		accountMenu.addSeparator();
		accountMenu.add(accountText);
		accountMenu.add(accountName);
	}

	public MainWindow() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Hoofdscherm");
		setLayout(new CoreLayout(30, 20));
		createAccountMenu();
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(accountMenu);
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Automatisch gegenereerde methodestub

	}

}
