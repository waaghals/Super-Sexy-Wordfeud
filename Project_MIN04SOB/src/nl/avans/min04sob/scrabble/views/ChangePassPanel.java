package nl.avans.min04sob.scrabble.views;

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
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWeights = new double[] {0.0, 0.0, 0.0,0.0, 0.0, 0.0, 0.0, 0.0};
		gbl.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30, 30};
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
		oldPass = new JPasswordField();
		c.gridwidth = 4;
		c.gridx = 4;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 0);
		add(oldPass, c);
	}

	
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
}