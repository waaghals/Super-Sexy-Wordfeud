package nl.avans.min04sob.scrabble.views;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;

import nl.avans.min04sob.scrabble.core.mvc.CorePanel;

public class TotalPanel extends CorePanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4725292255883443586L;

	public TotalPanel(){
		setLayout(new BorderLayout());
	}
	
	public void addCenterPanel(JPanel panel){
		add(panel, BorderLayout.CENTER);
	}
	
	public void addRightPanel(JPanel panel){
		add(panel, BorderLayout.EAST);
	}
	
	public void addTopPanel(JPanel panel){
		add(panel, BorderLayout.NORTH);
	}
	
	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
	}
	
}
