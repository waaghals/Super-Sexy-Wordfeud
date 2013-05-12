package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.AccountModel;

public class UserInfoPanel extends CorePanel{
	
	private JLabel usernameRow, usernameLabel;
	private JButton logout, changePass, adminButton;
	
	public UserInfoPanel(){
		
		GridBagLayout gbl = new GridBagLayout();
		setPreferredSize(new Dimension(630,30));
		gbl.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl.rowHeights = new int[] {30};
		setLayout(gbl);
		
		GridBagConstraints c = new GridBagConstraints();
		usernameRow = new JLabel("ingelogt als :");
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(usernameRow, c);
		
		c = new GridBagConstraints();
		usernameLabel = new JLabel("");
		c.gridwidth = 2;
		c.gridx = 4;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(usernameLabel, c);
		
		c = new GridBagConstraints();
		logout = new JButton("Logout");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;
		c.gridx = 17;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 5);
		add(logout, c);
		
		c = new GridBagConstraints();
		changePass = new JButton("Changepass");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 4;		
		c.gridx = 13;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 5);
		add(changePass, c);
		
		c = new GridBagConstraints();
		adminButton = new JButton("To adminpanel");
		c.gridwidth = 4;
		c.gridx = 9;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 5);
		add(adminButton, c);
		
	}
	
	public void setUsername(String username){
		usernameLabel.setText(username);
	}
	
	public void addActionListenerLogout(ActionListener listener){
		logout.addActionListener(listener);
	}
	
	public void addActionListenerChangePass(ActionListener listener){
		changePass.addActionListener(listener);
	}
	
	public void addActionListenerAdmin(ActionListener listener){
		adminButton.addActionListener(listener);
	}
	
	public void modelPropertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "login":
			AccountModel user = (AccountModel) evt.getNewValue();
			usernameLabel.setText(user.getUsername());
			break;

		default:
			break;
		}
		revalidate();
	}
}
