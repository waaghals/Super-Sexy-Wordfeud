package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import nl.avans.min04sob.scrabble.controllers.ChatController;
import nl.avans.min04sob.scrabble.core.CorePanel;

public class MainScreenUserVersion extends CorePanel{
	
	private final String username;
	private final boolean admin;
	private final int playerid;
	private JLabel usernameRow, usernameLabel;
	private JButton logout, changePass, adminButton;
	
	public MainScreenUserVersion(String username, final int  playerid, boolean admin){
		this.username = username;
		this.admin = admin;
		this.playerid = playerid;
		
		GridBagLayout gbl = new GridBagLayout();
		setPreferredSize(new Dimension(650,450));
		gbl.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		setLayout(gbl);
		
		GridBagConstraints c = new GridBagConstraints();
		usernameRow = new JLabel("ingelogt als :");
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(usernameRow, c);
		
		c = new GridBagConstraints();
		usernameLabel = new JLabel(username);
		c.gridwidth = 2;
		c.gridx = 4;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(usernameLabel, c);
		
		if(this.admin){
			c = new GridBagConstraints();
			adminButton = new JButton("To admin");
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridwidth = 3;
			c.gridx = 15;
			c.gridy = 0;
			c.insets = new Insets(0, 0, 0, 5);
			add(adminButton, c);
		}
		
		c = new GridBagConstraints();
		logout = new JButton("Logout");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;
		c.gridx = 18;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 5);
		add(logout, c);
		
		c = new GridBagConstraints();
		changePass = new JButton("Changepass");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 3;		
		c.gridx = 18;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 5);
		add(changePass, c);
		
	}
	
	
	
	
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
}
