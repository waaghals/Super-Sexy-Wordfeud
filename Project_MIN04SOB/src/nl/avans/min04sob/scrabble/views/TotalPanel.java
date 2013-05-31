package nl.avans.min04sob.scrabble.views;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

import nl.avans.min04sob.scrabble.core.CorePanel;

public class TotalPanel extends CorePanel{
	
	public TotalPanel(){
		setLayout(new BorderLayout());
	}
	
	public void addTopPanel(JPanel panel){
		add(panel, BorderLayout.NORTH);
	}
	
	public void addCenterPanel(JPanel panel){
		add(panel, BorderLayout.CENTER);
	}
	
	public void addRightPanel(JPanel panel){
		add(panel, BorderLayout.EAST);
	}
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
	
}
