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

public class RegisterPanel extends CorePanel {

	private JLabel result, usernameLabel, passwordLabel1, passwordLabel2;
	private JTextField usernameField;
	private JPasswordField passwordField1, passwordField2;
	private JButton cancelButton, registerButton;

	public void modelPropertyChange(PropertyChangeEvent evt) {
	}

	public RegisterPanel(){
		this.setPreferredSize(new Dimension(200,300));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		result = new JLabel("");
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		add(result,c);
		
		usernameLabel = new JLabel("Username :");
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		add(usernameLabel,c);
		
		usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(125,25));
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		add(usernameField,c);
		
		passwordLabel1 = new JLabel("Password :");
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 3;
		add(passwordLabel1,c);
		
		passwordField1 = new JPasswordField();
		passwordField1.setPreferredSize(new Dimension(125,25));
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 4;
		add(passwordField1,c);
		
		passwordLabel2 = new JLabel("Password :");
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 5;
		add(passwordLabel2, c);
		
		passwordField2 = new JPasswordField();
		passwordField2.setPreferredSize(new Dimension(125,25));
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 6;
		add(passwordField2,c);
		
		cancelButton = new JButton("Cancel");
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 7;
		add(cancelButton, c);
		
		registerButton = new JButton("Register");
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 7;
		add(registerButton,c);
		
	}
	
	public void addActionListenerCancel(ActionListener listener){
		cancelButton.addActionListener(listener);
	}
	
	public void addActionListenerRegister(ActionListener listener){
		registerButton.addActionListener(listener);
	}
	
	public void setResult(String text){
		result.setText(text);
	}
	
	public String getUsername(){
		return usernameField.getText();
	}

	public char[] getPassword1(){
		return passwordField1.getPassword();
	}
	
	public char[] getPassword2(){
		return passwordField2.getPassword();
	}
}
