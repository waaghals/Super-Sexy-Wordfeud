package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import javax.swing.JList;

public class InviteView extends CorePanel{
	/*
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
	*/
	public InviteView(){
		setLayout(new MigLayout("", "[100px:100px:100px,grow][100px:142.00px:100px,grow][100px:100px:100px]", "[100px:100px:25px,grow][100px:100px:100px,grow][100px:150px:100px,grow][100px:100px:25px]"));
		
		JLabel lblUsername = new JLabel("Logged in as:");
		add(lblUsername, "cell 0 0,alignx left,aligny top");
		
		JButton btnBack = new JButton("Back");
		add(btnBack, "cell 2 0,alignx right,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "cell 1 1 2 2,grow");
		
		JList list_2 = new JList();
		scrollPane_2.setViewportView(list_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 0 2 1 2,grow");
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		JButton btnInvite = new JButton("Invite");
		add(btnInvite, "cell 1 3,growx,aligny top");
		
		JButton btnJoin = new JButton("Join");
		add(btnJoin, "cell 2 3,growx,aligny top");
		
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
