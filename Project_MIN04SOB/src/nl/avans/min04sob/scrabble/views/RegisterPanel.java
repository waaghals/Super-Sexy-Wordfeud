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
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

//GUI checked
public class RegisterPanel extends CorePanel {

	private JLabel usernameLabel, passwordLabel1, passwordLabel2;
	private JTextField usernameField;
	private JPasswordField passwordField1, passwordField2;
	private JButton cancelButton, registerButton;
	private JComboBox<Role> roleBox;
	private JLabel errorLabel;

	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
	
	public RegisterPanel(){
		setPreferredSize(new Dimension(636, 170));
		setLayout(new MigLayout("", "[][1px][165.00px][20px:50px][115px][][125.00]", "[][19px][19px][19px][24px][25px]"));
		usernameLabel = new JLabel("Gebruikers naam");
		usernameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		add(usernameLabel, "cell 1 1,alignx right,aligny center");
		usernameField = new JTextField();
		add(usernameField, "cell 2 1 3 1,growx,aligny center");
		passwordLabel1 = new JLabel("Wachtwoord");
		add(passwordLabel1, "cell 1 2,alignx right,aligny center");
		passwordField1 = new JPasswordField();
		add(passwordField1, "cell 2 2 3 1,growx,aligny center");
		
		errorLabel = new JLabel("");
		add(errorLabel, "cell 5 2 2 1");
		passwordLabel2 = new JLabel("Herhaal wachtwoord");
		add(passwordLabel2, "cell 1 3,alignx right,aligny center");
		passwordField2 = new JPasswordField();
		add(passwordField2, "cell 2 3 3 1,growx,aligny center");
		JLabel label = new JLabel("Rol");
		add(label, "cell 1 4,alignx right,aligny center");
		
		roleBox = new JComboBox<Role>();
		add(roleBox, "cell 2 4 3 1,growx,aligny center");
		for (Role role : Role.values()) {
			roleBox.addItem(role);
		}
		registerButton = new JButton("Registreren");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(registerButton, "cell 2 5,growx,aligny center");
		cancelButton = new JButton("Annuleren");
		add(cancelButton, "cell 4 5,growx,aligny center");
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
			errorLabel.setText("");
		}else{
			usernameField.setBackground(Color.RED);
			errorLabel.setText(discription);
		}
	}
	//////////////////////////////////////////////////	
	public void setPassword1Mistake(boolean good, String discription){
		if(good){
			passwordField1.setBackground(Color.WHITE);
			errorLabel.setText("");			
		}else{
			passwordField1.setBackground(Color.RED);
			errorLabel.setText(discription);
		}
	}
	
	public void setPassword2Mistake(boolean good, String discription){
		if(good){
			passwordField2.setBackground(Color.WHITE);
			errorLabel.setText("");
		}else{
			passwordField2.setBackground(Color.RED);
			errorLabel.setText(discription);
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
		return (Role) roleBox.getSelectedItem();
	}

}
