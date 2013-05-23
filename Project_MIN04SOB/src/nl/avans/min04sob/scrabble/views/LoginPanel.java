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
import net.miginfocom.swing.MigLayout;
//GUI checked
public class LoginPanel extends CorePanel {

	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton, registerButton;

	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
	}

	public LoginPanel() {
		setPreferredSize(new Dimension(376, 86));
		setLayout(new MigLayout("", "[65px][20px][30px][][115px][][96.00][]", "[20px][][20px][23px]"));
		usernameLabel = new JLabel("Username :");
		add(usernameLabel, "cell 0 0,alignx center,aligny center");
		usernameField = new JTextField();
		add(usernameField, "cell 2 0 3 1,growx,aligny center");
		registerButton = new JButton("Go to register");
		add(registerButton, "cell 6 0 2 4,grow");
		passwordLabel = new JLabel("Password :");
		add(passwordLabel, "cell 0 2,alignx center,aligny center");
		passwordField = new JPasswordField();
		add(passwordField, "cell 2 2 3 1,growx,aligny center");
		loginButton = new JButton("Login");
		add(loginButton, "cell 4 3,growx,aligny center");
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
	
	public void setUsernameMistake(boolean mistake){
		if(mistake){
			usernameField.setBackground(Color.WHITE);
		}else{
			usernameField.setBackground(Color.RED);
		}
	}
	
	public void setPasswordMistake(boolean mistake){
		if(mistake){
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
