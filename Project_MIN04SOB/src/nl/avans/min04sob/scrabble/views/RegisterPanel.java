package nl.avans.min04sob.scrabble.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.Role;

//GUI checked
public class RegisterPanel extends CorePanel {

	private JLabel usernameLabel, passwordLabel1, passwordLabel2, usernameResult, password1Result, password2Result,roleResult;
	private JTextField usernameField;
	private JPasswordField passwordField1, passwordField2;
	private JButton cancelButton, registerButton;
	private JComboBox<Role> comboBox;

	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
	
	public RegisterPanel(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30};
		gridBagLayout.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		setLayout(gridBagLayout);
		setPreferredSize(new Dimension(390,170));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 5, 5);
		usernameLabel = new JLabel("Username :");
		c.gridx = 0;
		c.gridy = 0;
		add(usernameLabel, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 5;
		c.insets = new Insets(0, 0, 5, 5);
		usernameField = new JTextField();
		c.gridx = 3;
		c.gridy = 0;
		add(usernameField, c);
		
		c = new GridBagConstraints();
		c.gridwidth = 5;
		c.insets = new Insets(0, 0, 5, 0);
		usernameResult = new JLabel("");
		c.gridx = 8;
		c.gridy = 0;
		add(usernameResult, c); 
		
		c = new GridBagConstraints();
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 5, 5);
		passwordLabel1 = new JLabel("Password :");
		c.gridx = 0;
		c.gridy = 1;
		add(passwordLabel1, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 5;
		c.insets = new Insets(0, 0, 5, 5);
		passwordField1 = new JPasswordField();
		c.gridx = 3;
		c.gridy = 1;
		add(passwordField1, c);
		
		c = new GridBagConstraints();
		c.gridwidth = 5;
		c.insets = new Insets(0, 0, 5, 0);
		password1Result = new JLabel("");
		c.gridx = 8;
		c.gridy = 1;
		add(password1Result, c); 
		
		c = new GridBagConstraints();
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 5, 5);
		passwordLabel2 = new JLabel("Confirm :");
		c.gridx = 0;
		c.gridy = 2;
		add(passwordLabel2, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 5;
		c.insets = new Insets(0, 0, 5, 5);
		passwordField2 = new JPasswordField();
		c.gridx = 3;
		c.gridy = 2;
		add(passwordField2, c);
		
		c = new GridBagConstraints();
		c.gridwidth = 5;
		c.insets = new Insets(0, 0, 5, 0);
		password2Result = new JLabel("");
		c.gridx = 8;
		c.gridy = 2;
		add(password2Result, c); 
		/////////////////////////////////////
		c = new GridBagConstraints();
		c.gridwidth = 3;
		c.insets = new Insets(0, 0, 5, 5);
		c.gridx = 0;
		c.gridy = 3;
		add(new JLabel("role :"), c);
		
		comboBox = new JComboBox<Role>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 5;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 3;
		add(comboBox, gbc_comboBox);
		for (Role role : Role.values()) {
			comboBox.addItem(role);
		}
		
		
		///////////////////////////
		c = new GridBagConstraints();
		c.gridwidth = 5;
		c.insets = new Insets(0, 0, 5, 0);
		roleResult = new JLabel("");
		c.gridx = 8;
		c.gridy = 3;
		add(roleResult, c); 
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
		c.insets = new Insets(0, 5, 0, 5);
		cancelButton = new JButton("Cancel");
		c.gridx = 0;
		c.gridy = 4;
		add(cancelButton, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
		c.insets = new Insets(0, 5, 0, 5);
		registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		c.gridx = 4;
		c.gridy = 4;
		add(registerButton, c);
	}
	
	public void addActionListenerCancel(ActionListener listener){
		cancelButton.addActionListener(listener);
	}
	
	public void addActionListenerRegister(ActionListener listener){
		registerButton.addActionListener(listener);
	}
	
	public void addKeyListenerPassword(KeyAdapter listener) {
		passwordField2.addKeyListener(listener);
	}
	
	public void setUsernameMistake(boolean good, String discription){
		if(good){
			usernameField.setBackground(Color.WHITE);
			usernameResult.setText("");
		}else{
			usernameField.setBackground(Color.RED);
			usernameResult.setText(discription);
		}
	}
	//////////////////////////////////////////////////	
	public void setPassword1Mistake(boolean good, String discription){
		if(good){
			passwordField1.setBackground(Color.WHITE);
			password1Result.setText("");			
		}else{
			passwordField1.setBackground(Color.RED);
			password1Result.setText(discription);
		}
	}
	
	public void setPassword2Mistake(boolean good, String discription){
		if(good){
			passwordField2.setBackground(Color.WHITE);
			password2Result.setText("");
		}else{
			passwordField2.setBackground(Color.RED);
			password2Result.setText(discription);
		}
	}
	
	public void clearFields(){
		usernameField.setText("");
		passwordField1.setText("");
		passwordField2.setText("");
		setUsernameMistake(true, "");
		setPassword1Mistake(true, "");
		setPassword2Mistake(true, "");
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
	
	public Role getRole(){
		return (Role) comboBox.getSelectedItem();
	}

}
