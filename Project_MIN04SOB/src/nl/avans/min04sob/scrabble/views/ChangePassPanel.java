package nl.avans.min04sob.scrabble.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;
import javax.swing.JPasswordField;

import nl.avans.min04sob.scrabble.core.CorePanel;

public class ChangePassPanel extends CorePanel{
	
	private JLabel oldPassLabel, newPass1Label, newPass2Label, oldPassResult, newPass1Result, newPass2Result;
	private JPasswordField oldPass, newPass1, newPass2; 
	
	public ChangePassPanel(){
		setPreferredSize(new Dimension(365,180));
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl.rowHeights = new int[]{30, 30, 30, 30, 30, 30};
		setLayout(gbl);
		
		GridBagConstraints c = new GridBagConstraints();
		oldPassLabel = new JLabel("Old password :");
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(oldPassLabel, c);
		
		c = new GridBagConstraints();
		oldPass = new JPasswordField(11);
		c.gridwidth = 4;
		c.gridx = 4;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(oldPass, c);
		
		c = new GridBagConstraints();
		oldPassResult = new JLabel("");
		c.gridwidth = 4;
		c.gridx = 8;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(oldPassResult, c);
		
		c = new GridBagConstraints();
		newPass1Label = new JLabel("New pass :");
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		add(newPass1Label, c);
		
		c = new GridBagConstraints();
		newPass1 = new JPasswordField(11);
		c.gridwidth = 4;
		c.gridx = 4;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		add(newPass1, c);
		
		c = new GridBagConstraints();
		newPass1Result = new JLabel("");
		c.gridwidth = 4;
		c.gridx = 8;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		add(newPass1Result, c);
		
		newPass2Label = new JLabel("Confirm :");
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 0);
		add(newPass2Label, c);
		
		c = new GridBagConstraints();
		newPass2 = new JPasswordField(11);
		c.gridwidth = 4;
		c.gridx = 4;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 0);
		add(newPass2, c);
		
		c = new GridBagConstraints();
		newPass2Result = new JLabel("");
		c.gridwidth = 4;
		c.gridx = 8;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 0);
		add(newPass2Result, c);
	}

	
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
}