package nl.avans.min04sob.scrabble.views;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import nl.avans.min04sob.scrabble.models.AccountModel;
import nl.avans.min04sob.scrabble.models.CompetitionModel;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CompetitionView extends CorePanel{
	
	private JLabel competitionsLabel;
	private JLabel playersInCompetitonLabel;
	private JScrollPane scrollPane;
	private JList<String> competitionsList;
	private JScrollPane scrollPane_1;
	private JList<String> playerList;
	private JButton backButton;
	private JButton actionButton;
	
	public CompetitionView(){
		
		setLayout(new MigLayout("", "[200px:220px:260px][155px:160.00px:170px]", "[20px:20px:20px][200px:200px:200px][25px:25px:25px]"));
		
		competitionsLabel = new JLabel("Ingeschreven competities");
		add(competitionsLabel, "cell 0 0,alignx left");
		
		playersInCompetitonLabel = new JLabel("Spelers in de competitie");
		add(playersInCompetitonLabel, "cell 1 0,alignx right");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");
		
		competitionsList = new JList<String>();
		scrollPane.setViewportView(competitionsList);
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 1 1,grow");
		
		playerList = new JList<String>();
		scrollPane_1.setViewportView(playerList);
		
		backButton = new JButton("Terug");
		add(backButton, "cell 0 2,alignx left");
		
		actionButton = new JButton("Speler uitdagen");
		actionButton.setEnabled(false);
		add(actionButton, "cell 1 2,alignx right");
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public void setText(String labelText,String labelText2, String button, boolean visible) {
		playersInCompetitonLabel.setText(labelText2);
		competitionsLabel.setText(labelText);
		actionButton.setText(button);
		actionButton.setVisible(visible);
		revalidate();
	}
	
	public void addBackListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
	
	public void addActionButtonListener(ActionListener listener){
		actionButton.addActionListener(listener);
	}

	public void fillCompetitions(String[] comp_desc) {
		competitionsList.setListData(comp_desc);
	}

	public void fillPlayerList(String[] usersFromCompetition) {
		playerList.setListData(usersFromCompetition);
		actionButton.setEnabled(true);
	}
	
	public void fillAvailableCompetitions(String[] availableCompetitions) {
		competitionsList.setListData(availableCompetitions);
		actionButton.setEnabled(true);
	}

	public void fillAllCompetitions(String[] allCompetitions) {
		competitionsList.setListData(allCompetitions);
	}
	
	public String getSelectedPlayer(){
		String s = playerList.getSelectedValue();
		return s;
	}
}
