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
	
	private JLabel subscribedCompetitionsLabel;
	private JLabel playersInCompetitonLabel;
	private JScrollPane scrollPane;
	private JList<CompetitionModel> competitions;
	private JScrollPane scrollPane_1;
	private JList<AccountModel> playerList;
	private JButton backButton;
	private JButton challengePlayerButton;
	
	public CompetitionView(){
		setLayout(new MigLayout("", "[100px:117.00px:120px][200px:260.00px:220px]", "[20px:20px:20px][200px:200px:200px][25px:25px:25px]"));
		
		subscribedCompetitionsLabel = new JLabel("Ingeschreven competities");
		add(subscribedCompetitionsLabel, "cell 0 0,alignx left");
		
		playersInCompetitonLabel = new JLabel("Spelers in de competitie");
		add(playersInCompetitonLabel, "cell 1 0,alignx right");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1,grow");
		
		competitions = new JList<CompetitionModel>();
		scrollPane.setViewportView(competitions);
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 1 1,grow");
		
		playerList = new JList<AccountModel>();
		scrollPane_1.setViewportView(playerList);
		
		backButton = new JButton("Terug");
		add(backButton, "cell 0 2,growx");
		
		challengePlayerButton = new JButton("Speler uitdagen");
		challengePlayerButton.setEnabled(false);
		add(challengePlayerButton, "cell 1 2,alignx right");
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public void addBackListener(ActionListener listener){
		backButton.addActionListener(listener);
	}

	public void fillCompitions(CompetitionModel[] comp_ids) {
		competitions.setListData(comp_ids);
	}

	public void fillPlayerList(AccountModel[] usersFromCompetition) {
		playerList.setListData(usersFromCompetition);
		challengePlayerButton.setEnabled(true);
	}
}
