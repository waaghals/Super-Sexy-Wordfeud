package nl.avans.min04sob.scrabble.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import nl.avans.min04sob.scrabble.core.CoreController;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.views.ChangePassPanel;
import nl.avans.min04sob.scrabble.views.LoginPanel;
import nl.avans.min04sob.scrabble.views.RegisterPanel;

public class AccountController extends CoreController {

	private LoginPanel loginPanel;
	private RegisterPanel registerPanel;
	private AccountModel accountModel;
	private ChangePassPanel changepassPanel;
	private JFrame frame;
	private final int maxpass_userLength, minpass_userLength;

	public AccountController(AccountModel account) {

		maxpass_userLength = 11;
		minpass_userLength = 5;

		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		loginPanel = new LoginPanel();
		accountModel = account;
		registerPanel = new RegisterPanel();
		changepassPanel = new ChangePassPanel();

		frame.add(loginPanel);

		addView(registerPanel);
		addView(loginPanel);
		addView(changepassPanel);
		addModel(accountModel);

		loginPanel.addActionListenerLogin(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkLogin();
			}
		});

		loginPanel.addActionListenerRegister(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginToRegister();
			}
		});

		loginPanel.addKeyListenerPassword(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					checkLogin();
				}
			}
		});
		
		loginPanel.addKeyListenerUsername(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginPanel.getPasswordField().requestFocus();
				}
			}
		});

		registerPanel.addKeyListenerPassword2(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					tryToRegister();
				}
			}
		});

		registerPanel.addKeyListenerUsername(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					registerPanel.getPasswordField1().requestFocus();
				}
			}
		});
		
		registerPanel.addKeyListenerPassword1(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					registerPanel.getPasswordField2().requestFocus();
				}
			}
		});
		
		
		registerPanel.addActionListenerCancel(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerToLogin();
			}
		});

		registerPanel.addActionListenerRegister(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tryToRegister();
			}
		});

		changepassPanel.addCancelActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		changepassPanel.addKeyListenerOldPass(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					changepassPanel.get1NewPass().requestFocus();
				}
			}
		});
		
		changepassPanel.addKeyListenerNewPass1(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					changepassPanel.get2NewPass().requestFocus();
				}
			}
		});
		
		changepassPanel.addKeyListenerNewPass2(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					changePass();
				}
			}
		});

		changepassPanel.addChangeActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});

		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void addListeners() {
		// TODO Automatisch gegenereerde methodestub

	}

	public void changePass() {
		String oldpass = changepassPanel.getOldPass();
		String newpass1 = changepassPanel.getNewPass1();
		String newpass2 = changepassPanel.getNewPass2();

		if (oldpass.equals(accountModel.getpass())) {
			if (validateLength(newpass1.length()) == -1) {
				changepassPanel.setNewPass1Good(false, "Te Kort");
			} else if (validateLength(newpass1.length()) == 1) {
				changepassPanel.setNewPass1Good(false, "Te Lang");
			} else {
				if (newpass2.equals(newpass1)) {
					accountModel.changePass(changepassPanel.getNewPass1());
					changepassPanel.setOldPassGood(true, "");
					changepassPanel.setNewPass1Good(true, "");
					changepassPanel.setNewPass2Good(true, "");
					changepassPanel.passwordChange();
					frame.dispose();
				} else {
					changepassPanel.setNewPass2Good(false, "Verkeerd");
				}
			}
		} else {
			changepassPanel.setOldPassGood(false, "Fout");
		}
	}

	private void checkLogin() {
		accountModel.login(loginPanel.getUsername(), loginPanel.getPassword());
		 
		if (!accountModel.isLoggedIn()) {
			loginPanel.setUsernameMistake(false);
			loginPanel.setPasswordMistake(false);
		}
		else {
			frame.dispose();
			frame = null;
		}

	}

	public ChangePassPanel getchangepasspanel() {
		return changepassPanel;
	}

	@Override
	public void initialize() {
		// TODO Automatisch gegenereerde methodestub

	}

	public void loginToRegister() {
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

	public void setChangePassPanel() {
		frame.remove(loginPanel);
		frame.add(changepassPanel);
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}

	private void tryToRegister() {

		if (validateUsername() && validatePassword1() && validatePassword2()) {
			AccountModel.registerAccount(registerPanel.getUsername(),
					registerPanel.getPassword1(), registerPanel.getRoles());
			registerToLogin();
		}

	}

	private int validateLength(int length) {
		if (length < minpass_userLength) {
			return -1;
		} else if (length > maxpass_userLength) {
			return 1;
		} else {// TODO Automatisch gegenereerde methodestub
			
			return 0;
		}
	}

	private boolean validatePassword1() {
		if (validateLength(registerPanel.getPassword1().length) == -1) {
			registerPanel.setPassword1Mistake(false, "Te kort");
			return false;
		} else if (validateLength(registerPanel.getPassword1().length) == 1) {
			registerPanel.setPassword1Mistake(false, "Te lang");
			return false;
		} else {
			registerPanel.setPassword1Mistake(true, "");
			return true;
		}
	}

	private boolean validatePassword2() {
		if (validateLength(registerPanel.getPassword2().length) == -1) {
			registerPanel.setPassword2Mistake(false, "Te kort");
			return false;
		} else if (validateLength(registerPanel.getPassword2().length) == 1) {
			registerPanel.setPassword2Mistake(false, "Te lang");
			return false;
		} else {
			if (Arrays.equals(registerPanel.getPassword1(),
					registerPanel.getPassword2())) {
				registerPanel.setPassword2Mistake(true, "");
				return true;
			} else {
				registerPanel.setPassword2Mistake(false, "Wachtwoorden komen niet overeen");
				return false;
			}
		}
	}

	private boolean validateUsername() {
		if (validateLength(registerPanel.getUsername().length()) == -1) {
			registerPanel.setUsernameMistake(false, "Te kort");
			return false;
		} else if (validateLength(registerPanel.getUsername().length()) == 1) {
			registerPanel.setUsernameMistake(false, "Te lang");
			return false;
		} else {
			if (AccountModel
					.checkUsernameAvailable(registerPanel.getUsername())) {
				registerPanel.setUsernameMistake(true, "");
				return true;
			} else {
				registerPanel.setUsernameMistake(false, "Al in gebruik");
				return false;
			}
		}

	}
}
