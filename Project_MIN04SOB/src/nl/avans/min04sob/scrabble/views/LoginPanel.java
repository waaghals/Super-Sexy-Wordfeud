package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.mvc.CorePanel;
//GUI checked
public class LoginPanel extends CorePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2991196407210657700L;
	private JLabel usernameLabel, passwordLabel;
	private JButton loginButton, registerButton;
	private JTextField usernameField;
	private JPasswordField passwordField;

	public LoginPanel() {
		setPreferredSize(new Dimension(507, 117));
		setLayout(new MigLayout("", "[65px][50px:100px:100px,grow][100px:n]", "[20px][23px][]"));
		usernameLabel = new JLabel("Gebruikersnaam :");
		add(usernameLabel, "cell 0 0,alignx trailing,aligny center");
		
		usernameField = new JTextField();
		add(usernameField, "cell 1 0 2 1,growx");
		usernameField.setColumns(10);
		passwordLabel = new JLabel("Wachtwoord :");
		add(passwordLabel, "cell 0 1,alignx trailing,aligny center");
		
		passwordField = new JPasswordField();
		add(passwordField, "cell 1 1 2 1,growx");
		loginButton = new JButton("Login");
		add(loginButton, "cell 1 2,aligny center");
		registerButton = new JButton("Ik heb nog geen account");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(registerButton, "cell 2 2");
		//add(registerButton, "cell 5 2 2 1,grow");
	}

	public void addActionListenerLogin(ActionListener listener) {
		loginButton.addActionListener(listener);
	}

	
	public void addActionListenerRegister(ActionListener listener) {
		registerButton.addActionListener(listener);
	}
	
	public void addKeyListenerPassword(KeyAdapter listener) {
		passwordField.addKeyListener(listener);
	}

	public void addKeyListenerUsername(KeyAdapter listener) {
		usernameField.addKeyListener(listener);
	}

	public void clearFields(){
		usernameField.setText("");
		setUsernameMistake(true);
		passwordField.setText("");
		setPasswordMistake(true);
	}
	
	public char[] getPassword() {
		return passwordField.getPassword();
	}
	
	public JTextField getPasswordField() {
		return passwordField;
	}
	
	public String getUsername() {
		return usernameField.getText();
	}
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
	}
	
	public void setPasswordMistake(boolean mistake){
		if(mistake){
			passwordField.setBackground(Color.WHITE);
		}else{
			passwordField.setBackground(Color.RED);
		}
	}
	
	public void setUsernameField(String username){
		usernameField.setText(username);
	}
	
	public void setUsernameMistake(boolean mistake){
		if(mistake){
			usernameField.setBackground(Color.WHITE);
		}else{
			usernameField.setBackground(Color.RED);
		}
	}
}
