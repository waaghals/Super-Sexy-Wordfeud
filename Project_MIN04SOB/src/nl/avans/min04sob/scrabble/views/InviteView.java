package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import nl.avans.min04sob.scrabble.core.CorePanel;

public class InviteView extends CorePanel{
	
	private JScrollPane competitionScroll;
	private JScrollPane allCompetitionScroll;
	private JScrollPane competitionPlayersScroll;
	private JTextArea competitionField;
	private JTextArea allCompetitionField;
	private JTextArea competitionPlayersField;
	private JButton backButton;
	private JButton inviteButton;
	private JButton joinButton;
	private JLabel username;
	
	public InviteView(){
		
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
