package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.views.LoginPanel;
import nl.avans.min04sob.scrabble.views.RegisterPanel;

public class LoginController extends CoreController {

	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private AccountModel accountModel;
	private JFrame frame;
	private final int maxpass_userLength, minpass_userLength;

	public LoginController(AccountModel account) {

		maxpass_userLength = 11;
		minpass_userLength = 5;

		frame = new JFrame();

		loginPanel = new LoginPanel();
		accountModel = account;
		registerPanel = new RegisterPanel();

		frame.add(loginPanel);

		addView(registerPanel);
		addView(loginPanel);
		addModel(accountModel);

		loginPanel.addActionListenerLogin(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkLogin();
			}
		});

		loginPanel.addActionListenerRegister(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginToRegister();
			}
		});

		registerPanel.addActionListenerCancel(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerToLogin();
			}
		});

		registerPanel.addActionListenerRegister(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryToRegister();
				registerToLogin();
			}
		});

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private void checkLogin() {
		accountModel.login(loginPanel.getUsername(), loginPanel.getPassword());
		if (!accountModel.isLoggedIn()) {
			loginPanel.setUsernameMistake(false);
			loginPanel.setPasswordMistake(false);
		} else {
			frame.dispose();
			frame = null;
		}

	}

	private void tryToRegister() {
		boolean username = validateUsername();
		boolean pass1 = validatePassword1();
		boolean pass2 = validatePassword2();

		if (username && pass1 && pass2) {
			AccountModel.registerAccount(registerPanel.getUsername(),
					registerPanel.getPassword1());
		}

	}

	private int validateLength(int length) {
		if (length < minpass_userLength) {
			return -1;
		} else if (length > maxpass_userLength) {
			return 1;
		} else {
			return 0;
		}
	}

	private boolean validateUsername() {
		if (validateLength(registerPanel.getUsername().length()) == -1) {
			registerPanel.setUsernameMistake(false, "To short");
			return false;
		} else if (validateLength(registerPanel.getUsername().length()) == 1) {
			registerPanel.setUsernameMistake(false, "To long");
			return false;
		} else {
			if (AccountModel.checkUsernameAvailable(registerPanel.getUsername())) {
				registerPanel.setUsernameMistake(true, "");
				return true;
			} else {
				registerPanel.setUsernameMistake(false, "Already used");
				return false;
			}
		}

	}

	private boolean validatePassword1() {
		if (validateLength(registerPanel.getPassword1().length) == -1) {
			registerPanel.setPassword1Mistake(false, "To short");
			return false;
		} else if (validateLength(registerPanel.getPassword1().length) == 1) {
			registerPanel.setPassword1Mistake(false, "To long");
			return false;
		} else {
			registerPanel.setPassword1Mistake(true, "");
			return true;
		}
	}

	private boolean validatePassword2() {
		if (validateLength(registerPanel.getPassword2().length) == -1) {
			registerPanel.setPassword2Mistake(false, "To short");
			return false;
		} else if (validateLength(registerPanel.getPassword2().length) == 1) {
			registerPanel.setPassword2Mistake(false, "To long");
			return false;
		} else {
			if (Arrays.equals(registerPanel.getPassword1(),
					registerPanel.getPassword2())) {
				registerPanel.setPassword2Mistake(true, "");
				return true;
			} else {
				registerPanel.setPassword2Mistake(false, "doesn't match");
				return false;
			}
		}
	}

	private void loginToRegister() {
		frame.remove(loginPanel);
		frame.add(registerPanel);
		registerPanel.clearFields();
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}

	private void registerToLogin() {
		frame.remove(registerPanel);
		loginPanel.clearFields();
		frame.add(loginPanel);
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}

	@Override
	public void initialize() {
		// TODO Automatisch gegenereerde methodestub
		
	}

	@Override
	public void addListeners() {
		// TODO Automatisch gegenereerde methodestub
		
	}
}
