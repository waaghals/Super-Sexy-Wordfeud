package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import nl.avans.min04sob.scrabble.core.CorePanel;

public class LoginPanel extends CorePanel {

	private JLabel result, usernameLabel, passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton, registerButton;

	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
	}

	public LoginPanel() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setPreferredSize(new Dimension(200,300));
		
		result = new JLabel("");
		c.gridx = 0;
		c.gridy = 0;
		add(result, c);

		usernameLabel = new JLabel("Username :");
		c.gridx = 0;
		c.gridy = 1;
		add(usernameLabel, c);

		usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(150,25));
		c.gridx = 0;
		c.gridy = 2;
		add(usernameField, c);

		passwordLabel = new JLabel("Password :");
		c.gridx = 0;
		c.gridy = 3;
		add(passwordLabel, c);

		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(150,25));
		c.gridx = 0;
		c.gridy = 4;
		add(passwordField, c);

		loginButton = new JButton("Login");
		c.gridx = 0;
		c.gridy = 5;
		add(loginButton, c);

		registerButton = new JButton("Register");
		c.gridx = 0;
		c.gridy = 6;
		add(registerButton, c);
	}

	public void setResult(String text) {
		result.setText(text);
	}
	
	public String getUsername(){
		return usernameField.getText();
	}

	public char[] getPassword(){
		return passwordField.getPassword();
	}
	
	public void addActionListenerLogin(ActionListener listener){
		loginButton.addActionListener(listener);
	}
	
	public void addActionListenerRegister(ActionListener listener){
		registerButton.addActionListener(listener);
	}
}
