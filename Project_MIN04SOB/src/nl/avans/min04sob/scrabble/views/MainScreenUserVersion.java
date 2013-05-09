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

import nl.avans.min04sob.scrabble.core.CorePanel;

public class MainScreenUserVersion extends CorePanel{
	
	private final String username;
	private final boolean admin;
	private JLabel usernameRow, usernameLabel;
	private JButton logout;
	
	public MainScreenUserVersion(String username, boolean admin){
		this.username = username;
		this.admin = admin;
		
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		setLayout(gbl);
		setPreferredSize(new Dimension(600,390));
		
		GridBagConstraints c = new GridBagConstraints();
		usernameRow = new JLabel("ingelogt als :");
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(usernameRow, c);
		
		c = new GridBagConstraints();
		usernameLabel = new JLabel(username);
		c.gridwidth = 2;
		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(usernameLabel, c);
		
		c = new GridBagConstraints();
		logout = new JButton("Logout");
		c.gridwidth = 3;
		c.gridx = 17;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(logout, c);
		
		c = new GridBagConstraints();
		JTabbedPane options = new JTabbedPane();
		options.add("Games", new JPanel(){
			
		});
	}
	
	
	
	
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
}
