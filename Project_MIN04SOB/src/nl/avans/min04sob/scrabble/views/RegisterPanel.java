package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.Role;
import nl.avans.min04sob.scrabble.core.mvc.CorePanel;
import javax.swing.JCheckBox;

//GUI checked
public class RegisterPanel extends CorePanel {

	private JLabel usernameLabel, passwordLabel1, passwordLabel2;
	private JTextField usernameField;
	private JPasswordField passwordField1, passwordField2;
	private JButton cancelButton, registerButton;
	private JCheckBox observerCheck;
	private JCheckBox playerCheck;
	private JCheckBox administratorCheck;
	private JCheckBox moderatorCheck;
	private JLabel errorLabel;

	public RegisterPanel() {
		setPreferredSize(new Dimension(584, 266));
		setLayout(new MigLayout("", "[][1px][165.00px][20px:50px][115px][]", "[][19px][19px][19px][][][][24px][25px]"));
		
		errorLabel = new JLabel();
		add(errorLabel, "cell 2 0");
		usernameLabel = new JLabel("Gebruikers naam");
		usernameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		add(usernameLabel, "cell 1 1,alignx right,aligny center");
		usernameField = new JTextField();
		add(usernameField, "cell 2 1 3 1,growx,aligny center");
		passwordLabel1 = new JLabel("Wachtwoord");
		add(passwordLabel1, "cell 1 2,alignx right,aligny center");
		passwordField1 = new JPasswordField();
		add(passwordField1, "cell 2 2 3 1,growx,aligny center");
		passwordLabel2 = new JLabel("Herhaal wachtwoord");
		add(passwordLabel2, "cell 1 3,alignx right,aligny center");
		passwordField2 = new JPasswordField();
		add(passwordField2, "cell 2 3 3 1,growx,aligny center");
		JLabel label = new JLabel("Rol");
		add(label, "cell 1 4,alignx right,aligny center");
		registerButton = new JButton("Registreren");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		observerCheck = new JCheckBox("Observer");
		add(observerCheck, "cell 2 4");

		playerCheck = new JCheckBox("Speler");
		add(playerCheck, "cell 2 5");

		moderatorCheck = new JCheckBox("Moderator");
		add(moderatorCheck, "cell 2 6");

		administratorCheck = new JCheckBox("Administrator");
		add(administratorCheck, "cell 2 7");

		add(registerButton, "cell 2 8,growx,aligny center");
		cancelButton = new JButton("Annuleren");
		add(cancelButton, "cell 4 8,growx,aligny center");
	}

	public void addActionListenerCancel(ActionListener listener) {
		cancelButton.addActionListener(listener);
	}

	public void addActionListenerRegister(ActionListener listener) {
		registerButton.addActionListener(listener);
	}

	public void addKeyListenerPassword1(KeyAdapter listener) {
		passwordField1.addKeyListener(listener);
	}

	public void addKeyListenerPassword2(KeyAdapter listener) {
		passwordField2.addKeyListener(listener);
	}

	public void addKeyListenerUsername(KeyAdapter listener) {
		usernameField.addKeyListener(listener);
	}

	public void clearFields() {
		usernameField.setText("");
		passwordField1.setText("");
		passwordField2.setText("");
		setUsernameMistake(true, "");
		setPassword1Mistake(true, "");
		setPassword2Mistake(true, "");
	}

	public char[] getPassword1() {
		return passwordField1.getPassword();
	}

	public char[] getPassword2() {
		return passwordField2.getPassword();
	}

	public JPasswordField getPasswordField1() {
		return passwordField1;
	}

	public JPasswordField getPasswordField2() {
		return passwordField2;
	}

	public Role[] getRoles() {
		ArrayList<Role> roles = new ArrayList<Role>();
		if (observerCheck.isSelected()) {
			roles.add(Role.OBSERVER);
		}
		if (playerCheck.isSelected()) {
			roles.add(Role.PLAYER);
		}
		if (moderatorCheck.isSelected()) {
			roles.add(Role.MODERATOR);
		}
		if (administratorCheck.isSelected()) {
			roles.add(Role.ADMINISTRATOR);
		}
		return roles.toArray(new Role[roles.size()]);
	}

	public String getUsername() {
		return usernameField.getText();
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}

	// ////////////////////////////////////////////////
	public void setPassword1Mistake(boolean good, String discription) {
		if (good) {
			passwordField1.setBackground(Color.WHITE);
			errorLabel.setText("");
		} else {
			passwordField1.setBackground(Color.RED);
			errorLabel.setText(discription);
		}
	}

	public void setPassword2Mistake(boolean good, String discription) {
		if (good) {
			passwordField2.setBackground(Color.WHITE);
			errorLabel.setText("");
		} else {
			passwordField2.setBackground(Color.RED);
			errorLabel.setText(discription);
		}
	}

	public void setUsernameMistake(boolean good, String discription) {
		if (good) {
			usernameField.setBackground(Color.WHITE);
			errorLabel.setText("");
		} else {
			usernameField.setBackground(Color.RED);
			errorLabel.setText(discription);
		}
	}

}
