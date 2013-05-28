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
	
	private JLabel IngeschrevenCompetitiesLabel;
	private JLabel SpelersInDeCompetitieLabel;
	private JScrollPane scrollPane;
	private JList<CompetitionModel> competitions;
	private JScrollPane scrollPane_1;
	private JList<AccountModel> playerList;
	private JButton terugButton;
	private JButton btnSpelerUitdagen;
	
	public CompetitionView(){
		setLayout(new MigLayout("", "[100px:75.00px:120px,grow][100px:115.00px:100px,grow][100px:117.00px:100px]", "[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));
		
		IngeschrevenCompetitiesLabel = new JLabel("Ingeschreven competities");
		add(IngeschrevenCompetitiesLabel, "cell 0 0");
		
		SpelersInDeCompetitieLabel = new JLabel("Spelers in de competitie");
		add(SpelersInDeCompetitieLabel, "cell 1 0 2 1,alignx center");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");
		
		competitions = new JList<CompetitionModel>();
		scrollPane.setViewportView(competitions);
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 1 1 2 3,grow");
		
		playerList = new JList<AccountModel>();
		scrollPane_1.setViewportView(playerList);
		
		terugButton = new JButton("Terug");
		add(terugButton, "cell 0 4,growx");
		
		btnSpelerUitdagen = new JButton("Speler Uitdagen");
		btnSpelerUitdagen.setEnabled(false);
		add(btnSpelerUitdagen, "cell 2 4");
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	public void fillCompitions(CompetitionModel[] comp_ids) {
		competitions.setListData(comp_ids);
	}

	public void fillPlayerList(AccountModel[] usersFromCompetition) {
		playerList.setListData(usersFromCompetition);
		btnSpelerUitdagen.setEnabled(true);
	}
}
