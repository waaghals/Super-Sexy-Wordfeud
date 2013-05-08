package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
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
import java.awt.Insets;

public class LoginPanel extends CorePanel {

	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton, registerButton;

	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
	}

	public LoginPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30 };
		gridBagLayout.columnWidths = new int[] { 30, 30, 30, 30, 30, 30, 30, 30 };
		setLayout(gridBagLayout);
		setPreferredSize(new Dimension(240, 90));

		GridBagConstraints b = new GridBagConstraints();
		b.gridwidth = 3;
		b.insets = new Insets(0, 0, 5, 5);
		usernameLabel = new JLabel("Username :");
		b.gridx = 0;
		b.gridy = 0;
		add(usernameLabel, b);

		GridBagConstraints d = new GridBagConstraints();
		d.fill = GridBagConstraints.HORIZONTAL;
		d.gridwidth = 5;
		d.insets = new Insets(0, 0, 5, 5);
		usernameField = new JTextField();

		d.gridx = 3;
		d.gridy = 0;
		add(usernameField, d);

		GridBagConstraints e = new GridBagConstraints();
		e.gridwidth = 3;
		e.insets = new Insets(0, 0, 5, 5);
		passwordLabel = new JLabel("Password :");
		e.gridx = 0;
		e.gridy = 1;
		add(passwordLabel, e);

		GridBagConstraints f = new GridBagConstraints();
		f.fill = GridBagConstraints.HORIZONTAL;
		f.gridwidth = 5;
		f.insets = new Insets(0, 0, 5, 5);
		passwordField = new JPasswordField();

		f.gridx = 3;
		f.gridy = 1;
		add(passwordField, f);

		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.HORIZONTAL;
		g.gridwidth = 4;
		g.insets = new Insets(0, 5, 0, 0);
		loginButton = new JButton("Login");
		g.gridx = 0;
		g.gridy = 2;
		add(loginButton, g);

		GridBagConstraints h = new GridBagConstraints();
		h.insets = new Insets(0, 0, 0, 5);
		h.fill = GridBagConstraints.HORIZONTAL;
		h.gridwidth = 4;
		registerButton = new JButton("Register");
		h.gridx = 4;
		h.gridy = 2;
		add(registerButton, h);
	}

	public String getUsername() {
		return usernameField.getText();
	}

	public char[] getPassword() {
		return passwordField.getPassword();
	}

	public void addActionListenerLogin(ActionListener listener) {
		loginButton.addActionListener(listener);
	}

	public void addActionListenerRegister(ActionListener listener) {
		registerButton.addActionListener(listener);
	}
	
	public void setUsernameMistake(boolean good){
		if(good){
			usernameField.setBackground(Color.WHITE);
		}else{
			usernameField.setBackground(Color.RED);
		}
	}
	
	public void setPasswordMistake(boolean good){
		if(good){
			passwordField.setBackground(Color.WHITE);
		}else{
			passwordField.setBackground(Color.RED);
		}
	}
	
	public void clearFields(){
		usernameField.setText("");
		setUsernameMistake(true);
		passwordField.setText("");
		setPasswordMistake(true);
	}
	
	public void setUsernameField(String username){
		usernameField.setText(username);
	}
}
