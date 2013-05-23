package nl.avans.min04sob.scrabble.views;

import java.beans.PropertyChangeEvent;

import net.miginfocom.swing.MigLayout;
import nl.avans.min04sob.scrabble.core.CorePanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CompetitionView extends CorePanel{
	
	private JLabel IngeschrevenCompetitiesLabel;
	private JLabel SpelersInDeCompetitieLabel;
	private JScrollPane scrollPane;
	private JList competitions;
	private JScrollPane scrollPane_1;
	private JList playerList;
	private JButton terugButton;
	
	public CompetitionView(){
		setLayout(new MigLayout("", "[100px:75.00px:120px,grow][100px:115.00px:100px,grow][100px:117.00px:100px]", "[][100px:100px:100px,grow][][100px:150px:100px,grow][100px:100px:25px]"));
		
		IngeschrevenCompetitiesLabel = new JLabel("Ingeschreven competities");
		add(IngeschrevenCompetitiesLabel, "cell 0 0");
		
		SpelersInDeCompetitieLabel = new JLabel("Spelers in de competitie");
		add(SpelersInDeCompetitieLabel, "cell 1 0 2 1,alignx center");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 1 1 3,grow");
		
		competitions = new JList();
		scrollPane.setViewportView(competitions);
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 1 1 2 3,grow");
		
		playerList = new JList();
		scrollPane_1.setViewportView(playerList);
		
		terugButton = new JButton("Terug");
		add(terugButton, "cell 0 4,growx");
	}

	@Override
	public void modelPropertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
