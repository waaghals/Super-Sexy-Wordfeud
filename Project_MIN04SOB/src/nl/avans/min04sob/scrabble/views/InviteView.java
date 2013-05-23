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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setLayout(new MigLayout("", "[100px:120px:120px,grow][100px:142.00px:100px,grow][100px:100px:100px]", "[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));
		
		JLabel competitionLabel = new JLabel("xxxxx"); //aanpassen naar variabelle
		add(competitionLabel, "cell 0 0");
		
		JLabel spelersLabel = new JLabel("xxxxx"); //aanpassen naar variabelle
		add(spelersLabel, "cell 1 0");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");
		
		JList competitionList = new JList();
		scrollPane.setViewportView(competitionList);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		add(scrollPane_2, "cell 1 1 2 3,grow");
		
		JList playerList = new JList();
		scrollPane_2.setViewportView(playerList);
		
		JButton btnBack = new JButton("Annuleer");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(btnBack, "cell 0 4,alignx left,aligny top");
		
		JButton actieButton = new JButton("xxxxx"); //aanpassen naar variabelle
		actieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(actieButton, "cell 2 4,growx,aligny top");
		
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
